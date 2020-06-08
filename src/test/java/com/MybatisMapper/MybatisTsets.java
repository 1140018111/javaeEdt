package com.MybatisMapper;

import com.DxlApplication;
import com.generator.dao.UserContrImpl;
import com.generator.dao.UserMapper;
import com.generator.dao.UserServiceImpl;
import com.generator.entity.User;
import org.apache.ibatis.annotations.Case;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;

/**
 * com.MybatisMapper
 * dxl
 *
 * @author DL
 * @create 2020-05-25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext
public class MybatisTsets {
	//
//	@Autowired
//	private UserServiceImpl userServiceInface;
	@Autowired
	private UserMapper userMapper;

	//	@Autowired
//	private UserContrImpl UserContrImpl;
	@Test
	public void daTest() {
//		User user = userServiceInface.queryById("000001");
		User user1 = userMapper.selectByPrimaryKey("000001");
//		System.out.println(user.toString());
		System.out.println(user1.toString());
//		UserContrImpl.queey();

	}

	@Test
	public void mapTest() {
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("送悟空", "六小龄童");
		hashMap.put("猪八戒", "徐峥");
		hashMap.put("沙和尚", "老跑");
		hashMap.put("沙和尚", "王宝强");

		String s = hashMap.get("沙和尚");
		System.out.println(s);

		Collection<String> values = hashMap.values();
		for (String va : values) {
			System.err.println(va);
		}

		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> set = hashMap.keySet();
		for (String st : set) {
			System.out.println(st);
		}

		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<Map.Entry<String, String>> entries = hashMap.entrySet();
		for (Map.Entry me : entries) {
			System.err.println(me.getKey() + "----" + me.getValue());
		}
		System.out.println(hashMap.containsKey("徐峥"));
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		System.out.println(hashMap.containsValue("徐峥"));


	}

	@Test
	public void enumTst() {
		String ts = "DJF";
		switch (ts) {
			case "DJF":
				season.DJF.exexTsk(ts);
				break;
			case "WDB":
				season.WDB.getTesk();
				break;
			default:
				season.YQR.getTesk();
		}

		System.err.println(season.DJF.name());
		System.err.println(seasons.DJF.name());
		String sa = seasons.getBizType("P212121E2");
		System.out.println(sa);
		String e2 = seasons.getBizType("P212121-02");
		System.out.println(e2);
		if (season.DJF.equals(ts)) {
			System.err.println(season.DJF.name());
		}

//		System.out.println(wing.name()+"下标---"+wing.ordinal()+"wing.getNum()---"+wing.getNum());

	}

	@Test
	public void ts3() {
		seasons djf = seasons.DJF;
		HashMap<String, String> strMap = new HashMap<>();
		strMap.put("jiang", "12");
		strMap.put("yu", "15");
		strMap.put("dou", "19");
		Set<String> strings = strMap.keySet();
		for (String cd : strings) {
			System.out.println(cd+"----"+strMap.get(cd));
		}

	}

	@Test
	public void fileTest() {
//		File file = new File("fic");
//		boolean mkdir = file.mkdir();
//		File file1 = new File(file.getAbsolutePath(), "1.java");
//		try {
//			file1.createNewFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		new File("two").mkdir();

		File d = new File("fic");
//		File eFile = d.getAbsoluteFile();
//		File[] files = d.listFiles(new FileFilter() {
//			@Override
//			public boolean accept(File pathname) {
//				if(pathname.isDirectory()) return true;
//				if(pathname.isFile()) {
//					String name = pathname.getName();
//					if (name.endsWith(".java")) return true;
//
//				}
//				return false;
//			}
//		});


//		File[] files = d.listFiles();
//
//		for (File fis: files) {
//			if(fis.getName().endsWith(".java")) System.out.println(fis.getName());
//		}
//
//
//		for (File  fi: files ) {
//			System.out.println(fi.getName());
//		}

//		System.out.println(file.getAbsoluteFile());
//		try {
//			file.createNewFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		ster(d);

	}

	public static void ster(File s) {

		File[] files = s.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.isDirectory()) return true;
				if (pathname.isFile()) {
					String name = pathname.getName();
					if (name.endsWith(".java")) return true;
				}
				return false;
			}
		});
		for ( File fv: files) {
			if(fv.isDirectory()) ster(fv);
			else System.out.println(fv.getName());
		}



	}
}


enum season {
	DJF("待缴费") {
		public String exexTsk(String agr) {
			UserServiceImpl userService = new UserServiceImpl();
//			userService.queryById("000001");
			return "简单处理DJF业务";
		}
	},
	YQR("已确认") {
		public String exexTsk(String agr) {
			return "简单处理业YQE务";
		}
	},
	WDB("未打包") {
		public String exexTsk(String agr) {
			return "简单处理WDB业务";
		}
	},
	YDZ("已到账") {
		public String exexTsk(String agr) {
			return "简单处理YDZ业务";
		}
	};
	String tesk;

	season(String tesk) {
		this.tesk = tesk;
	}

	public String getTesk() {
		return tesk;
	}

	public abstract String exexTsk(String agr);
}

enum seasons {
	DJF,
	YQR,
	WDB,
	YDZ;

	public static String getBizType(String bizNo) {
		if (bizNo == null || "".equals(bizNo)) throw new IllegalArgumentException("业务号不允许为空");
		if (bizNo.startsWith("Q")) return "TYPE_Q";
		if (bizNo.startsWith("X")) return "TYPE_X";
		if (bizNo.split("[-E]").length == 2) {
			return bizNo.startsWith("F") ? "TYPE_EF" : bizNo.startsWith("P") ? "TYPE_E" : null;
		} else {
			return bizNo.startsWith("F") ? "TYPE_F" : bizNo.startsWith("P") ? "TYPE_P" : null;
		}
	}
}

