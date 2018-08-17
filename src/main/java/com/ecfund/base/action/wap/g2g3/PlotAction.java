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
import com.ecfund.base.model.g2g3.Farm;
import com.ecfund.base.model.g2g3.Plots;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.g2g3.PlotsService;

import net.sf.json.JSONObject;

/**
 * 地块信息
 * 
 * @author xxl
 *
 */
@Controller
@RequestMapping("/plot")
public class PlotAction {

	@Autowired
	private PlotsService plotService;

	@RequestMapping(value = "/add.action", method = RequestMethod.GET)
	public String add(String farmid, String type, Model model) {
		model.addAttribute("farmid", farmid);
		model.addAttribute("type", type);
		return "G2G3/plot_add";
	}

	@RequestMapping(value = "/check.action", method = RequestMethod.POST)
	@ResponseBody
	public void check(HttpServletRequest request, PrintWriter out, String plotname) {
		JSONObject json = new JSONObject();
		try {
			Users user = (Users) request.getSession().getAttribute("user");
			Plots plot = new Plots();
			plot.setPlotname(plotname);
			plot.setCompanyid(user.getCompany().getGuid());
			plot = plotService.findPlots(plot);
			if (plot != null) {
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
	public void checkcode(HttpServletRequest request, PrintWriter out, String plotcode) {
		JSONObject json = new JSONObject();
		try {
			Users user = (Users) request.getSession().getAttribute("user");
			Plots plot = new Plots();
			plot.setPlotcode(plotcode);
			plot.setCompanyid(user.getCompany().getGuid());
			plot = plotService.findPlots(plot);
			if (plot != null) {
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
	public void save(Plots plot, HttpServletRequest request, PrintWriter out) {
		JSONObject json = new JSONObject();
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			// 完善信息
			plot.setCreatedate(new Date(System.currentTimeMillis()));
			plot.setOperatorid(users.getGuid());
			plot.setCompanyid(users.getCompany().getGuid());

			JSONArray imageids = JSONArray.parseArray(request.getParameter("imageids"));

			plotService.saveplot(plot, users, imageids);

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
	public String detail(String plotid, String type, Model model,HttpServletRequest request) {
		try {
			Plots plot = new Plots();
			plot.setGuid(plotid);
			plot = plotService.view(plot);
			model.addAttribute("plot", plot);
			model.addAttribute("type", type);
			
			request.getSession().setAttribute("plotguid", plotid);
			return "G2G3/plot_detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

	@RequestMapping(value = "/edit.action", method = RequestMethod.GET)
	public String edit(String plotid, String type, Model model) {
		try {
			Plots plot = new Plots();
			plot.setGuid(plotid);
			plot = plotService.findPlots(plot);

			model.addAttribute("plot", plot);
			model.addAttribute("type", type);
			return "G2G3/plot_edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

	@RequestMapping(value = "/update.action", method = RequestMethod.POST)
	public String save(Plots plot, String imageid,String type, HttpServletRequest request, Model model) {
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			// 完善信息
			plot.setOperatorid(users.getGuid());

			plotService.updatePlot(plot, imageid);
			
			model.addAttribute("type", type);
			model.addAttribute("plotid", plot.getGuid());
			return "redirect:detail.action";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

}
