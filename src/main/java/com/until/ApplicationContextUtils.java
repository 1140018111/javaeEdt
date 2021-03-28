package com.until;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * com.until
 * dxl
 *
 * @author DL
 * @create 2020-06-12
 */
public class ApplicationContextUtils implements ApplicationContextAware {
	private static ApplicationContext context;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtils.context=applicationContext;
	}

	//根据bean的名字获取工厂中的bean对象
	public static Object getBean(String beanName){
       return context.getBean(beanName);
	}
}
