package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan(basePackages="com.generator.dao")
@ImportResource(locations = {"classpath:spring-mvc.xml"})
public class DxlApplication {


	public static void main(String[] args) {

		SpringApplication.run(DxlApplication.class, args);
		System.out.println("dkjskdjs");


	}

}
