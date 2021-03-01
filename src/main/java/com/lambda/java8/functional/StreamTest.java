package com.lambda.java8.functional;

import org.junit.Test;

/**
 * @author DL
 * @diescription com.lambda.java8.functional
 * @dis_projectdxl
 * @create 2020-12-01
 */
public class StreamTest {
	/**
	 * 数据的一个操作链
	 * stream包含中间操作和终止操作
	 * 集合  数组
	 * 1.构建方式
	 *     集合   集合.stream();
	 *     数组   Array.stream(数组)
	 *     自定义  stream.of(1,2,3,4);
	 * 2.stream静态方法
	 *     1) 迭代        初始值  函数   限制次数    终止操作
	 *     	  Stream.iterte(0,t->t+2).limit(10).forEach(system.out::println)
	 *     2. 生成               参数函数
	 *        Stream.generate(Math::rendom).limit(10).forEach(system.out::println)
	 * 3.Stream 中间操作
	 *     1）筛选与切片
	 *		  集合.stream().filter（Pridcade<T> p）
	 *	   2）跳过元素，获取给定数之后的数据，不超过给定数则返回空流
	 *	      集合.stream().skip（3）
	 *	   3）截断流，使元素不超过给定数
	 *	      集合.stream().limit（3）
	 *	   4）去重，通过流产生的hashcode和equals去除重复元素
	 *	      集合.stream().distinct()
	 *     5) 映射 根据函数操作后，转换另个集合
	 *        map(Function<T,R> f)
	 *	   6）集合里面有集合
	 *	      flatMap(Function<T,R> f)  了解
	 *	   7）排序
	 *	      【1】基础类型 集合.stream().sorted() 自然排序
	 *		  【2】对象类型 排序需对象类实现Comparable接口或走定制排序（集合.stream().sorted(比较条件)）
	 * 4.终止操作
	 *     1）匹配与查找
	 *       boolean b =集合.stream().allMatch(ridcade<T> p); //断言型，是否匹配所有元素
	 *       boolean b =集合.stream().anyMatch(ridcade<T> p); //断言型，是否至少匹配一个元素
	 *		 boolean b =集合.stream().noneMatch(ridcade<T> p); //断言型，是否没有匹配的元素
	 *		 optional o =集合.stream().findFrist(); //返回第一个元素
	 *		 optional o =集合.parallelStream().findAny(); //返回任意一个，并行流
	 *		 optional o =集合.stream().count(); //返回流中元素个数
	 *		 optional o =集合.stream().Max(Comparator c); //返回流中最小值
	 *		 集合.stream().forEach(Comparator c); //内部迭代
	 *     2) 规约
	 *		 集合.stream().reduce(t,binaryOperatot);//求总和，参数1初始值，参数函数
	 *		 集合.stream().reduce(binaryOperatot);//求和，参数函数
	 *	   3）收集
	 *	      collect(Collector c)
	 *	      例子：List l=集合.stream().collect(Collectors.toList());
	 * 5.Optional 类
	 *
	 */
	@Test
	public void test1(){

	}
}
