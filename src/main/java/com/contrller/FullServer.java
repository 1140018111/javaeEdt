package com.contrller;

import com.generator.dao.Dog;
import com.thread.ThresdTest;
import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author DL
 * @diescription com.contrller
 * @dis_projectdxl
 * @create 2021-03-24
 */
@RestController
@RequestMapping("/sever")
public class FullServer {
	private static volatile ThreadLocal<Dog> t1=new ThreadLocal<Dog>();
	//限流 参数限制线程数
	private static Semaphore sm=new Semaphore(50);
	//2个线程交换数据 需等待数据交换后才能执行下一步操作
	private static Exchanger<String> ex=new Exchanger<String>();
	static int count=0;
	@GetMapping("/q")
	public  String run9() throws InterruptedException {
		String s="1";
//		new Thread(ThresdTest::run1).start();
//		new Thread(ThresdTest::run2).start();
//	for (int i=0; i<100000;i++) {


//		Thread t3 = new Thread(FullServer::run3, "t3");
//		t3.start();
//		Thread t4 = new Thread(FullServer::run4, "t4");
//		t4.start();

//		new Thread(ThresdTest::run5,"t5").start();
//		new Thread(ThresdTest::run6,"t6").start();
//		t3.join();
//		t4.join();

		s = FullServer.count + "";
//	}
		return s;
	}




	private static void run2() {
		try {
			TimeUnit.SECONDS.sleep(3);
			System.out.println("111"+t1.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private static synchronized void deal(){
		count++;
	}
	private static void run3() {
		try {
			sm.acquire();
			deal();
			System.out.println("333===="+count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			sm.release();
		}
	}
	private static void run4() {
		try {
			sm.acquire();
			deal();
			System.out.println("444==="+count);
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
