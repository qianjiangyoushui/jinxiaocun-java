package com.ecfund.base.action.wap.eliteG1;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.model.eliteG1.Irrigation;
import com.ecfund.base.model.eliteG1.Lookimages;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.eliteG1.LookimagesService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.util.common.DTOBuilder;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

/**
 * 冬季测试情况
 * 
 * imagetype:116
 * 
 * @author wf
 *
 */

@Controller
@RequestMapping("/wintertest")
public class WintertestAction {
	
	
	@Autowired
	private UpimageService upimageService;
	
	@Autowired
	private LookimagesService lookimagesService;
	
	@RequestMapping(value = "/winterPage.action", method = RequestMethod.GET)
	public String winterPage(HttpServletRequest req){
		String seedfileid = req.getParameter("seedfileid");
		req.setAttribute("seedfileid", seedfileid);
		String url=req.getRequestURI();
		url=url.substring(1, url.length());
		
		url=url+"?seedfileid="+seedfileid;
		req.setAttribute("url", url);
		
		String operate = req.getParameter("operate");
		req.setAttribute("operate", operate);
		return "G1/wintertest";
	}
	
	@RequestMapping(value = "/lookListPage.action", method = RequestMethod.GET)
	public String lookListPage(HttpServletRequest req){
		String seedfileid = req.getParameter("seedfileid");
		req.setAttribute("seedfileid", seedfileid);
		
		String url=req.getRequestURI();
		url=url.substring(1, url.length());
		
		url=url+"?seedfileid="+seedfileid;
		req.setAttribute("url", url);
		
		return "G1/look_list";
	}
	
	@RequestMapping(value = "/lookList.action", method = RequestMethod.POST)
	public void lookList(HttpServletRequest request, Page page, PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			Lookimages lookimages = (Lookimages) DTOBuilder.getDTO(request,Lookimages.class);
			Page listpage = this.lookimagesService.findlist(lookimages, page.getBegin(), page.getPageSize());
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
	public String add(String relatedid,String type,String description ,String url,Model model,HttpServletRequest request){
		try {
			model.addAttribute("url", url);
			model.addAttribute("seedfileid", relatedid);
			model.addAttribute("type", type);
			model.addAttribute("description", description);
			description=URLDecoder.decode(description,"UTF-8");
			Upimage imag=new Upimage();
			imag.setRelatedid(relatedid);
			imag.setImagetype(type);
			List<Upimage> images=upimageService.find(imag);
			model.addAttribute("images", images);
			
			return "G1/look_add";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public void save(HttpServletRequest request, Lookimages lookimages,PrintWriter out){
		JSONObject json=new  JSONObject();
		try {
			lookimages = (Lookimages) DTOBuilder.getDTO(request,Lookimages.class);
			Users users = (Users) request.getSession().getAttribute("user");
			lookimages.setCreatedate(new Date());
			lookimages.setOperatorid(users.getGuid());
			String seedfileid = request.getParameter("seedfileid");
			lookimages.setSeedfileid(seedfileid);
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			
			this.lookimagesService.saveLookimages(lookimages, users, imageids);
			
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
	public String detail(HttpServletRequest request){
		try {
			Lookimages lookimages = (Lookimages) DTOBuilder.getDTO(request,Lookimages.class);
			lookimages = this.lookimagesService.getlook(lookimages);
			request.setAttribute("lookimage", lookimages);
			return "G1/look_detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		
	}

}
