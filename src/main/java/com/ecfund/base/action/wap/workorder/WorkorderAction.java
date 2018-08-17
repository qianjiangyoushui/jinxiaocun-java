package com.ecfund.base.action.wap.workorder;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.model.workorder.Workorder;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.service.workorder.WorkorderService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.common.SuccessFailView;
import com.ecfund.base.util.json.DateJsonValueProcessor;


@Controller
@RequestMapping("/workorder")
public class WorkorderAction {
	
	@Autowired
	private WorkorderService workorderService;
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value = "/index.action", method = RequestMethod.GET)
	public String index(){
		return "workorder/index";
	}

	@RequestMapping(value = "/add.action", method = RequestMethod.GET)
	public String add(HttpServletRequest req){
		try {
			Users users = new Users();
			List<Users> userList = this.usersService.find(users);
			req.setAttribute("userList", userList);
			return "workorder/add";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public View save(HttpServletRequest request,Workorder workorder){
		try {
			String canyuid = request.getParameter("canyuid");
			Users user = (Users) request.getSession().getAttribute("user");
			workorder.setCreatedate(new Date(System.currentTimeMillis()));
			workorder.setUserid(user.getGuid());
			workorder.setStatus("1");
			this.workorderService.save(workorder, canyuid);
			return new SuccessFailView(true,"保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SuccessFailView(false,"保存失败");
		}
	}
	
	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(){
		return "workorder/list";
	}
	
	@RequestMapping(value = "/pageList.action", method = RequestMethod.POST)
	@ResponseBody
	public void pageList(Page page,Workorder workorder,HttpServletRequest request,PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			Users user=(Users)request.getSession().getAttribute("user");
			workorder.setUserid(user.getGuid());
			Page pageList = this.workorderService.findpagelist(workorder, page.getBegin(), page.getPageSize());
			pageList.setPageNo(page.getPageNo());
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			JSONObject jsons=JSONObject.fromObject(pageList, config);
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
}
