package com.ecfund.base.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonInterceptor implements HandlerInterceptor{

	private static final Logger LOGGER = Logger.getLogger(CommonInterceptor.class);
    /** 
     * 在DispatcherServlet完全处理完请求后被调用  
     *  当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex)
			throws Exception {
//		System.out.println("==============执行顺序: 3、afterCompletion================");
	}
	
	//在业务处理器处理请求执行完成后,生成视图之前执行的动作 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mv)
			throws Exception {
		String openid="";
		openid= request.getParameter("openid");
		if(!"".equals(openid)&&null !=openid){
			if(mv!=null&&mv.getModelMap()!=null)
			mv.getModelMap().addAttribute("openid",openid);
		}
//		System.out.println("==============执行顺序: 2、postHandle================");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
//		LOGGER.info("==============执行顺序: 1、preHandle================");
		return true;
	}

}
