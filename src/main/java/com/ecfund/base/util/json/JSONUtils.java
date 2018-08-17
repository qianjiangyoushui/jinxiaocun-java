package com.ecfund.base.util.json;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;



/** 
 * @author jiaxd
 * @version 2015年12月10日 下午5:28:29
 */
public class JSONUtils {

	public static String fromString(String key, String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(key, value);
		return fromObject(map);
	}

	public static String fromObject(String key, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return fromObject(map);
	}

	public static String fromObject(Object obj) {
		JSONObject o = JSONObject.fromObject(obj, getConfig());
		return o.toString();
	}

	public static String fromArray(Object obj) {
		JSONArray a = JSONArray.fromObject(obj, getConfig());
		return a.toString();
	}

	/**
	 * 合并多个JSONArray数据
	 * 
	 * @param jsonArray
	 * @return
	 */
	public static String mergerJsonArray(JSONArray[] jsonArray) {
		JSONArray returnArray = new JSONArray();
		for (JSONArray array : jsonArray) {
			returnArray.addAll(array);
		}
		return fromArray(returnArray);
	}

	/**
	 * 将object转换为json数据，可以自行指定jsonConfig内容，用于扩展配置
	 * 
	 * @param obj
	 * @param config
	 * @return
	 */
	public static String fromObject(Object obj, JsonConfig config) {
		JSONObject o = JSONObject.fromObject(obj, config);
		return o.toString();
	}

	/**
	 * 注册相关json数据默认配置
	 * 
	 * @return
	 */
	private static JsonConfig getConfig() {
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "" });
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor());
		config.registerJsonValueProcessor(Integer.class,
				new DefaultJsonValueProcessor());
		config.registerJsonValueProcessor(Double.class,
				new DefaultJsonValueProcessor());
		config.registerJsonValueProcessor(Float.class,
				new DefaultJsonValueProcessor());
		config.registerJsonValueProcessor(BigDecimal.class,
				new BigdecimalJsonValueProcessor());
		return config;
	}
}
