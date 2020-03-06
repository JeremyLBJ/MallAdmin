package com.lhd.sys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lhd.sys.entity.ClassificationType;
import com.lhd.sys.entity.ClassificationofGoodsItem;


public interface ShopInfoService {
	
	    //根据id查询
	 ClassificationType  findById  ( Integer id ) ;
	 
	 //根据classification_type  id查询
	 List<ClassificationofGoodsItem> findShopInfoById (  int page , int pageSize , Integer id ) ;
	 
	 //classificationof表中的ID关联查询
	 List<ClassificationofGoodsItem> goodsItemsById ( Integer id ) ;
	 
	 //根据价格和品牌筛选
	 List<ClassificationofGoodsItem> searchByBrandAndMoney ( int pageNum , int pageSize ,  String brand , Integer moneyOne , Integer moneyTow ) ;
	 
	 //热卖 
	 List<ClassificationofGoodsItem> hotBuy ( int page  , int pageSize , String brand ,  Integer priceOne ,  Integer priceTow ) ;
	 
	 //用户登录 根据收藏商品推荐
	 List<ClassificationofGoodsItem> findByUserid ( int page , @Param("userId") Integer userId , int pageSize ,String brand , Integer moneyOne , Integer moneyTow ) ;
	 
	 //默认推荐
	 List<ClassificationofGoodsItem> defaultRecommended ( int page , int pageSize ,String brand , Integer moneyOne , Integer moneyTow  ) ;
	 
	 //查找所有商品排序
	 List<ClassificationofGoodsItem> findShopDesc ( int page , int pageSize ,String brand , Integer moneyOne , Integer moneyTow  ) ;

}
