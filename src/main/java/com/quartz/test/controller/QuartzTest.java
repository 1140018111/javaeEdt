package com.quartz.test.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * com.quertz.test.controller
 * dxl
 *基于配置文件定时器开发
 * @author DL
 * @create 2020-05-25
 */
public class QuartzTest {
	/*日志对象*/
	private static final Logger LOG = LoggerFactory.getLogger(QuartzTest.class);

	public void run(){
		if(LOG.isInfoEnabled()){
			LOG.info("测试任务开始执行");
		}
		int d=999999999+18888+99;
		System.out.println("配置文件定时器......");
		System.err.println("求和："+d);
	}
}
