package com.lhd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lhd.sys.service.LoginfoService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.SysLogVO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingAdminAppliction.class)
@MapperScan("com.lhd.sys.dao")
public class SysLogTest {
	
	@Autowired
	private LoginfoService loginfoService ;
	
	@Test
	public void allSysLog () {
		
		SysLogVO logVO = new SysLogVO() ;
		logVO.setPage(1) ;
		logVO.setLimit(15) ;
		logVO.setLoginname("刘先生");
		LaYuiPage findAllSysLog = this.loginfoService.findAllSysLog(logVO ) ;
		System.out.println(findAllSysLog);
	}

}
