package com.others.design.decorator;

/**
 * 设计模式
 * 装饰者模式
 * 装饰器
 * @author DL
 * @diescription com.others.design.decorator
 * @dis_projectdxl
 * @create 2021-04-01
 */
public abstract class Decorator implements Drink {
	private  Drink drink;

	public Decorator(Drink drink) {
		this.drink = drink;
	}


	@Override
	public double price() {
		return drink.price();
	}

	@Override
	public String decr() {
		return drink.decr();
	}
}
