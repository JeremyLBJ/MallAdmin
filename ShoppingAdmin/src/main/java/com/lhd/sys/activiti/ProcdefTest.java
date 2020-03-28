/*package com.lhd.sys.activiti;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcdefTest {
	
	@Autowired
	private ProcessEngineConfiguration configuration ;
	
	@Autowired  
	private ProcessEngineConfigurationImpl processEngineConfiguration;  
	
	
	@Autowired
	 RepositoryService repos ;
	
	@Autowired
	 RuntimeService runTime ;
	
	
	
	*//**
	 * 部署流程
	 *//*
	@Test
	public void processTest () {
		InputStream resourceAsStream = this.processEngineConfiguration.getClass().getResourceAsStream("/UserGroup.zip") ;
		RepositoryService repositoryService = this.processEngineConfiguration.getRepositoryService() ;
		ZipInputStream zipInputStream = new ZipInputStream(resourceAsStream);
		Deployment deploy = repositoryService.createDeployment().name("部署流程测试版本")
						 .addZipInputStream(zipInputStream)
						 .deploy() ;
		System.out.println("部署ID::::::"+deploy.getId()) ;
	}
	
	@Test
	public void start () {
		String processDefinitionKey = "userGroup" ;
		RuntimeService runtimeService = configuration.getRuntimeService() ;
//		RuntimeService runtimeService = configuration.getRuntimeService() ;
//		runtimeService.startProcessInstanceByKey(processDefinitionKey) ;
		runtimeService.startProcessInstanceByKey(processDefinitionKey) ;
		System.out.println("流程启动成功");
	}
	
	@Test
	public void process () {
		
	}

}
*/