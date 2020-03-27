package com.lhd.sys.service;

import com.lhd.sys.entity.SysLeavebill;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.SysLeaveBillVO;

public interface SysLeaveBillService {
	
	//全查询
	LaYuiPage allLeaveBill ( SysLeaveBillVO vo ) ;
	
	//根据ID查询请假单信息
	SysLeavebill findByIdLeaveBillManager ( Integer id ) ;
	
	//添加请假单
	void addLeaveBill ( SysLeaveBillVO vo ) ;
	
	//修改请假单
	void updateLeaveBill ( SysLeaveBillVO vo ) ;
	
	void updateLeave ( SysLeavebill leavebill ) ;
	
	//删除请假单
	void deleteLeaveBill ( Integer id ) ;
	
	//批量删除请假单
	void batechDelete ( Integer [] ids) ;
	
	SysLeavebill findById ( Integer id ) ;

}
