package com.lhd.sys.activiti;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;

import com.lhd.sys.Config.ActivitiConfig;

/**
 * 
 * 流程
 * @author ASUS
 *
 */
public class ProcessTest {
	
	
	private ProcessEngineConfiguration configuration = ActivitiConfig.activitiConfigStatic() ;
	
	
	/**
	 * 使用zip部署流程
	 * 
	 */
	@Test
	public void deployProcess () {
		InputStream inputStream = configuration.getClass().getResourceAsStream("/activitiBpmn.zip") ;
		ZipInputStream zipInputStream = new ZipInputStream(inputStream) ;
		RepositoryService repositoryService = configuration.getRepositoryService() ;
	    Deployment deploy = repositoryService.createDeployment().name("保修流程测试")
				.addZipInputStream(zipInputStream) 
				.deploy() ;
	   
		System.out.println(inputStream) ;
		System.out.println(deploy);
	}
	
	/**
	 * 
	 * 查询流程部署信息  act_re_deployment
	 * 
	 */
	@Test
	public void deployment () {
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		String de = "12501" ;
		Deployment singleResult = repositoryService.createDeploymentQuery()
		//条件
		.deploymentId(de)
		.singleResult() ;
		
		System.out.println(singleResult);
	}
	
	/**
	 * 流程定义  act_re_procdef表
	 * 
	 * 
	 */
	@Test
	public void process () {
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
		.list() ;
		if(null != list && list.size() > 0) {
			for (ProcessDefinition p : list) {
				System.out.println("部署流程id::::"+p.getDeploymentId());
				System.out.println("当前流程id::::"+p.getId());
				System.out.println("当前流程Key:::"+p.getKey());
				System.out.println("流程名"+p.getName());
				System.out.println("当前bpmn名:::"+p.getResourceName());
				System.out.println("版本:::::::::"+p.getVersion());
				System.out.println("png名字::::::"+p.getDiagramResourceName());
			
				
			}
		}
	}
	
	/**
	 * 
	 * 启动流程
	 * 
	 */
	@Test
	public void startProcess () {
		RuntimeService runtimeService = configuration.getRuntimeService() ;
		String processDefinitionKey = "payMent" ;
		runtimeService.startProcessInstanceByKey(processDefinitionKey) ;
		System.out.println("流程启动成功");
	}
	
	/**
	 * 删除流程定义
	 * 
	 */
	@Test
	public void deleteProcess () {
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		String deploymentId = "1" ;
		//repositoryService.deleteDeployment(deploymentId);
		repositoryService.deleteDeployment(deploymentId, true);
		System.out.println("删除成功");
	}
	
	
	/**
	 * 查询流程图 根据流程定义ID 并写入d盘
	 * 
	 * 
	 */
	@Test
	public void findProcessImg () {
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		String processDefinitionId = "activiti:1:4" ;
		InputStream processDiagram = repositoryService.getProcessDiagram(processDefinitionId) ;
		
		File file = new File("d:/activiti.png") ;
		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file)) ;
			int len = 0 ;
			byte [] b = new byte[1024] ;
			while ((len = processDiagram.read(b)) != -1) {
				stream.write(b, 0, len) ;
				stream.flush();
			}
			stream.close() ;
			processDiagram.close() ;
			System.out.println("查询成功");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	/**
	 * 
	 * 查询当前最新版本号
	 * 
	 */
	@Test
	public void findNewProcess () {
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>() ;
		//根据流程定义的版本号排序
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc().list() ;
		if(null != list && list.size() > 0) {
			for (ProcessDefinition pd : list) {
				map.put(pd.getKey(), pd) ;
			}
		}
		Collection<ProcessDefinition> values = map.values() ;
		for (ProcessDefinition p : values) {
			System.out.println("部署流程id::::"+p.getDeploymentId());
			System.out.println("当前流程id::::"+p.getId());
			System.out.println("当前流程Key:::"+p.getKey());
			System.out.println("流程名"+p.getName());
			System.out.println("当前bpmn名:::"+p.getResourceName());
			System.out.println("版本:::::::::"+p.getVersion());
			System.out.println("png名字::::::"+p.getDiagramResourceName());
			System.out.println("=========================================================");
		
		}
	}
	
	/**
	 * 
	 * 根据流程名删除流程
	 *  
	 */
	@Test
	public void deleteAllSameName () {
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		String processDefinitionKey = "leavenBill" ;
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).list() ;
		//repositoryService.deleteDeployment(deploymentId);
		if (null != list && list.size() > 0 ) {
			for (ProcessDefinition pd : list) {
				repositoryService.deleteDeployment(pd.getDeploymentId(), true);
				
			}
		}
	}

}
