package com.generator.dao;

import com.generator.entity.User;
import com.shiro.CustomerAuthenticator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	public User queey(HttpServletRequest request) {
		User user = userServiceInface.queryById("000001");
//		request.setAttribute("user",user);
		HttpSession session = request.getSession();
		session.setAttribute("user",user);
		System.out.println(user.getUsername());
//		String s = JSONUtils.toJSONString(user);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Timestamp t=new Timestamp(user.getCreatdate().getTime());
		String crateDate = format.format(user.getCreatdate());
		user.setCreatdate(new Date(user.getCreatdate().getTime()));
		user.setCreatdate(new Date(t.getTime()));
		System.err.println(new Date(t.getTime()));
		user.setUpdatedate(new Date(user.getUpdatedate().getTime()));

		CustomerAuthenticator.shirot();
		return user;
	}

	@RequestMapping("/b")
	@ApiOperation(value = "用户查询",httpMethod = "POST",response = String.class,notes = "用户查询根据usercode查询")
	public String queeys(HttpServletRequest request) {
		System.out.println(request.toString());
		User user = userServiceInface.queryById("000001");
		System.out.println(user.getUsername());
//		new Date(user.getCreatdate());
//		DateFormat.getDateTimeInstance(new Date());

		return "chenggong333";
	}
}
