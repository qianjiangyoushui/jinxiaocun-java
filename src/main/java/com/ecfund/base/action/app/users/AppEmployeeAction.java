package com.ecfund.base.action.app.users;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.users.Company;
import com.ecfund.base.model.users.Department;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.users.CompanyService;
import com.ecfund.base.service.users.DepartmentService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.JsonUtils;
import com.ecfund.base.util.common.MD5Utils;
import com.ecfund.base.util.common.Page;

/**
 * 职工添加
 * 
 * @author xxl
 *
 */
@Controller
@RequestMapping("/app_employee")
public class AppEmployeeAction {

	@Autowired
	private UsersService userService;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private DictionaryService dictService;

	/**
	 * 职工分页列表
	 * 
	 * @param page
	 * @param out
	 * @param request
	 */
	@RequestMapping(value = "/page.action", method = RequestMethod.POST)
	public void list(Page page, PrintWriter out, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Users users = new Users();
			users.setGuid(request.getParameter("userid"));
			users=userService.view(users);
			
			Users user = new Users();
			user.setCompanyid(users.getCompany().getGuid());
			Page listpage = userService.find(user, page.getBegin(), page.getPageSize());
			listpage.setPageNo(page.getPageNo());
			json.put("data", listpage);
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

	/**
	 * 获取部门列表
	 * @param out
	 */
	@RequestMapping(value="/getdepart.action",method=RequestMethod.POST)
	@ResponseBody
	public void getdepart(PrintWriter out) {
		JSONObject json=new JSONObject();
		try {
			// 获取部门列表
			Dictionary dict = new Dictionary();
			dict.setBelongsid("1");
			List<Dictionary> dicts = dictService.find(dict);
			
			json.put("msg", "ok");
			json.put("data", dicts);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.print(json.toJSONString());
			out.flush();
			out.close();
		}
	}

	/**
	 * 保存职工用户
	 * @param user
	 * @param departid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	@ResponseBody
	public void save(String departid, HttpServletRequest request,PrintWriter out) {
		JSONObject json=new JSONObject();
		try {
			Users user=(Users)JsonUtils.json2obj(request.getParameter("user"), Users.class);
			
			// 获取当前员工信息
			Users users = new Users();
			users.setGuid(request.getParameter("userid"));
			users=userService.view(users);
			// 完善数据
			user.setPassword(MD5Utils.encryString(user.getPassword()));
			user.setRegistdate(new Date(System.currentTimeMillis()));

			Department depart = new Department();
			depart.setCompanyid(users.getCompany().getGuid());
			depart.setDepartid(departid);
			userService.addEmployee(user, depart);
			
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
	
	
	/**
	 * 员工详细
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/detail.action", method = RequestMethod.POST)
	@ResponseBody
	public void detail(String userid, PrintWriter out) {
		JSONObject json=new JSONObject();
		try {
			Users users = new Users();
			users.setGuid(userid);
			users = userService.view(users);
			
			
			// 获取部门列表
			Dictionary dict = new Dictionary();
			dict.setBelongsid("1");
			List<Dictionary> dicts = dictService.find(dict);
			
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("users", users);
			map.put("dicts", dicts);//部门
			json.put("msg", "ok");
			json.put("data", map);
			
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 修改职工信息
	 * 
	 * @param departid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update.action", method = RequestMethod.POST)
	@ResponseBody
	public void update(String departguid, String departid, PrintWriter out,HttpServletRequest request) {
		JSONObject json=new JSONObject();
		try {
			Users user=(Users)JsonUtils.json2obj(request.getParameter("user"), Users.class);
			
			// 获取当前员工信息
			Users users = new Users();
			users.setGuid(request.getParameter("userid"));
			users=userService.view(users);
			// 完善数据
			user.setPassword(MD5Utils.encryString(user.getPassword()));
			user.setRegistdate(new Date(System.currentTimeMillis()));

			Department depart = new Department();
			depart.setGuid(departguid);
			depart.setCompanyid(users.getCompany().getGuid());
			depart.setDepartid(departid);

			userService.updateEmployee(user, depart);

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
	/**
	 * 检查电话号码
	 * 
	 * @param out
	 * @param request
	 */
	@RequestMapping(value = "/checkphone.action", method = RequestMethod.POST)
	@ResponseBody
	public void checkphone(String telphone, PrintWriter out) {
		JSONObject json = new JSONObject();
		try {
			Users user = new Users();
			user.setTelphone(telphone);
			user = userService.view(user);
			if (user != null) {
				json.put("msg", "ok");// 电话号码存在
			} else {
				json.put("msg", "fail");// 电话号码不存在
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

	/**
	 * 检查公司
	 * 
	 * @param out
	 * @param request
	 */
	@RequestMapping(value = "/checkpcompany.action", method = RequestMethod.POST)
	@ResponseBody
	public void checkpcompany(String companyname, PrintWriter out) {
		JSONObject json = new JSONObject();
		try {
			Company company = new Company();
			company.setCompanyname(companyname);
			company = companyService.view(company);
			if (company != null) {
				json.put("msg", "ok");// 公司存在
			} else {
				json.put("msg", "fail");// 公司不存在
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
}
