package com.lhd.sys.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhd.sys.common.Constast;
import com.lhd.sys.dao.SysDeptMapper;
import com.lhd.sys.dao.SysRoleMapper;
import com.lhd.sys.dao.SysRolePermissionMapper;
import com.lhd.sys.dao.SysRoleUserMapper;
import com.lhd.sys.dao.SysUserMapper;
import com.lhd.sys.entity.SysDept;
import com.lhd.sys.entity.SysDeptExample;
import com.lhd.sys.entity.SysRole;
import com.lhd.sys.entity.SysRoleExample;
import com.lhd.sys.entity.SysRoleUser;
import com.lhd.sys.entity.SysRoleUserExample;
import com.lhd.sys.entity.SysUser;
import com.lhd.sys.entity.SysUserExample;
import com.lhd.sys.service.SysUserService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.untils.MD5AndSalt;
import com.lhd.sys.vo.sysUserVO;
@Service("SysUserService")
public class SysUserServiceImpl implements SysUserService{
	
	@Autowired
	private SysUserMapper sysUserMapper ;
	
	@Autowired
	private SysDeptMapper sysDeptMapper ;
	
	@Autowired
	private SysRoleUserMapper sysRoleUserMapper ;
	
	@Autowired
	private SysRoleMapper sysRoleMapper ;
	
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper ;
	
	
	//注册
	@Transactional
	public void SysUserRegist ( SysUser sysUser ) {
		 sysUserMapper.insert(sysUser) ;
	}
	
	
	
	//登录
	public List<SysUser> SysUserToLogin ( SysUser sysUser ) {
		SysUserExample example = new SysUserExample() ;
		example.createCriteria().andNameEqualTo(sysUser.getName())
		              .andPwdEqualTo(sysUser.getPwd()) ;
		List<SysUser> list = sysUserMapper.selectByExample(example) ;
		return list ; 
	}
	


	/**
	 * 
	 * 全查询
	 * 
	 */
	@Override
	public LaYuiPage findAllSysUserInfo(sysUserVO sysUserVO) {
		Page<Object> page = PageHelper.startPage(sysUserVO.getPage(), sysUserVO.getLimit()) ;
		List<SysUser> list = this.sysUserMapper.allFindSysUserinfo(sysUserVO.getRemark(), sysUserVO.getName(),0 , sysUserVO.getDeptid(), 1) ;
		for (SysUser s : list) {
			Integer deptid = s.getDeptid() ;
			if (null != deptid) {
				//根据deptid查询部门表  并且设置 部门名称
				SysDeptExample deptExample = new SysDeptExample() ;
				deptExample.createCriteria().andAvailableEqualTo("1").andPidEqualTo(deptid) ;
				List<SysDept> selectByExample = this.sysDeptMapper.selectByExample(deptExample) ;
				s.setDeptname(selectByExample.get(0).getTitle());
			}
			Integer mgr = s.getMgr() ;
			if (0 != mgr) {
				//自关联查询  sys_user 表  并设置部门领导的姓名
				SysUserExample example = new SysUserExample() ;
				example.createCriteria().andIdEqualTo(mgr) ;
				List<SysUser> list2 = this.sysUserMapper.selectByExample(example) ;
				s.setLeadername(list2.get(0).getName());
			}
			
		}
		long total = page.getTotal() ;
		return new LaYuiPage ( list , total );
	}


	/**
	 * 
	 * 删除管理员
	 * 
	 */
	@Override
	@Transactional
	public void removeAdmin(sysUserVO sysUserVO) {
		sysUserVO.setAvailable(0);
		SysRoleUserExample example = new SysRoleUserExample() ;
		example.createCriteria().andUidEqualTo(sysUserVO.getId()) ;
		sysRoleUserMapper.deleteByExample(example) ;
		this.sysUserMapper.removeSysUser(sysUserVO.getAvailable(), sysUserVO.getId()) ;
		
	}



	/**
	 * 
	 * 修改
	 * 
	 */
	@Override
	@Transactional
	public void updateSysUserInfo(sysUserVO sysUserVO) {
		sysUserVO.setHiredate(new Date());
		sysUserVO.setPwd(MD5AndSalt.MD5(sysUserVO.getPwd()));
		sysUserVO.setSalt(MD5AndSalt.generate(sysUserVO.getPwd()));
		this.sysUserMapper.updateByPrimaryKeySelective(sysUserVO);
	}


