/*package com.lhd.sys.activiti;
*//**
 * 接收任务
 * 
 * @author ASUS
 *
 *//*

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import com.lhd.sys.Config.ActivitiConfig;

public class ReceiveTask {
	
	private ProcessEngineConfiguration configuration = ActivitiConfig.activitiConfigStatic() ;
	
	*//**
	 * 流程部署
	 * 
	 *//*
	@Test
	public void Process () {
		InputStream inputStream = configuration.getClass().getResourceAsStream("/MyProcess.zip") ;
		ZipInputStream zipInputStream = new ZipInputStream(inputStream) ;
		RepositoryService repositoryService = configuration.getRepositoryService() ;
	    Deployment deploy = repositoryService.createDeployment().name("接收任务流程测试")
				.addZipInputStream(zipInputStream) 
				.deploy() ;
	   
		System.out.println(inputStream) ;
		System.out.println(deploy);
		
	}
	
	*//**
	 * 启动流程实例
	 * 
	 *//*
	@Test
	public void startProcess () {
		RuntimeService runtimeService = this.configuration.getRuntimeService() ;
		String processDefinitionKey = "myProcess" ;
		ProcessInstance instance = runtimeService.startProcessInstanceByKey(processDefinitionKey ) ;
		System.out.println("流程实例ID:::"+instance.getId()) ;
		System.out.println("流程部署ID::::"+instance.getDeploymentId()) ;
		System.out.println("流程执行ID::::"+instance.getProcessInstanceId()) ;
	}
	
	@Test
	public void executionTask () {
		RuntimeService runtimeService = this.configuration.getRuntimeService() ;
		String activityId = "receivetask1" ;
		String processInstanceId = "197501" ;
		Execution singleResult = runtimeService.createExecutionQuery()
					.activityId(activityId )
					.processInstanceId(processInstanceId)
					.singleResult() ;
		System.out.println(singleResult.getActivityId()) ;
		System.out.println(singleResult.getId()) ;
		int  value = 10000 ;
		*//** 使用流程变量设置当日销售额，用来传递业务参数 *//*
		runtimeService.setVariable(singleResult.getId(), "当前的销售额", value) ;
		*//** 向后执行一步，如果流程处于等待状态，使得流程继续执行 *//*
		runtimeService.trigger(singleResult.getId()) ;
	}
	
	*//**
	 * 发送短信
	 * 
	 *//*
	@Test
	public void sendMessage () {
		RuntimeService runtimeService = this.configuration.getRuntimeService() ;
		String executionId = "197502" ;
		String variableName = "当前的销售额" ;
		int variable = (int) runtimeService.getVariable(executionId, variableName) ;
		*//** 向后执行一步，如果流程处于等待状态，使得流程继续执行 *//*
		runtimeService.trigger(executionId) ;
		System.out.println(variable);
	}

}
*/