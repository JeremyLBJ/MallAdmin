package com.lhd.sys.controller.activiti;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lhd.sys.common.ResultObject;
import com.lhd.sys.service.ToTaskService;
import com.lhd.sys.service.WolkFlowService;
import com.lhd.sys.vo.WolkFlowVO;

@Controller
@RequestMapping("/processDeployment")
public class ProcessDeploymentController {
	
	@Autowired
	private WolkFlowService wolkFlowService ;
	
	@Autowired
	private ToTaskService toTaskService ;
	
	
	
	/**
	 * 流程部署页面跳转
	 */
	@RequestMapping("/wolkFlowAdd")
	public String wolkFlowAdd () {
		return "wolkFlow/workFlowAdd.html" ;
	}
	
	
	/**
	 * 流程部署
	 */
	@RequestMapping("/ProcessDeploymentByZip")
	@ResponseBody
	public Map<String, Object> ProcessDeploymentByZip ( MultipartFile mf , WolkFlowVO vo ) {
		Map<String, Object> map = new HashMap<>() ;
		try {
			this.wolkFlowService.addWorkFlow( mf.getInputStream(), vo.getDeploymentName() ) ;
			map.put("msg", "部署成功") ;
		} catch (Exception e) {
			map.put("msg", "部署失敗") ;
			e.printStackTrace() ;
		}
		return map ;
	}
	
	/**
	 * 
	 * 删除流程部署
	 */
	@RequestMapping("/deleteProcessDeployment")
	@ResponseBody
	public ResultObject deleteProcessDeployment ( WolkFlowVO vo ) {
		try {
			this.wolkFlowService.removeProcessDeployment(vo.getDeploymentId()) ;
			return ResultObject.DELETE_SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return ResultObject.DELETE_ERROR ;
		}
	}
		
	
	
	/**
	 * 跳转到流程图页面
	 */
	@RequestMapping("/processDeploymentImg")
	public String processDeploymentImg ( String deploymentId ,Model model  ) {
		model.addAttribute("deploymentId", deploymentId) ;
		Map<String, Object> map = new HashMap<>() ;
		map.put("x", 0) ;
		map.put("y", 0) ;
		map.put("width", 0) ;
		map.put("height", 0) ;
		model.addAttribute("map", map) ;
		return "wolkFlow/viewProcessImage.html" ;
	}
	
	/**
	 * 根据任务ID查询 部署ID 并跳转到流程图页面
	 */
	@RequestMapping("/processDeploymentImgByTaskId")
	public String processDeploymentImgByTaskId ( WolkFlowVO vo , Model model ) {
		ProcessDefinition processDefinition = this.wolkFlowService.processDeploymentImgByTaskId(vo.getTaskId()) ;
		Map<String, Object> map = toTaskService.queryTaskCoordinatesByTaskId(vo.getTaskId()) ;
		String deploymentId = processDefinition.getDeploymentId() ;
		model.addAttribute("deploymentId", deploymentId) ;
		model.addAttribute("map", map) ;
		return "wolkFlow/viewProcessImage.html" ;
	}
	
	
	/**
	 * 查看流程图
	 */
	@RequestMapping("/toViewProcessImage")
	public void toViewProcessImage ( WolkFlowVO vo , HttpServletResponse response ) {
		InputStream stream =  this.wolkFlowService.queryProcessDeploymentImg(vo.getDeploymentId()) ;
		try {
			BufferedImage image = ImageIO.read(stream) ;
			ServletOutputStream outputStream = response.getOutputStream() ;
			ImageIO.write(image, "JPEG", outputStream) ;
			outputStream.close() ;
			stream.close() ;
			
		} catch ( Exception e) {
			
			e.printStackTrace() ;
		}
		
	}
	
	/**
	 * 启动流程
	 */
	@RequestMapping("/startProcess")
	@ResponseBody
	public Map<String, Object> startProcess ( WolkFlowVO vo ) {
		Map<String, Object> map = new HashMap<>() ;
		try {
			Integer leaveBill = vo.getId() ;
			this.wolkFlowService.startProcess(leaveBill);
			map.put("msg", "流程启动成功") ;
		} catch (Exception e) {
			e.printStackTrace() ;
			map.put("msg", "流程启动失败") ;
		}
		return map ;
	}

}