	/**
	 * 
	 * 移除管理员
	 * 
	 */
	@Override
	public List<SysUser> removeById(Integer id) {
		SysUserExample example = new SysUserExample() ;
		example.createCriteria().andMgrEqualTo(id) ;
		List<SysUser> list = this.sysUserMapper.selectByExample(example) ;
		return list ;
	}



	@Override
	public List<SysUser> findAllUserInfo() {
		SysUserExample example = new SysUserExample() ;
		example.setOrderByClause("ordernum desc");
		List<SysUser> list = this.sysUserMapper.selectByExample(example) ;
		return list;
	}



	/**
	 * 
	 * 根据部门号查找领导信息
	 * 
	 */
	@Override
	public List<SysUser> findByDeptidMgr(Integer deptid) {
		SysUserExample example = new SysUserExample() ;
		example.createCriteria().andDeptidEqualTo(deptid).andAvailableEqualTo(Constast.AVAILABLE_TRUE)
								.andTypeEqualTo(Constast.USER_TYPE_NORMAL);
		List<SysUser> list = this.sysUserMapper.selectByExample(example) ;
		return list;
	}



	@Override
	@Transactional
	public void addSysUseer(sysUserVO sysUserVO) {
		sysUserVO.setHiredate(new Date());
		sysUserVO.setPwd(MD5AndSalt.MD5(sysUserVO.getPwd()));
		sysUserVO.setSalt(MD5AndSalt.generate(sysUserVO.getPwd()));
		sysUserVO.setType(1);
		sysUserVO.setLoginname(sysUserVO.getAddress());
		this.sysUserMapper.insert(sysUserVO) ;
		
	}



	@Override
	public SysUser findLearderById(Integer id) {
		SysUser user = this.sysUserMapper.selectByPrimaryKey(id) ;
		return user;
	}



	/**
	 * 
	 * 查找可用的角色
	 * 
	 */
	@Override
	public List<SysRole> initRoleByUserId(Integer id) {
		SysRoleExample example = new SysRoleExample() ;
		example.createCriteria().andAvailableEqualTo("1") ;
		List<SysRole> list = this.sysRoleMapper.selectByExample(example) ;
		return list;
	}



	@Override
	public List<Integer> findIds(Integer id) {
		SysRoleUserExample example = new SysRoleUserExample() ;
		example.createCriteria().andUidEqualTo(id) ;
		List<SysRoleUser> list = sysRoleUserMapper.selectByExample(example) ;
		List<Integer> ids = new ArrayList<>() ;
		for (SysRoleUser s : list) {
			ids.add(s.getRid()) ;
		}
		return ids;
	}



	//根据用户 uid  和权限 rid  添加
	@Override
	@Transactional
	public void saveSysUser(Integer uid, Integer[] ids) {
		SysRoleUser record = new SysRoleUser() ;
		SysRoleUserExample example = new SysRoleUserExample() ;
		example.createCriteria().andUidEqualTo(uid) ;
		this.sysRoleUserMapper.deleteByExample(example) ;
		for (Integer id : ids) {
			record.setUid(uid);
			record.setRid(id);
				
				this.sysRoleUserMapper.insert(record) ;
			} 
	}



	@Override
	public List<Integer> findPidByRid(List<Integer> rid) {
		
		List<Integer> list = sysRolePermissionMapper.findPidByRid(rid) ;
		return list;
	}



	@Override
	public SysUser userLogin(String name, String pwd) {
		SysUser user = this.sysUserMapper.userLogin(name, pwd) ;
		return user;
	}



	/**
	 * 查找上级管理者
	 */
	@Override
	public SysUser findByMgr(Integer mgr) {
		SysUser user = this.sysUserMapper.findByMgr(mgr) ;
		return user ;
	}



	/**
	 * 查找部门信息
	 */
	@Override
	public SysDept queryDeptByDeptId(Integer deptId) {
		SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId) ;
		return sysDept ;
	}
	
}
