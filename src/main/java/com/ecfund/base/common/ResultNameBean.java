package com.ecfund.base.common;

public class ResultNameBean {
	/**
	 * 默认返回类型
	 */
	public final static String DEFAULT = "default";

	/**
	 * 成功返回类型
	 */
	public final static String SUCCESS = "success";

	/**
	 * 错误返回类型
	 */
	public final static String ERROR = "error";

	/**
	 * JSP页面返回类型
	 */
	public final static String JSPPAGE = "jspPage";

	/**
	 * FreeMarker页面返回类型
	 */
	public final static String FTLPAGE = "ftlPage";

	/**
	 * FreeMarker页面返回类型，重定向到指定的页面地址
	 */
	public final static String RDFTLPAGE = "rdFtlPage";

	/**
	 * 通用转向页面
	 */
	public final static String CPAGE = "cPage";

	/**
	 * 导出word文档返回类型
	 */
	public final static String EXPORTWORD = "exportWord";

	/**
	 * 导出Excel文档返回类型
	 */
	public final static String EXPORTXLS = "exportXls";

	/**
	 * 转向同action下的指定方法，必须指定ws.method参数
	 */
	public final static String FORWARD = "forward";

	/**
	 * 转向不同Action的指定方法，必须指定ws.action和ws.method方法
	 */
	public final static String REDIRECT = "redirect";

	/**
	 * 测试页面返回类型
	 */
	public final static String DEMO = "demo";
}
