package com.lambda.java8;

import com.sun.corba.se.impl.orb.ParserTable;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * lamabda表达式
 *   左边(形态参数)： （）
 *   中间(操作符) ：  ->
 *   右边(实现)   ：  {}
 * @author DL
 * @diescription com.lambda.java8
 * @dis_projectdxl
 * @create 2020-11-26
 */
public class LambdaBesa {

	@Test
	public void test1(){

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("123");
			}
		};
		runnable.run();
		//lambda表达式
		Runnable rd=()-> System.out.println("nihao");
		rd.run();
	}
	@Test
	public void test2() {
		Consumer<String> consumer = new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println("ceshi"+s);
			}
		};
		consumer.accept("123");

		//lambda表达式
		Consumer consumer2=(s)-> System.out.println("1213"+s);
		consumer2.accept("abc");
	}

	@Test
	public void test3() {
		Comparable<Integer> comparable = new Comparable<Integer>() {
			@Override
			public int compareTo(Integer o) {
				return o+3;
			}
		};
		System.out.println(comparable.compareTo(2));

		Comparable<Integer> cm=x->x+1;
		System.out.println(cm.compareTo(2));
	}

	@Test
	public void test4() {
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		};
		System.out.println(comparator.compare(5,3));

		Comparator<Integer> cm=(x,y)->x-y;
		System.out.println(cm.compare(9,1));

	}



}
