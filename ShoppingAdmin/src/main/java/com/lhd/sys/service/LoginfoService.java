package com.lhd.sys.service;

import com.lhd.sys.entity.SysLogInfo;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.LoginfoVO;
import com.lhd.sys.vo.SysLogVO;

public interface LoginfoService {
	
	LaYuiPage findAllOrderList ( int page , int limit ) ;
	
	LaYuiPage findAllLoginfo ( LoginfoVO loginfoVO ) ;
	
	LaYuiPage findAllSysLog ( SysLogVO logVO ) ;
	
	//删除
	void removeSysLoginfo ( Integer id ) ;
	
	void removeSysLog ( Integer id ) ;
	
	
	//批量删除
	void removeSysLoginfos ( Integer [] ids ) ;
	
	void removeSysLogs ( Integer [] ids ) ;
	
	//登录日志记录
	void loginMessage (SysLogInfo info) ;
	
	void SysLogin ( SysLogVO logVO ) ;

}
