package com.thread;

import com.generator.dao.Dog;
import org.aspectj.weaver.ast.Var;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author DL
 * @diescription com.thread
 * @dis_projectdxl
 * @create 2021-03-21
 */
public class ThresdTest {
	private static volatile ThreadLocal<Dog> t1=new ThreadLocal<Dog>();
	//限流 参数限制线程数
	private static Semaphore sm=new Semaphore(1);
	//2个线程交换数据 需等待数据交换后才能执行下一步操作
	private static Exchanger<String> ex=new Exchanger<String>();

	public static void main(String[] args) {
//		new Thread(ThresdTest::run1).start();
//		new Thread(ThresdTest::run2).start();

//		new Thread(ThresdTest::run3,"t3").start();
//		new Thread(ThresdTest::run4,"t4").start();

//		new Thread(ThresdTest::run5,"t5").start();
//		new Thread(ThresdTest::run6,"t6").start();
		System.out.println(run9());

	}
	private static String run9() {
		new Thread(ThresdTest::run3,"t3").start();
		new Thread(ThresdTest::run4,"t4").start();
		return "211";
	}
	private static void run2() {
		try {
			TimeUnit.SECONDS.sleep(3);
			System.out.println("111"+t1.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void run3() {
		try {
			sm.acquire();
			TimeUnit.SECONDS.sleep(2);
			System.out.println("333");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			sm.release();
		}
	}
	public static void run4() {
		try {
			sm.acquire();
			TimeUnit.SECONDS.sleep(1);
			System.out.println("444");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			sm.release();
		}
	}
	private static void run5() {
		try {
			String fig="t5";
			fig=  ex.exchange(fig);
			System.out.println("t5===="+fig);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private static void run6() {
		try {
			String figs="t6";
			Thread.sleep(2000);
			figs=ex.exchange(figs);
			System.out.println(Thread.currentThread().getName()+"----"+figs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void run1() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        t1.set(new Dog());
		System.out.println("222"+t1.get());
		t1.remove();


	}
}
