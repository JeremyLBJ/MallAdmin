package com.lhd.sys.service;

import java.util.List;
import java.util.Map;

import com.lhd.sys.common.DataGridView;
import com.lhd.sys.entity.SysLeavebill;
import com.lhd.sys.vo.WolkFlowVO;

public interface ToTaskService {
	
	DataGridView toTask ( WolkFlowVO vo ) ;
	
	SysLeavebill taskProcess ( String taskId ) ;
	
	//流程按钮
	List<String> find ( String taskId ) ;

	//根据任务ID查询批注信息
	DataGridView queryTaskComment(String taskId);
	
	//完成任务
	void doTask ( WolkFlowVO vo ) ;
	
	//根据任务ID 查询任务的坐标
	Map<String, Object> queryTaskCoordinatesByTaskId ( String taskId ) ;
	
}
