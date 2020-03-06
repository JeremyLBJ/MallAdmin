package com.lhd.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.lhd.sys.entity.UsersTable;


public interface UserService extends IService<UsersTable> {
	//查询所有
	List<UsersTable> findAll();
	//用户注册
	int registUser(UsersTable user);
	//用户登录
	UsersTable loginUser(UsersTable user);
	//批量删除
	void deleteBatch(Integer [] ids) ; 

}
