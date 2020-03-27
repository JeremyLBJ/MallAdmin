package com.lhd.sys.service.Impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhd.sys.Config.ActivitiConfig;
import com.lhd.sys.common.Constast;
import com.lhd.sys.common.DataGridView;
import com.lhd.sys.common.WebUntils;
import com.lhd.sys.entity.SysLeavebill;
import com.lhd.sys.entity.SysUser;
import com.lhd.sys.service.SysLeaveBillService;
import com.lhd.sys.service.WolkFlowService;
import com.lhd.sys.vo.WolkFlowVO;
import com.lhd.sys.vo.act.ActCommentEntity;
import com.lhd.sys.vo.act.ActDeployMent;
import com.lhd.sys.vo.act.ActsProcessDefinitionEntity;

@Service("WolkFlowService")
public class WolkFlowServiceImpl implements WolkFlowService {
	
	
	@Autowired
	private SysLeaveBillService sysLeaveBillService ;
	
	private ProcessEngineConfiguration configuration = ActivitiConfig.activitiConfigStatic() ;
	
	
	public DataGridView queryProcessDeploy ( WolkFlowVO vo ) {
		RepositoryService repositoryService = this.configuration.getRepositoryService() ;
		if ( null == vo.getDeploymentName() ) {
			vo.setDeploymentName("") ;
		}
		String nameLike = vo.getDeploymentName() ;
		//查询总条数
		long count = repositoryService.createDeploymentQuery()
									  .deploymentNameLike("%"+nameLike+"%")
									  .count() ;
		//分页查询条件
		int firstResult = (vo.getPage() - 1) * vo.getLimit() ;
		int maxResults = vo.getLimit() ;
		//查询 需要手动添加 % 查询时不会自动添加
		List<Deployment> listPage = repositoryService.createDeploymentQuery()
													 .deploymentNameLike("%"+nameLike+"%")
													 .listPage(firstResult, maxResults) ;
		List<ActDeployMent> data = new ArrayList<>() ; 
		for (Deployment d : listPage) {
			ActDeployMent act = new ActDeployMent() ;
			//spring自带的工具 
			BeanUtils.copyProperties(d, act) ;
			data.add(act) ;
		}
		return new DataGridView(count, data) ;
		
	}


	@Override
	public DataGridView queryAllProcessDefinition(WolkFlowVO vo) {
		RepositoryService repositoryService = this.configuration.getRepositoryService() ;
		if ( null == vo.getDeploymentName() ) {
			vo.setDeploymentName("") ;
		}
		//部署名称
		String nameLike = vo.getDeploymentName() ;
		//根据部署名 查出所有的部署ID
		List<Deployment> list = repositoryService.createDeploymentQuery()
												 .deploymentNameLike("%"+nameLike+"%")
												 .list() ;
		//存放部署ID的容器
		Set<String> deploymentIds = new HashSet<>() ;
		for (Deployment d : list) {
			deploymentIds.add(d.getId()) ;
		}
		//查询总条数
		long count = repositoryService.createProcessDefinitionQuery()
									  .deploymentIds(deploymentIds)
									  .count() ;
		//分页所需要的条件
		int firstResult = (vo.getPage() - 1) * vo.getLimit() ;
		int maxResults = vo.getLimit() ;
		//查询数据存放的容器
		List<ProcessDefinition> listPage = repositoryService
											.createProcessDefinitionQuery()
											.deploymentIds(deploymentIds)
											.listPage(firstResult, maxResults) ;
		//存放新数据容器
		List<ActsProcessDefinitionEntity> data = new ArrayList<>() ;
		for (ProcessDefinition pd : listPage) {
			ActsProcessDefinitionEntity entity = new ActsProcessDefinitionEntity() ;
			BeanUtils.copyProperties(pd, entity) ;
			data.add(entity) ;
		}
		return new DataGridView(count, data) ;
	}


