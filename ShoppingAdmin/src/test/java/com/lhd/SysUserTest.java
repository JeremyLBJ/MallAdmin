package com.lhd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lhd.sys.entity.SysUser;
import com.lhd.sys.service.SysUserService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.untils.MD5AndSalt;
import com.lhd.sys.vo.sysUserVO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingAdminAppliction.class)
@MapperScan("com.lhd.sys.dao")
public class SysUserTest {
	
	@Autowired
	private SysUserService sysUserService ;
	
	
	
	@Test
	public void userLoginTest () {
		SysUser user = this.sysUserService.userLogin("刘先生", MD5AndSalt.MD5("lhd19980526")) ;
		System.out.println(user);
	}
	
	
	@Test
	public void allPidTest () {
		List<Integer> rid = new ArrayList<Integer>() ;
		rid.add(6);
		rid.add(5) ;
		List<Integer> list = this.sysUserService.findPidByRid(rid) ;
		System.out.println(list);
	}
	
	@Test
	public void findAllSysUserTest () {
		sysUserVO sysUserVO = new sysUserVO() ;
		sysUserVO.setPage(1);
		sysUserVO.setLimit(15);
		LaYuiPage page = this.sysUserService.findAllSysUserInfo(sysUserVO)  ;
		System.out.println(page.toString());
	}
	
	
	@Test
	public void userRegistTest () {
		SysUser user = new SysUser() ;
		user.setAddress("普通管理员");
		user.setAvailable(1);
		user.setDeptid(2);
		user.setType(1);
		user.setHiredate(new Date());
		user.setLoginname("admin");
		user.setName("王先生");
		user.setOrdernum(5);
		user.setPwd(MD5AndSalt.MD5("lhd19980526"));
		user.setSalt(MD5AndSalt.generate("lhd19980526"));
		user.setSex("男");
		user.setRemark("管理者");
		 sysUserService.SysUserRegist(user);
		
	}
	
	@Test
	public void userInfoTest () {
		sysUserVO sysUserVO = new sysUserVO() ;
		sysUserVO.setPage(1);
		sysUserVO.setLimit(15);
		sysUserVO.setName("先生");
		LaYuiPage page = this.sysUserService.findAllSysUserInfo(sysUserVO) ;
		System.out.println(page.toString());
	}
	
	@Test
	public void removeUserInfoTest () {
		sysUserVO sysUserVO = new sysUserVO() ;
		sysUserVO.setId(1);
		this.sysUserService.removeAdmin(sysUserVO);
	}
	
	@Test
	public void updateSysUserInfoTest () {
		sysUserVO sysUserVO = new sysUserVO() ;
		sysUserVO.setId(1);
		sysUserVO.setName("橘先生");
		sysUserVO.setAddress("超级管理员");
		sysUserVO.setAvailable(0);
		sysUserVO.setHiredate(new Date());
		sysUserVO.setMgr(2);
		sysUserVO.setDeptid(5);
		this.sysUserService.updateSysUserInfo(sysUserVO);
	}

}
