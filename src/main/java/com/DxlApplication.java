package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@MapperScan("com.generator.dao")
//@ImportResource(locations = {"classpath:spring-mvc.xml"})
//拦截器和监听器注解
@ServletComponentScan
public class DxlApplication {


	public static void main(String[] args) {

		SpringApplication.run(DxlApplication.class, args);


	}

}
