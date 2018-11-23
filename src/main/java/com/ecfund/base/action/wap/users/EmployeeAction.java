package com.ecfund.base.action.wap.users;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.action.wap.seedfile.SeedfileAction;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.*;
import com.ecfund.base.service.users.AreapresidentService;
import com.ecfund.base.service.users.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.MD5Utils;
import com.ecfund.base.util.common.Page;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@Autowired
	private RolesService rolesService;
	@Autowired
	private AreapresidentService areapresidentService;

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

	@RequestMapping(value = "/roleList.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String roleList(HttpServletRequest request) {
		try {
			//获取角色列表
			Roles roles = new Roles();
			roles.setDescription("1");
			List<Roles> rolesList = rolesService.find(roles);
			JSONArray jsa = new JSONArray();
			for (Roles  r:rolesList ) {
				Item item = new Item(r.getName(),r.getGuid());
				jsa.add(item);
			}
			return jsa.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public String save(Users user, String departid,String[] roles,  HttpServletRequest request) {
		try {
			// 获取当前员工信息
			Users users = (Users) request.getSession().getAttribute("user");
			// 完善数据
			user.setPassword(MD5Utils.encryString(user.getPassword()));
			user.setRegistdate(new Date(System.currentTimeMillis()));

			Department depart = new Department();
			depart.setCompanyid(users.getCompany().getGuid());
			depart.setDepartid(departid);
			userService.addEmployee(user, depart,roles);

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
	public String update(Users user, String departguid, String departid, String[] roles, HttpServletRequest request) {
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

			userService.updateEmployee(user, depart,roles);

			return "redirect:list.action";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

	@RequestMapping(value = "/arealist.action", method = RequestMethod.GET)
	public String arealist(Model model){
		Areapresident areapresident = new Areapresident();
		try {
			List<Areapresident> list =  areapresidentService.find("findDetail",areapresident);
			model.addAttribute("list",list);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "employee/arealist";
	}
	@RequestMapping(value = "/areaadd.action", method = RequestMethod.GET)
	public String areaadd(){
		return "employee/areaadd";
	}
	@RequestMapping(value = "/areasave.action", method = RequestMethod.POST)
	public String areasave(String username,String telphone,String password,String area)throws Exception{
		areapresidentService.addPresident(username,telphone,password,area);
		return "redirect:arealist.action";
	}
	@RequestMapping(value = "/areaupdate.action", method = RequestMethod.POST)
	public String areaupdate(Areapresident areapresident)throws Exception{
		areapresidentService.update(areapresident);
		return "redirect:arealist.action";
	}
	@RequestMapping(value = "/areadelete.action", method = RequestMethod.POST)
	public String areadelete(Areapresident areapresident )throws Exception{
		areapresidentService.deletePresident(areapresident);
		return "redirect:arealist.action";
	}
	@RequestMapping(value = "/areaedit.action", method = RequestMethod.GET)
	public String areaedit(String guid,Model model)throws Exception{
		Areapresident areapresident = new Areapresident();
		areapresident.setGuid(guid);
		areapresident = areapresidentService.view("findDetail",areapresident);
		model.addAttribute("areapresident",areapresident);
		return "employee/areaedit";
	}
	class Item{

		String title="";
		String value="";
		public Item(){}

		public Item(String tittle, String value) {
			this.title = tittle;
			this.value = value;
		}
		public String getObject(){
			StringBuffer sb = new StringBuffer("");
			sb.append("{title:").append("\"").append(this.getTitle()).append("\"").append(",").append("value:").append("\"").append(this.getValue()).append("\"").append("}");
			return sb.toString();
		}

		public String getTitle() {
			return title;
		}

		public void setTittle(String tittle) {
			this.title = tittle;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
