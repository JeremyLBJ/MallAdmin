package com.lhd.sys.service;

import com.lhd.sys.entity.ClassificationofGoodsItem;
import com.lhd.sys.entity.OrderList;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.OrderListVO;

public interface OrderListService {
	
	
	LaYuiPage findAllOrderList ( int page , int limit ) ;
	
	LaYuiPage queryOrderLists ( OrderListVO vo ) ;
	
	//根据ID进行删除
	void deleteOrderListById ( Integer id) ;

	OrderList queryOrderListById(Integer id) ;

	//根据cid查询商品对象信息
	ClassificationofGoodsItem queryGoodsByCid(Integer cId);
	
	void batchDelete ( Integer [] ids) ;

	
	

}
