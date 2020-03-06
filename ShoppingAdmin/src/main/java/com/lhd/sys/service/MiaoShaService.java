package com.lhd.sys.service;

import com.lhd.sys.entity.MiaoSha;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.MiaoShaShopVO;

public interface MiaoShaService {
	
	
	//全查询
	LaYuiPage findAllMiaoShaShops ( MiaoShaShopVO miaoShaShopVO ) ;
	
	//添加商品
	void addMiaoShaShop ( MiaoSha miaoSha ) ;
	
	//最大id
	Integer maxIdOfMiaoShaShop () ;
	
	//删除数据
	void deleteMiaoShaShop ( Integer id ) ;
	
	//删除秒杀图片
	void removeImg ( Integer cid ) ; 
	
	//添加秒杀tup
	void addImg ( String path , Integer cid ) ;
	
	//修改
	void updateMiaoShaInfo ( MiaoSha miaoSha ) ;
	
	//添加图片的cid
	void addImgCid ( Integer cid ) ;

}
