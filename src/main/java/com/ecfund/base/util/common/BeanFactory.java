package com.ecfund.base.util.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author HuZhigang
 * @date 2009-8-17
 */

public class BeanFactory {

	/**
	 * 从spring注入获取dao的bean实例,此方法留作测试使用
	 * 
	 * @param pathStr
	 * @param valueStr
	 * @return
	 * @author HuZhigang
	 */
	public static Object getBean(String pathStr, String valueStr) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(pathStr);
		return ctx.getBean(valueStr);
		// ClassPathResource rs = new ClassPathResource(pathStr);
		// org.springframework.beans.factory.BeanFactory factory = new
		// XmlBeanFactory(
		// rs);
		// return factory.getBean(valueStr);
	}

	public static Object getBean(String valueStr) {
		return getBean("applicationContext.xml", valueStr);
	}

}
