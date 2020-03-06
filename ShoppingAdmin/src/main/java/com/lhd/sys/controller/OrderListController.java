package com.lhd.sys.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.service.OrderListService;
import com.lhd.sys.untils.LaYuiPage;

@Controller
@RequestMapping("/orderList")
public class OrderListController {
	
	@Autowired
	private OrderListService orderListService ;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@RequestMapping("/allOrderLists")
	@ResponseBody
	public Object allOrderLists ( String page , String limit ) {
		logger.info("layui查询订单开始======== >>>>>>");
		LaYuiPage pages = orderListService.findAllOrderList( Integer.parseInt(page) , Integer.parseInt(limit)) ;
		logger.info("layui查询订单结束======== >>>>>>");
		return pages ; 
	}

}
