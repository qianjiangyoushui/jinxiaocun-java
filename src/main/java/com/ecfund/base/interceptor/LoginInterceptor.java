package com.ecfund.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.users.UsersService;
import com.mysql.jdbc.StringUtils;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UsersService userService;

	private static final Logger LOGGER = Logger.getLogger(LoginInterceptor.class);

	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex)
			throws Exception {
		System.out.println("==============执行顺序: 3、afterCompletion================");
	}

	// 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mv)
			throws Exception {
		System.out.println("==============执行顺序: 2、postHandle================");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		LOGGER.info("==============执行顺序: 1、preHandle================");

		String requestURI = request.getRequestURI();
		if(requestURI.startsWith("/")){
			requestURI = requestURI.substring(1);
		}
		Users user = (Users) request.getSession().getAttribute("user");
		if (user == null) {
			String telphone = request.getParameter("telphone");
			String password = request.getParameter("password");

			
			if (!StringUtils.isNullOrEmpty(telphone) && !StringUtils.isNullOrEmpty(password)) {
				Users users = new Users();
				users.setTelphone(telphone);
				users.setPassword(password);
				users = userService.view(users);
				
				if (users == null) {
					request.getRequestDispatcher("/users/login.action?uri=" + requestURI).forward(request, response);
					//response.sendRedirect("/users/login.action");
					return false;
				} else {
					request.getSession().setAttribute("user", users);
					return true;
				}
			}
			request.getRequestDispatcher("/users/login.action?uri=" + requestURI).forward(request, response);
			//response.sendRedirect("/users/login.action");
			return false;
		} else {
			return true;
		}
	}
}
