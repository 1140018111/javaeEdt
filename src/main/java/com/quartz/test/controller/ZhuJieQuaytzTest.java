package com.quartz.test.controller;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * com.quartz.test.controller
 * dxl
 *基于注解定时器开发
 * @author DL
 * @create 2020-05-26
 */
@Component
@Configurable
@EnableScheduling
public class ZhuJieQuaytzTest {
	@Scheduled(cron = "0/5 * * * * ?")
	public void report(){
		System.out.println("注解定时器.....");
	}

}
