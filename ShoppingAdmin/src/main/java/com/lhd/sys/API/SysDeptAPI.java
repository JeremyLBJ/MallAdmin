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
import com.lhd.sys.common.Result;
import com.lhd.sys.common.ResultObject;
import com.lhd.sys.common.TreeNode;
import com.lhd.sys.entity.SysDept;
import com.lhd.sys.service.SysDeptService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.DeptVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value="部门管理功能" , description="部门管理功能")
@RequestMapping("/sysDept")
public class SysDeptAPI {
	
	@Autowired
	private SysDeptService sysDeptService ;
	
	@GetMapping("/deptTree")
	@ApiOperation(value="获取部门树")
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
	
	@GetMapping("/findAllDeptInfo")
	@ApiOperation(value="获取所有部门信息")
	@ResponseBody
	public LaYuiPage findAllDeptInfo ( @ModelAttribute @Valid @RequestBody DeptVO deptVO) {
		deptVO.setAvailable("1");
		LaYuiPage page = sysDeptService.findAllDeptInfo(deptVO) ;
		return page ;
	}
	
	@PostMapping("/addSysDept")
	@ApiOperation(value="添加部门")
	@ResponseBody
	public ResultObject addSysDept ( @ModelAttribute @Valid @RequestBody DeptVO deptVO ) {
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
	@GetMapping("/maxOrderNum")
	@ApiOperation(value="查找最大排序号")
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
	@PostMapping("/deleteDeptInfo")
	@ApiOperation(value="删除部门")
	@ResponseBody
	public ResultObject deleteDeptInfo ( @ModelAttribute @Valid @RequestBody DeptVO deptVO ) {
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
	@PostMapping("/updateDeptInfo")
	@ApiOperation(value="修改部门信息")
	@ResponseBody
	public ResultObject updateDeptInfo ( @ModelAttribute @Valid @RequestBody DeptVO deptVO ) {
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
	@GetMapping("/findChildNode")
	@ResponseBody
	public Map<String, Object> findChildNode ( @ModelAttribute @Valid @RequestBody DeptVO deptVO ) {
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
