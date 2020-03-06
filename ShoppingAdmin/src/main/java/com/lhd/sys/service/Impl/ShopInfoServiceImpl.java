package com.lhd.sys.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.lhd.sys.dao.ClassificationTypeMapper;
import com.lhd.sys.dao.ClassificationofGoodsItemMapper;
import com.lhd.sys.entity.ClassificationType;
import com.lhd.sys.entity.ClassificationofGoodsItem;
import com.lhd.sys.service.ShopInfoService;

@Service("ShopInfoService")
public class ShopInfoServiceImpl implements ShopInfoService{
	@Autowired
	private ClassificationTypeMapper classificationTypeMapper ;
	
	@Autowired
	private ClassificationofGoodsItemMapper classificationofGoodsItemMapper ;
	
	public ClassificationType  findById (Integer id) {
		ClassificationType classificationType = classificationTypeMapper.selectByPrimaryKey(id) ;
		return classificationType ;
	}

	@Override
	public List<ClassificationofGoodsItem> goodsItemsById(Integer id) {
		List<ClassificationofGoodsItem> goodsItemsById = classificationofGoodsItemMapper.goodsItemsById(id) ;
		return goodsItemsById ;
	}

	/**
	 * 根据品牌和价格搜索
	 */
	@Override
	public List<ClassificationofGoodsItem> searchByBrandAndMoney(int pageNum , int pageSize , 
					String brand , Integer moneyOne , Integer moneyTow) {
		PageHelper.startPage(pageNum, 16) ;
		List<ClassificationofGoodsItem> list = new ArrayList<ClassificationofGoodsItem>() ;
		if ( moneyOne == null && moneyTow == null ) {
			 list = classificationofGoodsItemMapper.searchByBrandsOrPrice(brand, null, null) ;
		} else {
			list = classificationofGoodsItemMapper.searchByBrandsOrPrice(brand, moneyOne, moneyTow) ;
		}
		return list ;
	}

	//热卖
	@Override
	public List<ClassificationofGoodsItem> hotBuy( int page , int pageSize , String brand ,
				Integer priceOne ,  Integer priceTow) {
		PageHelper.startPage(page, pageSize) ;
		List<ClassificationofGoodsItem> hotBuy = classificationofGoodsItemMapper.hotBuy( brand , priceOne , priceTow ) ; 
		return hotBuy ;
	}

	//用户登录 根据收藏商品推荐
	@Override
	public List<ClassificationofGoodsItem> findByUserid(int page , Integer userId , int pageSize
			,String brand , Integer moneyOne , Integer moneyTow ) {
		PageHelper.startPage(page , pageSize ) ;
		List<ClassificationofGoodsItem> list = classificationofGoodsItemMapper.findByUserid(brand ,  moneyOne , moneyTow , userId ) ;
		return list ;
	}

	//默认推荐
	@Override
	public List<ClassificationofGoodsItem> defaultRecommended( int page , int pageSize 
			,String brand , Integer moneyOne , Integer moneyTow ) {
		PageHelper.startPage(page, pageSize) ;
		List<ClassificationofGoodsItem> list = classificationofGoodsItemMapper.selectAll(brand , moneyOne , moneyTow) ; 
		return list ;
	}

	//查找所有商品通过排序
	@Override
	public List<ClassificationofGoodsItem> findShopDesc( int page , int pageSize
			,String brand , Integer moneyOne , Integer moneyTow ) {
		PageHelper.startPage(page, pageSize) ;
		List<ClassificationofGoodsItem> list = classificationofGoodsItemMapper.findShopByDesc(brand,moneyOne,moneyTow) ;
		return list;
	}

	@Override
	public List<ClassificationofGoodsItem> findShopInfoById( int page , int pageSize , Integer id) {
		PageHelper.startPage(page, pageSize) ;
		List<ClassificationofGoodsItem> list = classificationofGoodsItemMapper.findShopInfoById(id) ;
		return list;
	}


}
