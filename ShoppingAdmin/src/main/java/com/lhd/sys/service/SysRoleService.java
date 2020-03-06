package com.lhd.sys.service;

import java.util.List;

import com.lhd.sys.entity.SysPermission;
import com.lhd.sys.entity.SysRolePermission;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.SysRoleVO;

public interface SysRoleService {
	
	LaYuiPage findAllSysRole( SysRoleVO sysRoleVO) ;
	
	 List<SysPermission> findAllMenuAndRole () ;
	 
	 //根据rolid查询
	 List<SysRolePermission> findByRoleId ( Integer rolid) ;
	 
	 //根据rolid查询菜单和权限
	 List<SysPermission> findByIds ( List<Integer> ids ) ;
	 
	 void insertSysRolePer ( Integer rid , Integer [] ids ) ;
	 
	 void removeByRid ( Integer rid ) ;
	
	
	void removeSysRoleById( Integer id) ;
	
	void updateSysRoleById( SysRoleVO sysRoleVO) ;
	
	void addSysRole ( SysRoleVO roleVO) ;

}
