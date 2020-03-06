package com.lhd.sys.service;

import com.lhd.sys.entity.SysLogInfo;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.LoginfoVO;

public interface LoginfoService {
	
	LaYuiPage findAllOrderList ( int page , int limit ) ;
	
	LaYuiPage findAllLoginfo ( LoginfoVO loginfoVO ) ;
	
	//删除
	void removeSysLoginfo ( Integer id ) ;
	
	
	//批量删除
	void removeSysLoginfos ( Integer [] ids ) ;
	
	//登录日志记录
	void loginMessage (SysLogInfo info) ;

}
