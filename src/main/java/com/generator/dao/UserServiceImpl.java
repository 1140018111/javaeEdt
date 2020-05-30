package com.generator.dao;


import com.generator.dao.UserMapper;
import com.generator.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * com.generator.dao
 * dxl
 *
 * @author DL
 * @create 2020-05-24
 */
@Component
public class UserServiceImpl implements UserServiceInface{

	@Autowired
	private UserMapper userMapper;

	@Override
	public User queryById(String id) {
		System.out.println("service+++++");
		return this.userMapper.selectByPrimaryKey(id);
	}
}
