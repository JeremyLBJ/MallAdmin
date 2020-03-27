package com.lhd.sys.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/HiMallAdmin")
public class SuserController {
	
	
	/**
	 * 
	 * Shior页面跳转
	 */
	
	/*@RequestMapping("/index")
	public String index () {
		return "forward:/resources/index.html";
	}*/
	
	
	//用户登录
/*	@RequestMapping("/ShiorLogin")
	public String ShiorLogin () {
		
		return "forward:/resources/login.html";
	}
	*/
	
	/*@RequestMapping("/toLogin")
	public String toLogin () {
		return "redirect:/resources/page/login/login.html" ;
	}*/
	
	@RequestMapping("/toLogin")
	public String toLogin () {
		return "/login/login.html" ;
	}
	
	@RequestMapping("/loginfoMessage")
	public String loginfoMessage () {
		return "loginfo/loginfoMessage.html" ;
	}
	
	@RequestMapping("/index")
	public String staticHtml () {
		return "index.html" ;
	}
	
	//index页面中显示数据页面
	@RequestMapping("/toMainPage")
	public String main () {
		return "mainPage/main.html" ;
	}
	
	//订单列表
	@RequestMapping("/newList")
	public String newList () {
		return "orderList/orderListManaget.html" ;
	}
	
	//系统公告
	@RequestMapping("/sysNotice")
	public String sysNotice () {
		return "notice/noticeMessage.html" ;
	}
	
	/**
	 * 授权成功跳转页面
	 */
	/*@RequestMapping("/shiroIndex")
	public String shiroIndex () {
		return "redirect:/resources/index.html";
	}*/

}
