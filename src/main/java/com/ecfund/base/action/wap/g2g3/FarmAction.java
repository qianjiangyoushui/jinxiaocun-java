package com.ecfund.base.action.wap.g2g3;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.model.g0.Seedbed;
import com.ecfund.base.model.g2g3.Farm;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.g2g3.FarmService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/farm")
public class FarmAction {

	@Autowired
	private FarmService farmService;

	@RequestMapping(value = "/page.action", method = RequestMethod.POST)
	@ResponseBody
	public void page(PrintWriter out, HttpServletRequest request, Page page) {
		JSONObject json = new JSONObject();
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			Farm farm = new Farm();
			farm.setCompanyid(users.getCompany().getGuid());
			Page pagelist = farmService.findPageList(farm, page.getBegin(), page.getPageSize());
			pagelist.setPageNo(page.getPageNo());
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
			JSONObject jsons = JSONObject.fromObject(pagelist, config);
			json.put("page", jsons);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		} finally {
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}

	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(String type, Model model) {
		model.addAttribute("type", type);
		return "G2G3/farm_list";
	}

	@RequestMapping(value = "/add.action", method = RequestMethod.GET)
	public String add(String type, Model model) {
		model.addAttribute("type", type);
		return "G2G3/farm_add";
	}

	@RequestMapping(value = "/check.action", method = RequestMethod.POST)
	@ResponseBody
	public void check(HttpServletRequest request, PrintWriter out, String farmname) {
		JSONObject json = new JSONObject();
		try {
			Users user = (Users) request.getSession().getAttribute("user");
			Farm farm = new Farm();
			farm.setFarmname(farmname);
			farm.setCompanyid(user.getCompany().getGuid());
			farm = farmService.view(farm);
			if (farm != null) {
				json.put("msg", "fail");
			} else {
				json.put("msg", "ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		} finally {
			out.print(json.toString());
			out.flush();
			out.close();
		}

	}
	@RequestMapping(value = "/checkcode.action", method = RequestMethod.POST)
	@ResponseBody
	public void checkcode(HttpServletRequest request, PrintWriter out, String farmcode) {
		JSONObject json = new JSONObject();
		try {
			Users user = (Users) request.getSession().getAttribute("user");
			Farm farm = new Farm();
			farm.setFarmcode(farmcode);
			farm.setCompanyid(user.getCompany().getGuid());
			farm = farmService.view(farm);
			if (farm != null) {
				json.put("msg", "fail");
			} else {
				json.put("msg", "ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		} finally {
			out.print(json.toString());
			out.flush();
			out.close();
		}

	}

	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	@ResponseBody
	public void save(Farm farm, HttpServletRequest request, PrintWriter out) {
		JSONObject json = new JSONObject();
		try {
			// 完善数据
			Users user = (Users) request.getSession().getAttribute("user");

			farm.setCompanyid(user.getCompany().getGuid());
			farm.setCreatedate(new Date(System.currentTimeMillis()));
			farm.setOperatorid(user.getGuid());

			JSONArray imageids = JSONArray.parseArray(request.getParameter("imageids"));

			farmService.saveFarm(farm, user, imageids);

			json.put("msg", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		} finally {
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}

	@RequestMapping(value = "/detail.action", method = RequestMethod.GET)
	public String detail(String farmid, String type, Model model,HttpServletRequest request,String operate) {
		try {
			Farm farm = new Farm();
			farm.setGuid(farmid);
			farm = farmService.findDetail(farm);
			model.addAttribute("farm", farm);
			model.addAttribute("type", type);
			
			request.getSession().setAttribute("operate", operate);
			return "G2G3/farm_detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

	@RequestMapping(value = "/edit.action", method = RequestMethod.GET)
	public String edit(String farmid, String type, Model model) {
		try {
			Farm farm = new Farm();
			farm.setGuid(farmid);
			farm = farmService.view(farm);
			model.addAttribute("farm", farm);
			model.addAttribute("type", type);
			return "G2G3/farm_edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

	@RequestMapping(value = "/update.action", method = RequestMethod.POST)
	public String update(Farm farm, String imageid,String type,HttpServletRequest request,Model model) {
		try {
			// 完善数据
			Users user = (Users) request.getSession().getAttribute("user");
			farm.setOperatorid(user.getGuid());

			farmService.updatefarm(farm, imageid);
			
			model.addAttribute("type", type);
			model.addAttribute("farmid", farm.getGuid());
			return "redirect:detail.action";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

}
