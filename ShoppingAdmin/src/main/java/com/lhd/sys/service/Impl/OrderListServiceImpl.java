package com.lhd.sys.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.lhd.sys.dao.OrderListMapper;
import com.lhd.sys.entity.OrderList;
import com.lhd.sys.service.OrderListService;
import com.lhd.sys.untils.LaYuiPage;

@Service("OrderListService")
public class OrderListServiceImpl implements OrderListService {
	
	@Autowired
	private OrderListMapper orderListMapper ;

	@Override
	public LaYuiPage findAllOrderList( int page , int limit) {
		 List<OrderList> list2 = orderListMapper.findIsShowOne() ;
		PageHelper.startPage(page, limit) ;
		List<OrderList> list = orderListMapper.findIsShowOne() ;
		return new LaYuiPage(list, list2.size());
	}
	
	/**
	 * 1.往数据库表中 gir  orderlist 等表中添加一个自带  isShow  类型为  0 (不显示数据) | 1 (显示数据)  逻辑删除
	 * 2.重新写一条sql语句 分别查询  isShow  为    0  |   1  
	 */
	
	

}
