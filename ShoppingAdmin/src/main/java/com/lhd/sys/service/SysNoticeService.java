package com.lhd.sys.service;

import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.NoticeVO;

public interface SysNoticeService {
	
	//全查询 (模糊查询等)
	LaYuiPage findAllSysNotice ( NoticeVO noticeVO ) ;
	
	//批量删除
	void removeNotice ( Integer [] ids ) ;
	
	//删除
	void removeNoticeById ( Integer  id ) ;
	
	//修改
	void updateNoticeById (NoticeVO noticeVO ) ;
	
	//添加
	void addNoticeByVo ( NoticeVO noticeVO ) ;

}
