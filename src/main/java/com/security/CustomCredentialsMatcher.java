/**
 * 
 */
package com.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月7日
 */
public class CustomCredentialsMatcher extends HashedCredentialsMatcher {
	
	@Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		//如果<property name="hashAlgorithmName" value="SHA-1" /> 未指定加密，会保存明文，
		//这里就可以换成自己的加密方式 
		//tokenHashedCredentials是提交的密码
		//accountCredentials是保存的密码
        Object tokenHashedCredentials = hashProvidedCredentials(token, info);
        
        Object accountCredentials = getCredentials(info);
        equals(tokenHashedCredentials, accountCredentials);
        return equals(tokenHashedCredentials, accountCredentials);
    }
}
