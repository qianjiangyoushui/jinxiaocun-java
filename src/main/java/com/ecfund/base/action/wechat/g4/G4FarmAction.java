package com.ecfund.base.action.wechat.g4;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.common.Constants;
import com.ecfund.base.model.g2g3.Farm;
import com.ecfund.base.model.g2g3.Plots;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.g2g3.FarmService;
import com.ecfund.base.service.g2g3.PlotsService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;

@Controller
@RequestMapping("/wechat/farm")
public class G4FarmAction {

	@Autowired
	private FarmService farmService;
	@Autowired
	private UsersService userService;
	@Autowired
	private PlotsService plotService;

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

	@RequestMapping(value = "/list.action",produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String list(HttpServletRequest request,Page page) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		Farm farm = new Farm();
		farm.setCompanyid(user.getCompany().getGuid());
		Page pagelist = farmService.findPageList(farm, page.getBegin(), page.getPageSize());
		pagelist.setPageNo(page.getPageNo());
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		com.alibaba.fastjson.JSONObject content = new com.alibaba.fastjson.JSONObject();
		content.put("list", pagelist.getRows());
		com.alibaba.fastjson.JSONObject result = new com.alibaba.fastjson.JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
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
			String skey = request.getHeader(Constants.WX_HEADER_SKEY);
			Users user = new Users();
			user.setGuid(skey);
			user = userService.view(user);

			farm.setCompanyid(user.getCompany().getGuid());
			farm.setCreatedate(new Date(System.currentTimeMillis()));
			farm.setOperatorid(user.getGuid());

			JSONArray imageids = JSONArray.parseArray(request.getParameter("imageids"));

			farmService.insert(farm);

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

	@RequestMapping(value = "/detail.action", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public @ResponseBody String detail(String guid,HttpServletRequest request) {
		try {
			Farm farm = new Farm();
			farm.setGuid(guid);
			farm = farmService.findDetail(farm);
			com.alibaba.fastjson.JSONObject content = new com.alibaba.fastjson.JSONObject();
			content.put("farm", farm);
			com.alibaba.fastjson.JSONObject result = new com.alibaba.fastjson.JSONObject();
			result.put("success",true);
			result.put("content", content);
			return result.toJSONString();
		} catch (Exception e) {
			com.alibaba.fastjson.JSONObject result = new com.alibaba.fastjson.JSONObject();
			result.put("success",false);
			result.put("error", "数据异常");
			return result.toJSONString();
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

	@RequestMapping(value = "/plotsave.action", method = RequestMethod.POST)
	@ResponseBody
	public String  plotsave(Plots plot, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			String skey = request.getHeader(Constants.WX_HEADER_SKEY);
			Users user = new Users();
			user.setGuid(skey);
			user = userService.view(user);
			// 完善信息
//			Plots plot = new Plots();
//			plot.setPlotname(plotname);
//			plot.setPlotcode(plotcode);
//			plot.setFarmid(farmid);
			plot.setCreatedate(new Date(System.currentTimeMillis()));
			plot.setOperatorid(user.getGuid());
			plot.setCompanyid(user.getCompany().getGuid());
//			BigDecimal area = new BigDecimal(plantarea);
//			plot.setPlantarea(area);
			JSONArray imageids = JSONArray.parseArray(request.getParameter("imageids"));

			plotService.insert(plot);

			json.put("msg", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		} finally {
		}
		return json.toString();
	}
	@RequestMapping(value = "/plotdetail.action", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public String  plotdetail(Plots plot, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			plot = plotService.view("findPlots",plot);
			JSONObject content = new JSONObject();
			content.put("plot",plot);
			result.put("success",true);
			result.put("content",content);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success",false);
			result.put("erro",e.getMessage());
		} finally {
		}
		return result.toString();
	}
	@RequestMapping(value = "/plotupdate.action", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public String  plotupdate(Plots plot, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			plotService.update(plot);
			JSONObject content = new JSONObject();
			content.put("plot",plot);
			result.put("success",true);
			result.put("content",content);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success",false);
			result.put("erro",e.getMessage());
		} finally {
		}
		return result.toString();
	}

}
