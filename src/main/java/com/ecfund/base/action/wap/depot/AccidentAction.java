package com.ecfund.base.action.wap.depot;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.model.depot.Accident;
import com.ecfund.base.model.depot.Depot;
import com.ecfund.base.model.depot.Outinstorage;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.depot.AccidentService;
import com.ecfund.base.service.depot.DepotService;
import com.ecfund.base.service.depot.OutinstorageService;
import com.ecfund.base.service.g0.SeedbedService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/accident")
public class AccidentAction {

	@Autowired
	private SeedfileService seedfileService;
	@Autowired
	private DepotService depotService;
	@Autowired
	private AccidentService accidentService;
	@RequestMapping(value="/accident_index.action",method=RequestMethod.GET)
	public String outin_index(Model model,Depot depot,HttpServletRequest request) throws Exception{
		depot=depotService.view(depot);
		model.addAttribute("depot", depot);
		return "depot/accident_index";
	}
	
	@RequestMapping(value="/page.action",method=RequestMethod.POST)
	@ResponseBody
	public void page(String guid,Page page,PrintWriter out,HttpServletRequest request){
		JSONObject json=new JSONObject();
		try {
			Accident accident=new Accident();
			accident.setDepotid(guid);
			Page pagelist=accidentService.find(accident, page.getBegin(), page.getPageSize());
			pagelist.setPageNo(page.getPageNo());
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			JSONObject jsons=JSONObject.fromObject(pagelist, config);
			json.put("page", jsons);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping(value="/accident_add.action",method=RequestMethod.GET)
	public String outin_add(Model model,String type,Depot depot,Accident accident,HttpServletRequest request) throws Exception{
		model.addAttribute("depot", depot);
		return "depot/accident_add";
	}

	
	@RequestMapping(value = "/save.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void save(PrintWriter out,Depot depot,HttpServletRequest request,Accident accident){
		JSONObject json=new  JSONObject();
		try {
			Users user = (Users) request.getSession().getAttribute("user");
			accident.setOperatorid(user.getGuid());
			accident.setCreatedate(new Date());
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			accidentService.save(accident, user, imageids);
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
	
	@RequestMapping(value="/accident_detail.action",method=RequestMethod.GET)
	public String outin_detail(Model model,Accident accident,HttpServletRequest request) throws Exception{
		String url=request.getRequestURI();
		url=url.substring(1, url.length());
		
		url=url+"?guid="+accident.getGuid();
		model.addAttribute("url", url);
		
		accident=accidentService.view(accident);
		model.addAttribute("accident", accident);
		return "depot/accident_detail";
	}
}
