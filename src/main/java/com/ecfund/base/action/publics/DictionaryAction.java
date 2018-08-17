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
	
}
