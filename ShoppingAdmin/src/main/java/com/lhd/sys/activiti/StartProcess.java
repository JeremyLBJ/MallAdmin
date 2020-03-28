/*package com.lhd.sys.activiti;

import java.util.List;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import com.lhd.sys.Config.ActivitiConfig;

*//**
 * 启动流程
 * @author ASUS
 *
 *//*
public class StartProcess {
	
	private ProcessEngineConfiguration configuration = ActivitiConfig.activitiConfigStatic() ;
	
	@Test
	public void startProce () {
		RuntimeService runtimeService = this.configuration.getRuntimeService() ;
		String processDefinitionKey = "myProcess" ;
		ProcessInstance instance = runtimeService.startProcessInstanceByKey(processDefinitionKey ) ;
		System.out.println("启动成功::::"+instance.getId()) ;
	}
	
	//查询任务
	@Test
	public void findExcution () {
		RuntimeService runtimeService = this.configuration.getRuntimeService() ;
		List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list() ;
		if ( null != list && list.size() > 0 ) {
			for (ProcessInstance p : list) {
				System.out.println("业务Key::::"+p.getBusinessKey()) ;
				System.out.println("部署ID:::::"+p.getDeploymentId()) ;
				System.out.println("流程ID:::"+p.getId()) ;
				System.out.println("流程名::::"+p.getName()) ;
				System.out.println("流程实例ID::::"+p.getProcessInstanceId()) ;
			}
		}
	}
	
	//办理任务
	@Test
	public void Task () {
		TaskService taskService = this.configuration.getTaskService() ;
		String taskId = "5005" ;
		taskService.complete(taskId ) ;
		System.out.println("任务办理成功");
	}

}
*/