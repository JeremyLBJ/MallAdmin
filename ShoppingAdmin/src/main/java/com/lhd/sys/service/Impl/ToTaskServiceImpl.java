package com.lhd.sys.service.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.DiagramLayout;
import org.activiti.engine.repository.DiagramNode;
import org.activiti.engine.runtime.Execution;
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
import com.lhd.sys.service.ToTaskService;
import com.lhd.sys.vo.WolkFlowVO;
import com.lhd.sys.vo.act.ActCommentEntity;
import com.lhd.sys.vo.act.ActTaskEntity;

@Service("ToTaskService")
public class ToTaskServiceImpl implements ToTaskService {
	
	@Autowired
	private SysLeaveBillService sysLeaveBillService ;
	
	
	private ProcessEngineConfiguration configuration = ActivitiConfig.activitiConfigStatic() ;

	@Override
	public DataGridView toTask(WolkFlowVO vo) {
		TaskService taskService = this.configuration.getTaskService() ;
		//获取当前办理人的信息
		SysUser user = (SysUser) WebUntils.getSession().getAttribute("user") ;
		String assignee = user.getName() ;
		//获取总条数
		long count = taskService.createTaskQuery().taskAssignee(assignee).count() ;
		//查询集合
		int firstResult = (vo.getPage() - 1) * vo.getLimit() ;
		int maxResults = vo.getLimit() ;
		List<Task> list = taskService.createTaskQuery().taskAssignee(assignee).listPage(firstResult, maxResults) ;
		List<ActTaskEntity> taskEntities = new ArrayList<>() ;
		for (Task t : list) {
			ActTaskEntity entity = new ActTaskEntity() ;
			BeanUtils.copyProperties(t, entity) ;
			taskEntities.add(entity) ;
		}

		return new DataGridView ( count , taskEntities ) ;
	}
	
	
	/**
	 * 根据当前的任务ID 查询请假单信息
	 */
	public SysLeavebill taskProcess ( String taskId ) {
		TaskService taskService = this.configuration.getTaskService() ;
		//根据任务ID查询任务实例
		Task singleResult = taskService.createTaskQuery().taskId(taskId).singleResult() ;
		String instanceId = singleResult.getProcessInstanceId() ;
		//根据流程实例ID查询流程实例
		RuntimeService runtimeService = this.configuration.getRuntimeService() ;
		ProcessInstance result = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult() ;
		String businessKey = result.getBusinessKey() ;
		String leaveBillId = businessKey.split(":")[1] ;
		//根据ID查询请假单信息
		return this.sysLeaveBillService.findByIdLeaveBillManager( Integer.valueOf(leaveBillId) ) ;
	}


	@Override
	public List<String> find(String taskId) {
		
		List<String> names = new ArrayList<>() ;
		//根据任务ID 查询任务实例
		TaskService taskService = this.configuration.getTaskService() ;
		Task singleResult = taskService.createTaskQuery().taskId(taskId).singleResult() ;
		//执行实例ID
		String executionId = singleResult.getExecutionId() ;
		//流程定义ID
		String processDefinitionId = singleResult.getProcessDefinitionId() ;
		//根据流程实例ID 查询流程实例对象
		RuntimeService runtimeService = this.configuration.getRuntimeService() ;
		//根据执行实例ID 查询执行实例对象
	    Execution result = runtimeService.createExecutionQuery().executionId(executionId).singleResult() ;
	    
		RepositoryService repositoryService = this.configuration.getRepositoryService() ;

		//从执行实例对象中获取 活动ID
		String activityId = result.getActivityId() ;
		
		//根据流程定义ID获取bpmn信息
		BpmnModel model = repositoryService.getBpmnModel(processDefinitionId) ;
		if(model != null) {
			//获取所有FlowElements的节点
		    Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements() ;
		    //循环所有的FlowElements节点
		    for(FlowElement e : flowElements) {
		    	//获取任务节点信息
		    	if (e instanceof UserTask ) {
		    		UserTask  userTask =  (UserTask) e ;
		    		if ( null != userTask ) {
		    			List<SequenceFlow> outgoingFlows = userTask.getOutgoingFlows() ;
		    			for (SequenceFlow o : outgoingFlows) {
		    				if ( activityId.equals(o.getSourceRef())  ) {
		    					names.add(o.getName()) ;
		    				}
						}
		    		}
		    	}
		    }
		}
		return names ;
	}


