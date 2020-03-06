package com.lhd.sys.activiti;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
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
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.Test;

import com.lhd.sys.Config.ActivitiConfig;

/**
 * 
 * 
 * 流程操作测试
 * @author ASUS
 *
 */
public class Process {
	
	
	private ProcessEngineConfiguration configuration = ActivitiConfig.activitiConfigStatic() ;
	
	
	/**
	 * 使用zip部署流程
	 * 
	 */
	@Test
	public void ProcessZip () {
		InputStream resourceAsStream = configuration.getClass().getResourceAsStream("/activiti3.zip") ;
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		ZipInputStream zipInputStream = new ZipInputStream(resourceAsStream) ;
		repositoryService.createDeployment().name("保修流程003")
		                                    .addZipInputStream(zipInputStream)
		                                    .deploy();
		System.out.println("部署成功");
	}
	
	
	
	/**
	 * 
	 * 查询流程部署信息  act_re_deployment
	 * 
	 */
	@Test
	public void findAllDeployment () {
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		String deploymentId = "47501" ;
		 Deployment result = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult() ;
		System.out.println(result);
	}
	
	
	
	/**
	 * 流程定义  act_re_procdef表
	 * 
	 * 
	 */
	@Test
	public void Procdef () {
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list() ;
		if ( null != list && list.size() > 0 ) {
			for (ProcessDefinition pd : list) {
				System.out.println("流程部署ID:::::"+pd.getDeploymentId()) ;
				System.out.println("文件png图片名称:::::"+pd.getDiagramResourceName()) ;
				System.out.println("部署id:::"+pd.getId()) ;
				System.out.println("部署Key名称:::::"+pd.getKey()) ;
				System.out.println("bpmn名称::::::"+pd.getName()) ;
				System.out.println("版本号:::::::"+pd.getVersion()) ;
				System.out.println("=================================");
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
		String processDefinitionKey = "leavenBill" ;
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
		String deploymentId = "40001" ;
		repositoryService.deleteDeployment(deploymentId, true) ;
		System.out.println("删除成功");
	}
	

	/**
	 * 查询流程图 根据流程定义ID 并写入d盘
	 * 
	 * 
	 */
	@Test
	public void finaBpmn () {
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		String processDefinitionId = "leavenBill:1:45004" ; 
		InputStream inputStream = repositoryService.getProcessDiagram(processDefinitionId) ;
		File file = new File("d:/activiti.png") ;
		
		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file)) ;
			int len = 0 ;
			byte [] b = new byte [1024] ;
			while ((len = inputStream.read(b)) != -1) {
				stream.write(b, 0, len) ;
			}
			stream.close() ;
			inputStream.close() ;
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
	public void findNewVesion () {
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition> () ;
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc().list() ;
		if ( null != list && list.size() > 0 ) {
			for (ProcessDefinition pd : list) {
				map.put(pd.getKey(), pd) ;
				
			}
		}
		Collection<ProcessDefinition> values = map.values() ;
		for (ProcessDefinition pd : values) {
			System.out.println("流程部署ID:::::"+pd.getDeploymentId()) ;
			System.out.println("文件png图片名称:::::"+pd.getDiagramResourceName()) ;
			System.out.println("部署id:::"+pd.getId()) ;
			System.out.println("部署Key名称:::::"+pd.getKey()) ;
			System.out.println("bpmn名称::::::"+pd.getName()) ;
			System.out.println("版本号:::::::"+pd.getVersion()) ;
			System.out.println("=================================");
			
		}
	}
	
	
	
	/**
	 * 
	 * 根据流程名删除流程
	 *  
	 */
	@Test
	public void deleteByName () {
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		String processDefinitionName = "leavenBill" ;
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionName).list() ;
		if ( null != list && list.size() > 0 ) {
			for (ProcessDefinition pd : list) {
				repositoryService.deleteDeployment(pd.getDeploymentId(), true) ;
				
			}
			
		}
	}

}
