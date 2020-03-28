package com.lhd.sys.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lhd.sys.common.FileUploadUtils;
import com.lhd.sys.service.GoodsService;
import com.lhd.sys.service.MiaoShaService;

import cn.hutool.core.date.DateUtil;

@RequestMapping("/upload")
@Controller
public class FileController {
	
	@Autowired
	private GoodsService goodsService ;
	
	@Autowired
	private MiaoShaService miaoShaService ;
	
	
	@RequestMapping("/uploadFile")
	@ResponseBody
	public Map<String,Object> uploadFile ( Integer id , MultipartFile file  ){
		Map<String,Object> map = new HashMap<String, Object>() ;
		//得到文件名
		String string = file.getOriginalFilename() ;
		//根据文件名生成新的文件名
		String fileName = FileUploadUtils.createNewFileName(string) ;
		//得到当前日期字符串
		String dirName = DateUtil.format(new Date(), "yyyy-MM-dd") ;
		//构造文件夹
		File dirFile = new File(FileUploadUtils.UPLOAD_PATH, dirName) ;
		//判断文件是否存在
		if (! dirFile.exists()) {
			dirFile.mkdirs() ; // 创建文件夹
		}
		//构造文件对象
		File files = new File(dirFile , fileName) ;
		//把图片写入文件夹中
		Integer cid = 0 ;
		try {
			file.transferTo(files);
			String path = files.getAbsolutePath() ;
			System.out.println(fileName);
			System.out.println(dirFile);
			if ( null == id ) {
				 cid = this.goodsService.findMaxId() + 1 ;
			} else {
				//现根据id删除图片的路径 在添加进去即可 
				cid = id ;
				try {
					this.goodsService.removeImg(cid);
				} catch (Exception e) {
					throw new RuntimeException("删除商品图片失败") ;
				}
			}
			
			try {
				//写入数据库
				this.goodsService.uploadImgPath(path, cid );
			} catch (Exception e) {
				throw new RuntimeException("文件存入数据库发送错误") ;
			}
			map.put("value", "图片上传成功") ;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			map.put("value", "图片上传失败") ;
		} 
		return map ;
	}
	
	
	/**
	 * 清除按钮功能
	 * 用户第一次图片上传时 自动上传  此时已经上传到数据库
	 * 清除功能需要把上传的图片从数据库删除 
	 * 
	 */
	@RequestMapping("/cleanImg")
	@ResponseBody
	public Map<String, Object> cleanImg ( Integer id ) {
		Map<String, Object> map = new HashMap<>() ;
		Integer maxId = null ;
		if ( null == id ) {
			maxId = this.goodsService.findMaxId() + 1 ;
			
		} else {
			maxId = id ;
		}
		try {
			this.goodsService.deleteImgByCid(maxId);
			map.put("value", "删除成功") ;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("value", "删除失败") ;
		}
		return map ; 
	}
	
	
	/**
	 * 
	 * 秒杀商品图片
	 * 
	 * @param id
	 * @param file
	 * @return
	 */

	@RequestMapping("/miaoShaShopUploadFile")
	@ResponseBody
	public Map<String,Object> miaoShaShopUploadFile ( Integer id , MultipartFile file  ){
		Map<String,Object> map = new HashMap<String, Object>() ;
		//得到文件名
		String string = file.getOriginalFilename() ;
		//根据文件名生成新的文件名
		String fileName = FileUploadUtils.createNewFileName(string) ;
		//得到当前日期字符串
		String dirName = DateUtil.format(new Date(), "yyyy-MM-dd") ;
		//构造文件夹
		File dirFile = new File(FileUploadUtils.UPLOAD_PATH, dirName) ;
		//判断文件是否存在
		if (! dirFile.exists()) {
			dirFile.mkdirs() ; // 创建文件夹
		}
		//构造文件对象
		File files = new File(dirFile , fileName) ;
		//把图片写入文件夹中
		//Integer cid = 0 ;
		try {
			file.transferTo(files);
			String path = files.getAbsolutePath() ;
			/*if ( null == id ) {
				 cid = this.miaoShaService.maxIdOfMiaoShaShop() + 1 ;
			} else {
				//现根据id删除图片的路径 在添加进去即可 
				cid = id ;
			}*/
			
			try {
				//写入数据库
				this.miaoShaService.addImg(path, 0 );
			} catch (Exception e) {
				throw new RuntimeException("文件存入数据库发送错误") ;
			}
			map.put("value", "图片上传成功") ;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			map.put("value", "图片上传失败") ;
		} 
		return map ;
	}
	
	@RequestMapping("/cleanImgs")
	@ResponseBody
	public Map<String, Object> cleanImgs ( Integer id ) {
		Map<String, Object> map = new HashMap<>() ;
		Integer maxId = null ;
		if ( null == id ) {
			maxId = this.miaoShaService.maxIdOfMiaoShaShop() + 1 ;
			
		} else {
			maxId = id ;
		}
		try {
			this.miaoShaService.removeImg(maxId) ;
			map.put("value", "删除成功") ;
		} catch (Exception e) {
			e.printStackTrace() ;
			map.put("value", "删除失败") ;
		}
		return map ; 
	}
	

}
