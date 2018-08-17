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
import com.ecfund.base.model.eliteG1.Pest;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.eliteG1.PestService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.util.common.DTOBuilder;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;


@Controller
@RequestMapping("/pest")
public class PestAction {
	
	@Autowired
	private PestService pestService;
	
	@Autowired
	private UpimageService upimgService;
	
	
	
	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(HttpServletRequest request,Page page){
		try {
			Pest pest = (Pest) DTOBuilder.getDTO(request,Pest.class);
			request.setAttribute("pest", pest);
			String operate = request.getParameter("operate");
			request.setAttribute("operate", operate);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "G1/pest_list";
	}
	
	@RequestMapping(value = "/pageList.action", method = RequestMethod.POST)
	public void pageList(HttpServletRequest request,Page page,PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			Pest pest = (Pest) DTOBuilder.getDTO(request,Pest.class);
			Page listpage =pestService.find(pest,page.getBegin(), page.getPageSize());
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
		Pest pest = (Pest) DTOBuilder.getDTO(request,Pest.class);
		request.setAttribute("pest", pest);
		return "G1/pest_add";
	}
	
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public void save(HttpServletRequest request,Pest pest,PrintWriter out){
		JSONObject json=new  JSONObject();
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			pest.setCreatedate(new Date());
			pest.setOperatorid(users.getGuid());
			
			
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			
			this.pestService.savePest(pest,users,imageids);
			//return "redirect:list.action?seedfileid="+pest.getSeedfileid();
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
			Pest pest = (Pest) DTOBuilder.getDTO(request,Pest.class);
			pest = this.pestService.view(pest);
			request.setAttribute("pest", pest);
			Upimage upimage = new Upimage();
			upimage.setImagetype("111");
			upimage.setRelatedid(pest.getGuid());
			List<Upimage> imgList = this.upimgService.find(upimage);
			request.setAttribute("imgList", imgList);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "G1/pest_detail";
	}
}
