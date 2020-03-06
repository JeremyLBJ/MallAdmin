package com.lhd.sys.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhd.sys.dao.MiaoShaMapper;
import com.lhd.sys.dao.MiaoshaOrderImgMapper;
import com.lhd.sys.entity.MiaoSha;
import com.lhd.sys.entity.MiaoshaOrderImg;
import com.lhd.sys.entity.MiaoshaOrderImgExample;
import com.lhd.sys.service.MiaoShaService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.MiaoShaShopVO;

@Service("MiaoShaService")
public class MiaoShaServiceImpl implements MiaoShaService{
	
	@Autowired
	private MiaoShaMapper miaoShaMapper ;
	
	@Autowired
	private MiaoshaOrderImgMapper miaoShaImgMapper ;

	@Override
	@Transactional
	public void addMiaoShaShop(MiaoSha miaoSha) {
		miaoSha.setCreatetime(new Date());
		this.miaoShaMapper.insert(miaoSha) ;
	}

	/**
	 * 
	 * 全查询
	 */
	@Override
	public LaYuiPage findAllMiaoShaShops(MiaoShaShopVO miaoShaShopVO) {
		Page<Object> page = PageHelper.startPage(miaoShaShopVO.getPage(), miaoShaShopVO.getLimit()) ;
		List<MiaoSha> list = this.miaoShaMapper.allMiaoShaMessage(miaoShaShopVO.getCname(), miaoShaShopVO.getCofname(),
																miaoShaShopVO.getPriceOne(), miaoShaShopVO.getPriceTow()) ;
		long total = page.getTotal() ;
		return new LaYuiPage(list, total) ;
	}

	@Override
	public Integer maxIdOfMiaoShaShop() {
		Integer maxId = this.miaoShaMapper.findMaxId() ;
		return maxId;
	}

	@Override
	@Transactional
	public void deleteMiaoShaShop(Integer id) {
		this.miaoShaMapper.deleteByPrimaryKey(id) ;
		
	}

	@Override
	@Transactional
	public void removeImg(Integer cid) {
		MiaoshaOrderImgExample example = new MiaoshaOrderImgExample() ;
		example.createCriteria().andCidEqualTo(cid) ;
		this.miaoShaImgMapper.deleteByExample(example) ;
		
	}

	@Override
	@Transactional
	public void addImg(String path, Integer cid) {
		MiaoshaOrderImg record = new MiaoshaOrderImg() ;
		record.setCid(cid) ;
		record.setPath(path) ;
		record.setCreatetime(new Date()) ;
		this.miaoShaImgMapper.insert(record) ;
		
	}

	//修改
	@Override
	@Transactional
	public void updateMiaoShaInfo(MiaoSha miaoSha) {
		this.miaoShaMapper.updateMiaoShaShopById( miaoSha.getCname() ,miaoSha.getNum() 
												,miaoSha.getPrice() , miaoSha.getCofname()
												, new Date()
												,miaoSha.getCofid() ,miaoSha.getContent() 
												,miaoSha.getId() ) ;
		
	}

	@Override
	@Transactional
	public void addImgCid(Integer cid) {
		this.miaoShaImgMapper.addImgCid(cid) ;
		
	}

}
