package com.ecfund.base.action.publics;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.service.publics.DictionaryService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dict")
public class DictionaryAction {

	@Autowired
	private DictionaryService dictService;
	
	@RequestMapping(value="/getDict.action",method=RequestMethod.POST)
	@ResponseBody
	public void getDict(String belongsid,PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			Dictionary dict=new Dictionary();
			dict.setBelongsid(belongsid);
			List<Dictionary> dicts=dictService.find(dict);
			json.put("dict",dicts);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "fail");
		}finally{
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}
	@RequestMapping(value = "/list.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String list(HttpServletRequest request) throws Exception{
		String key = request.getParameter("key");
		Dictionary dict = new Dictionary();
		dict.setBelongsid(key);
		List<Dictionary> variety = dictService.find(dict);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", JSONObject.toJSON(variety));
		return result.toJSONString();
	}

}
