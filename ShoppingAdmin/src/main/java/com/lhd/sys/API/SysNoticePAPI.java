package com.lhd.sys.API;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhd.sys.common.ResultObject;
import com.lhd.sys.service.SysNoticeService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.NoticeVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value="系统公告管理" , description="系统公告管理")
@RequestMapping("/SysNoticePAPI")
public class SysNoticePAPI {
	
	@Autowired
	private SysNoticeService sysNoticeService ;
	
	/**
	 * 全查询
	 * 
	 */
	@GetMapping("/findAllNoticeInfo")
	public Object findAllNoticeInfo ( @ModelAttribute @Valid @RequestBody NoticeVO noticeVO ) {
		LaYuiPage page = sysNoticeService.findAllSysNotice(noticeVO) ;
		
		return page ;
	}
	
	/**
	 * 
	 * 批量删除
	 * 
	 */
	@PostMapping("/batechDeleteNotice")
	public Object batechDeleteNotice ( @ApiParam("批量删除") @RequestParam("ids") String ids ) {
		String[] split = ids.split(",") ;
		Integer [] id = new Integer [split.length]  ;
		
		  for(int i=0;i<split.length;i++){
			  
		        id[i] = Integer.parseInt(split[i]);
		        
		    }
		  try {
			  sysNoticeService.removeNotice(id) ;
			return ResultObject.DELETE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.DELETE_ERROR ;
		}
	}
	
	/**
	 * 
	 * 删除
	 * 
	 */
	@PostMapping("/deleteNotice")
	public Object deleteNotice ( @ApiParam("公告id") @RequestParam("id") Integer id ) {
		  try {
			  sysNoticeService.removeNoticeById(id) ;
			  return ResultObject.DELETE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			 return ResultObject.DELETE_ERROR ;
		}
	}
	
	
	/**
	 * 
	 * 修改
	 * 
	 */
	@PostMapping("/updateNotice")
	public Object updateNotice ( @ModelAttribute @Valid @RequestBody NoticeVO noticeVO ) {
		try {
			 sysNoticeService.updateNoticeById(noticeVO);
			  return ResultObject.UPDATE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			 return ResultObject.UPDATE_ERROR ;
		}
	}
	
	/**
	 * 
	 * 添加
	 * 
	 */
	@PostMapping("/addNotice")
	public Object addNotice ( @ModelAttribute @Valid @RequestBody NoticeVO noticeVO ) {
		 try {
			 sysNoticeService.addNoticeByVo(noticeVO);
			  return ResultObject.ADD_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			 return ResultObject.ADD_ERROR ;
		}
	}

}
