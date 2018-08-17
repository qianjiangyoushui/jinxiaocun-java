package com.ecfund.base.common;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class Message {
	
	public static final String SUCCESS = "success";
	
	public static final String FALSE = "false";
	
	private String flag=Message.SUCCESS;
	private HashMap<Object, Object> data = new HashMap<Object, Object>();
	private HashMap<Object, Object> map=new HashMap<Object, Object>();
	public void setFlag(String flag) {
		this.flag=flag;
	}

	public void setDetailMsg(String message) {
		data.put("detailMsg", message);
	}
	public void setPage(String key,Page page) {
		data.put("page", page);
	}
	public void setObj(String key,Object obj) {
		data.put(key, obj);
	}
	public Map<?, ?> getData() {
		data.put("flag", this.flag);
		if (SUCCESS.equals(flag)) {
			map.put("msg", "ok");
		} else {
			map.put("msg", "fail");
		}
		return data;
	}

	
	public String getJsonOne() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class , new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		map.put("data", getData());
		JSONObject json = JSONObject.fromObject(map, jsonConfig);
		return json.toString();
	}
	
	public String getJsonWeb(Object Obj,Class clazz) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class , new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONObject json = JSONObject.fromObject(Obj, jsonConfig);
		return json.toString();
	}
	public void sendMsg(HttpServletResponse response,String content) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.getWriter().write(content);
		} catch (Exception ex) {
			
		}finally{
			try {
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void sendMsg_json(HttpServletResponse response,String content) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/javascript");
			response.getWriter().write(content);
		} catch (Exception ex) {

		}finally{
			try {
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
