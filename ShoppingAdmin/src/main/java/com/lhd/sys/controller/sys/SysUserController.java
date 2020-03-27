package com.lhd.sys.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.common.Constast;
import com.lhd.sys.common.DataGridView;
import com.lhd.sys.common.ResultObject;
import com.lhd.sys.common.WebUntils;
import com.lhd.sys.entity.SysDept;
import com.lhd.sys.entity.SysRole;
import com.lhd.sys.entity.SysUser;
import com.lhd.sys.service.SysUserService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.sysUserVO;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService ;
	
	
	/**
	 * 
	 * 跳转到用户管理页面
	 * 
	 */
	
	@RequestMapping("/userManager")
	public String userManager () {
		return "sysUser/SysUserInfo" ;
	}
	
	/**
	 * 用户中心
	 */
	@RequestMapping("/sysUserCenter")
	public String sysUserCenter ( Model model ) {
		//根据当前用户 获取用户相关信息
		SysUser user = (SysUser) WebUntils.getSession().getAttribute("user") ;
		model.addAttribute("name", user.getName()) ; //用户名
		model.addAttribute("remark", user.getAddress()) ;  // 系统角色 
		model.addAttribute("address", user.getRemark()) ;      // 用户组
		//根据用户部门ID 查询所在部门
		SysDept sysDept = this.sysUserService.queryDeptByDeptId(user.getDeptid()) ;
		model.addAttribute("deptTitle", sysDept.getTitle()) ;
		//上级领导
		if ( 0 == user.getMgr() ) {
			model.addAttribute("mgr", "最高领导") ;
		} else {
			SysUser mgr = this.sysUserService.findByMgr(user.getMgr()) ;
			model.addAttribute("mgr", mgr.getName()) ;
		}
		model.addAttribute("sex", user.getSex()) ; //性别
		model.addAttribute("phone", user.getImgpath()) ; // 手机号码
		model.addAttribute("hiredate", user.getHiredate()) ; //入职时间
		model.addAttribute("email", user.getLoginname()) ; //邮箱
		return "user/userInfo.html" ;
	}
	
	
	/**
	 * 
	 * 全查询
	 * 
	 */
	@RequestMapping("/sysUserAllInfo")
	@ResponseBody
	public LaYuiPage sysUserAllInfo ( sysUserVO sysUserVO ) {
		LaYuiPage page = this.sysUserService.findAllSysUserInfo(sysUserVO) ;
		return page ;
	}
	
	/**
	 * 
	 * 管理员时候为 超级管理员
	 * 
	 */
	@RequestMapping("/findChildNode")
	@ResponseBody
	public Map<String, Object> findChildNode ( Integer id ) {
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
	@RequestMapping("/removeAdmin")
	@ResponseBody
	public ResultObject removeAdmin ( Integer id ) {
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
	@RequestMapping("/maxOrderNum")
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
	@RequestMapping("/leaderDept")
	@ResponseBody
	public DataGridView leaderDept ( Integer deptid ) {
		List<SysUser> list = this.sysUserService.findByDeptidMgr(deptid) ;
		return new DataGridView(list) ;
	}
	
	
	/**
	 * 
	 * 添加用户
	 * 
	 */
	@RequestMapping("/addSysUser")
	@ResponseBody
	public ResultObject addSysUser ( sysUserVO sysUserVO ) {
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
	@RequestMapping("/updateSysUser")
	@ResponseBody
	public ResultObject updateSysUser ( sysUserVO sysUserVO ) {
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
	@RequestMapping("/findLearderById")
	@ResponseBody
	public DataGridView findLearderById ( Integer id ) {
		SysUser user = this.sysUserService.findLearderById(id) ;
		return new DataGridView(user) ; 
	}
	
	
	/**
	 * 
	 * 
	 * 根据用户id查找角色对应的权限
	 * 
	 */
	@RequestMapping("/initRoleByUserId")
	@ResponseBody
	public DataGridView initRoleByUserId ( Integer id ) {
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
	@RequestMapping("/saveSysUser")
	@ResponseBody
	public ResultObject saveSysUser ( Integer uid , Integer [] ids) {
		try {
			this.sysUserService.saveSysUser(uid, ids);
			return ResultObject.DISPATCH_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.DISPATCH_ERROR ;
		}
	}

}
