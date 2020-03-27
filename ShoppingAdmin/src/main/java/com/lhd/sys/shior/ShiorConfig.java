package com.lhd.sys.shior;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager ;

import com.lhd.sys.realm.SysAdminRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import lombok.Data;

/**
 * Shior配置类
 * @author ASUS
 *
 */
@Configuration
@ConditionalOnClass(value = { SecurityManager.class })
@ConditionalOnWebApplication
@ConfigurationProperties(prefix = "shiro")
@Data
public class ShiorConfig {
	
	private static final String SHIRO_FILTER = "shiroFilter";
	
	
	
	/**
	 * 创建ShiorFilterFactoryBean
	 * ShiorFilterFactoryBean
	 */
	@Bean(SHIRO_FILTER)
	public ShiroFilterFactoryBean getshiorFilterFactoryBean (@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean() ;
		
		//设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		//添加Shiro内置过滤器
		/**
		 * Shiro内置过滤器,可以实现权限相关的拦截器
		 * 		常用的过滤器:
		 * 			anon: 无需认证(登录) 可以访问
		 * 			authc:必须认证才可以访问
		 * 			user:如果使用rememberMe的功能可以直接访问
		 * 			perms:该资源必须得到资源权限才可以访问
		 * 			role:该资源必须得到角色权限才可以访问
		 */
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String , String>() ;
		/*filterChainDefinitionMap.put("/add", "authc") ;
		filterChainDefinitionMap.put("/update", "authc") ;
		filterChainDefinitionMap.put("/index", "authc") ;*/
		//filterChainDefinitionMap.put("/*", "anon") ;
		//filterChainDefinitionMap.put("/test", "anon") ;
		//如果登录需要放行登录方法
		//filterChainDefinitionMap.put("/admin/adminLoginByShiro", "anon") ;
		//resources 目录下的所有资源进行权限控制
		filterChainDefinitionMap.put("/resources/css/*", "anon") ;
		filterChainDefinitionMap.put("/resources/images/*", "anon") ;
		filterChainDefinitionMap.put("/resources/js/*", "anon") ;
		filterChainDefinitionMap.put("/resources/json/*", "anon") ;
		filterChainDefinitionMap.put("/resources/layui/*", "anon") ;
		filterChainDefinitionMap.put("/resources/page/main.html", "anon") ;
		filterChainDefinitionMap.put("/resources/css/404.html", "anon") ;
		//拦截admin下面所有的方法请求
		filterChainDefinitionMap.put("/HiMallAdmin/toLogin", "anon") ;
		filterChainDefinitionMap.put("/HiMallAdmin/toLogin", "anon") ;
		filterChainDefinitionMap.put("/HiMallAdmin/**", "authc") ;
		
		
		//修改权限拦截是指定跳转页面
		shiroFilterFactoryBean.setLoginUrl("/HiMallAdmin/toLogin");
		//设置未授权页面
		shiroFilterFactoryBean.setUnauthorizedUrl("") ;
		
		
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean ;
		
	}
	
	
	/**
	 * 创建DefaultWebSecurityManager
	 * 
	 */
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager (@Qualifier("sysAdminRealm") SysAdminRealm sysAdminRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager() ;
		securityManager.setRealm(sysAdminRealm) ;
		return securityManager ;
	}
	
	
	/**
	 * 创建Realm
	 */
	@Bean(name="sysAdminRealm")
	public SysAdminRealm getRealm () {
		return new SysAdminRealm() ;
	}
	
	
	
	/* 加入注解的使用，不加入这个注解不生效--开始 */
	/**
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager( securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	
	
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}
	/* 加入注解的使用，不加入这个注解不生效--结束 */

	
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
	
	

}
