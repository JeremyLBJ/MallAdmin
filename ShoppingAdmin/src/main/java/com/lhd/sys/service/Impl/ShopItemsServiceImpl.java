package com.lhd.sys.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhd.sys.dao.ClassificationofGoodsItemMapper;
import com.lhd.sys.entity.ClassificationofGoodsItem;
import com.lhd.sys.service.ShopItemsService;


@Service("ShopItemsService")
public class ShopItemsServiceImpl implements ShopItemsService{
	
	@Autowired
	private ClassificationofGoodsItemMapper classificationofGoodsItemMapper ;
	
	public List<ClassificationofGoodsItem> seachShop (String name) {
		List<ClassificationofGoodsItem> fuzzyQuery = classificationofGoodsItemMapper.fuzzyQuery(name);
		return fuzzyQuery ; 
	}

}
