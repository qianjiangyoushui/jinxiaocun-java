package com.ecfund.base.action.wap.depot;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecfund.base.model.depot.Depot;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.depot.DepotService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/depot")
public class DepotAction {

	@Autowired
	private DepotService depotService;
	
	@RequestMapping(value="/index.action",method=RequestMethod.GET)
	public String index(){
		return "depot/index";
	}
	@RequestMapping(value="/depot_index.action",method=RequestMethod.GET)
	public String depot_index(Model model,Depot depot,HttpServletRequest request) throws Exception{
		depot=depotService.view(depot);
		model.addAttribute("depot", depot);
		return "depot/depot_index";
	}
	
	@RequestMapping(value="/page.action",method=RequestMethod.POST)
	@ResponseBody
	public void page(Page page,PrintWriter out,HttpServletRequest request){
		JSONObject json=new JSONObject();
		try {
			Users user=(Users)request.getSession().getAttribute("user");
			Depot depot=new Depot();
			depot.setCompanyid(user.getCompany().getGuid());
			Page pagelist=depotService.find(depot, page.getBegin(), page.getPageSize());
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
	
	@RequestMapping(value="/add.action",method=RequestMethod.GET)
	public String add(){
		return "depot/depot_add";
	}

	
	@RequestMapping(value="/save.action",method=RequestMethod.POST)
	public String save(Depot depot,HttpServletRequest request){
		try {
			//完善数据
			Users user=(Users)request.getSession().getAttribute("user");
			depot.setCompanyid(user.getCompany().getGuid());
			depot.setCreatedate(new Date(System.currentTimeMillis()));
			depot.setOperatorid(user.getGuid());
			depotService.insert(depot);
			return "redirect:index.action";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
}
