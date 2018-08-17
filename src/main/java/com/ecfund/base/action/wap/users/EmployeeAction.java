package com.ecfund.base.action.wap.users;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.users.Department;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.MD5Utils;
import com.ecfund.base.util.common.Page;

/**
 * 员工管理
 * 
 * @author xxl
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeAction {

	@Autowired
	private UsersService userService;

	@Autowired
	private DictionaryService dictService;

	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list() {
		return "employee/list";
	}

	@RequestMapping(value = "/page.action", method = RequestMethod.POST)
	public void list(Page page, PrintWriter out, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			Users user = new Users();
			user.setCompanyid(users.getCompany().getGuid());
			Page listpage = userService.find(user, page.getBegin(), page.getPageSize());
			listpage.setPageNo(page.getPageNo());
			json.put("page", listpage);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "fail");
		} finally {
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}

	@RequestMapping(value = "/add.action", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		try {
			// 获取部门列表
			Dictionary dict = new Dictionary();
			dict.setBelongsid("1");
			List<Dictionary> dicts = dictService.find(dict);
			model.addAttribute("dicts", dicts);
			return "employee/add";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public String save(Users user, String departid, HttpServletRequest request) {
		try {
			// 获取当前员工信息
			Users users = (Users) request.getSession().getAttribute("user");
			// 完善数据
			user.setPassword(MD5Utils.encryString(user.getPassword()));
			user.setRegistdate(new Date(System.currentTimeMillis()));

			Department depart = new Department();
			depart.setCompanyid(users.getCompany().getGuid());
			depart.setDepartid(departid);
			userService.addEmployee(user, depart);

			return "redirect:list.action";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

	/**
	 * 员工详细
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/detail.action", method = RequestMethod.GET)
	public String detail(String userid, Model model) {
		try {
			Users users = new Users();
			users.setGuid(userid);
			users = userService.view(users);
			model.addAttribute("users", users);

			// 获取部门列表
			Dictionary dict = new Dictionary();
			dict.setBelongsid("1");
			List<Dictionary> dicts = dictService.find(dict);
			model.addAttribute("dicts", dicts);

			return "employee/detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

	/**
	 * 修改职工信息
	 * 
	 * @param user
	 * @param departid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update.action", method = RequestMethod.POST)
	public String update(Users user, String departguid, String departid, HttpServletRequest request) {
		try {
			// 获取当前员工信息
			Users users = (Users) request.getSession().getAttribute("user");
			// 完善数据
//			user.setPassword(MD5Utils.encryString(user.getPassword()));
			user.setRegistdate(new Date(System.currentTimeMillis()));

			Department depart = new Department();
			depart.setGuid(departguid);
			depart.setCompanyid(users.getCompany().getGuid());
			depart.setDepartid(departid);

			userService.updateEmployee(user, depart);

			return "redirect:list.action";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
}
