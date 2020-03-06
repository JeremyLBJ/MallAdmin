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
import com.lhd.sys.entity.SysDept;
import com.lhd.sys.service.SysDeptService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.DeptVO;

@Controller
@RequestMapping("/sys/dept")
public class SysDeptController {
	
	@Autowired
	private SysDeptService sysDeptService ;
	
	/**
	 * 左边部门树
	 * 
	 * @return
	 */
	@RequestMapping("/deptLeft")
	public String deptLeft () {
		return "dept/deptLeft" ;
	}
	
	
	/**
	 * 
	 * 右边部门具体信息
	 * @return
	 */
	@RequestMapping("/deptRight")
	public String deptRight () {
		return "dept/deptRight" ;
	}
	
	
	/**
	 * 跳转部门主页面
	 * 
	 * @return
	 */
	@RequestMapping("/deptManager")
	public String deptManager () {
		return "dept/deptManager" ;
	}
	
	@RequestMapping("/deptTree")
	@ResponseBody
	public Result deptTree () {
		List<SysDept> list = sysDeptService.deptTree() ;
		List<TreeNode> treeNode = new ArrayList<>() ;
		for (SysDept sysDept : list) {
			Boolean spread = sysDept.getOpen() == Constast.OPEN_TRUE ? true : false ;
			treeNode.add(new TreeNode(sysDept.getId(), sysDept.getPid(), sysDept.getTitle(), spread)) ;
		}
		return new Result(treeNode) ;
	}
	
	@RequestMapping("/findAllDeptInfo")
	@ResponseBody
	public LaYuiPage findAllDeptInfo ( DeptVO deptVO) {
		deptVO.setAvailable("1");
		LaYuiPage page = sysDeptService.findAllDeptInfo(deptVO) ;
		return page ;
	}
	
	@RequestMapping("/addSysDept")
	@ResponseBody
	public ResultObject addSysDept ( DeptVO deptVO ) {
		try {
			this.sysDeptService.addSysDept(deptVO);
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
		List<SysDept> list = this.sysDeptService.allDept() ;
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
	@RequestMapping("/deleteDeptInfo")
	@ResponseBody
	public ResultObject deleteDeptInfo ( DeptVO deptVO ) {
		try {
			this.sysDeptService.deleteDeptInfo(deptVO);
			return ResultObject.DELETE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObject.DELETE_ERROR ;
		}
		
	}
	
	
	/**
	 * 
	 * 删除 只做物理删除 让数据库字段设置为0
	 * 
	 */
	@RequestMapping("/updateDeptInfo")
	@ResponseBody
	public ResultObject updateDeptInfo ( DeptVO deptVO ) {
		try {
			this.sysDeptService.updateById(deptVO);
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
	public Map<String, Object> findChildNode ( DeptVO deptVO ) {
		List<SysDept> list = this.sysDeptService.findChildNode(deptVO) ;
		Map<String, Object> map = new HashMap<String , Object>() ;
		if ( list.size() > 0 ) {
			map.put("value", true) ;
		} else {
			map.put("value", false) ;
		}
		return map ;
	}

}
