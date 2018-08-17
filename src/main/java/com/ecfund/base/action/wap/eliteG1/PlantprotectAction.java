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
import com.ecfund.base.model.eliteG1.Plantprotect;
import com.ecfund.base.model.fertilization.Fertilization;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.eliteG1.PlantprotectService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.DTOBuilder;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;


@Controller
@RequestMapping("/plantprotect")
public class PlantprotectAction {
	
	@Autowired
	private PlantprotectService plantpritectService;
	
	@Autowired
	private UpimageService upimageService;
	
	@Autowired
	private UsersService userService;
	
	
	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(HttpServletRequest request,Page page){
		try {
			Plantprotect plantprotect = (Plantprotect) DTOBuilder.getDTO(request,Plantprotect.class);
			request.setAttribute("plantprotect", plantprotect);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "G1/plantprotect_list";
	}
	
	@RequestMapping(value = "/pageList.action", method = RequestMethod.POST)
	public void pageList(HttpServletRequest request,Page page,PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			Plantprotect plantprotect = (Plantprotect) DTOBuilder.getDTO(request,Plantprotect.class);
			Page listpage =plantpritectService.find(plantprotect,page.getBegin(), page.getPageSize());
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
		Plantprotect plantprotect = (Plantprotect) DTOBuilder.getDTO(request,Plantprotect.class);
		request.setAttribute("plantprotect", plantprotect);
		Users users = (Users) request.getSession().getAttribute("user");
		request.setAttribute("uploadperson", users.getUsername());
		return "G1/plantprotect_add";
	}
	
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public void save(HttpServletRequest request,Plantprotect plantprotect,PrintWriter out){
		JSONObject json=new  JSONObject();
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			plantprotect.setCreatedate(new Date());
			plantprotect.setOperatorid(users.getGuid());
			plantprotect.setUploadperson(users.getGuid());
			
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			
			this.plantpritectService.savePlantProtect(plantprotect,users,imageids);
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
	public void batchsave(HttpServletRequest request,Plantprotect plantprotect,PrintWriter out){
		JSONObject json=new  JSONObject();
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			plantprotect.setCreatedate(new Date());
			plantprotect.setOperatorid(users.getGuid());
			plantprotect.setUploadperson(users.getGuid());

			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			JSONArray batchArray=JSONArray.parseArray(request.getParameter("batch"));
			this.plantpritectService.batchsavePlantProtect(plantprotect,users,imageids,batchArray);
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

	@RequestMapping(value = "/edit.action", method = RequestMethod.GET)
	public String edit(){
		return "G1/plantprotect_edit";
	}
	
	@RequestMapping(value = "/update.action", method = RequestMethod.POST)
	public void update(){
		
	}
	
	@RequestMapping(value = "/detail.action", method = RequestMethod.GET)
	public String detail(HttpServletRequest req){
		String guid = req.getParameter("guid");
		System.out.println(guid);
		try {
			Plantprotect p = new Plantprotect();
			p.setGuid(guid);
			p = this.plantpritectService.view(p);
			req.setAttribute("plantprotect", p);
			
			Upimage image = new Upimage();
			image.setRelatedid(guid);
			List<Upimage> list = this.upimageService.find(image);
			req.setAttribute("list", list);
			
			Users user = new Users();
			user.setGuid(p.getUploadperson());
			user = this.userService.view(user);
			req.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "G1/plantprotect_detail";
	}
}
