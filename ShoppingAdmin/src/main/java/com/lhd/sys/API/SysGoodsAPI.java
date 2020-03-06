package com.lhd.sys.API;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.API.apiEntity.GoodsEntity;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@Api(value="商品管理功能" , description="商品管理功能")
@RequestMapping("/sysGoods")
public class SysGoodsAPI {
	
	@Autowired
	private GoodsService goodsService ;
	
	@Autowired
	private MiaoShaService miaoShaService ;
	
	
	@PostMapping(value="/allGoodsInfo")
	@ApiOperation(value="获取所有商品信息")
	@ResponseBody
	public LaYuiPage allGoodsInfo ( @ModelAttribute @Valid @RequestBody GoodsEntity goodsVO ) {
		LaYuiPage page = this.goodsService.allGoodsMessageApi(goodsVO) ;
		return page ;
	}
	
	@GetMapping(value="/goodsInfo")
	@ApiOperation(value="跳转到商品页面")
	public String goodsInfo () {
		return "goods/goodsMessage" ;
	}
	
	/**
	 * 商品类型1
	 * 
	 */
	@GetMapping(value="/shopTypeOne")
	@ApiOperation(value="商品第一级分类")
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
	@GetMapping(value="/shopTypeTow")
	@ApiOperation(value="商品第二级分类")
	@ResponseBody
	public DataGridView shopTypeTow ( @ApiParam("商品上级id") @RequestParam("cid") Integer cid ) {
		List<Classificationof> list = this.goodsService.findAll(cid) ;
		return new DataGridView(list) ;
	}
	
	@GetMapping(value="/shopTypeThree")
	@ApiOperation(value="商品第三极分类")
	@ResponseBody
	public DataGridView shopTypeThree ( @ApiParam("商品上级id") @RequestParam("cid") Integer cid ) {
		List<ClassificationType> list = this.goodsService.findAllClassType(cid) ;
		return new DataGridView(list) ;
	}
	
	
	/**
	 * 
	 * 秒杀商品添加
	 * 
	 */
	@PostMapping(value="/addMiaoShaShop")
	@ApiOperation(value="添加秒杀商品")
	@ResponseBody
	public ResultObject addMiaoShaShop ( @ModelAttribute @Valid @RequestBody MiaoSha miaoSha ) {
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
	@PostMapping(value="/removeShopById")
	@ApiOperation(value="根据id删除商品")
	@ResponseBody
	public ResultObject removeShopById ( @ApiParam("商品id") @RequestParam("id") Integer id ) {
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
	@PostMapping(value="/updateShop")
	@ApiOperation(value="修改商品信息")
	@ResponseBody
	public ResultObject updateShop ( @ModelAttribute @Valid @RequestBody GoodsVO goodsVO ) {
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
	@PostMapping(value="/addShop")
	@ApiOperation(value="添加商品")
	@ResponseBody
	public ResultObject addShop ( @ModelAttribute @Valid @RequestBody ClassificationofGoodsItem goodsItem ) {
		try {
			this.goodsService.addShop(goodsItem) ;
			return ResultObject.ADD_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return ResultObject.ADD_ERROR ;
		}
	}
	

}
