package com.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author DL
 * @diescription com.aop
 * @dis_projectdxl
 * @create 2021-03-04
 */
@Aspect
@Component
public class Proxy {
//	@Pointcut("within(com.generator.service..*)") // the pointcut expression
//	private void anyOldTransfer() {} // the pointcut signature

	@AfterReturning(value = "execution(* com.generator.service.*.*(..)) && args(id,..)",returning = "user")
//	@Before("within(com.generator.service..*)")
	public void affter(String id, Object user){
		Class<Integer> type = Integer.TYPE;
		System.err.println(id+"&&&&&&&&&&&&&&&&&&&&&&仿石砖&&&&&&&&&&&&&&&&&&&&&");
		System.out.println("kkkkkkkk"+user);
	}
}
