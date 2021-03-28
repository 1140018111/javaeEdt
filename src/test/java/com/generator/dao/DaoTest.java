package com.generator.dao;

import com.data.interdata.lotterydata.NetData;
import com.generator.entity.User;
import com.generator.service.UserServiceInface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	private LotterySsqMapper lotterySsqMapper;
	@Autowired
	private NetData netData;

	@Test
	public void danTest() {
		User user = userServiceInface.queryById("000001");
		System.out.println(user.toString());
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

	@Test
	public void lotTest() {
		netData.getNewLOt();
//		LotterySsq lot=new LotterySsq();
//		lot.setVersionid("002");
//		lot.setRedareal("01921");
//		lot.setBuleareal("13");
//		LotterySsq lotterySsq = lotterySsqMapper.selectByPrimaryKey("001");
//		int insert = lotterySsqMapper.insert(lot);
//		System.out.println(insert);
	}
}
