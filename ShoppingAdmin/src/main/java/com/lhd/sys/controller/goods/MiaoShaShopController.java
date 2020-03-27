package com.lhd.sys.controller.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.common.ResultObject;
import com.lhd.sys.entity.MiaoSha;
import com.lhd.sys.service.MiaoShaService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.MiaoShaShopVO;

@Controller
@RequestMapping("/miaoSha")
public class MiaoShaShopController {
	
	@Autowired
	private MiaoShaService miaoShaService ;
	
	
	@RequestMapping("/miaoShaShopInfo")
	public String miaoShaShopInfo () {
		return "miaoShaShop/maioShaShopInfo.html" ;
	}
	
	/**
	 * 
	 * 全查询秒杀商品
	 * 
	 */
	@RequestMapping("/allMiaoShaShopInfos")
	@ResponseBody
	public LaYuiPage allMiaoShaShopInfos ( MiaoShaShopVO miaoShaShopVO ) {
		LaYuiPage page = this.miaoShaService.findAllMiaoShaShops(miaoShaShopVO) ;
		return page ;
	}
	
	/**
	 * 删除秒杀商品
	 * 
	 * 
	 */
	@RequestMapping("/removeMiaoShaShop")
	@ResponseBody
	public ResultObject removeMiaoShaShop ( Integer id ) {
		try {
			this.miaoShaService.deleteMiaoShaShop(id) ;
			this.miaoShaService.removeImg(id) ;
			return ResultObject.DELETE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.DELETE_ERROR ;
		}
	}
	
	/**
	 * 
	 * 删除图片
	 * 
	 */
	@RequestMapping("/removeImgs")
	@ResponseBody
	public ResultObject removeImgs ( Integer id ) {
		if ( null == id ) {
			return ResultObject.OPERATION_SECCESS ;
		} else {
			try {
				this.miaoShaService.removeImg(id) ;
				return ResultObject.OPERATION_SECCESS ;
			} catch (Exception e) {
				e.printStackTrace() ;
				return ResultObject.OPERATION_ERROR ;
			}
		}
		
	}
	
	/**
	 * 
	 * 修改商品信息
	 * 
	 */
	@RequestMapping("/updateMiaoShaInfo")
	@ResponseBody
	public ResultObject updateMiaoShaInfo ( MiaoSha miaoSha ) {
		try {
			this.miaoShaService.updateMiaoShaInfo(miaoSha);
			return ResultObject.UPDATE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.UPDATE_ERROR ;
		}
	}
	
	/**
	 * 添加图片的cid
	 * 
	 */
	@RequestMapping("/addImgCid")
	@ResponseBody
	public ResultObject addImgCid ( Integer id ) {
		Integer maxIdOfMiaoShaShop = null ;
		 if ( null == id ) {
			 maxIdOfMiaoShaShop = this.miaoShaService.maxIdOfMiaoShaShop() ;
		 } else {
			 maxIdOfMiaoShaShop = id ;
		 }
		//查找当前的最大id
		try {
			this.miaoShaService.addImgCid(maxIdOfMiaoShaShop) ;
			return ResultObject.ADD_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return ResultObject.ADD_ERROR ;
		}
	}

}
