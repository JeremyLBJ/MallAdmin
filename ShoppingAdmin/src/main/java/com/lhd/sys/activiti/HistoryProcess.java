package com.lhd.sys.activiti;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.junit.Test;

import com.lhd.sys.Config.ActivitiConfig;

/**
 * 
 * 查看历史
 * 
 * @author ASUS
 *
 */
public class HistoryProcess {
	
	private ProcessEngineConfiguration configuration = ActivitiConfig.activitiConfigStatic() ;
	
	
	@Test
	public void findHistory () {
		HistoryService historyService = configuration.getHistoryService() ;
		 String processInstanceId = "77501" ;
		 List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list() ;
		if ( null != list && list.size() > 0 ) {
			for (HistoricVariableInstance h : list) {
				System.out.println(h.getId()) ;
				System.out.println("流程实例ID::::"+h.getProcessInstanceId()) ;
				System.out.println("任务ID:::::::"+h.getTaskId()) ;
				System.out.println(h.getValue()) ;
				System.out.println(h.getVariableTypeName()) ;
				System.out.println("==================") ;
				
			}
		}
		
	}
	
	
	/**
	 * 查看历史活动
	 * 
	 */
	@Test
	public void allHistoryProcess () {
		HistoryService historyService = configuration.getHistoryService() ;
		List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().list() ;
		if ( null != list && list.size() > 0 ) {
			for (HistoricActivityInstance h : list) {
				System.out.println("活动ID::::"+h.getActivityId()) ;
				System.out.println("办理人::::"+h.getAssignee()) ;
				System.out.println("流程ID:::"+h.getExecutionId()) ;
				System.out.println("流程部署ID::::"+h.getProcessDefinitionId()) ;
				System.out.println("流程实例ID:::"+h.getProcessInstanceId()) ;
				System.out.println("任务ID::::"+h.getTaskId()) ;
				System.out.println("结束时间::::"+h.getEndTime()) ;
				System.out.println("开始时间"+h.getStartTime()) ;
				System.out.println("========================================");
			}
		}
	}
	
	/**
	 * 
	 * 查看历史任务
	 */
	@Test
	public void findTask () {
		HistoryService historyService = configuration.getHistoryService() ;
		String processInstanceId = "2501" ;
		List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list() ;
		if ( null != list && list.size() > 0 ) {
			for (HistoricTaskInstance h : list) {
				System.out.println(h.getAssignee()) ;
				System.out.println(h.getExecutionId()) ;
				System.out.println(h.getName()) ;
				System.out.println(h.getProcessDefinitionId()) ;
				System.out.println(h.getProcessInstanceId()) ;
				System.out.println("=================================");
			}
		}
	}
	
	/**
	 * 查看历史流程变量
	 */
	@Test
	public void historyVarinst () {
		HistoryService historyService = configuration.getHistoryService() ;
		String variableName = "请假天数" ;
		List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().variableName(variableName).list() ;
		if ( null != list && list.size() > 0 ) {
			for (HistoricVariableInstance h : list) {
				System.out.println("流程实例ID::::"+h.getProcessInstanceId()) ;
				System.out.println("任务ID:::::::"+h.getTaskId()) ;
				System.out.println("值:::::"+h.getValue()) ;
				System.out.println("类型::::::"+h.getVariableTypeName()) ;
				System.out.println("======================================");
			}
		}
	}

}
