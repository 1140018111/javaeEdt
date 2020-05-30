package com.generator.dao;


import com.generator.entity.User;
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
public interface UserServiceInface {
	/**
	 * idc查询
	 *
	 */
	public User queryById(String id);

}
