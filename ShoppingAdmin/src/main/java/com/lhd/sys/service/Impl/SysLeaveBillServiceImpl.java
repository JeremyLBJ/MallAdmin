package com.lhd.sys.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhd.sys.common.WebUntils;
import com.lhd.sys.dao.SysLeavebillMapper;
import com.lhd.sys.entity.SysLeavebill;
import com.lhd.sys.entity.SysUser;
import com.lhd.sys.service.SysLeaveBillService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.SysLeaveBillVO;

@Service("SysLeaveBillService")
public class SysLeaveBillServiceImpl implements SysLeaveBillService {
	
	
	@Autowired
	private SysLeavebillMapper sysLeaveBill ;

	@Override
	public LaYuiPage allLeaveBill(SysLeaveBillVO vo) {
		Page<Object> startPage = PageHelper.startPage(vo.getPage() , vo.getLimit()) ;
		List<SysLeavebill> list = this.sysLeaveBill.allSysLeaveBill(vo.getTitle(),vo.getContent() 
										,vo.getStartTime() ,vo.getEndTime() , vo.getUserid() ) ;
		long total = startPage.getTotal() ;
		return new LaYuiPage(list, total) ;
	}

	@Override
	@Transactional
	public void addLeaveBill(SysLeaveBillVO vo) {
		SysUser user = (SysUser) WebUntils.getSession().getAttribute("user") ;
		vo.setLeavetime(new Date()) ;
		vo.setUserid(user.getId()) ;
		this.sysLeaveBill.insert(vo) ;
		
	}

	@Override
	@Transactional
	public void updateLeaveBill(SysLeaveBillVO vo) {
		this.sysLeaveBill.updateByPrimaryKeySelective(vo) ;
		
	}

	@Override
	@Transactional
	public void deleteLeaveBill(Integer id) {
		this.sysLeaveBill.deleteByPrimaryKey(id) ;
		
	}

	@Override
	@Transactional
	public void batechDelete(Integer[] ids) {
		this.sysLeaveBill.batchDelete(ids) ;
		
	}

	@Override
	public SysLeavebill findById(Integer id) {
		SysLeavebill leavebill = this.sysLeaveBill.selectByPrimaryKey(id) ;
		return leavebill ;
	}

	@Override
	@Transactional
	public void updateLeave(SysLeavebill leavebill) {
		this.sysLeaveBill.updateByPrimaryKeySelective(leavebill) ;
		
	}

	@Override
	public SysLeavebill findByIdLeaveBillManager(Integer id) {
		SysLeavebill leavebill = this.sysLeaveBill.selectByPrimaryKey(id) ;
		return leavebill ;
	}

}
