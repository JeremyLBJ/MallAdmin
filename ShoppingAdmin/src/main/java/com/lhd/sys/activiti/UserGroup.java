package com.lhd.sys.activiti;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lhd.sys.Config.ActivitiConfig;

public class UserGroup {
	
	@Autowired
	private ProcessEngine engine ;
	
	
	private ProcessEngineConfiguration configuration = ActivitiConfig.activitiConfigStatic() ;
	
	/**
	 * 部署任务
	 */
	@Test
	public void Process () {
		InputStream inputStream = configuration.getClass().getResourceAsStream("/UserGroup.zip") ;
		ZipInputStream zipInputStream = new ZipInputStream(inputStream) ;
		RepositoryService repositoryService = configuration.getRepositoryService() ;
	    Deployment deploy = repositoryService.createDeployment().name("组接收任务流程测试")
				.addZipInputStream(zipInputStream) 
				.deploy() ;
	    
		System.out.println(inputStream) ;
		System.out.println(deploy.getId());
	}
	
	
	public void saag () {
		InputStream resourceAsStream = engine.getClass().getResourceAsStream("") ;
		RepositoryService repositoryService = this.engine.getRepositoryService() ;
		ZipInputStream zipInputStream = new ZipInputStream(resourceAsStream) ;
		repositoryService.createDeployment().name("")
			.addZipInputStream(zipInputStream)
			.deploy() ;
	}
	
	/**
	 * 启动任务
	 * 
	 */
	@Test
	public void startTask () {
		RuntimeService runtimeService = this.configuration.getRuntimeService() ;
		String processDefinitionKey = "userGroup" ;
		ProcessInstance instance = runtimeService.startProcessInstanceByKey(processDefinitionKey) ;
		System.out.println(instance.getId()) ;
	}
	
	
	/**
	 * 查看组任务
	 * 
	 */
	@Test
	public void group () {
		TaskService taskService = this.configuration.getTaskService() ;
		String candidateUser = "张三" ;
		List<Task> list = taskService.createTaskQuery().taskCandidateUser(candidateUser).list() ;
		if ( null != list && list.size() > 0 ) {
			for (Task t : list) {
				System.out.println("办理人::::"+t.getAssignee()) ;
				System.out.println("组任务ID::::"+t.getId()) ;
				System.out.println("流程实例ID:::::"+t.getProcessInstanceId()) ;
				System.out.println("任务key::::"+t.getTaskDefinitionKey()) ;
				System.out.println("==============================================");
			}
		}
	}
	
	/**
	 * 查询个人任务
	 */
	@Test
	public void userTask () {
		TaskService taskService = this.configuration.getTaskService() ;
		String assignee = "张三" ;
		List<Task> list = taskService.createTaskQuery().taskAssignee(assignee).list() ;
		if ( null != list && list.size() > 0 ) {
			for (Task t : list) {
				System.out.println("办理人:::::"+t.getAssignee()) ;
				System.out.println("流程执行ID::::"+t.getExecutionId()) ;
				System.out.println("任务ID:::::"+t.getId()) ;
				System.out.println("执行实例ID:::::"+t.getProcessInstanceId()) ;
				System.out.println("流程定义ID:::::"+t.getProcessDefinitionId()) ;
				System.out.println("=======================================");
			}
		}
	}
	
	/**
	 * 
	 * 指定任务办理人
	 */
	@Test
	public void assiger () {
		TaskService taskService = this.configuration.getTaskService() ;
		String taskId = "220005" ;
		String userId = "张三" ;
		taskService.claim(taskId, userId ) ;
	}
	
	/**
	 * 撤销任务办理人
	 */
	@Test
	public void taskRollerback () {
		TaskService taskService = this.configuration.getTaskService() ;
		String taskId = "220005" ;
		taskService.claim(taskId, null) ;
	}
	
	/**
	 * 查询组任务成员列表
	 * 
	 */
	@Test
	public void groupList () {
		String taskId = "220005" ;
		 List<IdentityLink> list = this.configuration.getTaskService().getIdentityLinksForTask(taskId ) ;
		 if ( null != list && list.size() > 0 ) {
			 for (IdentityLink i : list) {
				 System.out.println(i.getUserId()) ;
				System.out.println("组ID::::"+i.getGroupId()) ;
				System.out.println("流程实例ID:::::"+i.getProcessInstanceId()) ;
				System.out.println("任务ID:::::"+i.getTaskId()) ;
				System.out.println("类型:::::"+i.getType()) ;
				System.out.println("==========================================");
			}
		 }
		
	}
	
	
	/**
	 * 
	 * ${usernames} 部署流程
	 * 
	 */
	@Test
	public void ProcessGroup () {
		InputStream inputStream = configuration.getClass().getResourceAsStream("/UserGroup.zip") ;
		ZipInputStream zipInputStream = new ZipInputStream(inputStream) ;
		RepositoryService repositoryService = configuration.getRepositoryService() ;
	    Deployment deploy = repositoryService.createDeployment().name("流程变量接收任务流程测试")
				.addZipInputStream(zipInputStream) 
				.deploy() ;
	   
		System.out.println(inputStream) ;
		System.out.println(deploy.getId());
	}
	
	/**
	 * 使用流程变量启动任务
	 */
	@Test
	public void startTasks () {
		RuntimeService runtimeService = this.configuration.getRuntimeService() ;
		String processDefinitionKey = "userGroup" ;
		Map<String, Object> variables = new HashMap<>() ;
		variables.put("usernames", "张三,李四,王五,赵柳") ;
		runtimeService.startProcessInstanceByKey(processDefinitionKey, variables) ;
		System.out.println("任务启动成功");
	}

}
