package com.lhd.sys.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhd.sys.dao.ClassificationofGoodsItemMapper;
import com.lhd.sys.dao.OrderListMapper;
import com.lhd.sys.entity.ClassificationofGoodsItem;
import com.lhd.sys.entity.OrderList;
import com.lhd.sys.service.OrderListService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.OrderListVO;

@Service("OrderListService")
public class OrderListServiceImpl implements OrderListService {
	
	@Autowired
	private OrderListMapper orderListMapper ;
	
	@Autowired
	private ClassificationofGoodsItemMapper goodItem ;

	@Override
	public LaYuiPage findAllOrderList( int page , int limit) {
		 List<OrderList> list2 = orderListMapper.findIsShowOne() ;
		PageHelper.startPage(page, limit) ;
		List<OrderList> list = orderListMapper.findIsShowOne() ;
		return new LaYuiPage(list, list2.size());
	}

	@Override
	public LaYuiPage queryOrderLists(OrderListVO vo) {
		Page<Object> startPage = PageHelper.startPage(vo.getPage(), vo.getLimit()) ;
		List<OrderList> list = this.orderListMapper.allFindOrderLists(vo.getUserName() ,vo.getRioId() 
											,vo.getStartTime() 
											,vo.getEndTime() ,1 ) ;
		long total = startPage.getTotal() ;
		return new LaYuiPage(list, total) ;
	}

	@Override
	@Transactional
	public void deleteOrderListById(Integer id) {
		this.orderListMapper.deleteByPrimaryKey(id) ;
		
	}

	/**
	 * 根据ID查询 订单对象
	 */
	@Override
	public OrderList queryOrderListById(Integer id) {
		OrderList orderList = this.orderListMapper.selectByPrimaryKey(id) ;
		return orderList ;
	}

	/**
	 * 根据cid查询商品对象信息
	 */
	@Override
	public ClassificationofGoodsItem queryGoodsByCid(Integer cId) {
		ClassificationofGoodsItem item = this.goodItem.selectByPrimaryKey(cId) ;
		return item ;
	}

	@Override
	@Transactional
	public void batchDelete(Integer[] ids) {
		this.orderListMapper.batchDelete(ids) ;
		
	}
	
	
	
}
