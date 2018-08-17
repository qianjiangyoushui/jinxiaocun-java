package com.ecfund.base.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 登录过滤器
 * 
 * @author jiaxd
 * 
 */
public class LoginFilter implements Filter {
	private FilterConfig filterConfig;
	private String loginPage;
	private String registerPage;
	private String adminLoginPage;

	public void destroy() {
		filterConfig = null;
		System.gc();
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
		return;
	}

	public void init(FilterConfig config) throws ServletException {
		filterConfig = config;
		if (filterConfig.getInitParameter("loginPage") != null)
			loginPage = filterConfig.getInitParameter("loginPage");// 'loginPa.xml中配置的参数
		if (filterConfig.getInitParameter("registerPage") != null)
			registerPage = filterConfig.getInitParameter("registerPage");// 'loginPa.xml中配置的参数
		if (filterConfig.getInitParameter("adminLoginPage") != null)
			adminLoginPage = filterConfig.getInitParameter("adminLoginPage");// 'loginPa.xml中配置的参数
		// 该参数为要跳转到的页面
	}
}
