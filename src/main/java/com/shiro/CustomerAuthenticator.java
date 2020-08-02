package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
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
public class CustomerAuthenticator {
//	public static void main(String[] args) {
	public static void shirot() {

		//创建安全管理器对象
		DefaultSecurityManager securityManager=new DefaultSecurityManager();

		//给安全管理器设置realm
		CustomerRealm realm = new CustomerRealm();
		//设置reaml使用hash凭证匹配器
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5"); //md5加密
//		hashedCredentialsMatcher.setHashIterations(1024);//散列次数1024 2048

		realm.setCredentialsMatcher(hashedCredentialsMatcher);
		securityManager.setRealm(realm);

		//securityUtils 将安全管理器注入安全工具
		SecurityUtils.setSecurityManager(securityManager);

		//关键对象
		Subject subject = SecurityUtils.getSubject();

		//创建令牌
		UsernamePasswordToken token = new UsernamePasswordToken("000001","1239456");

		try {
			subject.login(token); //认证
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			System.out.println("认证失败：密码错误**");
		}catch (UnknownAccountException s){
			s.printStackTrace();
			System.out.println("认证失败：账户不存在**");
		}

	}
}
