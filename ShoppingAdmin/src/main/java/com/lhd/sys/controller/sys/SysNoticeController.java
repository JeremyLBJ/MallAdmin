package com.lhd.sys.controller.sys;
/**
 * 系统公告
 * 
 * 
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhd.sys.common.ResultObject;
import com.lhd.sys.service.SysNoticeService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.NoticeVO;

@RequestMapping("/sys/Notice")
@RestController
public class SysNoticeController {
	
	@Autowired
	private SysNoticeService sysNoticeService ;
	
	/**
	 * 全查询
	 * 
	 */
	@RequestMapping("/findAllNoticeInfo")
	public Object findAllNoticeInfo ( NoticeVO noticeVO ) {
		LaYuiPage page = sysNoticeService.findAllSysNotice(noticeVO) ;
		
		return page ;
	}
	
	/**
	 * 
	 * 批量删除
	 * 
	 */
	@RequestMapping("/batechDeleteNotice")
	public Object batechDeleteNotice ( String ids ) {
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
	@RequestMapping("/deleteNotice")
	public Object deleteNotice ( Integer id ) {
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
	@RequestMapping("/updateNotice")
	public Object updateNotice ( NoticeVO noticeVO ) {
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
	@RequestMapping("/addNotice")
	public Object addNotice ( NoticeVO noticeVO ) {
		 try {
			 sysNoticeService.addNoticeByVo(noticeVO);
			  return ResultObject.ADD_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			 return ResultObject.ADD_ERROR ;
		}
	}

}
