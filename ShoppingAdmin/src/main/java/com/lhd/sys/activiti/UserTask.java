package com.lhd.sys.activiti;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import com.lhd.sys.Config.ActivitiConfig;

/**
 * 用户任务  设置流程变量
 * 
 * @author ASUS
 *
 */
public class UserTask {
	
	private ProcessEngineConfiguration configuration = ActivitiConfig.activitiConfigStatic() ;
	
	/**
	 * 部署任务
	 * 
	 */
	@Test
	public void Process () {
		InputStream inputStream = configuration.getClass().getResourceAsStream("/activiti.zip") ;
		ZipInputStream zipInputStream = new ZipInputStream(inputStream) ;
		RepositoryService repositoryService = configuration.getRepositoryService() ;
	    Deployment deploy = repositoryService.createDeployment().name("用户接收任务流程测试")
				.addZipInputStream(zipInputStream) 
				.deploy() ;
	   
		System.out.println(inputStream) ;
		System.out.println(deploy);
	}
	
	/**
	 * 启动任务
	 * 
	 */
	@Test
	public void startTask () {
		RuntimeService runtimeService = this.configuration.getRuntimeService() ;
		String processDefinitionKey = "activiti" ;
		Map<String, Object> variables = new HashMap<String, Object>() ;
		variables.put("username", "张三") ;
		ProcessInstance instance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables) ;
		System.out.println(instance.getId()) ;
		runtimeService.trigger(processDefinitionKey);
	}
	
	
	
	
	/**
	 *查看任务 
	 * 
	 */
	@Test
	public void findTask () {
		TaskService taskService = configuration.getTaskService() ;
		String assignee = "王五" ;
		 List<Task> list = taskService.createTaskQuery()
				   .taskAssignee(assignee)
				   .list() ;
		 if ( null != list && list.size() > 0 ) {
			 for (Task t : list) {
				System.out.println("办理人:::::"+t.getAssignee()) ;
				System.out.println("流程执行ID::::"+t.getExecutionId()) ;
				System.out.println("任务ID:::::"+t.getId()) ;
				System.out.println("执行实例ID:::::"+t.getProcessInstanceId()) ;
				System.out.println("流程定义ID:::::"+t.getProcessDefinitionId()) ;
			}
		 }
	}
	
	
	
	/**
	 * 办理任务
	 * 
	 */
	@Test
	public void task () {
		TaskService taskService = this.configuration.getTaskService() ;
		String taskId = "212502" ;
		Map<String, Object> variables = new HashMap<String, Object>() ;
		variables.put("username", "王五") ;
		taskService.complete(taskId, variables) ;
		System.out.println("办理成功");
	}

}
