package com.lhd.sys.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhd.sys.dao.UsersTableMapper;
import com.lhd.sys.entity.UsersTable;
import com.lhd.sys.entity.UsersTableExample;
import com.lhd.sys.service.UserService;
@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UsersTableMapper userMapper;
	
	//查询所有用户
	public List<UsersTable> findAll() {
		List<UsersTable> list = userMapper.selectByExample(null);
		return list;
	}
	//用户注册
	@Override
	@Transactional
	public int registUser(UsersTable user) {
		int i = userMapper.insert(user);
		return i;
	}

	//用户登录
	@Override
	public UsersTable loginUser(UsersTable user) {
		UsersTableExample example = new UsersTableExample();
		example.createCriteria().andUsernameEqualTo(user.getUsername())
		.andPasswordEqualTo(user.getPassword());
		List<UsersTable> list = userMapper.selectByExample(example);
		if( list.size() > 0 ) {
			return list.get(0);
		}
		return null;
	}
}
