package com.shfb.common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final String[] IGNORE_URI = {"/login.do", "/logout.do"};
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean flag=false;
		String url = request.getRequestURL().toString();
		for(String s:IGNORE_URI){
			if(url.contains(s)){
				flag=true;
				break;
			}
		}
		if(!flag){
			Cookie[] cookies = request.getCookies();
			boolean loginflag=false;
            for(Cookie c :cookies ){
            	if("user".equals(c.getName())){
            		loginflag=true;
            	}
//                System.out.println(c.getName()+"--->"+c.getValue());
            }
            if(loginflag){
                flag=true;
            }else{
            	flag=false;
            	response.sendRedirect("login.html");
            }
		}
		return flag;
	}
}
