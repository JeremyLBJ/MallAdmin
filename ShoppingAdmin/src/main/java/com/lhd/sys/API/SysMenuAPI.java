package com.lhd.sys.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.common.Constast;
import com.lhd.sys.common.DataGridView;
import com.lhd.sys.common.Result;
import com.lhd.sys.common.ResultObject;
import com.lhd.sys.common.TreeNode;
import com.lhd.sys.common.TreeNodeBuilder;
import com.lhd.sys.common.WebUntils;
import com.lhd.sys.entity.SysPermission;
import com.lhd.sys.entity.SysUser;
import com.lhd.sys.service.SysMenuService;
import com.lhd.sys.service.SysUserService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.PermissionVO;

import io.swagger.annotations.Api;

@Controller
@Api(value="菜单管理" , description="菜单管理")
@RequestMapping("/SysMenuAPI")
public class SysMenuAPI {
	

	@Autowired
	private SysMenuService sysMenuService ;
	
	@Autowired
	private SysUserService sysUserService ;
	
	
	@GetMapping(value="/indexLeftMenu")
	@ResponseBody
	public DataGridView indexLeftMenu () {
		SysPermission permission = new SysPermission() ;
		//设置只能查询菜单
		permission.setAvailable(Constast.AVAILABLE_TRUE) ;
		permission.setType(Constast.TYPE_MENU) ;
		
		SysUser users = (SysUser) WebUntils.getSession().getAttribute("user") ;
		List<SysPermission> list = null ;
		
		if ( users.getType() == Constast.USER_TYPE_SUPER ) {
			 list = sysMenuService.findAllMenuBysysPermission(permission) ;
		} else {
			//普通用户根据用户id加角色等查询  此处角色管理还未添加直接查询所有菜单
			Integer userId = users.getId() ;
			//根据用户查询sys_user_role表中rid
			List<Integer> findIds = this.sysUserService.findIds(userId) ;
			//根据角色表中的rid查询sys_role_permission表中的pid 
			if ( findIds.size() > 0 ) { //当前管理员是否获取了 菜单和权限
				List<Integer> findPidByRid = this.sysUserService.findPidByRid(findIds) ;
				//根据pid 查询sys_permission表中的id
				if ( findPidByRid.size() > 0 ) {
					list = this.sysMenuService.findByPidOfPermission(permission , findPidByRid) ;
				} else {
					list = new ArrayList<>() ;
				}
			} else {
				list = new ArrayList<>() ;
			}
			
		}
		
		List<TreeNode> treeNodes = new ArrayList<TreeNode>() ;
		for (SysPermission p : list) {
			
			Integer id = p.getId() ;
			Integer pid = p.getPid() ;
			String title = p.getTitle() ;
			String icon = p.getIcon() ;
			String href = p.getHref() ;
			Boolean spread = p.getSpread() == Constast.OPEN_TRUE ? true : false ;
			treeNodes.add(new TreeNode(id, pid, title, icon, href, spread)) ;
			
		}
		//构造层级关系
		List<TreeNode> build = TreeNodeBuilder.build(treeNodes, 1) ;
		return new DataGridView (build) ;
	}
	
	
	@GetMapping("/menuTree")
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
	
	@GetMapping("/findAllmenuInfo")
	@ResponseBody
	public LaYuiPage findAllmenuInfo ( @ModelAttribute @Valid @RequestBody PermissionVO menuVO) {
		menuVO.setType(Constast.TYPE_MENU) ;
		menuVO.setAvailable(Constast.AVAILABLE_TRUE) ;
		LaYuiPage page = sysMenuService.findAllmenuInfo(menuVO) ;
		return page ;
	}
	
	@PostMapping("/addSysMenu")
	@ResponseBody
	public ResultObject addSysmenu ( @ModelAttribute @Valid @RequestBody PermissionVO menuVO) {
		try {
			this.sysMenuService.addSysMenu(menuVO);
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
	@GetMapping("/maxOrderNum")
	@ResponseBody
	public Map<String, Object> maxOrderNum () {
		Map<String, Object> map = new HashMap<String, Object>() ;
		PermissionVO permissionVO = new PermissionVO() ;
		permissionVO.setType(Constast.TYPE_MENU);
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
	@PostMapping("/deleteMenuInfo")
	@ResponseBody
	public ResultObject deletemenuInfo ( @ModelAttribute @Valid @RequestBody PermissionVO menuVO ) {
		try {
			this.sysMenuService.deleteMenuInfo(menuVO);
			return ResultObject.DELETE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.DELETE_ERROR ;
		}
		
	}
	
	
	/**
	 * 
	 *  修改 让数据库字段设置为0
	 * 
	 */
	@PostMapping("/updateMenuInfo")
	@ResponseBody
	public ResultObject updateMenuInfo ( @ModelAttribute @Valid @RequestBody PermissionVO menuVO ) {
		try {
			menuVO.setType(Constast.TYPE_MENU);
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
	@GetMapping("/findChildNode")
	@ResponseBody
	public Map<String, Object> findChildNode ( @ModelAttribute @Valid @RequestBody PermissionVO menuVO ) {
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
