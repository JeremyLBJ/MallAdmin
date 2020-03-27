package com.lhd.sys.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhd.sys.API.apiEntity.GoodsEntity;
import com.lhd.sys.dao.ClassificationTypeMapper;
import com.lhd.sys.dao.ClassificationofGoodsItemMapper;
import com.lhd.sys.dao.ClassificationofMapper;
import com.lhd.sys.dao.ClassificationofgoodsMapper;
import com.lhd.sys.dao.ImginfoMapper;
import com.lhd.sys.entity.ClassificationType;
import com.lhd.sys.entity.ClassificationTypeExample;
import com.lhd.sys.entity.Classificationof;
import com.lhd.sys.entity.ClassificationofExample;
import com.lhd.sys.entity.ClassificationofGoodsItem;
import com.lhd.sys.entity.Classificationofgoods;
import com.lhd.sys.entity.Imginfo;
import com.lhd.sys.entity.ImginfoExample;
import com.lhd.sys.service.GoodsService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.GoodsVO;

@Service("GoodsService")
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	private ClassificationofGoodsItemMapper goodsItemMapper ;
	
	@Autowired
	private ImginfoMapper imgInfoMapper ;
	
	@Autowired
	private ClassificationofgoodsMapper classifMapper ;
	
	@Autowired
	private ClassificationTypeMapper cTypeMapper ;
	
	@Autowired
	private ClassificationofMapper cTionMapp ;

	/**
	 * 全查询
	 * 
	 */
	@Override
	public LaYuiPage allGoodsMessage(GoodsVO goodsVO) {
		Page<Object> page = PageHelper.startPage(goodsVO.getPage(), goodsVO.getLimit()) ;
		List<ClassificationofGoodsItem> list = this.goodsItemMapper.allGoodsMessage(
															  goodsVO.getCname()
															, 1
															, goodsVO.getBrand()
															, goodsVO.getPriceOne()
															, goodsVO.getPriceTow()) ;
		long total = page.getTotal() ;
		return new LaYuiPage(list , total) ;
	}

	/**
	 * 
	 * 最大id
	 * 
	 */
	@Override
	public Integer findMaxId() {
		Integer maxId = this.goodsItemMapper.findMaxId() ;
		return maxId;
	}

	/**
	 * 
	 * 把图片路径上传到数据库中
	 * 
	 */
	@Override
	@Transactional
	public void uploadImgPath(String path, Integer cid) {
		Imginfo record = new Imginfo() ;
		record.setCid(cid);
		record.setCreatetime(new Date());
		record.setImgpath(path);
		this.imgInfoMapper.insert(record) ;
		
	}

	/**
	 * 删除图片
	 * 
	 * 
	 */
	@Override
	@Transactional
	public void deleteImgByCid(Integer cid) {
		ImginfoExample example = new ImginfoExample() ;
		example.createCriteria().andCidEqualTo(cid) ;
		this.imgInfoMapper.deleteByExample(example) ;
		
	}

	//查找最顶级的商品类型
	@Override
	public List<Classificationofgoods> findAllGoodsType() {
		List<Classificationofgoods> list = this.classifMapper.selectByExample(null) ;
		return list;
	}

	//查找最低级的商品类型
	@Override
	public List<ClassificationType> findAllClassType(Integer cid) {
		ClassificationTypeExample example = new ClassificationTypeExample() ;
		example.createCriteria().andCtionfIdEqualTo(cid) ;
		List<ClassificationType> list = this.cTypeMapper.selectByExample(example) ;
		return list;
	}

	//查找地二级商品类型
	@Override
	public List<Classificationof> findAll(Integer cid) {
		ClassificationofExample example = new ClassificationofExample() ;
		example.createCriteria().andCidEqualTo(cid) ;
		List<Classificationof> list = this.cTionMapp.selectByExample(example) ;
		return list;
	}

	//根据id删除表中的商品
	@Override
	@Transactional
	public void removeShopById(Integer id) {
		this.goodsItemMapper.updateById(0 , id) ;
		
	}

	/**
	 * 修改
	 * 1.商品名称
	 * 2.商品价格
	 * 3.商品品牌
	 * 4.商品库存
	 * 5.商品描述
	 * 
	 * 
	 */
	@Override
	@Transactional
	public void updateShop(GoodsVO goodsVO) {
		this.goodsItemMapper.updateShopById( goodsVO.getCname(),goodsVO.getInventory() ,goodsVO.getPrice() 
												, goodsVO.getBrand() , new Date(), goodsVO.getDetail(),  goodsVO.getId(), goodsVO.getCid() ) ;
		
	}

	@Override
	@Transactional
	public void removeImg(Integer cId) {
		this.imgInfoMapper.removeByCid(cId);
		
	}

	
	@Override
	@Transactional
	public void addShop(ClassificationofGoodsItem goodsItem) {
		goodsItem.setCreateTime(new Date());
		goodsItem.setShowstatus(1);
		this.goodsItemMapper.insert(goodsItem) ;
		
	}

	@Override
	public LaYuiPage allGoodsMessageApi(GoodsEntity goodsVO) {
		Page<Object> page = PageHelper.startPage(goodsVO.getPage(), goodsVO.getLimit()) ;
		List<ClassificationofGoodsItem> list = this.goodsItemMapper.allGoodsMessage(
															  goodsVO.getCname()
															, 1
															, goodsVO.getBrand()
															, goodsVO.getPriceOne()
															, goodsVO.getPriceTow()) ;
		long total = page.getTotal() ;
		return new LaYuiPage(list , total) ;
	}
	

}
