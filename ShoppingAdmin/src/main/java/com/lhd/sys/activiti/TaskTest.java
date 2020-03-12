package com.lhd.sys.activiti;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import com.lhd.sys.Config.ActivitiConfig;

public class TaskTest {
	
	private ProcessEngineConfiguration configuration = ActivitiConfig.activitiConfigStatic() ;
	
	/**
	 * 
	 * 启动流程
	 * 
	 */
	@Test
	public void startProcess () {
		RuntimeService runtimeService = configuration.getRuntimeService() ;
		String processDefinitionKey = "activiti" ;
		//设置流程变量
		Map<String, Object> variables = new HashMap<String, Object>() ;
		variables.put("请假天数", 5) ;
		variables.put("请假原因", "生病") ;
		variables.put("请假时间", new Date()) ;
		runtimeService.startProcessInstanceByKey(processDefinitionKey , variables) ;
		System.out.println("流程启动成功");
	}
	
	/**
	 * 
	 * 查询我的个人任务
	 * 
	 */
	@Test
	public void findMyTask () {
		TaskService taskService = configuration.getTaskService() ;
//		String assignee = "财务" ;
		String assignee = "王五" ;
//		String assignee = "买家" ;
		List<Task> list = taskService.createTaskQuery()
				   .taskAssignee(assignee)
				   .orderByTaskCreateTime()
				   .desc()
				   .list() ;
		if ( null != list && list.size() > 0 ) {
			for (Task t : list) {
				System.out.println("办理人::::"+t.getAssignee()) ;
				System.out.println("当前运行时ID::::"+t.getExecutionId()) ;
				System.out.println("当前任务ID::::::"+t.getId()) ;
				System.out.println("流程实例ID:::::"+t.getProcessDefinitionId()) ;
				System.out.println("任务名KEY:::::"+t.getTaskDefinitionKey()) ;
				System.out.println("任务创建时间:::::"+t.getCreateTime()) ;
				
			}
		}
	}
	
	/**
	 * 办理任务
	 * 52505
	 * 
	 */
	@Test
	public void Process () {
		TaskService taskService = configuration.getTaskService() ;
		String taskId = "180002" ;
		taskService.complete(taskId) ;
		System.out.println("任务办理成功");
	}
	
	
	
	/**
	 * 办理任务 并指定流程变量指定流程走向
	 * 52505
	 * 
	 */
	@Test
	public void ProcessVar () {
		TaskService taskService = configuration.getTaskService() ;
		String taskId = "142504" ;
		Map<String, Object> map = new HashMap<>() ;
		map.put("money", 800) ;
		taskService.complete(taskId, map) ;
		System.out.println("任务办理成功");
	}
	
	/**
	 * 查询当前任务办理状态
	 * 
	 */
	@Test
	public void findProcessStutes () {
		//已知流程ID
		 RuntimeService runtimeService = configuration.getRuntimeService() ;
		 String processDefinitionId = "160001" ;
		ProcessInstance singleResult = runtimeService.createProcessInstanceQuery().processInstanceId(processDefinitionId ).singleResult() ;
		if ( null != singleResult ) {
			System.out.println("任务正在办理");
		} else {
			System.out.println("任务已经结束");
		}
		
		//任务ID
		/*TaskService taskService = configuration.getTaskService() ;
		String taskId = "75005" ;
		Task result = taskService.createTaskQuery().taskId(taskId ).singleResult() ;
		RuntimeService service = configuration.getRuntimeService() ;
		ProcessInstance instance = service.createProcessInstanceQuery().processInstanceId(result.getProcessInstanceId()).singleResult() ;
		if ( null != instance ) {
			System.out.println("任务正在办理");
		} else {
			System.out.println("任务已经结束");
		}*/
		
	}
	
	/**
	 * 设置流程变量
	 */
	@Test
	public void setProcess () {
		RuntimeService runtimeService = configuration.getRuntimeService() ;
		String executionId = "95005" ;
		Map<String,  Object> variables = new HashMap<>() ;
		variables.put("请假天数", 6) ;
		variables.put("请假原因", "生病") ;
		variables.put("请假时间", new Date()) ;
		runtimeService.setVariables(executionId, variables) ;
		System.out.println("设置成功");
	}
	
	/**
	 * 获取流程变量
	 */
	@Test
	public void getProcess () {
		RuntimeService runtimeService = configuration.getRuntimeService() ;
		String executionId = "95005" ;
		String variableName = "请假天数" ;
		Integer variable = (Integer) runtimeService.getVariable(executionId, variableName ) ;
		System.out.println(variable);
	}
	
	/**
	 * 查看历史流程变量
	 */
	@Test
	public void hiProcess () {
		HistoryService historyService = configuration.getHistoryService() ;
		String processInstanceId = "95001" ;
		 List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId ).list() ;
		 
		if (null != list && list.size() > 0 ) {
			for (HistoricVariableInstance h : list) {
				System.out.println("变量名:::::::"+h.getVariableName()) ;
				System.out.println("值:::::::::::;"+h.getValue()) ;
				System.out.println("类型::::::::"+h.getVariableTypeName()) ;
				
			}
		}
	}
}
