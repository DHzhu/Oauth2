/**
 * 
 */
package com.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.model.User;
import com.service.UserService;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月7日
 */
@SuppressWarnings("unused")
public class ShiroDbRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		User user = (User) super.getAvailablePrincipal(principals);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();	
		return authorizationInfo;
	}

	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		User user = new User();
		user.setUserName(upToken.getUsername());
		User admin = userService.getUser(user);
		if(admin == null) {
			throw new UnknownAccountException();
		}
		return new SimpleAuthenticationInfo(admin,admin.getPassword(),String.valueOf(user.getId()));
	}


	/**
	 * 
	 */
	public UserService getUserService() {
		return userService;
	}


	/**
	 * 
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
