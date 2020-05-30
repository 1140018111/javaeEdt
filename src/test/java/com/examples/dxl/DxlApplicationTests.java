package com.examples.dxl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
@SpringBootTest
class DxlApplicationTests {
	@Autowired
	private JdbcTemplate JdbcTemplate;

	@Test
	void contextLoads() {

	}

	@Test
	void xitest() {
		List<Map<String, Object>> maps = JdbcTemplate.queryForList("SELECT * FROM new_test_user.user");
		System.out.println("查询结果为："+maps.size());
	}@Test
	void extest() {
		JdbcTemplate.execute("update new_test_user.user set username='小黑' where username='小白'");
		System.out.println("查询结果为：success");
	}


}
