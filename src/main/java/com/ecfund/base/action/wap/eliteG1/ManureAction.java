package com.ecfund.base.action.wap.eliteG1;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.model.eliteG1.Aphid;
import com.ecfund.base.model.eliteG1.Manure;
import com.ecfund.base.model.eliteG1.Plantprotect;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.eliteG1.ManureService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.util.common.DTOBuilder;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

/**
 * 
 * 施肥记录
 * @author wf
 *
 */

@Controller
@RequestMapping("/manure")
public class ManureAction {
	
	@Autowired
	private ManureService manureService;
	
	@Autowired
	private UpimageService upimageService;
	
	
	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(HttpServletRequest request,Page page){
		try {
			Manure manure = (Manure) DTOBuilder.getDTO(request,Manure.class);
			request.setAttribute("manure", manure);
			String operate = request.getParameter("operate");
			request.setAttribute("operate", operate);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "G1/manure_list";
	}
	
	@RequestMapping(value = "/pageList.action", method = RequestMethod.POST)
	public void pageList(HttpServletRequest request,Page page,PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			Manure manure = (Manure) DTOBuilder.getDTO(request,Manure.class);
			Page listpage =manureService.find(manure,page.getBegin(), page.getPageSize());
			listpage.setPageNo(page.getPageNo());
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			JSONObject jsons=JSONObject.fromObject(listpage, config);
			json.put("page", jsons);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.println(json.toString());
			out.flush();
			out.close();
		}
	}
	
	
	@RequestMapping(value = "/add.action", method = RequestMethod.GET)
	public String add(HttpServletRequest request){
		Manure manure = (Manure) DTOBuilder.getDTO(request,Manure.class);
		request.setAttribute("manure", manure);
		return "G1/manure_add";
	}
	
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public void save(HttpServletRequest request,Manure manure,PrintWriter out){
		JSONObject json=new  JSONObject();
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			manure.setCreatedate(new Date());
			manure.setOperatorid(users.getGuid());
			
			
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			
			
			
			this.manureService.saveManure(manure,users,imageids);
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
	@RequestMapping(value = "/batchsave.action", method = RequestMethod.POST)
	public void batchsave(HttpServletRequest request,Manure manure,PrintWriter out){
		JSONObject json=new  JSONObject();
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			manure.setCreatedate(new Date());
			manure.setOperatorid(users.getGuid());


			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			String batch = request.getParameter("batch");
			JSONArray batchArray=JSONArray.parseArray(request.getParameter("batch"));


			this.manureService.batchsaveManure(manure,users,imageids,batchArray);
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
	
	@RequestMapping(value = "/detail.action", method = RequestMethod.GET)
	public String detail(HttpServletRequest req){
		String guid = req.getParameter("guid");
		try {
			Manure manure = new Manure();
			manure.setGuid(guid);
			manure = this.manureService.view(manure);
			req.setAttribute("manure", manure);
			
			Upimage image = new Upimage();
			image.setRelatedid(guid);
			List<Upimage> list = this.upimageService.find(image);
			req.setAttribute("list", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "G1/manure_detail";
	}

}
