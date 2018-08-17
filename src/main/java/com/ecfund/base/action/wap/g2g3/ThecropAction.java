package com.ecfund.base.action.wap.g2g3;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecfund.base.model.g2g3.Thecrop;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.g2g3.ThecropService;
import com.ecfund.base.util.common.DateUtil;

@Controller
@RequestMapping("/thecrop")
public class ThecropAction {

	@Autowired
	private ThecropService thecropService;
	
	@RequestMapping(value="/add.action",method=RequestMethod.GET)
	public String add(String plotid,String type,Model model){
		model.addAttribute("plotid", plotid);
		model.addAttribute("type", type);
		model.addAttribute("years", DateUtil.getSysYear());
		return "G2G3/thecrop_add";
	}
	
	@RequestMapping(value="/save.action",method=RequestMethod.POST)
	public  String save(Thecrop thecrop,HttpServletRequest request,Model model){
		try {
			Users users=(Users)request.getSession().getAttribute("user");
			thecrop.setCreatedate(new Date(System.currentTimeMillis()));
			thecrop.setOperatorid(users.getGuid());
			thecropService.insert(thecrop);
			
			model.addAttribute("type", request.getParameter("type"));
			model.addAttribute("plotid", thecrop.getPlotid());
			return "redirect:/plot/detail.action";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		
	}
}
