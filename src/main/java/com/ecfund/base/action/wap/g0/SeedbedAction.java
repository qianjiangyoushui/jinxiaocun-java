package com.ecfund.base.action.wap.g0;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import net.sf.json.JSONObject;
import com.ecfund.base.model.g0.Seedbed;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.g0.SeedbedService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

import net.sf.json.JsonConfig;


/**
 * 苗床
 * 
 * @author xxl
 *
 */
@Controller
@RequestMapping("/seedbed")
public class SeedbedAction {

	@Autowired
	private SeedbedService seedbedService;
	
	@Autowired
	private UpimageService upimageService;
	
	@Autowired
	private SeedfileService seedfileService;

	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list() {
		return "G0/seedbed_list";
	}

	@RequestMapping(value = "/add.action", method = RequestMethod.GET)
	public String add() {
		return "G0/seedbed_add";
	}

	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	@ResponseBody
	public void save(Seedbed seedbed, HttpServletRequest request,PrintWriter out) {
		JSONObject json=new  JSONObject();
		try {
			
			// 完善数据
			Users user = (Users) request.getSession().getAttribute("user");
			seedbed.setCreatedate(new Date(System.currentTimeMillis()));
			seedbed.setCompanyid(user.getCompany().getGuid());
			seedbed.setOperatorid(user.getGuid());
			

			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			
			
			seedbedService.saveseedbed(seedbed, user, imageids);
			
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
	
	
	@RequestMapping(value = "/page.action", method = RequestMethod.POST)
	@ResponseBody
	public void page(Page page,HttpServletRequest request,PrintWriter out) {
		JSONObject json=new JSONObject();
		try {
			Users users=(Users)request.getSession().getAttribute("user");
			Seedbed seedbed=new Seedbed();
			seedbed.setCompanyid(users.getCompany().getGuid());
			
			Page pagelist=seedbedService.getPagelist(seedbed, page);
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
	
	/**
	 * 苗床详细
	 * @return
	 */
	@RequestMapping(value = "/detail.action", method = RequestMethod.GET)
	public String detail(Model model,String operate,String seedbedid,HttpServletRequest request) {
		try {
			Seedbed seed=new Seedbed();
			seed.setGuid(seedbedid);
			seed=seedbedService.findDetail(seed);
			model.addAttribute("seedbed",seed);
			request.getSession().setAttribute("operate", operate);
			return "G0/seedbed_detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	@RequestMapping(value = "/check.action", method = RequestMethod.POST)
	@ResponseBody
	public void check(HttpServletRequest request,PrintWriter out,String seedbedname){
		JSONObject json=new JSONObject();
		try {
			Users user=(Users)request.getSession().getAttribute("user");
			Seedbed seedbed=new Seedbed();
			seedbed.setSeedbedname(seedbedname);
			seedbed.setCompanyid(user.getCompany().getGuid());
			seedbed=seedbedService.view(seedbed);
			if(seedbed!=null){
				json.put("msg", "fail");
			}else{
				json.put("msg", "ok");
			}
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
	public String edit(String seedbedid,String operate,Model model){
		try {
			Seedbed seedbed=new Seedbed();
			seedbed.setGuid(seedbedid);
			seedbed=seedbedService.view(seedbed);
			model.addAttribute("seedbed", seedbed);
			model.addAttribute("operate", operate);
			return "G0/seedbed_edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	@RequestMapping(value = "/update.action", method = RequestMethod.POST)
	public String update(Seedbed seedbed,String imageid, String operate,HttpServletRequest request,Model model) {
		try {
			// 完善数据
			Users user = (Users) request.getSession().getAttribute("user");
			seedbed.setOperatorid(user.getGuid());
			
			seedbedService.updateseedbed(seedbed, imageid);
			
			model.addAttribute("operate", operate);
			model.addAttribute("seedbedid", seedbed.getGuid());
			return "redirect:detail.action";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}

	}
}
