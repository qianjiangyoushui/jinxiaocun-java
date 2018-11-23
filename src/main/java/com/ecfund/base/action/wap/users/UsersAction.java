package com.ecfund.base.action.wap.users;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.model.users.Company;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.users.CompanyService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.MD5Utils;

@Controller
@RequestMapping("/users")
public class UsersAction {

	@Autowired
	private UsersService userService;
	
	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public String login(String uri,Model model,HttpServletRequest request) {
		
		String sysInfo=request.getHeader("User-Agent");
		model.addAttribute("uri", uri);
		
		return "user/login";
	}

	@RequestMapping(value = "/dologin.action", method = RequestMethod.POST)
	@ResponseBody
	public void dologin(Users user, HttpServletRequest request,PrintWriter out) {
		JSONObject json=new JSONObject();
		try {
			user.setPassword(MD5Utils.encryString(user.getPassword()));
			user = userService.view(user);
			if (user != null) {
				if(user.getCompany()==null||user.getCompany().getStatus().equals("2")){
					request.getSession().setAttribute("user", user);
					json.put("msg", "ok");
				}else{
					json.put("msg", "登录失败,需通过审核后才能登录！");
				}
			} else {
				json.put("msg", "登录失败,用户名或密码错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "登录失败,系统错误！");
		}finally{
			out.print(json.toJSONString());
			out.flush();
			out.close();
		}
	}


	@RequestMapping(value = "/regist.action", method = RequestMethod.GET)
	public String regist() {
		return "user/regist";
	}

	@RequestMapping(value = "/doregist.action", method = RequestMethod.POST)
	public String doregist(Users users, Company company, String address, Model model) {
		try {
			//完善数据
			users.setRegistdate(new Date(System.currentTimeMillis()));
			users.setPassword(MD5Utils.encryString(users.getPassword()));
			
			String[] adds=address.split(" ");
			company.setProvince(adds[0]);
			company.setCity(adds[1]);
			company.setArea(adds[2]);
			company.setRegistdate(new Date(System.currentTimeMillis()));
			company.setStatus("1");
			userService.regist(users, company);
			return "redirect:login.action";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "系统错误,请联系管理员！");
			return "user/regist";
		}
		
	}

	/**
	 * 检查电话号码
	 * 
	 * @param out
	 * @param
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
	 * 检查电话号码
	 * 
	 * @param out
	 * @param
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
	
	
	@RequestMapping(value = "/getUserInfo.action", method = RequestMethod.POST)
	@ResponseBody
	public void checkpcompany( PrintWriter out,HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Users user=(Users)request.getSession().getAttribute("user");
			if(user!=null){
				json.put("msg", "ok");
				json.put("telphone", user.getTelphone());
				json.put("password", user.getPassword());
			}else{
				json.put("msg", "fail");
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
	
	
	@RequestMapping(value = "/remblogin.action", method = RequestMethod.POST)
	public String remblogin(String telphone,String password, HttpServletRequest request) {
		try {
			Users user=new Users();
			user.setTelphone(telphone);
			user.setPassword(password);
			user = userService.view(user);
			if (user != null) {
				if(user.getCompany().getStatus().equals("2")){
					request.getSession().setAttribute("user", user);
					return "index/index";
				}else{
					return "user/login";
				}
			} else {
				return "user/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "user/login";
		}
	}
	
}
