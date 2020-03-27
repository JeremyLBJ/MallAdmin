package com.lhd.sys.listener;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lhd.sys.common.WebUntils;
import com.lhd.sys.entity.SysUser;
import com.lhd.sys.service.SysUserService;

public class TaskAssigneeListener implements TaskListener {
	

	@Override
	public void notify(DelegateTask delegateTask) {
		//得到当前用户
		SysUser user = (SysUser) WebUntils.getSession().getAttribute("user") ;
		//得到领导ID
		Integer mgr = user.getMgr() ;
		//获取IOC容器
		HttpServletRequest request = WebUntils.getRequest() ;
		WebApplicationContext context = WebApplicationContextUtils
										.getWebApplicationContext(request.getServletContext()) ;
		//从IOC容器获取service
		SysUserService userService = context.getBean(SysUserService.class) ;
		SysUser byMgr = userService.findByMgr(mgr) ;
		//设置办理人
		delegateTask.setAssignee(delegateTask.getId()) ;
		
	}

}
