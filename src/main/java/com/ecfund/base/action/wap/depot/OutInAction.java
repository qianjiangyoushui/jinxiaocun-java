package com.ecfund.base.action.wap.depot;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.model.depot.Depot;
import com.ecfund.base.model.depot.Outinstorage;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.depot.DepotService;
import com.ecfund.base.service.depot.OutinstorageService;
import com.ecfund.base.service.g0.SeedbedService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/outin")
public class OutInAction {

	@Autowired
	private SeedfileService seedfileService;
	@Autowired
	private DepotService depotService;
	@Autowired
	private OutinstorageService outinstorageService;
	@RequestMapping(value="/outin_index.action",method=RequestMethod.GET)
	public String outin_index(Model model,Depot depot,HttpServletRequest request) throws Exception{
		depot=depotService.view(depot);
		Outinstorage outinstorage=new Outinstorage();
		outinstorage.setDepotid(depot.getGuid());
		List outinmap=  outinstorageService.find("findtj",outinstorage);
		model.addAttribute("outinmap", outinmap);
		model.addAttribute("depot", depot);
		return "depot/outin_index";
	}
	
	@RequestMapping(value="/page.action",method=RequestMethod.POST)
	@ResponseBody
	public void page(String guid,Page page,PrintWriter out,HttpServletRequest request){
		JSONObject json=new JSONObject();
		try {
			Outinstorage outinstorage=new Outinstorage();
			outinstorage.setDepotid(guid);
			Page pagelist=outinstorageService.find(outinstorage, page.getBegin(), page.getPageSize());
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
	
	@RequestMapping(value="/outin_add.action",method=RequestMethod.GET)
	public String outin_add(Model model,String type,Depot depot,Outinstorage outinstorage,HttpServletRequest request) throws Exception{
		Users users = (Users) request.getSession().getAttribute("user");
		Seedfile seedfile = new Seedfile();
		seedfile.setCompanyid(users.getCompany().getGuid());
		seedfile.setType("3");
		List<Seedfile> files = seedfileService.find(seedfile);
		seedfile.setType("4");
		files.addAll(seedfileService.find(seedfile));
		seedfile.setType("5");
		files.addAll(seedfileService.find(seedfile));
		model.addAttribute("files", files);
		model.addAttribute("type", type);
		model.addAttribute("depot", depot);
		return "depot/outin_add";
	}

	
	@RequestMapping(value = "/save.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void save(PrintWriter out,Depot depot,HttpServletRequest request,Outinstorage outinstorage){
		JSONObject json=new  JSONObject();
		try {
			Users user = (Users) request.getSession().getAttribute("user");
			outinstorage.setOperatorid(user.getGuid());
			outinstorage.setCreatedate(new Date());
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			outinstorageService.save(outinstorage, user, imageids);
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
	
	@RequestMapping(value="/outin_detail.action",method=RequestMethod.GET)
	public String outin_detail(Model model,Outinstorage outinstorage,HttpServletRequest request) throws Exception{
		String url=request.getRequestURI();
		url=url.substring(1, url.length());
		
		url=url+"?guid="+outinstorage.getGuid();
		model.addAttribute("url", url);
		
		outinstorage=outinstorageService.view(outinstorage);
		model.addAttribute("outinstorage", outinstorage);
		return "depot/outin_detail";
	}
}
