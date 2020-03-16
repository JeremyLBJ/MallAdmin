package com.lhd.sys.Config;

import javax.sql.DataSource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Activiti配置类
 * 
 * @author ASUS
 *
 */
@Configuration
public class ActivitiConfigs {
	
		@Autowired
	    private DataSource dataSource;
	 
	    
	    /**
	     * 初始化配置，将创建28张表
	     * @return
	     */
	    @Bean
	    public StandaloneProcessEngineConfiguration processEngineConfiguration() {
	        StandaloneProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
	        configuration.setDataSource(dataSource);
	        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
	        configuration.setAsyncExecutorActivate(false);
	        return configuration;
	    }
	    
	    
	    /**
	     * 
	     * 流程引擎
	     * 
	     * @return
	     */
	    @Bean
	    public ProcessEngine processEngine() {
	        return processEngineConfiguration().buildProcessEngine();
	    }
	 
	    /**
	     * 
	     * 
	     * @return
	     */
	    @Bean
	    public RepositoryService repositoryService() {
	        return processEngine().getRepositoryService();
	    }
	 
	    /**
	     * 运行时   act_ru
	     * @return
	     */
	    @Bean
	    public RuntimeService runtimeService() {
	        return processEngine().getRuntimeService();
	    }
	 
	    /**
	     * 任务   act_task
	     * @return
	     */
	    @Bean
	    public TaskService taskService() {
	        return processEngine().getTaskService();
	    }
	 
	    /**
	     * 历史 act_hi
	     * @return
	     */
	    @Bean
	    public HistoryService historyService() {
	        return processEngine().getHistoryService();
	    }
	    
}
