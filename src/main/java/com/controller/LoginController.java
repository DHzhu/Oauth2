/**
 * 
 */
package com.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.utils.StringHelper;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月8日
 */
@Controller
public class LoginController {
	@RequestMapping(value = "/login.do")
	public String doLogin(HttpServletRequest request,Map<String, Object> map){
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			return "redirect:/index.do"; 
		}
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		if(StringHelper.isEmpty(userName) || StringHelper.isEmpty(password)){
			return "login";
		}
		
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		
		try{
			subject.login(token);
			if(subject.isAuthenticated()){
				return "redirect:/index.do"; 
			}
		}catch (UnknownAccountException e) {
			map.put("result", "帐号不存在");
			e.printStackTrace();
		}catch (IncorrectCredentialsException e) {
			map.put("result", "密码不正确");
			e.printStackTrace();
		}catch (Exception e) {
			map.put("result", "未知错误");
			e.printStackTrace();
		}
		
		return "login";
	}
	
	@RequestMapping(value = "/logout.do")
	public Object doLogout(String url){
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			subject.logout();
		}
		if(url != null && !url.equals("")){
			return  new ModelAndView(new RedirectView(url));
		}
		return "redirect:/login.do"; 
	}
}
