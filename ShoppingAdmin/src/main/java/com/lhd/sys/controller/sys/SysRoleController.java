package com.lhd.sys.controller.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.common.DataGridView;
import com.lhd.sys.common.ResultObject;
import com.lhd.sys.common.TreeNode;
import com.lhd.sys.entity.SysPermission;
import com.lhd.sys.entity.SysRolePermission;
import com.lhd.sys.service.SysRoleService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.SysRoleVO;

@Controller
@RequestMapping("/sysRole")
public class SysRoleController {
	
	@Autowired
	private SysRoleService sysRoleService ;
	
	
	@RequestMapping("/sysRoleManager")
	public String sysRoleManager () {
		return "sysRole/sysRoleManager" ;
	}
	
	/**
	 * 全查询
	 * 
	 */
	@RequestMapping("/findAllSysRole")
	@ResponseBody
	public Object findAllSysRole ( SysRoleVO sysRoleVO) {
		LaYuiPage page = this.sysRoleService.findAllSysRole(sysRoleVO) ;
		
		return page ;
	}
	
	
	/**
	 * 
	 * 删除
	 * 
	 */
	@RequestMapping("/deleteSysRole")
	@ResponseBody
	public Object deleteNotice ( Integer id ) {
		  try {
			  this.sysRoleService.removeSysRoleById(id) ;
			  return ResultObject.DELETE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			 return ResultObject.DELETE_ERROR ;
		}
	}
	
	
	/**
	 * 
	 * 修改
	 * 
	 */
	@RequestMapping("/updateSysRole")
	@ResponseBody
	public Object updateSysRole ( SysRoleVO sysRoleVO ) {
		try {
			 this.sysRoleService.updateSysRoleById(sysRoleVO);
			  return ResultObject.UPDATE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			 return ResultObject.UPDATE_ERROR ;
		}
	}
	
	/**
	 * 
	 * 添加
	 * 
	 */
	@RequestMapping("/addSysRole")
	@ResponseBody
	public Object addSysRole ( SysRoleVO sysRoleVO  ) {
		 try {
			 this.sysRoleService.addSysRole(sysRoleVO);
			  return ResultObject.ADD_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			 return ResultObject.ADD_ERROR ;
		}
	}
	
	
	/**
	 * 
	 * 权限树
	 * 
	 */
	@RequestMapping("/initPermissionByRoleId")
	@ResponseBody
	public Object initPermissionByRoleId ( Integer roleId) {
		//查找所有可用的菜单和权限
		 List<SysPermission> list = this.sysRoleService.findAllMenuAndRole() ;
		//根据角色id查询当前角色拥有的权限
		 List<SysRolePermission> findByRoleId = this.sysRoleService.findByRoleId(roleId) ;
		 List<Integer> ids = new ArrayList<Integer>() ;
		 List<SysPermission> findByIds = null ;
		 if ( findByRoleId.size() > 0 ) { //如果有id 把id添加进去 在查询
			 for (SysRolePermission s : findByRoleId) {
				 ids.add(s.getPid()) ;
			}
			  findByIds = this.sysRoleService.findByIds(ids) ;
		 } else {
			 findByIds = new ArrayList<>() ;
		 }
		
		 //构造List<TreeNode>
		 List<TreeNode> node = new ArrayList<>() ;
		 for (SysPermission s1 : list) {
			 String checkArr="0";
			 for (SysPermission s2 : findByIds) { //findByIds 此时是查询出来的结果集 只要遍历两个集合的id相等即可
					 if ( s1.getId() == s2.getId() ) {
						 checkArr="1";
							break;
					 }
			 }
			 if ( "".equals( s1.getTitle() )) {
				 s1.setTitle(s1.getPercode());
			 }
			 Boolean spread=(s1.getSpread()==null||s1.getSpread()==1)?true:false;
			 node.add(new TreeNode(s1.getId(), s1.getPid(), s1.getTitle(), spread, checkArr));
		 }
		 
		return new DataGridView(node);
		
	}
	
	
	@RequestMapping("/saveRolePermission")
	@ResponseBody
	public ResultObject saveRolePermission ( Integer rid , Integer [] ids ) {
		try {
			//根据rid删除sys_role_permission表中的数据  判断ids不为空 长度大于03
			this.sysRoleService.removeByRid(rid);
			if ( null != ids && ids.length > 0 ) {
				this.sysRoleService.insertSysRolePer(rid, ids);
			}
			return ResultObject.DISPATCH_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.DISPATCH_ERROR ;
		}
	}


}
