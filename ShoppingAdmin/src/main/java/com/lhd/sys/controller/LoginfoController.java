package com.lhd.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.common.ResultObject;
import com.lhd.sys.service.LoginfoService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.LoginfoVO;

/**
 * 
 * 
 * 系统登录日志
 * @author ASUS
 *
 */
@Controller
@RequestMapping("/loginfo")
public class LoginfoController {
	
	@Autowired
	private LoginfoService loginfoService ;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// , String page , String limit
	@RequestMapping("/loginfosMessage")
	@ResponseBody
	public Object loginfosMessage (LoginfoVO loginfoVO  ) {
		System.out.println("登录名称:"+loginfoVO.getLoginname() +"::::::"+"登录地址:"+loginfoVO.getLoginip() +"idwei :"+loginfoVO.getId());
		logger.info("layui查询订单开始======== >>>>>>");
		//LaYuiPage pages = loginfoService.findAllOrderList( loginfoVO.getPage() ,loginfoVO.getLimit() ) ;
		LaYuiPage pages = loginfoService.findAllLoginfo(loginfoVO) ;
		System.out.println(pages.toString());
		logger.info("layui查询订单结束======== >>>>>>");
		return pages ; 
	}
	

	/**
	 * 删除
	 */
	@RequestMapping("/deleteSysLoginfo")
	@ResponseBody
	public ResultObject deleteSysLoginfo ( Integer id ) {
		try {
			loginfoService.removeSysLoginfo(id);
			return ResultObject.DELETE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.DELETE_ERROR ;
		}
	}
	
	
	/**
	 * 批量删除
	 */
	@RequestMapping("/batchDeleteLoginfo")
	@ResponseBody
	public ResultObject batchDeleteLoginfo ( String ids ) {
		String[] split = ids.split(",") ;
		Integer [] id = new Integer [split.length]  ;
		
		  for(int i=0;i<split.length;i++){
			  
		        id[i] = Integer.parseInt(split[i]);
		        
		    }
		  try {
			loginfoService.removeSysLoginfos(id);
			return ResultObject.DELETE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.DELETE_ERROR ;
		}
	}
	
}
