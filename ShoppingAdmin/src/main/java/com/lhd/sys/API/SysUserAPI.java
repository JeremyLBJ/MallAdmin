package com.lhd.sys.API;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.common.Constast;
import com.lhd.sys.common.DataGridView;
import com.lhd.sys.common.ResultObject;
import com.lhd.sys.entity.SysRole;
import com.lhd.sys.entity.SysUser;
import com.lhd.sys.service.SysUserService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.sysUserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/SysUserAPI")
@Api(value="系统用户管理" , description="系统用户管理")
public class SysUserAPI {
	
	
	
	@Autowired
	private SysUserService sysUserService ;
	
	/**
	 * 
	 * 全查询
	 * 
	 */
	@GetMapping(value="/sysUserAllInfo")
	@ApiOperation(value="查询所有用户")
	@ResponseBody
	public LaYuiPage sysUserAllInfo ( @ModelAttribute @Valid @RequestBody sysUserVO sysUserVO ) {
		LaYuiPage page = this.sysUserService.findAllSysUserInfo(sysUserVO) ;
		return page ;
	}
	
	/**
	 * 
	 * 管理员时候为 超级管理员
	 * 
	 */
	@GetMapping(value="/findChildNode")
	@ApiOperation(value="获取超级管理员")
	@ResponseBody
	public Map<String, Object> findChildNode ( @ApiParam("验证管理员") @RequestParam("id") Integer id ) {
		List<SysUser> user = this.sysUserService.removeById(id) ;
		Map<String, Object> map = new HashMap<String , Object>() ;
			if ( user.size() > 0 ) {
				map.put("value", true) ;
			} else {
				map.put("value", false) ;
			}
			return map ;
	}
	
	/**
	 * 
	 * 删除  需要把对于的权限也要进行删除 sys_role_user
	 * 
	 */
	@PostMapping(value="/removeAdmin")
	@ApiOperation(value="移除管理员")
	@ResponseBody
	public ResultObject removeAdmin ( @ApiParam("根据id删除管理员") @RequestParam("id") Integer id ) {
		sysUserVO sysUserVO = new sysUserVO() ;
		sysUserVO.setId(id);
		try {
			this.sysUserService.removeAdmin(sysUserVO);
			return ResultObject.DELETE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.DELETE_ERROR ;
		}
	}
	
	/**
	 * 
	 * 查找排序码
	 * 
	 */
	@GetMapping(value="/maxOrderNum")
	@ApiOperation(value="获取排序码")
	@ResponseBody
	public Map<String, Object> maxOrderNum () {
		List<SysUser> list = this.sysUserService.findAllUserInfo() ;
		Map<String, Object> map = new HashMap<>() ;
		if (list.size() > 0) {
			map.put("maxOrderNum", list.get(0).getOrdernum() + 1) ;
		} else {
			map.put("maxOrderNum", 1) ;
		}
		return map ;
	}
	
	/**
	 * 
	 * 领导部门
	 * 
	 */
	@GetMapping(value="/leaderDept")
	@ApiOperation(value="获取领导部门")
	@ResponseBody
	public DataGridView leaderDept ( @ModelAttribute @Valid @RequestBody Integer deptid ) {
		List<SysUser> list = this.sysUserService.findByDeptidMgr(deptid) ;
		return new DataGridView(list) ;
	}
	
	
	/**
	 * 
	 * 添加用户
	 * 
	 */
	@PostMapping(value="/addSysUser")
	@ApiOperation(value="添加系统用户")
	@ResponseBody
	public ResultObject addSysUser ( @ModelAttribute @Valid @RequestBody sysUserVO sysUserVO ) {
		try {
			sysUserVO.setType(Constast.USER_TYPE_NORMAL);
			this.sysUserService.addSysUseer(sysUserVO);
			return ResultObject.ADD_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.ADD_SUCCESS ;
		}
	}
	
	/**
	 * 
	 * 修改用户
	 * 
	 */
	@PostMapping(value="/updateSysUser")
	@ApiOperation(value="修改系统用户")
	@ResponseBody
	public ResultObject updateSysUser ( @ModelAttribute @Valid @RequestBody sysUserVO sysUserVO ) {
		try {
			this.sysUserService.updateSysUserInfo(sysUserVO);
			return ResultObject.UPDATE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.UPDATE_ERROR ;
		}
		
	}
	
	/**
	 * 
	 * 根据用户Id查找领导部门
	 * 
	 */
	@GetMapping(value="/findLearderById")
	@ApiOperation(value="根据用户id查找领导部门")
	@ResponseBody
	public DataGridView findLearderById ( @ApiParam("根据用户Id查找领导部门") @RequestParam("id") Integer id ) {
		SysUser user = this.sysUserService.findLearderById(id) ;
		return new DataGridView(user) ; 
	}
	
	
	/**
	 * 
	 * 
	 * 根据用户id查找角色对应的权限
	 * 
	 */
	@GetMapping(value="/initRoleByUserId")
	@ApiOperation(value="根据用户id查找角色对应的权限")
	@ResponseBody
	public DataGridView initRoleByUserId ( @ApiParam("根据用户id查找角色对应的权限") @RequestParam("id") Integer id ) {
		//根据用户id查找 可用的角色
		List<SysRole> list = this.sysUserService.initRoleByUserId(id) ;
	
		//查询当前用户拥有的角色ID集合
		List<Integer> ids = this.sysUserService.findIds(id) ;
		for (SysRole s1 : list) {
				Boolean LAY_CHECKED = false ;
				Integer roleId = s1.getId() ;
				for (Integer rid : ids) {
					if (roleId == rid) {
						LAY_CHECKED = true;
						break;
					}
				}
				s1.setLAY_CHECKED(LAY_CHECKED);
			}
		return new DataGridView(Long.valueOf(list.size()) , list) ;
	}
	
	/**
	 * 
	 * 保存用户 分配角色
	 * 
	 */
	@PostMapping(value="/saveSysUser")
	@ApiOperation(value="保存用户 分配角色")
	@ResponseBody
	public ResultObject saveSysUser ( @ApiParam("用户id") @RequestParam("uid") Integer uid 
								, @ApiParam("用户角色") @RequestParam("ids") Integer [] ids) {
		try {
			this.sysUserService.saveSysUser(uid, ids);
			return ResultObject.DISPATCH_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.DISPATCH_ERROR ;
		}
	}


}
