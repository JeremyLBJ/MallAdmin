package com.lhd;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import org.junit.Test;


public class SysIndexTest {
	
	@Test
	public void sysTest () {
		OperatingSystemMXBean osmx = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
		double systemLoadAverage = osmx.getSystemLoadAverage() ;
		System.out.println(systemLoadAverage);
	}

}
