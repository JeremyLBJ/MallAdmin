package com.lhd.sys.controller.sys;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lhd.sys.common.DateUtils;

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
		return "login/login.html" ;
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
	public String main ( Model model ) {

		OperatingSystemMXBean osmx = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
		 String format = DateUtils.format(new Date(), "yyyy年MM月dd日 HH:mm:ss") ; //系統時間
		 model.addAttribute("sysTime", format) ;
		 String osName = System.getProperty("os.name") ; // 操作系统
		 model.addAttribute("osName", osName) ;
		 String osArch = System.getProperty("os.arch") ; // 系统架构
		 model.addAttribute("osArch", osArch) ;
		 String osVersion = System.getProperty("os.version") ;  //系统版本
		 model.addAttribute("osVersion", osVersion) ;
		 String language = System.getProperty("user.language") ; // 系统语言
		 model.addAttribute("userLanguage", language) ;
		 String userDir = System.getProperty("user.dir") ; // 工作目录
		 model.addAttribute("userDir", userDir) ;
		 int processors = osmx.getAvailableProcessors() ;  // processors  cpu 核数
		 model.addAttribute("processors", processors) ;
		 String jvmName = System.getProperty("java.vm.name") ; // JVM信息:
		 model.addAttribute("jvmName", jvmName) ;
		 String javaVersion = System.getProperty("java.version") ; // JVM版本:
		 model.addAttribute("javaVersion", javaVersion) ;
		 String javaHome = System.getProperty("java.home") ; //JAVA_HOME:
		 model.addAttribute("javaHome", javaHome) ;
		 long totalMemory = Runtime.getRuntime().totalMemory() ;
		 long javaTotalMemory = totalMemory / 1024 / 1024 ; //JVM占用内存:
		 model.addAttribute("javaTotalMemory", javaTotalMemory) ;
		 long freeMemory = Runtime.getRuntime().freeMemory() ;
		 long javaFreeMemory = freeMemory / 1024 / 1024 ; //JVM空闲内存:
		 model.addAttribute("javaFreeMemory", javaFreeMemory) ;
		 long maxMemory = Runtime.getRuntime().maxMemory() ;
		 long javaMaxMemory = maxMemory / 1024 / 1024 ;//JVM最大内存:
		 model.addAttribute("javaMaxMemory", javaMaxMemory) ;
		 String userName = System.getProperty("user.name") ;//当前用户:
		 model.addAttribute("userName", userName) ;
		 String userTimezone = System.getProperty("user.timezone") ;  //系统时区:
		 model.addAttribute("userTimezone", userTimezone) ;
		 double systemLoadAverage = osmx.getSystemLoadAverage() ; //负载均衡
		 model.addAttribute("systemLoadAverage", systemLoadAverage) ;
	
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
