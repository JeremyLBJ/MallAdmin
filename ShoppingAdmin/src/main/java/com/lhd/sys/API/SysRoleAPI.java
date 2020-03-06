package com.lhd.sys.API;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.common.DataGridView;
import com.lhd.sys.common.ResultObject;
import com.lhd.sys.common.TreeNode;
import com.lhd.sys.entity.SysPermission;
import com.lhd.sys.entity.SysRolePermission;
import com.lhd.sys.service.SysRoleService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.SysRoleVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/SysRoleAPI")
@Api(value="权限分配管理" , description="权限分配管理")
public class SysRoleAPI {
	
	@Autowired
	private SysRoleService sysRoleService ;
	
	
	
	/**
	 * 全查询
	 * 
	 */
	@GetMapping(value="/findAllSysRole")
	@ApiOperation(value="查询部门信息")
	@ResponseBody
	public Object findAllSysRole (  @ModelAttribute @Valid @RequestBody SysRoleVO sysRoleVO) {
		LaYuiPage page = this.sysRoleService.findAllSysRole(sysRoleVO) ;
		
		return page ;
	}
	
	
	/**
	 * 
	 * 删除
	 * 
	 */
	@PostMapping(value="/deleteSysRole")
	@ApiOperation(value="删除部门")
	@ResponseBody
	public Object deleteNotice (  @ApiParam("根据id删除") @RequestParam("id") Integer id ) {
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
	@PostMapping(value="/updateSysRole")
	@ApiOperation(value="修改部门")
	@ResponseBody
	public Object updateSysRole (  @ModelAttribute @Valid @RequestBody SysRoleVO sysRoleVO ) {
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
	@PostMapping(value="/addSysRole")
	@ApiOperation(value="增加部门")
	@ResponseBody
	public Object addSysRole (  @ModelAttribute @Valid @RequestBody SysRoleVO sysRoleVO  ) {
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
	@GetMapping(value="/initPermissionByRoleId")
	@ApiOperation(value="权限树")
	@ResponseBody
	public Object initPermissionByRoleId (  @ApiParam("根据角色id获取对应的权限树") @RequestParam("roleId") Integer roleId) {
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
	
	
	@PostMapping(value="/saveRolePermission")
	@ApiOperation(value="添加权限")
	@ResponseBody
	public ResultObject saveRolePermission (  @ApiParam("添加权限") @RequestParam("rid") Integer rid , Integer [] ids ) {
		try {
			//根据rid删除sys_role_permission表中的数据  判断ids不为空 长度大于0
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
