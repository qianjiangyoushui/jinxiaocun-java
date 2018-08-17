package com.ecfund.base.action.app.logs;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.util.json.JSONUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/app_logs")
public class AppLogsAction {

	@Autowired
	private LogsService logsService;
	
	@RequestMapping(value="/list.action",method=RequestMethod.POST)
	@ResponseBody
	public void list(String relatedid,PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			Logs log=new Logs();
			log.setRelatedid(relatedid);
			List<Logs> logs=logsService.find(log);
			
			json.put("data", JSONUtils.fromArray(logs));
			json.put("msg", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}

}
