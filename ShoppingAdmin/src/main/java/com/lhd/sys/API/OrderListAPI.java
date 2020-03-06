package com.lhd.sys.API;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.service.OrderListService;
import com.lhd.sys.untils.LaYuiPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Controller
@RequestMapping("/OrderListAPI")
@Api(value="商品订单管理" , description="商品订单管理")
public class OrderListAPI {
	

	@Autowired
	private OrderListService orderListService ;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@GetMapping(value="/allOrderLists")
	@ApiOperation(value="获取所有订单")
	@ResponseBody
	public Object allOrderLists ( @ApiParam("页数") @RequestParam("page") String page 
								, @ApiParam("行数") @RequestParam("limit") String limit ) {
		logger.info("layui查询订单开始======== >>>>>>");
		LaYuiPage pages = orderListService.findAllOrderList( Integer.parseInt(page) , Integer.parseInt(limit)) ;
		logger.info("layui查询订单结束======== >>>>>>");
		return pages ; 
	}

}
