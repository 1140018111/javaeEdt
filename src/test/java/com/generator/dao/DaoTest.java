package com.generator.dao;

import com.generator.entity.User;
import com.generator.service.UserService;
import com.generator.service.UserServiceInface;
import junit.textui.TestRunner;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * com.generator.dao
 * dxl
 *
 * @author DL
 * @create 2020-05-24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DaoTest {
	@Autowired
	private UserServiceInface userServiceInface;
	@Autowired
	private UserMapper userMapper;

	@Test
	public void danTest() {
		User user = userServiceInface.queryById("000001");
		User user1 = userMapper.selectByPrimaryKey("000001");
		System.out.println(user.toString());
		System.out.println(user1.toString());
//		User user = new User();
		Class<User> userClass = User.class;
		System.out.println(userClass);

		String name = userClass.getName();
		System.out.println(name);
		Class<?>[] classes = userClass.getClasses();
		System.out.println(classes);
		Package aPackage = userClass.getPackage();
		System.out.println(aPackage);
//		JSONArray.parseArray(r, WorkItem.class);


	}
}
