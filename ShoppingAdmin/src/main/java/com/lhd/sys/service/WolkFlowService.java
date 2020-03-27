package com.lhd.sys.service;

import java.io.InputStream;

import org.activiti.engine.repository.ProcessDefinition;

import com.lhd.sys.common.DataGridView;
import com.lhd.sys.vo.WolkFlowVO;

public interface WolkFlowService {
	
	//查询流程部署信息
	DataGridView queryProcessDeploy ( WolkFlowVO vo ) ;
	
	//查询流程定义信息
	DataGridView queryAllProcessDefinition ( WolkFlowVO vo ) ;

	void addWorkFlow( InputStream inputStream, String deploymentName ) ;

	void removeProcessDeployment( String deploymentId );

	InputStream queryProcessDeploymentImg(String deploymentId);

	void startProcess( Integer leaveBill );

	//根据任务ID 查询部署ID
	ProcessDefinition processDeploymentImgByTaskId(String taskId);

	//根据请假单ID 查询批注信息
	DataGridView queryCommentByLeaveBillId(Integer id);

}
