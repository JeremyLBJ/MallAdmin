package com.lhd.sys.controller.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.common.DataGridView;
import com.lhd.sys.common.ResultObject;
import com.lhd.sys.entity.ClassificationType;
import com.lhd.sys.entity.Classificationof;
import com.lhd.sys.entity.ClassificationofGoodsItem;
import com.lhd.sys.entity.Classificationofgoods;
import com.lhd.sys.entity.MiaoSha;
import com.lhd.sys.service.GoodsService;
import com.lhd.sys.service.MiaoShaService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.GoodsVO;

/**
 * 
 * 商品操作
 * 
 * @author ASUS
 *
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	
	@Autowired
	private GoodsService goodsService ;
	
	@Autowired
	private MiaoShaService miaoShaService ;
	
	/**
	 * 
	 * 查询条件
	 * 1.商品名称
	 * 2.商品品牌
	 * 3.商品价格
	 * 
	 * 
	 */
	@RequestMapping("/allGoodsInfo")
	@ResponseBody
	public LaYuiPage allGoodsInfo ( GoodsVO goodsVO ) {
		LaYuiPage page = this.goodsService.allGoodsMessage(goodsVO) ;
		return page ;
	}
	
	@RequestMapping("/adduser")
	public String addUser () {
		return "news/newsAdd.html" ;
	}
	
	
	
	
	
	@RequestMapping("/goodsInfo")
	public String goodsInfo () {
		return "goods/goodsMessage" ;
	}
	
	/**
	 * 商品类型1
	 * 
	 */
	@RequestMapping("/shopTypeOne")
	@ResponseBody
	public DataGridView shopTypeOne () {
		List<Classificationofgoods> list = this.goodsService.findAllGoodsType() ;
		return new DataGridView(list) ;
	}
	
	/**
	 * 
	 * 根据商品类型1的id进行级联查询
	 * 
	 */
	@RequestMapping("/shopTypeTow")
	@ResponseBody
	public DataGridView shopTypeTow ( Integer cid ) {
		List<Classificationof> list = this.goodsService.findAll(cid) ;
		return new DataGridView(list) ;
	}
	
	@RequestMapping("/shopTypeThree")
	@ResponseBody
	public DataGridView shopTypeThree ( Integer cid ) {
		List<ClassificationType> list = this.goodsService.findAllClassType(cid) ;
		return new DataGridView(list) ;
	}
	
	/**
	 * 
	 * 秒杀商品添加
	 * 
	 */
	@RequestMapping("/addMiaoShaShop")
	@ResponseBody
	public ResultObject addMiaoShaShop ( MiaoSha miaoSha ) {
		try {
			this.miaoShaService.addMiaoShaShop(miaoSha);
			return ResultObject.ADD_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.ADD_ERROR ;
		}
	}
	
	/**
	 * 
	 * 根据id删除商品
	 * 
	 */
	@RequestMapping("/removeShopById")
	@ResponseBody
	public ResultObject removeShopById ( Integer id ) {
		try {
			this.goodsService.removeShopById(id) ;
			return ResultObject.DELETE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return ResultObject.DELETE_ERROR ;
		}
	}
	
	
	/**
	 * 商品修改
	 * 
	 */
	@RequestMapping("/updateShop")
	@ResponseBody
	public ResultObject updateShop ( GoodsVO goodsVO ) {
		try {
			this.goodsService.updateShop(goodsVO);
			return ResultObject.UPDATE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.UPDATE_ERROR ;
		}
	}
	
	/**
	 * 
	 * 
	 * 添加商品
	 * 
	 */
	@RequestMapping("/addShop")
	@ResponseBody
	public ResultObject addShop ( ClassificationofGoodsItem goodsItem ) {
		try {
			this.goodsService.addShop(goodsItem) ;
			return ResultObject.ADD_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return ResultObject.ADD_ERROR ;
		}
	}
}
