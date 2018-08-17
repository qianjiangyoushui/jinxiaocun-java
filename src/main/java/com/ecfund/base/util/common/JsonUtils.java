package com.ecfund.base.util.common;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
/**     
 * @Makedate:2014-12-11 上午10:15:39
 * @author xxl
 * @Description:  
 */
public class JsonUtils {
	private static JsonUtils ju;
	private static JsonFactory jf;
	private static ObjectMapper mapper;
	
	private static final Logger LOG = Logger.getLogger(JsonUtils.class);
	
	private JsonUtils(){
		
	}
	
	/**
	 * 根据传入JSON串返回对应KEY的VALUE
	 * @param json JSON字符串
	 * @param key  KEY
	 * @return value
	 */
	public static String getJsonByKey(String json,String key){
		mapper = getMapper();
		String nodeStr =null;
		try {
			JsonNode node = mapper.readTree(json);
			node = node.get(key);
			nodeStr = node.toString();
			//如果第一位和最后一位是 "
			if(nodeStr.charAt(0) == '"' && nodeStr.charAt(nodeStr.length()-1) == '"'){
				nodeStr = nodeStr.substring(1,nodeStr.length()-1);
			}
		} catch (Exception e) {
			return null;
		}
		return nodeStr;
	}
	
	
	/**
	 * 实例化对象，做成了单例
	 * @return 生成的实例
	 */
	public static JsonUtils getInstance() {
		if(ju==null){
			ju = new JsonUtils();
		}
		return ju;
	}
	
	/**
	 * 获取对象映射器
	 * @return 生成的映射器
	 */
	public static ObjectMapper getMapper() {
		if(mapper==null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}
	
	/**
	 * 获取JSON工厂
	 * @return JSON工厂
	 */
	public static JsonFactory getFactory() {
		if(jf==null){
			jf = new JsonFactory();
		}
		return jf;
	}
	
	/**
	 * 将对象转换成json串
	 * @param obj 待转换的对象
	 * @return json字符串
	 */
	public static String obj2json(Object obj) {
		JsonGenerator jg = null;
		try {
			jf = getFactory();
			mapper = getMapper();
			StringWriter out = new StringWriter();
			jg = jf.createJsonGenerator(out);
			mapper.writeValue(jg, obj);
			return out.toString();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			try {
				if(jg!=null) {
					jg.close();
				}
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return null;
	}
	
	/**
	 * 将json串转换成对象
	 * @param json 待转换的json串
	 * @param clz 对象
	 * @return 生成的对象
	 */
	public static Object json2obj(String json,Class<?> clz) throws IOException {
		mapper = getMapper();
		return mapper.readValue(json,clz);
	}
	
	
	/**
	 * @param request
	 * @param parm
	 * @param clz
	 * @return
	 * @throws IOException
	 */
	public static Object initRequestParm2Object(HttpServletRequest request,String parm,Class<?> clz) throws IOException{
		String json = String.valueOf(request.getAttribute(parm));
		return json2obj(json,clz);
	}

	/**
	 * 创建一个easyUI_dataGrid可用的JSON
	 * @param list
	 * @return
	 */
	public static JSONObject createDataGrid(List list){
		JSONObject json = new JSONObject();
		json.put("total", list.size());
		JSONArray obj = JSONArray.fromObject(list);
		json.put("rows", obj.toString());
		return json;
	}
	/**
	 * 创建一个easyUI_treeGrid可用的JSON
	 * 目前与上一方法一样，后期会扩充
	 * "_parentId":2   必须数字类型
	 * "state":"closed" 
	 * @param list
	 * @return
	 */
	public static JSONObject createTreeGrid(List list){
		JSONObject json = new JSONObject();
		json.put("total", list.size());
		JSONArray obj = JSONArray.fromObject(list);
		json.put("rows", obj.toString());
		return json;
	}

}
