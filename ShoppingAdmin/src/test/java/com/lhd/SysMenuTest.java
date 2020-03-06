package com.lhd;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lhd.sys.common.Constast;
import com.lhd.sys.entity.SysPermission;
import com.lhd.sys.service.SysMenuService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.PermissionVO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingAdminAppliction.class)
@MapperScan("com.lhd.sys.dao")
public class SysMenuTest {
	
	@Autowired
	private SysMenuService sysMenuService ;
	
	
	@Test
	public void testOne () {
		PermissionVO permissionVO = new PermissionVO() ;
		permissionVO.setType(Constast.TYPE_PERMISSION);
		List<SysPermission> list = this.sysMenuService.allmenu(permissionVO) ;
		System.out.println(list);
	}
	
	@Test
	public void testTow () {
		SysPermission permission = new SysPermission() ;
		permission.setAvailable(1);
		permission.setType(Constast.TYPE_MENU);
		List<SysPermission> list = this.sysMenuService.findAllMenuBysysPermission(permission) ;
		System.out.println(list);
	}
	
	@Test
	public void testThree () {
		PermissionVO permissionVO = new PermissionVO() ;
		permissionVO.setAvailable(1);
		permissionVO.setType(Constast.TYPE_MENU);
		permissionVO.setTitle("管理");
		permissionVO.setPage(1);
		permissionVO.setLimit(10);
		LaYuiPage page = this.sysMenuService.findAllmenuInfo(permissionVO) ;
		System.out.println(page);
	}
	
	@Test
	public void testFour () {
		PermissionVO permissionVO = new PermissionVO() ;
		permissionVO.setId(1);
		List<SysPermission> list = this.sysMenuService.findChildNode(permissionVO) ;
		System.out.println(list.get(0));
	}
	
	@Test
	public void testFive () {
		PermissionVO permissionVO = new PermissionVO() ;
		permissionVO.setPid(5);
		permissionVO.setAvailable(0);
		permissionVO.setHref("哈哈哈");
		permissionVO.setIcon("11111111");
		permissionVO.setOrdernum(15);
		permissionVO.setTitle("222222");
		permissionVO.setSpread(0);
		this.sysMenuService.addSysMenu(permissionVO);
	}
	
	@Test
	public void testSix () {
		PermissionVO permissionVO = new PermissionVO() ;
		permissionVO.setId(15);
		permissionVO.setAvailable(1);
		this.sysMenuService.deleteMenuInfo(permissionVO);
	}
	
	@Test
	public void testServen () {
		PermissionVO permissionVO = new PermissionVO() ;
		permissionVO.setPid(4);
		permissionVO.setAvailable(0);
		permissionVO.setHref("哈哈1111哈");
		permissionVO.setIcon("1111122222111");
		permissionVO.setOrdernum(15);
		permissionVO.setTitle("22233333222");
		permissionVO.setSpread(0);
		permissionVO.setId(15);;
		permissionVO.setType(Constast.TYPE_MENU);
		this.sysMenuService.updateById(permissionVO);
	}

}
