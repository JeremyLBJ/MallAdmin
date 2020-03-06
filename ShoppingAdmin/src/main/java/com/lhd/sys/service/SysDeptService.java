package com.lhd.sys.service;

import java.util.List;

import com.lhd.sys.entity.SysDept;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.DeptVO;

public interface SysDeptService {
	
	List<SysDept> deptTree () ;
	
	LaYuiPage findAllDeptInfo ( DeptVO deptVO ) ;
	
	void addSysDept (DeptVO deptVO) ;
	
	Integer maxorderNum () ;
	
	List<SysDept> allDept () ;
	
	void deleteDeptInfo ( DeptVO deptVO ) ;
	
	void updateById ( DeptVO deptVO ) ;
	
	List<SysDept> findChildNode ( DeptVO deptVO) ;

}
