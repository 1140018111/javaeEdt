package com.others.design.decorator;

/**
 * 设计模式
 * 装饰者模式
 * 被装饰者：奶茶
 * @author DL
 * @diescription com.others.design.decorator
 * @dis_projectdxl
 * @create 2021-04-01
 */
public class MillkTea implements Drink {
	@Override
	public double price() {
		return 6;
	}

	@Override
	public String decr() {
		return "奶茶";
	}


}
