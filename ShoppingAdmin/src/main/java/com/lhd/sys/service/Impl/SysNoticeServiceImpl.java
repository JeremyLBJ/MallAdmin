package com.lhd.sys.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhd.sys.common.WebUntils;
import com.lhd.sys.dao.SysNoticeMapper;
import com.lhd.sys.entity.SysNotice;
import com.lhd.sys.entity.SysUser;
import com.lhd.sys.service.SysNoticeService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.NoticeVO;

@Service("SysNoticeService")
public class SysNoticeServiceImpl implements SysNoticeService {
	
	@Autowired
	private SysNoticeMapper sysNoticeMapper ;

	@Override
	public LaYuiPage findAllSysNotice(NoticeVO noticeVO) {
		Page<Object> page = PageHelper.startPage(noticeVO.getPage(), noticeVO.getLimit()) ;
		List<SysNotice> list = sysNoticeMapper.allFindSysNotice(noticeVO.getTitle(), noticeVO.getOpername(),
									noticeVO.getStartTime(), noticeVO.getEndTime()) ;
		long total = page.getTotal() ;
		return new LaYuiPage(list, total);
	}

	/**
	 * 批量删除
	 * 
	 */
	@Override
	@Transactional
	public void removeNotice(Integer[] ids) {
		sysNoticeMapper.batchDelete(ids) ;
		
	}

	/**
	 * 删除
	 * 
	 */
	@Override
	@Transactional
	public void removeNoticeById(Integer id) {
		sysNoticeMapper.deleteByPrimaryKey(id) ;
		
	}

	/**
	 * 修改
	 * 
	 */
	@Override
	@Transactional
	public void updateNoticeById( NoticeVO noticeVO) {
		SysNotice record = new SysNotice() ;
		SysUser user = (SysUser) WebUntils.getSession().getAttribute("user") ;
		record.setId(noticeVO.getId());
		record.setTitle(noticeVO.getTitle());
		record.setContent(noticeVO.getContent());
		record.setCreatetime(new Date());
		record.setOpername(user.getName());
		sysNoticeMapper.updateByPrimaryKey(record) ;
		
	}

	
	/**
	 * 添加
	 * 
	 */
	@Override
	@Transactional
	public void addNoticeByVo(NoticeVO noticeVO) {
		SysNotice record = new SysNotice() ;
		SysUser user = (SysUser) WebUntils.getSession().getAttribute("user") ;
		record.setContent(noticeVO.getContent());
		record.setTitle(noticeVO.getTitle());
		record.setOpername(user.getName());
		record.setCreatetime(new Date());
		sysNoticeMapper.insert(record) ;
	}

}
