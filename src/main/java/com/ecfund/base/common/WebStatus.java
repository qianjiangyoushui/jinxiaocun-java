package com.ecfund.base.common;

/**
 * 
 * 页面所有信息状态类，以ws.xxx传递参数，如有其他参数，可继承该类重写
 * 
 * @date 2012-4-24 下午5:30:01
 * @filename WebStatus.java
 * @author HMILYLD
 * 
 */

public class WebStatus {

	/**
	 * 页面提示信息
	 */
	private String tip;

	/**
	 * 页面状态信息
	 */
	private String flag;

	/**
	 * 页面执行动作
	 */
	private String act;

	/**
	 * 页面执行方法
	 */
	private String method;

	/**
	 * 页面返回Action
	 */
	private String action;

	/**
	 * 页面返回拼接参数
	 */
	private String param;

	/**
	 * 类型
	 */
	private String type;

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
