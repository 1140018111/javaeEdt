package com.generator.dao;

import com.generator.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.generator.dao
 * dxl
 *
 * @author DL
 * @create 2020-05-24
 */

@RestController
@RequestMapping("/query")
public class UserContrImpl implements UserContr {

	@Autowired
	private UserServiceImpl userServiceInface;


	@RequestMapping("/a")
	public String queey() {
		User user = userServiceInface.queryById("000001");
		System.out.println(user.getUsername());
		return "chenggong";
	}
}
