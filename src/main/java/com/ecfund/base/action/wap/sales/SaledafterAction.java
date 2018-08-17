package com.ecfund.base.action.wap.sales;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.sales.Clients;
import com.ecfund.base.model.sales.Saledservice;
import com.ecfund.base.model.sales.Salesrecord;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.service.sales.ClientsService;
import com.ecfund.base.service.sales.SaledserviceService;
import com.ecfund.base.service.sales.SalesrecordService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 售后服务
 * @author xxl
 *
 */
@Controller
@RequestMapping("/saledafter")
public class SaledafterAction {
	
	@Autowired
	private ClientsService clientService;
	
	@Autowired
	private SalesrecordService salesrecordService;
	@Autowired
	private SaledserviceService saledserviceService;
	@Autowired
	private UpimageService upimageService;
	
	@RequestMapping(value="/list.action",method=RequestMethod.GET)
	public String list(String clientid,Salesrecord salesrecord,Model model) throws Exception{
		model.addAttribute("clientid", clientid);
		return "sales/saledafter_list";
	}
	
	@RequestMapping(value="/page.action",method=RequestMethod.POST)
	@ResponseBody
	public void page(Page page,String clientid,Saledservice saledservice,PrintWriter out,HttpServletRequest request){
		JSONObject json=new JSONObject();
		try {
//			Users users=(Users)request.getSession().getAttribute("user");
			saledservice.setClientid(clientid);
			Page pagelist=saledserviceService.find(saledservice, page.getBegin(), page.getPageSize());
			pagelist.setPageNo(page.getPageNo());
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			JSONObject jsons=JSONObject.fromObject(pagelist, config);
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
	
	
	@RequestMapping(value="/add.action",method=RequestMethod.GET)
	public String add(String clientid,Model model,HttpServletRequest request){
		try {
			Clients client=new Clients();
			client.setGuid(clientid);
			client=clientService.view(client);
			
			Salesrecord record=new Salesrecord();
			record.setClientid(clientid);
			
			List<Salesrecord> records=salesrecordService.find(record);
			
			model.addAttribute("client", client);
			model.addAttribute("records", records);
			model.addAttribute("date", new Date(System.currentTimeMillis()));
			return "sales/saledafter_add";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		
	}
	@RequestMapping(value="/edit.action",method=RequestMethod.GET)
	public String edit(String clientid,Saledservice saledservice,Model model,HttpServletRequest request){
		try {
			Clients client=new Clients();
			client.setGuid(clientid);
			client=clientService.view(client);
			
			Salesrecord record=new Salesrecord();
			record.setClientid(clientid);
			List<Salesrecord> records=salesrecordService.find(record);
			
			saledservice=saledserviceService.view(saledservice);
			model.addAttribute("saledservice", saledservice);
			
			
			model.addAttribute("client", client);
			model.addAttribute("records", records);
			model.addAttribute("date", new Date(System.currentTimeMillis()));
			return "sales/saledafter_edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		
	}
	
	@RequestMapping(value = "/save.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void save(String type,PrintWriter out,Saledservice saledservice,HttpServletRequest request,Model model) {
		JSONObject json=new  JSONObject();
		try {
			//完善数据
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			Users user=(Users)request.getSession().getAttribute("user");
			saledservice.setOperatorid(user.getGuid());
			saledservice.setCreatedate(new Date());
			if(StringUtils.isBlank(saledservice.getIsdisinfect())){
				saledservice.setIsdisinfect("0");
			}
			if(StringUtils.isBlank(saledservice.getIsdisinfecte())){
				saledservice.setIsdisinfecte("0");
			}
			if(StringUtils.isBlank(saledservice.getIsmedicine())){
				saledservice.setIsmedicine("0");
			}
			if(StringUtils.isBlank(saledservice.getIsremoved())){
				saledservice.setIsremoved("0");
			}
//			saledserviceService.insert(saledservice);
			saledserviceService.save(saledservice, user, imageids);
			json.put("msg", "ok");
		} catch (Exception e) {
			json.put("msg", "error");
			e.printStackTrace();
		}finally{
			out.print(json.toString());
			out.flush();
			out.close();
		}

		
	}
	@RequestMapping(value = "/update.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void update(String type,PrintWriter out,Saledservice saledservice,HttpServletRequest request,Model model) {
		JSONObject json=new  JSONObject();
		try {
			//完善数据
//			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			Users user=(Users)request.getSession().getAttribute("user");
			saledservice.setOperatorid(user.getGuid());
//			saledservice.setCreatedate(new Date());
			if(StringUtils.isBlank(saledservice.getIsdisinfect())){
				saledservice.setIsdisinfect("0");
			}
			if(StringUtils.isBlank(saledservice.getIsdisinfecte())){
				saledservice.setIsdisinfecte("0");
			}
			if(StringUtils.isBlank(saledservice.getIsmedicine())){
				saledservice.setIsmedicine("0");
			}
			if(StringUtils.isBlank(saledservice.getIsremoved())){
				saledservice.setIsremoved("0");
			}
//			saledserviceService.insert(saledservice);
			saledserviceService.update(saledservice);
			json.put("msg", "ok");
		} catch (Exception e) {
			json.put("msg", "error");
			e.printStackTrace();
		}finally{
			out.print(json.toString());
			out.flush();
			out.close();
		}
		
		
	}
	@RequestMapping(value = "/detail.action", method = RequestMethod.GET)
	public String detail(Saledservice saledservice,HttpServletRequest request,Model model) {
		try {
			
			saledservice=saledserviceService.view(saledservice);
			Upimage upimage=new Upimage();
			upimage.setRelatedid(saledservice.getGuid());
			List listimg=upimageService.find(upimage);
			model.addAttribute("saledservice", saledservice);
			model.addAttribute("listimg", listimg);
			return "sales/saledafter_detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		
		
	}
}
