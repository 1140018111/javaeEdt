package com.others.design.decorator;

/**
 *
 * 设计模式
 * 装饰者模式
 * 装饰物
 * @author DL
 * @diescription com.others.design.decorator
 * @dis_projectdxl
 * @create 2021-04-01
 */
public class Gem extends Decorator{
	public Gem(Drink drink) {
		super(drink);
	}

	@Override
	public double price() {
		return super.price()+2;
	}

	@Override
	public String decr() {
		return super.decr()+"加珍珠";
	}

	public static void main(String[] args) {
		Drink millkTea = new MillkTea();
		System.out.println(millkTea.price());
		System.out.println(millkTea.decr());

		Drink gem = new Gem(millkTea);
		System.out.println(gem.price());
		System.out.println(gem.decr());
	}
}
