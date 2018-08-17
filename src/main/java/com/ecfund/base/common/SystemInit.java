package com.ecfund.base.common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;


public class SystemInit extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Configuration config;
	protected final Logger logger = Logger.getLogger(getClass().getName());

	public void destroy() {
		super.destroy();
	}

	/**
	 * 启动时自动加载的Servlet
	 * 
	 * @throws ServletException
	 */
	public void init() throws ServletException {
		try {
				config = new PropertiesConfiguration("systemConfig.properties");
				this.getServletContext().setAttribute("path",config.getString("path"));
				this.getServletContext().setAttribute("imagesPath",config.getString("imagesPath"));
				this.getServletContext().setAttribute("wechatPath",config.getString("wechatPath"));
				this.getServletContext().setAttribute("upimagesPath",config.getString("upimagesPath"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
