package com.lhd;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lhd.sys.dao.SysDeptMapper;
import com.lhd.sys.dao.SysPermissionMapper;
import com.lhd.sys.entity.SysPermission;
import com.lhd.sys.service.SysNoticeService;
import com.lhd.sys.service.SysRoleService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.NoticeVO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingAdminAppliction.class)
@MapperScan("com.lhd.sys.dao")
public class SysNoticeTest {
	
	@Autowired
	private SysNoticeService  sysNoticeService ;
	
	@Autowired
	private SysDeptMapper sysDeptMapper ;
	
	@Autowired
	private SysPermissionMapper sys ;
	
	@Autowired
	private SysRoleService sysRoleService ;
	
	@Test
	public void testRole () {
		Integer rid = 1 ;
		Integer [] ids = {1,2,3,4,5,6} ;
		this.sysRoleService.insertSysRolePer(rid, ids);
	}
	
	
	
	
	
	@Test
	public void testSys () {
		List<Integer> ids = new ArrayList<Integer>() ;
		ids.add(1);
		ids.add(2);
		ids.add(3);
		ids.add(4);
		List<SysPermission> list = this.sys.findByroleId(ids) ;
		System.out.println(list);
	}
	
	@Test
	public void sysNotcieTest () {
		NoticeVO vo = new NoticeVO() ;
		vo.setPage(1);
		vo.setLimit(10);
		vo.setStartTime(null);
		vo.setEndTime(null);
		vo.setTitle("关于系统");
		vo.setOpername("管理员");
		LaYuiPage page = sysNoticeService.findAllSysNotice(vo) ;
		System.out.println(page.toString());
	}
	
	
	//添加
	@Test
	public void addNoticeTest () {
		NoticeVO noticeVO = new NoticeVO() ;
		noticeVO.setContent("你好啊！世界真不同了");
		noticeVO.setOpername("刘先生");
		noticeVO.setTitle("系统公告");
		sysNoticeService.addNoticeByVo(noticeVO);
	}
	
	@Test
	public void testmaxorderNum () {
		Integer integer = this.sysDeptMapper.maxorderNum() ;
		System.out.println(integer+"：：：：：：：：：maxorderNum");
	}

}
