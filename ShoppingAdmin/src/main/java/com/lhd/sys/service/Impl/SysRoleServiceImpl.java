package com.lhd.sys.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhd.sys.dao.SysPermissionMapper;
import com.lhd.sys.dao.SysRoleMapper;
import com.lhd.sys.dao.SysRolePermissionMapper;
import com.lhd.sys.entity.SysPermission;
import com.lhd.sys.entity.SysPermissionExample;
import com.lhd.sys.entity.SysRole;
import com.lhd.sys.entity.SysRolePermission;
import com.lhd.sys.entity.SysRolePermissionExample;
import com.lhd.sys.service.SysRoleService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.SysRoleVO;

@Service("SysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private SysRoleMapper sysRpleMapper ;
	
	@Autowired
	private SysPermissionMapper sysPermissionMapper ;
	
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper ;

	@Override
	public LaYuiPage findAllSysRole(SysRoleVO sysRoleVO) {
		Page<Object> page = PageHelper.startPage(sysRoleVO.getPage(), sysRoleVO.getLimit()) ;
		List<SysRole> list = this.sysRpleMapper.findAllSysRole(sysRoleVO.getName(), 
														1, sysRoleVO.getRemark()) ;
		long total = page.getTotal() ;
		return new LaYuiPage (list , total);
	}
	
	

	@Override
	@Transactional
	public void removeSysRoleById(Integer id) {
		this.sysRpleMapper.updateSysRoleById(0, id) ;
		
	}

	@Override
	@Transactional
	public void updateSysRoleById(SysRoleVO sysRoleVO) {
		sysRoleVO.setCreatime(new Date());
		this.sysRpleMapper.updateByPrimaryKey(sysRoleVO) ;
		
	}

	@Override
	@Transactional
	public void addSysRole(SysRoleVO roleVO) {
		roleVO.setCreatime(new Date());
		this.sysRpleMapper.insert(roleVO);
		
	}



	@Override
	public  List<SysPermission> findAllMenuAndRole() {
		SysPermissionExample example = new SysPermissionExample() ;
		example.createCriteria().andAvailableEqualTo(1) ;
		 List<SysPermission> list = this.sysPermissionMapper.selectByExample(example) ;
		return list;
	}


	@Override
	public List<SysRolePermission> findByRoleId(Integer rolid) {
		SysRolePermissionExample example = new SysRolePermissionExample() ;
		example.createCriteria().andRidEqualTo(rolid) ;
		List<SysRolePermission> list = this.sysRolePermissionMapper.selectByExample(example) ;
		return list;
	}



	@Override
	public List<SysPermission> findByIds(List<Integer> ids) {
		List<SysPermission> list = sysPermissionMapper.findByroleId(ids) ;
		return list;
	}



	@Override
	@Transactional
	public void insertSysRolePer(Integer rid, Integer[] ids) {
		for (Integer id : ids) {
			this.sysRolePermissionMapper.insertRidPid(rid, id);  ;
		}
	}



	@Override
	@Transactional
	public void removeByRid(Integer rid) {
		SysRolePermissionExample example = new SysRolePermissionExample() ;
		example.createCriteria().andRidEqualTo(rid) ;
		this.sysRolePermissionMapper.deleteByExample(example) ;
		
	}

}
