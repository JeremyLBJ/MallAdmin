package com.lhd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lhd.sys.entity.SysLeavebill;
import com.lhd.sys.service.ToTaskService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingAdminAppliction.class)
@MapperScan("com.lhd.sys.dao")
public class TaskTest {
	
	@Autowired
	private ToTaskService toTaskServices ;
	
	
	@Test
	public void leaveBillTestssss () {
		
		String taskId = "17506" ;
		SysLeavebill taskProcess = this.toTaskServices.taskProcess(taskId) ;
		System.out.println(taskProcess.toString()) ;
	}

}
