package com.lhd.sys.service;

import java.util.List;

import com.lhd.sys.API.apiEntity.GoodsEntity;
import com.lhd.sys.entity.ClassificationType;
import com.lhd.sys.entity.Classificationof;
import com.lhd.sys.entity.ClassificationofGoodsItem;
import com.lhd.sys.entity.Classificationofgoods;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.GoodsVO;

public interface GoodsService {
	
	LaYuiPage allGoodsMessage ( GoodsVO goodsVO ) ;
	
	LaYuiPage allGoodsMessageApi ( GoodsEntity entity ) ;
	
	
	Integer findMaxId () ;
	
	//上传图片路径
	void uploadImgPath ( String path , Integer cid) ;
	
	//清除图片
	void deleteImgByCid ( Integer cid) ;
	
	//修改商品信息
	void updateShop ( GoodsVO goodsVO ) ;
	
	//删除商品图片
	void removeImg ( Integer cId ) ;
	
	//添加商品
	void addShop ( ClassificationofGoodsItem goodsItem ) ;
	
	
	/**
	 * 查询所有商品类型
	 * 
	 */
	//查找最顶级的商品类型
	List<Classificationofgoods> findAllGoodsType () ;
	
	//查找最低级的商品类型
	List<ClassificationType> findAllClassType ( Integer cid ) ;
	
	//查找地二级商品类型
	List<Classificationof> findAll ( Integer cid ) ;
	
	
	//根据id删除对应的商品
	void removeShopById ( Integer id ) ;
	
	
}
