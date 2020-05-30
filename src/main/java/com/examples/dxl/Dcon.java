package com.examples.dxl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.examples.dxl
 * dxl
 *
 * @author DL
 * @create 2020-05-21
 */
@RestController
@RequestMapping("/main")
public class Dcon {
	@RequestMapping("/show")
	public  String showTest(){
		return "车是数据。。。。。";
	}

}
