package com.ecfund.base.action.app.users;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.model.users.Company;
import com.ecfund.base.model.users.Department;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.users.CompanyService;
import com.ecfund.base.service.users.DepartmentService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.JsonUtils;
import com.ecfund.base.util.common.MD5Utils;

@Controller
@RequestMapping("/app_user")
public class AppUsersAction {

	@Autowired
	private UsersService userService;

	@Autowired
	private DepartmentService departService;

	@Autowired
	private CompanyService companyService;

	/**
	 * 注册
	 * 
	 * @param out
	 */
	@RequestMapping(value = "/regist.action", method = RequestMethod.POST)
	@ResponseBody
	public void regist(PrintWriter out, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Users user = (Users) JsonUtils.json2obj(request.getParameter("user"), Users.class);
			// 完善用户信息
			user.setRegistdate(new Date(System.currentTimeMillis()));
			// 密码加密
			user.setPassword(MD5Utils.encryString(user.getPassword()));

			Company company = (Company) JsonUtils.json2obj(request.getParameter("company"), Company.class);
			// 完善公司信息
			company.setRegistdate(new Date(System.currentTimeMillis()));

			// 检查用户是否注册
			Users users = new Users();
			users.setTelphone(user.getTelphone());
			users = userService.view(users);

			// 检查公司是否注册
			Company com = new Company();
			com.setCompanyname(company.getCompanyname());
			com = companyService.view(com);

			if (users != null) {
				json.put("msg", "此手机号已注册");// 用户已被注册
			} else {
				if (com != null) {
					json.put("msg", "此公司已注册");// 公司已被注册
				} else {
					userService.regist(user, company);
					json.put("msg", "ok");// 注册成功
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");// 系统错误
		} finally {
			out.print(json.toString());
			out.flush();
			out.close();
		}

	}

	/**
	 * 登录
	 * 
	 * @param out
	 * @param request
	 */
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	@ResponseBody
	public void login(PrintWriter out, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Users user = (Users) JsonUtils.json2obj(request.getParameter("user"), Users.class);
			user.setPassword(MD5Utils.encryString(user.getPassword()));
			user = userService.view(user);
			if (user != null) {
				request.getSession().setAttribute("user", user);
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("userid", user.getGuid());
				json.put("data", map);
				json.put("msg", "ok");
			} else {
				json.put("msg", "fail");// 手机号或者密码错误
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");// 系统错误
		} finally {
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
	@RequestMapping(value = "/checkcompany.action", method = RequestMethod.POST)
	@ResponseBody
	public void checkpcompany(String companyname, PrintWriter out) {
		JSONObject json = new JSONObject();
		try {
			Company company=new Company();
			company.setCompanyname(companyname);
			company=companyService.view(company);
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
