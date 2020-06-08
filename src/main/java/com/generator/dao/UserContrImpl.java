package com.generator.dao;

import com.generator.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags="用户api")
public class UserContrImpl implements UserContr {

	@Autowired
	private UserServiceImpl userServiceInface;


	@RequestMapping("/a")
	@ApiOperation(value = "用户权限校验",httpMethod = "POST",response = String.class,notes = "用户权限查询")
	public String queey() {
		User user = userServiceInface.queryById("000001");
		System.out.println(user.getUsername());
		return "chenggong";
	}

	@RequestMapping("/b")
	@ApiOperation(value = "用户查询",httpMethod = "POST",response = String.class,notes = "用户查询根据usercode查询")
	public String queey(String id) {
		User user = userServiceInface.queryById("000001");
		System.out.println(user.getUsername());
		return "chenggong333";
	}
}
