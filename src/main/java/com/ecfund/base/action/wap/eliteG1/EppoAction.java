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
import com.ecfund.base.model.eliteG1.Eppo;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.eliteG1.EppoService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.util.common.DTOBuilder;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;


/**
 * 
 * G1  新植保
 * 
 * @author wf
 *
 */
@Controller
@RequestMapping("/eppo")
public class EppoAction {
	
	@Autowired
	private EppoService eppoService;
	
	@Autowired
	private UpimageService upimageService;
	
	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(HttpServletRequest request,Page page){
		try {
			Eppo eppo = (Eppo) DTOBuilder.getDTO(request,Eppo.class);
			request.setAttribute("eppo", eppo);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "G1/eppo_list";
	}
	
	@RequestMapping(value = "/pageList.action", method = RequestMethod.POST)
	public void pageList(HttpServletRequest request,Page page,PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			Eppo eppo = (Eppo) DTOBuilder.getDTO(request,Eppo.class);
			Page listpage =eppoService.find(eppo,page.getBegin(), page.getPageSize());
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
		Eppo eppo = (Eppo) DTOBuilder.getDTO(request,Eppo.class);
		request.setAttribute("eppo", eppo);
		//Users users = (Users) request.getSession().getAttribute("user");
		//request.setAttribute("uploadperson", users.getUsername());
		return "G1/eppo_add";
	}
	
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public void save(HttpServletRequest request,Eppo eppo,PrintWriter out){
		JSONObject json=new  JSONObject();
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			eppo.setCreatedate(new Date());
			eppo.setOperatorid(users.getGuid());
			
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			
			this.eppoService.saveEppo(eppo,users,imageids);
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
	public void batchsave(HttpServletRequest request,Eppo eppo,PrintWriter out){
		JSONObject json=new  JSONObject();
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			eppo.setCreatedate(new Date());
			eppo.setOperatorid(users.getGuid());

			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			JSONArray batchArray=JSONArray.parseArray(request.getParameter("batch"));
			this.eppoService.batchSaveEppo(eppo,users,imageids,batchArray);
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
			Eppo eppo = new Eppo();
			eppo.setGuid(guid);
			eppo = this.eppoService.view(eppo);
			req.setAttribute("eppo", eppo);
			
			Upimage image = new Upimage();
			image.setRelatedid(guid);
			List<Upimage> list = this.upimageService.find(image);
			req.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "G1/eppo_detail";
	}

}
