package com.lhd.sys.Config;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivitiConfig {
	
	public static ProcessEngineConfiguration activitiConfigStatic () {
		ProcessEngineConfiguration configuration = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration() ;
	configuration.setJdbcUrl("jdbc:mysql://localhost:3306/highconcurrency?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false") ;
	configuration.setJdbcDriver("com.mysql.jdbc.Driver") ;
	configuration.setJdbcUsername("root") ;
	configuration.setJdbcPassword("a") ;
	configuration.setDatabaseSchemaUpdate("true") ;
	
	//得到流程引擎
	ProcessEngine engine = configuration.buildProcessEngine() ;
	return configuration ;
	}
	
	
	@Test
	public  void initTables () {
		//创建流程引擎配置
		ProcessEngineConfiguration configuration = ProcessEngineConfiguration
					.createStandaloneProcessEngineConfiguration() ;
		configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false") ;
		configuration.setJdbcDriver("com.mysql.jdbc.Driver") ;
		configuration.setJdbcUsername("root") ;
		configuration.setJdbcPassword("a") ;
		configuration.setDatabaseSchemaUpdate("true") ;
		
		//得到流程引擎
		ProcessEngine engine = configuration.buildProcessEngine() ;
	}
	

}
