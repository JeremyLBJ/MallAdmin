package com.lhd.sys.service.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.lhd.sys.Config.ActivitiConfig;
import com.lhd.sys.common.DataGridView;
import com.lhd.sys.service.WolkFlowService;
import com.lhd.sys.vo.WolkFlowVO;
import com.lhd.sys.vo.act.ActDeployMent;
import com.lhd.sys.vo.act.ActsProcessDefinitionEntity;

@Service("WolkFlowService")
public class WolkFlowServiceImpl implements WolkFlowService {
	
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

}
