package com.generator.dao;

import com.generator.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * com.generator.dao
 * dxl
 *
 * @author DL
 * @create 2020-05-24
 */

public interface UserContr {
	/**
	 *查询
	 */
	public User queey(HttpServletRequest request);
}
