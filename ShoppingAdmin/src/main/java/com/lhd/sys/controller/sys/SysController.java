package com.lhd.sys.controller.sys;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.common.ActiverUser;
import com.lhd.sys.common.ResultObject;
import com.lhd.sys.common.WebUntils;
import com.lhd.sys.common.annotation.SysLog;
import com.lhd.sys.entity.SysLogInfo;
import com.lhd.sys.service.LoginfoService;


@Controller
@RequestMapping("/sys")
public class SysController {
	
	
	@Autowired
	private LoginfoService loginfoService ;
	
	
	/**
	 * 使用Shiro进行登录
	 */
	@RequestMapping("/adminLoginByShiro")
	@SysLog(value="登录日志")
	@ResponseBody
	public ResultObject adminLoginByShiro ( String name , String password  ) {
		//1.获取Subject
		Subject subject = SecurityUtils.getSubject() ;
		
		//封装用户信息
		UsernamePasswordToken token = new UsernamePasswordToken( name , password ) ;
		
		
			try {
				// 登录方法
				subject.login(token);
				// 登录成功
				// 设置到session中
				ActiverUser user = (ActiverUser) subject.getPrincipal();
				WebUntils.getSession().setAttribute("user", user.getSysUser());

				// 登录日志记录
				SysLogInfo info = new SysLogInfo();
				info.setLoginname(user.getSysUser().getName() + "-" + user.getSysUser().getLoginname());
				info.setLogintime(new Date());
				info.setLoginip(WebUntils.getRequest().getRemoteAddr());
				loginfoService.loginMessage(info);

				return ResultObject.LONG_SUCCESS;
			} catch (UnknownAccountException e) {
				// TODO: handle exception
				// 用户名不存在
				// 返回登录页面
				return ResultObject.LONG_ERROR_PASS;
			} catch (IncorrectCredentialsException e) {
				// TODO: handle exception
				// 密码错误
				// 返回登录页面
				return ResultObject.LONG_ERROR_PASS;
			}

		}
	
	
	//用户等级
	@RequestMapping("/userGrade")
	public String userGrade () {
		return "user/userGrade.html" ;
	}
	
	//退出系统
	@RequestMapping("/loginOut")
	public String loginOut () {
		WebUntils.getSession().removeAttribute("user");
		return "redirect:/HiMallAdmin/toLogin" ;
	}

	@RequestMapping("/shiroLoginOut")
	public String shiroLoginOut () {
		//1.获取Subject
		Subject subject = SecurityUtils.getSubject() ;
		WebUntils.getSession().removeAttribute("user");
		subject.logout() ;
		return "redirect:/HiMallAdmin/toLogin" ;
	}
}
