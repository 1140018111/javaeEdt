package com.lambda.java8.functional;

import com.generator.entity.Cat;
import com.lambda.java8.functional.function.TestInterface;
import org.junit.Test;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author DL
 * @diescription com.lambda.java8.functional
 * @dis_projectdxl
 * @create 2020-11-26
 */
public class FunctionalTest {
	/**
	 *                               **四大函数式接口**
	 *
	 *            接口             参数类型   返回类型        用途
	 * 1.Consumer<T> 消费性接口       T        void        对类型T的对象应用操作，vod  accept（T t）
	 * 2.Supplier<T> 供给型接口       无         T         返回类型T的对象， T  get（）
	 * 3.Function<T,R> 函数型接口     T          R         对类型T对象进行操作，返回类型R对象， R apply（T t）
	 * 4.Predicate<T> 断定型接口       T       boolean      对类型T对象进行操作，返回boolean值，boolean test（T t）
	 *
	 */

	@Test
	public  void test1(){

		Consumer<Integer> consumer= TestInterface::getString;
		consumer.accept(2);
		String sd="12";
		Predicate<Object> pc=Objects::isNull;
		System.out.println(gets(sd, Objects::isNull));
		java.lang.Object s=new Cat();
		Function<String ,String> p= x->2+"";
	}
	public static Object gets(Object age, Predicate<Object> pc){
		if(pc.test(age)) return "空了";
		return age;
	}

}
