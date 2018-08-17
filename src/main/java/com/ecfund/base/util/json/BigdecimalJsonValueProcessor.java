package com.ecfund.base.util.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date 2012-11-14 下午3:00:38
 * @filename BigdecimalJsonValueProcessor.java
 * @author HMILYLD
 * 
 */

public class BigdecimalJsonValueProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		if (value == null) {
			return "";
		} else {
			return value;
		}
	}

	@Override
	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		if (value == null) {
			return "";
		} else {
			return value;
		}
	}

}
