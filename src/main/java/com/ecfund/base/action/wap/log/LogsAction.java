package com.ecfund.base.action.wap.log;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.service.publics.LogsService;

@Controller
@RequestMapping("/logs")
public class LogsAction {

	@Autowired
	private LogsService logsService;
	
	@RequestMapping(value="/list.action",method=RequestMethod.GET)
	public String list(String relatedid,Model model){
		try {
			Logs log=new Logs();
			log.setRelatedid(relatedid);
			List<Logs> logs=logsService.find(log);
			model.addAttribute("logs", logs);
			return "logs/list";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
}
