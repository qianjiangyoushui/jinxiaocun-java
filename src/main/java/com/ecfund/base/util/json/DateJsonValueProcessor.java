package com.ecfund.base.util.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date 2012-8-7 下午6:15:52
 * @filename DateJsonValueProcessor.java
 * @author HMILYLD
 * 
 */

public class DateJsonValueProcessor implements JsonValueProcessor {

	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	private DateFormat dateFormat;

	public DateJsonValueProcessor() {
		dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
	}

	/**
	 * 构造方法.
	 * 
	 * @param datePattern
	 *            日期格式
	 */
	public DateJsonValueProcessor(String datePattern) {
		try {
			dateFormat = new SimpleDateFormat(datePattern);
		} catch (Exception ex) {
			dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		}
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		if (value == null) {
			return "";
		} else {
			return process(value);
		}
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		if (value == null) {
			return "";
		} else {
			return process(value);
		}
	}

	private Object process(Object value) {
		return dateFormat.format((Date) value);
	}
}
