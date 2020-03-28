/*package com.lhd.sys.activiti;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

import com.lhd.sys.Config.ActivitiConfig;
*//**
 * 流程部署
 * 
 * @author ASUS
 *
 *//*
public class ProcessDeployment {
	
	private ProcessEngineConfiguration configuration = ActivitiConfig.activitiConfigStatic() ;
	
	
	@Test
	public void processDeployment () {
		InputStream resourceAsStream = configuration.getClass().getResourceAsStream("/LeaveBill.zip") ;
		RepositoryService repositoryService = configuration.getRepositoryService() ;
		ZipInputStream zipInputStream = new ZipInputStream(resourceAsStream) ;
		Deployment deploy = repositoryService.createDeployment().name("员工请假流程")
		                                    .addZipInputStream(zipInputStream)
		                                    .deploy() ;
		System.out.println("部署成功ID为:::"+deploy.getId()) ;
	}

}
*/