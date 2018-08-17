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
import com.ecfund.base.model.eliteG1.Irrigation;
import com.ecfund.base.model.eliteG1.Watering;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.eliteG1.WateringService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.util.common.DTOBuilder;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

/**
 * 
 * 新灌溉 
 * 
 * @author wf
 *
 */
@Controller
@RequestMapping("/watering")
public class WateringAction {
	
	@Autowired
	private WateringService wateringService;
	
	@Autowired
	private UpimageService upimageService;
	
	
	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(HttpServletRequest request,Page page){
		try {
			Watering watering = (Watering) DTOBuilder.getDTO(request,Watering.class);
			request.setAttribute("watering", watering);
			String operate = request.getParameter("operate");
			request.setAttribute("operate", operate);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "G1/watering_list";
	}
	
	@RequestMapping(value = "/pageList.action", method = RequestMethod.POST)
	public void pageList(HttpServletRequest request,Page page,PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			Watering watering = (Watering) DTOBuilder.getDTO(request,Watering.class);
			Page listpage =wateringService.find(watering,page.getBegin(), page.getPageSize());
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
		Users users = (Users) request.getSession().getAttribute("user");
		Watering watering = (Watering) DTOBuilder.getDTO(request,Watering.class);
		request.setAttribute("watering", watering);
		request.setAttribute("users", users);
		return "G1/watering_add";
	}
	
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public void save(HttpServletRequest request, Watering watering ,PrintWriter out){
		JSONObject json=new  JSONObject();
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			watering.setCreatedate(new Date());
			watering.setOperatorid(users.getGuid());
			
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			
			this.wateringService.saveWatering(watering, users, imageids);
			
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
	public void batchsave(HttpServletRequest request, Watering watering ,PrintWriter out){
		JSONObject json=new  JSONObject();
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			watering.setCreatedate(new Date());
			watering.setOperatorid(users.getGuid());
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			JSONArray batchArray=JSONArray.parseArray(request.getParameter("batch"));
			this.wateringService.batchsaveWatering(watering, users, imageids,batchArray);

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
			Watering watering = new Watering();
			watering.setGuid(guid);
			watering = this.wateringService.view(watering);
			req.setAttribute("watering", watering);
			
			Upimage image = new Upimage();
			image.setRelatedid(guid);
			List<Upimage> list = this.upimageService.find(image);
			req.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "G1/watering_detail";
	}


}
