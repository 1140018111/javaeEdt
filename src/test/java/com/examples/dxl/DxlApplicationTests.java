package com.examples.dxl;

import com.data.CloneTest;
import com.generator.dao.Dog;
import com.generator.entity.Cat;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Date;
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
		System.out.println("查询结果为："+maps);
	}@Test
	void extest() {
		JdbcTemplate.execute("update new_test_user.user set username='小黑' where username='小白'");
		System.out.println("查询结果为：success");
		new Dog().setTou("21")
				 .setPaole("");
		String ssa="wqqww";
		byte[] bytes = ssa.getBytes();
		byte[] encode = Base64.encode(bytes);
		String s = Base64.encodeToString(bytes);
		System.out.println("解码前1："+s);
		System.out.println("解码前2："+encode);
		byte[] decode = Base64.decode(encode);
		String s1 = Base64.decodeToString(s);
		try {
			String ddddd = new String(decode, "utf-8");
			System.out.println("解码后1："+ddddd);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String sdc = CodecSupport.toString(decode);

		System.out.println("解码后2："+s1);

		Cat c=new Cat();
		c.setMao("111");

		Date date = new Date();
		CloneTest c1 = new CloneTest().setAge("dsds").setName("cai").setCat(c).setDate(date);
		try {
			Thread.sleep(1500);
			CloneTest c2= c1.clone();
			c2.setDate(new Date());
			c2.setAge("2").setName("cat");
			System.out.println("c1="+c1);
			c2.getCat().setMao("222");
			System.out.println("c2="+c2);
		} catch (CloneNotSupportedException | InterruptedException e) {
			e.printStackTrace();
		}


		Dog g1 = new Dog().setTou("goutou").setYibai("100");
		Dog g2=g1;
		g2.setTou("maotoy").setYibai("200");
		System.out.println("g1="+g1);
		System.out.println("g2="+g2);


//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getForObject("", Dog.class,"0001");
	}


}
