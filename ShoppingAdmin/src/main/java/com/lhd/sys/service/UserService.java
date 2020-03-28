package com.lhd.sys.service;

import java.util.List;

import com.lhd.sys.entity.UsersTable;


public interface UserService  {
	//查询所有
	List<UsersTable> findAll();
	//用户注册
	int registUser(UsersTable user);
	//用户登录
	UsersTable loginUser(UsersTable user);

}
