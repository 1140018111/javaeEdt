package com.listenner;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * com.listenner
 * dxl
 *
 * @author DL
 * @create 2020-06-08
 */
@WebListener
public class MyListenner implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("系统启动时候，初始化数据，增加基本数据库配置，可放到缓存中");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("销毁");
	}
}
