package com.lhd.sys.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.common.ResultObject;
import com.lhd.sys.entity.ClassificationofGoodsItem;
import com.lhd.sys.entity.OrderList;
import com.lhd.sys.service.OrderListService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.OrderListVO;

@Controller
@RequestMapping("/orderList")
public class OrderListController {
	
	@Autowired
	private OrderListService orderListService ;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@RequestMapping("/allOrderLists")
	@ResponseBody
	public LaYuiPage allOrderLists ( OrderListVO vo ) {
		logger.info("layui查询订单开始======== >>>>>>");
		LaYuiPage pages = orderListService.queryOrderLists(vo) ;
		logger.info("layui查询订单结束======== >>>>>>");
		return pages ; 
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping("/removeOrderList")
	@ResponseBody
	public ResultObject removeOrderList ( Integer id ) {
		try {
			this.orderListService.deleteOrderListById(id) ;
			return ResultObject.DELETE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return ResultObject.DELETE_ERROR ;
		}
	}
	
	
	/**
	 * 批量删除
	 */
	@RequestMapping("/breaDelOrderList")
	@ResponseBody
	public ResultObject breaDelOrderList ( String  ids ) {
		String[] split = ids.split(",") ;
		Integer [] id = new Integer [split.length]  ;
		
		  for(int i=0;i<split.length;i++){
			  
		        id[i] = Integer.parseInt(split[i]);
		        
		    }
		  try {
			this.orderListService.batchDelete(id);
			return ResultObject.DELETE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return ResultObject.DELETE_ERROR ;
		}
	
	}
	
	/**
	 * 根据ID 查看订单信息详情 并返回页面
	 */
	@RequestMapping("/queryOrderListView")
	public String queryOrderListView ( OrderListVO vo ,Model model ) {
		OrderList orderList = this.orderListService.queryOrderListById (vo.getId()) ;
		//根据cid查询商品对象信息
		ClassificationofGoodsItem goodItem = this.orderListService.queryGoodsByCid (orderList.getcId()) ;
		model.addAttribute("rioid", orderList.getRioid()) ; //订单号
		model.addAttribute("address", orderList.getAddress()) ; //地址
		model.addAttribute("payMoney", orderList.getPaymoney()) ; //支付金额
		model.addAttribute("payTime", orderList.getPaytime()) ; // 支付时间
		model.addAttribute("mun", orderList.getNumbers()) ; //分量
		model.addAttribute("userName", orderList.getUsername()) ; //买家姓名
		model.addAttribute("tel", orderList.getTel()) ; //电话
		model.addAttribute("shopName", goodItem.getCname()) ; //商品名称
		model.addAttribute("brand", goodItem.getBrand()) ;  //品牌
		model.addAttribute("price", goodItem.getPrice()) ; //单价
		return "orderList/orderListInfo.html" ;
	}

}
