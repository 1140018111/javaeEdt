package com.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * com.shiro
 * dxl
 *
 * @author DL
 * @create 2020-06-10
 */
public class Md5Test {
	public static void main(String[] args) {
		//生成md5
		Md5Hash md5Hash = new Md5Hash("123456");
		System.out.println(md5Hash.toHex());

		//生成md5+盐
		Md5Hash md5Hash1 = new Md5Hash("999", "ah5*s");
		System.out.println(md5Hash1.toHex());
		//生成md5+盐+散列

		Md5Hash md5Hash2 = new Md5Hash("999", "ah5*s", 1024);
		System.out.println(md5Hash2.toHex());


	}
}
