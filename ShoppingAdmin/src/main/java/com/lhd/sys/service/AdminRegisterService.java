package com.lhd.sys.service;

import java.util.List;

import com.lhd.sys.entity.AdminTable;

public interface AdminRegisterService {
	
	int adminRegister (AdminTable adminTable) ;
	
	List<AdminTable> adminLogin (AdminTable adminTable) ;

}
