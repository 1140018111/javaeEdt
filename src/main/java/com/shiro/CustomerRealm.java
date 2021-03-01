package com.shiro;

import com.generator.entity.User;
import com.generator.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * com.shiro
 * dxl
 *
 * @author DL
 * @create 2020-06-10
 */
@Component
public class CustomerRealm extends AuthorizingRealm {

	//自动进行密码认证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		return null;
	}

	//账户认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//在token中获取用户名
		String principal =(String) token.getPrincipal();

		//链接数据库取数据
//		User user = userMapper.queryById(principal);
//		UserServiceImpl userServiceImpl = (UserServiceImpl) ApplicationContextUtils.getBean("userServiceImpl");

		UserService userServiceImpl = new UserService();
		System.out.println(userServiceImpl);
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
																		.getRequestAttributes())
											 .getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

//		User user = userServiceImpl.queryById(principal);

		//根据身份信息获取数据库中的信息
		if(!ObjectUtils.isEmpty(user)&&user.getUserid().equals(principal)){//这里取的缓存，需进行用户名校验

			//密码校验：参数1：数据库中正确的用户名，参数2：数据库中密码，参数3：注册时的随即盐  参数4：获取realm名字，直接记录就可以
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserid(),
					user.getPassword(),
					ByteSource.Util.bytes("ah5*s"),
					this.getName());
			return authenticationInfo;
		}
		return null;
	}
}
