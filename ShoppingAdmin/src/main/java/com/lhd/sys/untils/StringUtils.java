package com.lhd.sys.untils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtils {
	
	public static String randomString () {
		Random ran =  new Random() ;
		int i = ran.nextInt(10000) ;
		String orderNo = new SimpleDateFormat("yyyMMddHHmm").format(new Date())  + i  ;
		return orderNo;
		
	}
}
