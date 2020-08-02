package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * com.shiro
 * dxl
 *
 * @author DL
 * @create 2020-06-10
 */
public class OneAuthenticator {
	public static void main(String[] args) {

		//创建安全管理器对象
		DefaultSecurityManager securityManager=new DefaultSecurityManager();

		//给安全管理器设置realm
		securityManager.setRealm(new IniRealm("classpath:shiro/shiro.ini"));

		//securityUtils 全局安全工具类
		SecurityUtils.setSecurityManager(securityManager);

		//关键对象
		Subject subject = SecurityUtils.getSubject();

		//创建令牌
		UsernamePasswordToken token = new UsernamePasswordToken("xiaodou7","999");

		try {
			subject.login(token); //认证
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			System.out.println("认证失败：密码错误**");
		}catch (UnknownAccountException s){
			s.printStackTrace();
			System.out.println("认证失败：账户不存在**");
		}

		Realm realm;
	}
}