	/**
	 * 根据任务信息查询批注信息
	 */
	@Override
	public DataGridView queryTaskComment(String taskId) {
		TaskService taskService = this.configuration.getTaskService() ;
		//根据任务ID查询任务实例
		Task singleResult = taskService.createTaskQuery().taskId(taskId).singleResult() ;
		//流程实例ID
		String instanceId = singleResult.getProcessInstanceId() ;
		//获取批注信息
		List<Comment> comments = taskService.getProcessInstanceComments(instanceId) ;
		List<ActCommentEntity> entities = new ArrayList<>() ;
		if ( null != comments && comments.size() > 0 ) {
			for (Comment c : comments) {
				ActCommentEntity commentEntity = new ActCommentEntity() ;
				BeanUtils.copyProperties(c, commentEntity) ;
				entities.add(commentEntity) ;
			}
		}
		return new DataGridView (Long.valueOf(entities.size()), entities) ;
		
	}


	/**
	 * 完成任务
	 */
	@Override
	public void doTask(WolkFlowVO vo) {
		TaskService taskService = this.configuration.getTaskService() ;
		RuntimeService runtimeService = this.configuration.getRuntimeService() ;
		//任务ID
		String taskId = vo.getTaskId() ;
		//批注信息
		String comment = vo.getComment() ;
		//连线名称
		String outcome = vo.getOutcome() ;
		//请假单ID
		Integer leaveBillId = vo.getId() ;
		// 1.根据任务ID查询任务实例
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult() ;
		// 2.从任务里面取出流程实例ID
		String processInstanceId = task.getProcessInstanceId() ;
		// 3.设置批注人
		SysUser user = (SysUser) WebUntils.getSession().getAttribute("user") ;
		String name = user.getName() ;
		Authentication.setAuthenticatedUserId(name) ;
		// 4.添加批注信息
		taskService.addComment(taskId, processInstanceId,  "[" + outcome + "]" + comment) ;
		// 5.完成任务
		Map<String, Object> variables = new HashMap<String, Object>() ;
		variables.put("outcome", outcome);
		taskService.complete(taskId, variables ) ;
		// 6.判断任务是否结束
		ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult() ;
		if ( null == instance ) {
			SysLeavebill sysLeaveBill = new SysLeavebill() ;
			sysLeaveBill.setId(leaveBillId) ;
			// 流程是否结束
			if ( outcome.equals("放弃") ) {
				sysLeaveBill.setState(Constast.STATE_LEVEBILL_THREE) ; //已放弃
			} else {
				sysLeaveBill.setState(Constast.STATE_LEVEBILL_TOW) ; //审批完成
			}
			this.sysLeaveBillService.updateLeave(sysLeaveBill) ; //更新数据库
		}
	}


	@Override
	public Map<String, Object> queryTaskCoordinatesByTaskId(String taskId) {
		Map<String, Object> map = new HashMap<>() ;
		//根据任务ID 查询任务实例
		TaskService taskService = this.configuration.getTaskService() ;
		Task singleResult = taskService.createTaskQuery().taskId(taskId).singleResult() ;
		//执行实例ID
		String executionId = singleResult.getExecutionId() ;
		//流程定义ID
		String processDefinitionId = singleResult.getProcessDefinitionId() ;
		//根据流程实例ID 查询流程实例对象
		RuntimeService runtimeService = this.configuration.getRuntimeService() ;
		//根据执行实例ID 查询执行实例对象
	    Execution result = runtimeService.createExecutionQuery().executionId(executionId).singleResult() ;
	    
		RepositoryService repositoryService = this.configuration.getRepositoryService() ;

		//从执行实例对象中获取 活动ID
		String activityId = result.getActivityId() ;
		

		//流程定义id 获取所有信息
		DiagramLayout processDiagramLayout = repositoryService.getProcessDiagramLayout(processDefinitionId) ;
		 
		 List<DiagramNode> nodes = processDiagramLayout.getNodes() ;
		 for (DiagramNode d : nodes) {
			if ( activityId .equals( d.getId() ) && activityId.equals("usertask1")) {
					map.put("x", (d.getX()+31)) ;
					map.put("y", (d.getY()+14)) ;
					map.put("height", (d.getHeight()+8)) ;
					map.put("width", (d.getWidth()+15)) ;
			 } else if ( activityId .equals( d.getId() ) && activityId.equals("usertask2") ) {
				 	map.put("x", (d.getX()+29)) ;
					map.put("y", (d.getY()+32)) ;
					map.put("height", (d.getHeight()+11)) ;
					map.put("width", (d.getWidth()+20)) ;
			 } else if ( activityId .equals( d.getId() ) && activityId.equals("usertask3") ) {
				 	map.put("x", (d.getX()+29)) ;
					map.put("y", (d.getY()+54)) ;
					map.put("height", (d.getHeight()+8)) ;
					map.put("width", (d.getWidth()+20)) ;
				 
			 }
		}
		return map ;
	}
}
