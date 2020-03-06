package com.lhd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.lhd.sys.dao.ClassificationofGoodsItemMapper;
import com.lhd.sys.dao.SysLogInfoMapper;
import com.lhd.sys.entity.SysLogInfo;
import com.lhd.sys.entity.SysUser;
import com.lhd.sys.service.LoginfoService;
import com.lhd.sys.service.SysUserService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.untils.MD5AndSalt;
import com.lhd.sys.vo.LoginfoVO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingAdminAppliction.class)
@MapperScan("com.lhd.sys.dao")
public class TestOne {
	
	@Autowired
	private SysUserService sysUserService ;
	
	@Autowired
	private LoginfoService loginfoService ;
	
	@Autowired
	private SysLogInfoMapper sysLogInfoMapper ;
	
	@Autowired
	private ClassificationofGoodsItemMapper c ; 
	
	@Test
	public void findMaxIdTest () {
		Integer integer = this.c.findMaxId() ;
		System.out.println(integer);
	}
	
	
	//登录测试
	/*public void loginTest () {
		SysUser sysUser = new SysUser() ;
		sysUser.setName("刘先生");
		sysUser.setPwd(MD5AndSalt.MD5("lhd19980526"));
		List<SysUser> list = sysUserService.SysUserToLogin(sysUser) ;
		if ( list.size() != 0 ) {
			System.out.println("登录成功");
		}
		
	}*/
	
	
	//注册测试
	@Test
	public void registTest () {
		SysUser sysUser = new SysUser() ;
		sysUser.setAddress("系统人员");
		sysUser.setAvailable(1);
		sysUser.setHiredate(new Date());
		sysUser.setLoginname("system");
		sysUser.setName("刘先生");
		sysUser.setOrdernum(1);
		sysUser.setPwd(MD5AndSalt.MD5("lhd19980526"));
		sysUser.setRemark("刘先生");
		sysUser.setSalt(MD5AndSalt.generate("lhd19980526"));
		sysUser.setType(0);
		sysUser.setSex("男");
		sysUserService.SysUserRegist(sysUser) ;
	}
	
	//登录日志测试
	@Test
	public void loginfoTest () {
		LaYuiPage pages = loginfoService.findAllOrderList( 2, 15) ;
		System.out.println(pages.toString());
	}
	
	//登录日志全查询测试
	@Test
	public void allFindSysLoginfoTest () throws ParseException {
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LoginfoVO loginfoVO = new LoginfoVO() ;
		loginfoVO.setLoginip("127");
		loginfoVO.setLoginname("超");
		loginfoVO.setStartTime( simp.parse("2018-12-21 16:54:52"));
		loginfoVO.setEndTime( simp.parse("2018-12-22 09:57:46"));
		PageHelper.startPage(1, 15) ;
		List<SysLogInfo> list = sysLogInfoMapper.allFindSysLoginfo(loginfoVO.getLoginip(),loginfoVO.getLoginname() , loginfoVO.getStartTime(), loginfoVO.getEndTime()) ;
		System.out.println(list);
	}
	
	//批量删除
	@Test
	public void batchDelete () {
		Integer [] ids = {32,33} ;
		//System.out.println(ids);
		//sysLogInfoMapper.batchDelete(ids);
		loginfoService.removeSysLoginfos(ids);
	}

}