	@Override
	public void addWorkFlow(InputStream inputStream, String deploymentName) {
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		ZipInputStream zipInputStream = new ZipInputStream(inputStream) ;
	    repositoryService.createDeployment().name(deploymentName)
		                                    .addZipInputStream(zipInputStream)
		                                    .deploy() ;
	    try {
			zipInputStream.close();
			inputStream.close();
		} catch (Exception e) {
			throw new RuntimeException("流程部署資源流關閉失敗") ;
		}
	}


	/**
	 * 普通删除,如果当前有人在使用就不会删除
	 */
	@Override
	public void removeProcessDeployment(String deploymentId) {
		RepositoryService repositoryService = this.configuration.getRepositoryService() ;
		repositoryService.deleteDeployment(deploymentId) ;
		
	}


	
	/**
	 * 流程图
	 */
	@Override
	public InputStream queryProcessDeploymentImg(String deploymentId) {
		RepositoryService repositoryService = this.configuration.getRepositoryService() ;
		//根据流程部署ID查询对象
		ProcessDefinition result = repositoryService
											.createProcessDefinitionQuery()
											.deploymentId(deploymentId)
											.singleResult() ;
		//从流程定义对象中得到图片名称
		String resourceName = result.getDiagramResourceName() ;
		//使用部署ID和图片名称去查图片流 
		InputStream inputStream = repositoryService.getResourceAsStream(deploymentId, resourceName) ;
		
		return inputStream ;
	}


	/**
	 * 启动流程
	 */
	@Override
	public void startProcess(Integer leaveBill) {
		RuntimeService runtimeService = this.configuration.getRuntimeService() ;
		//原本根据对应的流程实体类的类名获取对应的流程key 此处部署名与类名不一致 所以这样写
		String processDefinitionKey = "LeaveBill" ;
		String businessKey = processDefinitionKey+":"+leaveBill ;
		//设置流程变量
		//查询当前用户的主管
		SysUser user = (SysUser) WebUntils.getSession().getAttribute("user") ;
//		SysUser findByMgr = sysUserService.findByMgr(user.getMgr()) ;
		Map<String, Object> variables = new HashMap<>() ;
		variables.put("username", user.getName()) ;
		runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables) ;
		//设置sys_leaveBill 的状态
		SysLeavebill sysLeavebill = sysLeaveBillService.findById(leaveBill) ;
		sysLeavebill.setState(Constast.STATE_LEVEBILL_ONE) ; //设置状态为审批中
		this.sysLeaveBillService.updateLeave(sysLeavebill) ;
	}


	/**
	 * 根据任务id 查询部署ID
	 * @return 
	 */
	@Override
	public ProcessDefinition processDeploymentImgByTaskId(String taskId) {
		TaskService taskService = this.configuration.getTaskService() ;
		RuntimeService runtimeService = this.configuration.getRuntimeService() ;
		RepositoryService repositoryService = this.configuration.getRepositoryService() ;
		//获取任务实例对象
		Task task = taskService.createTaskQuery().taskId(taskId) .singleResult() ;
		//取出流程实例ID
		String processInstanceId = task.getProcessInstanceId() ;
		//根据流程实例ID 查询
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult() ;
		//流程定义ID
		String processDefinitionId = processInstance.getProcessDefinitionId() ;
		//根据流程定义ID 查询定义对象
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult() ;
		return processDefinition ;
	}


	@Override
	public DataGridView queryCommentByLeaveBillId(Integer id) {
		//拼装 LeaveBill:8 business_key
		String businessKey = "LeaveBill"+":"+id ;
		HistoryService historyService = this.configuration.getHistoryService() ;
		TaskService taskService = this.configuration.getTaskService() ;
		HistoricProcessInstance singleResult = historyService.createHistoricProcessInstanceQuery()
															.processInstanceBusinessKey(businessKey)
															.singleResult() ;
		List<Comment> comments = taskService.getProcessInstanceComments(singleResult.getId()) ;
		List<ActCommentEntity> act = new ArrayList<>() ;
		for (Comment c : comments) {
			ActCommentEntity entity = new ActCommentEntity() ;
			BeanUtils.copyProperties(c, entity) ;
			act.add(entity) ;
					
		}
		return new DataGridView(Long.valueOf(act.size()), act) ;
		
	}
	
}
