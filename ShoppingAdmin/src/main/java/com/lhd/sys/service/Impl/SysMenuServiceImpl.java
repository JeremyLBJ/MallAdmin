package com.lhd.sys.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhd.sys.common.Constast;
import com.lhd.sys.dao.SysPermissionMapper;
import com.lhd.sys.entity.SysPermission;
import com.lhd.sys.entity.SysPermissionExample;
import com.lhd.sys.service.SysMenuService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.PermissionVO;
@Service("SysMenuService")
public class SysMenuServiceImpl implements SysMenuService{
	
	@Autowired
	private SysPermissionMapper sysPermissionMapper ;
	
	public List<SysPermission> findAllMenuBysysPermission ( SysPermission permission ) {
		SysPermissionExample example = new SysPermissionExample() ;
		example.createCriteria().andAvailableEqualTo(permission.getAvailable())
							.andTypeEqualTo(permission.getType()) ;
		List<SysPermission> list = sysPermissionMapper.selectByExample(example) ;
		return list ; 
	}

	
	/**
	 * 
	 * 查找菜单所有有用的数据
	 * 
	 * @param permissionVO
	 * @return
	 */
	@Override
	public LaYuiPage findAllmenuInfo(PermissionVO permissionVO) {
		Page<Object> page = PageHelper.startPage(permissionVO.getPage(), permissionVO.getLimit()) ;
		List<SysPermission> list = this.sysPermissionMapper.allFindSysMenuinfo(permissionVO.getId()
							, permissionVO.getId(), permissionVO.getType(), permissionVO.getTitle()
							, permissionVO.getAvailable()) ; 
		long total = page.getTotal() ;
		
		return  new LaYuiPage (list , total);
	}
	
	
	/**
	 * 
	 * 查找子部门
	 * 
	 * @param permissionVO
	 * @return
	 */
	@Override
	public List<SysPermission> findChildNode(PermissionVO permissionVO) {
		SysPermissionExample example = new SysPermissionExample () ;
		example.createCriteria().andPidEqualTo(permissionVO.getId()) ;
		List<SysPermission> list = this.sysPermissionMapper.selectByExample(example) ;
		return list;
	}

	
	/**
	 * 
	 * 根据id进行修改
	 * 
	 * @param permissionVO
	 */
	@Override
	@Transactional
	public void updateById(PermissionVO permissionVO) {
		this.sysPermissionMapper.updateByPrimaryKey(permissionVO) ;
		
	}

	
	/**
	 * 
	 * 删除菜单
	 * 
	 */
	@Override
	@Transactional
	public void deleteMenuInfo(PermissionVO permissionVO) {
		this.sysPermissionMapper.updateById(permissionVO.getAvailable(), permissionVO.getId()) ;
		
	}

	
	/**
	 * 
	 * 查找所有菜单 查找排序号
	 * 
	 * 
	 */
	@Override
	public List<SysPermission> allmenu(PermissionVO permissionVO) {
		SysPermissionExample example = new SysPermissionExample() ;
		example.createCriteria().andTypeEqualTo(permissionVO.getType()) ;
		example.setOrderByClause("ordernum desc");
		List<SysPermission> list = this.sysPermissionMapper.selectByExample(example) ;
		return list;
	}

	/**
	 * 
	 * 添加菜单
	 * 
	 */
	@Override
	@Transactional
	public void addSysMenu(PermissionVO permissionVO) {
		permissionVO.setType(Constast.TYPE_MENU);
		this.sysPermissionMapper.insert(permissionVO) ;
		
	}
	
	


	
	/**
	 * 
	 * 查询权限
	 * 
	 */
	@Override
	public LaYuiPage allFindSysPermissioninfo(PermissionVO permissionVO) {
		Page<Object> page = PageHelper.startPage(permissionVO.getPage(), permissionVO.getLimit()) ;
		List<SysPermission> list = this.sysPermissionMapper.allFindSysPermissioninfo(permissionVO.getId(), permissionVO.getId()
								, permissionVO.getName(), permissionVO.getType(), permissionVO.getPercode(), permissionVO.getAvailable()) ;
		long total = page.getTotal() ;
		return new LaYuiPage (list, total);
	}


	@Override
	@Transactional
	public void addSysPermission(PermissionVO permissionVO) {
		permissionVO.setType(Constast.TYPE_PERMISSION);
		this.sysPermissionMapper.insert(permissionVO) ;
		
	}


	@Override
	public List<SysPermission> findByPidOfPermission(SysPermission permission, List<Integer> pids) {
		List<SysPermission> list = this.sysPermissionMapper.findPidOfPermission(permission.getAvailable()
											, permission.getType(), pids) ;
		return list;
	}

}
