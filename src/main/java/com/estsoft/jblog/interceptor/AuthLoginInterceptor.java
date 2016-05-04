
package com.estsoft.jblog.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.estsoft.jblog.service.UserService;
import com.estsoft.jblog.vo.UserVo;



public class AuthLoginInterceptor extends HandlerInterceptorAdapter 
{
	@Autowired
	UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle)
			throws Exception 
	{
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		
		UserVo userVo = new UserVo();
		userVo.setId(id);
		userVo.setPassword(password);
		
		//login 서비스 호출 = 로그인 작업
		UserVo authUser = userService.login(userVo);
		
		if (authUser == null)
		{
			response.sendRedirect(request.getContextPath() + "/user/loginform?result=fail");  //request.getContextPath() = /mysite3  이다.
			return false;
		}
		//로그인 처리
		HttpSession session = request.getSession(true);  // getSession에 true가 들어가면 세션이 없는경우 세션을 만들고, 세션이 있으면 있는 세션을 이용하라는 뜻.
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath() + "/main");
		return false;  // 리턴 펄스면 뒤에 핸들러 까지 전달이 안된다
	}
}
