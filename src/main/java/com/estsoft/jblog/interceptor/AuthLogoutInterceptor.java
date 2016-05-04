package com.estsoft.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthLogoutInterceptor extends HandlerInterceptorAdapter 
{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession httpSession = request.getSession();
		if (httpSession != null)
		{
			httpSession.removeAttribute("authUser");
			httpSession.invalidate();
		}
		response.sendRedirect(request.getContextPath() + "/main");
		return false;  // 리턴 펄스면 뒤에 핸들러 까지 전달이 안된다
	}

}
