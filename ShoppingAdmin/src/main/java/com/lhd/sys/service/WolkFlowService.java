package com.lhd.sys.service;

import com.lhd.sys.common.DataGridView;
import com.lhd.sys.vo.WolkFlowVO;

public interface WolkFlowService {
	
	//查询流程部署信息
	DataGridView queryProcessDeploy ( WolkFlowVO vo ) ;
	
	//查询流程定义信息
	DataGridView queryAllProcessDefinition ( WolkFlowVO vo ) ;

}
