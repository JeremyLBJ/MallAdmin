package com.lhd.sys.service;

import java.util.List;

import com.lhd.sys.entity.SysPermission;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.PermissionVO;

public interface SysMenuService {
	
	List<SysPermission> findAllMenuBysysPermission ( SysPermission permission ) ;
	
	
	//查找所有可用菜单
	LaYuiPage findAllmenuInfo ( PermissionVO permissionVO ) ;
	
	LaYuiPage allFindSysPermissioninfo ( PermissionVO permissionVO ) ;
	
	List<SysPermission> findChildNode( PermissionVO permissionVO) ;
	
	void updateById (PermissionVO permissionVO) ;
	
	void deleteMenuInfo (PermissionVO permissionVO) ; 
	
	List<SysPermission> allmenu ( PermissionVO  permissionVO ) ;
	
	
	void addSysMenu (PermissionVO permissionVO) ;
	
	void addSysPermission ( PermissionVO permissionVO ) ;
	
	//根据pid 查询sys_permission表中的id
	List<SysPermission> findByPidOfPermission ( SysPermission permission , List<Integer> pids) ;

}
