package com.lhd.sys.activiti;
/**
 * 
 * 
 * activiti流程测试
 * 
 * @author ASUS
 *
 */

import java.util.List;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.Test;

import com.lhd.sys.Config.ActivitiConfig;

public class Activiti {
	
	private ProcessEngineConfiguration configuration = ActivitiConfig.activitiConfigStatic() ;
	
	//部署流程
	@Test
	public void deployProess () {
		
		//创建流程引擎配置
		
				/*ProcessEngineConfiguration configuration = ProcessEngineConfiguration
							.createStandaloneProcessEngineConfiguration() ;
				configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti") ;
				configuration.setJdbcDriver("com.mysql.jdbc.Driver") ;
				configuration.setJdbcUsername("root") ;
				configuration.setJdbcPassword("a") ;
				configuration.setDatabaseSchemaUpdate("true") ;
				ProcessEngine engine = configuration.buildProcessEngine() ;*/
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		Deployment deploy = repositoryService.createDeployment().name("请假流程001") 
		.addClasspathResource("activiti.bpmn")
		.addClasspathResource("activiti.png")
		.deploy() ;
		System.out.println("流程对象为:::::::::::::"+deploy) ;
		System.out.println("流程ID:::::::::::::"+deploy.getId()) ;
		System.out.println("流程时间:::::::::::::"+deploy.getDeploymentTime()) ;
		
	}
	
	/**
	 * 
	 * 启动流程
	 * 
	 */
	@Test
	public void startProcess () {
		RuntimeService runtimeService = configuration.getRuntimeService() ;
		String processDefinitionKey = "activiti" ;
		runtimeService.startProcessInstanceByKey(processDefinitionKey) ;
		System.out.println("流程启动成功");
	}
	
	
	/**
	 * 
	 * 查询任务
	 * 
	 */
	@Test
	public void findTask () {
		TaskService taskService = configuration.getTaskService() ;
		String assignee = "张九南" ;
		 List<Task> list = taskService.createTaskQuery().taskAssignee(assignee).list() ;
		 if (null != list && list.size() > 0 ) {
			 for (Task t : list) {
				 System.out.println("代理人名字::::::::"+t.getAssignee());
				 System.out.println("任务ID:::::::::::::"+t.getId());
				 System.out.println("任务名称:::::::::"+t.getName());
				 System.out.println("流程实例id:::::::::::::"+t.getProcessInstanceId());
				 System.out.println("执行实例ID:::::::::::"+t.getExecutionId());
				 System.out.println("流程定义ID::::::::::::"+t.getProcessDefinitionId());
				
			}
		 }
	}
	
	/**
	 * 办理任务
	 * 
	 */
	@Test
	public void doTask () {
		TaskService taskService = configuration.getTaskService() ;
		String taskId = "20002" ;
		taskService.complete(taskId);
		System.out.println("任务完成");
	}

}
