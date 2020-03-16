package com.lhd.sys.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.lhd.sys.common.ActiverUser;
import com.lhd.sys.common.WebUntils;
import com.lhd.sys.common.annotation.SysLog;
import com.lhd.sys.service.LoginfoService;
import com.lhd.sys.vo.SysLogVO;

/**
 * 
 * 
 * 系统日志,切面处理类
 * 
 * @author ASUS
 *
 */
@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	private LoginfoService loginfoService ;
	
	@Pointcut("@annotation(com.lhd.sys.common.annotation.SysLog)")
	public void logPointCut() { 
		
	}
	
	
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysLog(point, time);

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		
		SysLogVO logVO = new SysLogVO() ;
	
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			//注解上的描述
			logVO.setOperation(syslog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		logVO.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = new Gson().toJson(args[0]);
			logVO.setParams(params);
		}catch (Exception e){

		}

		//设置IP地址
		logVO.setIp(WebUntils.getRequest().getRemoteAddr());
		
		//用户名
		String username = ((ActiverUser) SecurityUtils.getSubject().getPrincipal()).getSysUser().getName() ;
		logVO.setLoginname(username) ;

		logVO.setTime(time);
		logVO.setCreatedate(new Date());
		//保存系统日志
		loginfoService.SysLogin(logVO) ;
		System.out.println("aop拦截::::::"+logVO);
	}

}
