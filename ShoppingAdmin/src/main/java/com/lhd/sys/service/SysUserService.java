package com.lhd.sys.service;

import java.util.List;

import com.lhd.sys.entity.SysRole;
import com.lhd.sys.entity.SysUser;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.sysUserVO;

public interface SysUserService {
	
	//管理员注册
	void SysUserRegist ( SysUser sysUser ) ;
	
	
	//管理员登录
	//List<SysUser> SysUserToLogin ( SysUser sysUser ) ;
	
	SysUser userLogin ( String name , String pwd) ;
	
	//全查询
	LaYuiPage findAllSysUserInfo ( sysUserVO sysUserVO ) ;
	
	//删除管理员
	void removeAdmin ( sysUserVO sysUserVO ) ;
	
	//修改
	void updateSysUserInfo ( sysUserVO sysUserVO ) ;
	
	//删除时 根据id查询 是否有他管理的部门 如果有就不能删除
	List<SysUser> removeById ( Integer id ) ;
	
	//查询所有数据  按照ordermun排序
	List<SysUser> findAllUserInfo () ;
	
	
	//根据部门编号 查找领导
	List<SysUser> findByDeptidMgr ( Integer deptid ) ;
	
	//添加用户
	void addSysUseer ( sysUserVO sysUserVO ) ;
	
	
	
	SysUser findLearderById ( Integer id ) ;
	
	
	//查找可用的角色
	List<SysRole> initRoleByUserId ( Integer id ) ;
	
	//根据用户id查找sys_user_role中的
	List<Integer> findIds ( Integer id ) ;
	
	//查询pid
	List<Integer> findPidByRid ( List<Integer> rid ) ;
	
	//根据用户 uid  和权限 rid  添加
	void saveSysUser ( Integer uid , Integer [] ids ) ;

}
