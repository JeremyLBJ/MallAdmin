package com.lhd.sys.controller.activiti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhd.sys.common.DataGridView;
import com.lhd.sys.service.WolkFlowService;
import com.lhd.sys.vo.WolkFlowVO;
/**
 * 
 * 流程部署
 * 
 * @author ASUS
 *
 */
@Controller
@RequestMapping("/activitiDeploy")
public class ProcessDeployController {
	
	@Autowired
	private WolkFlowService wolkFlowService ;
	
	
	@RequestMapping("/processManager")
	public String processManager () {
		return "wolkFlow/wolkFlowManager.html" ;
	}
	
	@RequestMapping("/processDeploy")
	@ResponseBody
	public DataGridView processDeploy ( WolkFlowVO vo ) {
		DataGridView gridView = this.wolkFlowService.queryProcessDeploy(vo) ;
		return gridView ;
	}

}
