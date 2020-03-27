package com.lhd.sys.controller.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.common.DataGridView;
import com.lhd.sys.entity.SysLeavebill;
import com.lhd.sys.service.ToTaskService;
import com.lhd.sys.vo.WolkFlowVO;


@Controller
@RequestMapping("/task")
public class ToTaskController {
	
	@Autowired
	private ToTaskService toTaskService ;
	
	
	
	@RequestMapping("/taskManager")
	public String taskManager () {
		return "wolkFlow/taskManager.html" ;
	}
	
	
	
	@RequestMapping("/doTaskManager")
	public String doTaskManager ( WolkFlowVO vo , Model model ) {
		//1,根据任务ID查询请假单的信息
		SysLeavebill leavebill = this.toTaskService.taskProcess(vo.getTaskId()) ;
		model.addAttribute("leavebill", leavebill) ;
		model.addAttribute("vo", vo) ;
		//2,根据任务ID查询连线信息
		List<String> find = this.toTaskService.find(vo.getTaskId()) ;
		model.addAttribute("outcomes", find) ;
		return "wolkFlow/doTaskManaget.html" ;
	}
	
	
	@RequestMapping("/toTask")
	@ResponseBody
	public DataGridView toTask ( WolkFlowVO vo ) {
		DataGridView view = this.toTaskService.toTask(vo) ;
		return view ;
	}
	
	/**
	 * 根据任务ID查询批注信息
	 */
	@RequestMapping("/taskCommnet")
	@ResponseBody
	public DataGridView taskCommnet ( WolkFlowVO vo ) {
		DataGridView gridView = this.toTaskService.queryTaskComment (vo.getTaskId()) ;
		return gridView ;
	}
	
	/**
	 * 完成任务
	 */
	@RequestMapping("/doTask")
	@ResponseBody
	public Map<String, Object> doTask ( WolkFlowVO vo ) {
		Map<String, Object> map = new HashMap<String, Object>() ;
		try {
			this.toTaskService.doTask(vo);
			map.put("msg", "任务完成成功") ;
		} catch (Exception e) {
			e.printStackTrace() ;
			map.put("msg", "任务完成失败") ;
		}
		return map ;
	}

}
