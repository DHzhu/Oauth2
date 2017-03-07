/**
 * 
 */
package com.security;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月7日
 */
public class AjaxFormAuthenticationFilter extends FormAuthenticationFilter{
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
               
                return executeLogin(request, response);
            } else {
                
                //allow them to see the login page ;)
                return true;
            }
        } else {

        	//ajax请求超时则返回json格式数据
			if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("{\"timeout\":true,\"success\":false}");
				out.flush();
				out.close();
				return false;
			}
        	
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }
}
