package com.generator.service;


import com.generator.dao.UserMapper;
import com.generator.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * com.generator.dao
 * dxl
 *
 * @author DL
 * @create 2020-05-24
 */
@Service
public class UserService implements UserServiceInface {

	@Autowired
	private UserMapper userMapper;


	public User queryById(String id) {
		System.out.println("service+++++");
		return this.userMapper.selectByPrimaryKey(id);
	}
}
