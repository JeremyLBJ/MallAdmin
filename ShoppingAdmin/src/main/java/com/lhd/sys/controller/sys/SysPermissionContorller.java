package com.lhd.sys.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.common.Constast;
import com.lhd.sys.common.Result;
import com.lhd.sys.common.ResultObject;
import com.lhd.sys.common.TreeNode;
import com.lhd.sys.common.WebUntils;
import com.lhd.sys.entity.SysPermission;
import com.lhd.sys.entity.SysUser;
import com.lhd.sys.service.SysMenuService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.PermissionVO;

@Controller
@RequestMapping("/sysPermission")
public class SysPermissionContorller {
	
	
	@Autowired
	private SysMenuService sysMenuService ;
	
	/**
	 * 
	 * 左边树
	 * @return
	 */
	@RequestMapping("/permissionLeft")
	public String permissionLeft () {
		return "permission/permissionLeft" ;
	}
	
	/**
	 * 
	 * 右边数据
	 * @return
	 */
	@RequestMapping("/permissionRight")
	public String permissionRight () {
		return "permission/permissionRight" ;
	}
	
	@RequestMapping("/permissionManager")
	public String permissionManager () {
		return "permission/permissionManager" ;
	}
	
	
	@RequestMapping("/menuTree")
	@ResponseBody
	public Result menuTree () {
		SysPermission permission = new SysPermission() ;
		//设置只能查询菜单
		permission.setAvailable(Constast.AVAILABLE_TRUE) ;
		permission.setType(Constast.TYPE_MENU) ;
				
		SysUser users = (SysUser) WebUntils.getSession().getAttribute("user") ;
		List<SysPermission> list = null ;
		List<TreeNode> treeNode = new ArrayList<>() ;
				
		if ( users.getType() == Constast.USER_TYPE_SUPER ) {
			 list = sysMenuService.findAllMenuBysysPermission(permission) ;
		} else {
			//普通用户根据用户id加角色等查询  此处角色管理还未添加直接查询所有菜单
			list = sysMenuService.findAllMenuBysysPermission(permission) ;
		}
		for (SysPermission s : list) {
			Boolean spread = s.getSpread()== Constast.OPEN_TRUE ? true : false ;
			treeNode.add(new TreeNode(s.getId(), s.getPid(), s.getTitle(), spread)) ;
		}
		return new Result(treeNode) ;
	}
	
	@RequestMapping("/findAllPermissionInfo")
	@ResponseBody
	public LaYuiPage findAllmenuInfo ( PermissionVO menuVO) {
		menuVO.setType(Constast.TYPE_PERMISSION) ;
		menuVO.setAvailable(Constast.AVAILABLE_TRUE) ;
		LaYuiPage page = sysMenuService.allFindSysPermissioninfo(menuVO) ;
		return page ;
	}
	
	@RequestMapping("/addPermission")
	@ResponseBody
	public ResultObject addSysmenu (PermissionVO menuVO) {
		try {
			this.sysMenuService.addSysPermission(menuVO);
			return ResultObject.ADD_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.ADD_ERROR ;
		}
	}
	
	
	/**
	 * 
	 * 最大排序号
	 * 
	 */
	@RequestMapping("/maxOrderNum")
	@ResponseBody
	public Map<String, Object> maxOrderNum () {
		Map<String, Object> map = new HashMap<String, Object>() ;
		PermissionVO permissionVO = new PermissionVO() ;
		permissionVO.setType(Constast.TYPE_PERMISSION);
		List<SysPermission> list = this.sysMenuService.allmenu(permissionVO) ;
		if ( list.size() > 0) {
			map.put("maxOrderNum", list.get(0).getOrdernum() + 1) ;
		} else {
			map.put("maxOrderNum", 1) ;
		}
		return map ;
	}
	
	/**
	 * 
	 * 删除 只做物理删除 让数据库字段设置为0
	 * 
	 */
	@RequestMapping("/deletePermissionInfo")
	@ResponseBody
	public ResultObject deletemenuInfo ( PermissionVO menuVO ) {
		try {
			menuVO.setAvailable(0);
			this.sysMenuService.deleteMenuInfo(menuVO);
			return ResultObject.DELETE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.DELETE_ERROR ;
		}
		
	}
	
	
	/**
	 * 
	 * 
	 * 
	 */
	@RequestMapping("/updatePermissionInfo")
	@ResponseBody
	public ResultObject updatemenuInfo ( PermissionVO menuVO ) {
		try {
			this.sysMenuService.updateById(menuVO);
			return ResultObject.UPDATE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.UPDATE_ERROR ;
		}
		
	}
	
	
	/**
	 * 
	 * 查找当前节点的id是否有子节点
	 * 
	 */
	@RequestMapping("/findChildNode")
	@ResponseBody
	public Map<String, Object> findChildNode ( PermissionVO menuVO ) {
		List<SysPermission> list = this.sysMenuService.findChildNode(menuVO) ;
		Map<String, Object> map = new HashMap<String , Object>() ;
		if ( list.size() > 0 ) {
			map.put("value", true) ;
		} else {
			map.put("value", false) ;
		}
		return map ;
	}


}
