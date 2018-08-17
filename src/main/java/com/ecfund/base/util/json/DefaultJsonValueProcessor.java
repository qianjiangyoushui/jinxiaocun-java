package com.ecfund.base.util.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date 2012-8-8 上午11:00:36
 * @filename DefaultJsonValueProcessor.java
 * @author HMILYLD
 * 
 */

public class DefaultJsonValueProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		if (arg0 == null) {
			return "";
		}
		return arg0;
	}

	@Override
	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		if (arg1 == null) {
			return "";
		}
		return arg1;
	}

}
