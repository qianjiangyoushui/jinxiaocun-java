package com.ecfund.base.action.wap.tasks;

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
import org.springframework.web.servlet.View;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.model.tasks.Tasks;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.tasks.TasksService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.service.workorder.WorkorderService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.common.SuccessFailView;
import com.ecfund.base.util.json.DateJsonValueProcessor;


@Controller
@RequestMapping("/tasks")
public class TasksAction {

	@Autowired
	private TasksService tasksService;
	
	@Autowired
	private WorkorderService workorderService;
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value = "/index.action", method = RequestMethod.GET)
	public String index(){
		return "tasks/index";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/taskList.action", method = RequestMethod.POST)
	public void taskList(HttpServletRequest req,Page page,PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			String date = req.getParameter("startdate");
			Users user=(Users)req.getSession().getAttribute("user");
			Tasks task = new Tasks();
			task.setDate(date);
			task.setUserid(user.getGuid());
			Page listpage = this.tasksService.findList(task, page.getBegin(), page.getPageSize());
			listpage.setPageNo(page.getPageNo());
			System.out.println(listpage.getRows().size());
			for(Tasks tasks : (List<Tasks>)listpage.getRows()){
				Users wuser = new Users();
				wuser.setGuid(tasks.getWorkorder().getPrincipal());
				Users u = this.usersService.view(wuser);
				tasks.getWorkorder().setUsername(u.getUsername());
			}
			
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
	
	@RequestMapping(value = "/receive.action", method = RequestMethod.POST)
	public View receive(HttpServletRequest req){
		String guid = req.getParameter("guid");
		try {
			Tasks task = new Tasks();
			task.setGuid(guid);
			task.setStatus("2");
			this.tasksService.receive(task);
		} catch (Exception e) {
			return new SuccessFailView(false,"领取失败");
		}
		return new SuccessFailView(true,"领取成功");
	}
	
	@RequestMapping(value = "/feedback.action", method = RequestMethod.GET)
	public String feedback(HttpServletRequest req){
		try {
			String guid = req.getParameter("guid");
			Tasks task = new Tasks();
			task.setGuid(guid);
			task = this.tasksService.getTasks(task);
			Users tusers = new Users();
			tusers.setGuid(task.getWorkorder().getUserid());
			tusers = this.usersService.view(tusers);
			task.getWorkorder().setUsername(tusers.getUsername());
			req.setAttribute("tasks", task);
			return "tasks/feedback";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	@RequestMapping(value = "/update.action", method = RequestMethod.POST)
	public void update(Tasks tasks,HttpServletRequest request,PrintWriter out){
		JSONObject json=new  JSONObject();
		try {
			Users user=(Users)request.getSession().getAttribute("user");
			tasks.setEnddate(new Date());
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			this.tasksService.edit(tasks,user,imageids);
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
}
