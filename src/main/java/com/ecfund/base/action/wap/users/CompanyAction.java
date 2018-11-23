package com.ecfund.base.action.wap.users;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Constants;
import com.ecfund.base.model.publics.Message;
import com.ecfund.base.model.users.Areapresident;
import com.ecfund.base.model.users.Company;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.publics.MessageService;
import com.ecfund.base.service.users.AreapresidentService;
import com.ecfund.base.service.users.CompanyService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.common.SuccessFailView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/company")
public class CompanyAction {
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private AreapresidentService  areapresidentService;
	@Autowired
	private UsersService  usersService;

	@RequestMapping(value = "/manager.action")
	public String manager(Page page,HttpServletRequest req) throws Exception{
		int count = (page.getPageCount() - 1) * page.getPageSize();
		Company company = new Company();
		company.setCompanyname(req.getParameter("companyname"));
		company.setStatus(req.getParameter("status"));
		Page pagelist = this.companyService.find(company, page.getBegin(), page.getPageSize());
		pagelist.setPageNo(page.getPageNo());
		pagelist.setCountItem(this.companyService.findCount(company));
		System.out.println(pagelist.getCountItem());
		req.setAttribute("pages", pagelist);
		req.setAttribute("pager", pagelist.getRows());
		return "company/manager";
	}

	@RequestMapping(value = "/list.action")
	public String list(Page page,HttpServletRequest req) throws Exception{
		int count = (page.getPageCount() - 1) * page.getPageSize();
		Company company = new Company();
		company.setCompanyname(req.getParameter("companyname"));
		company.setStatus(req.getParameter("status"));
		Page pagelist = this.companyService.find(company, page.getBegin(), page.getPageSize());
		pagelist.setPageNo(page.getPageNo());
		pagelist.setCountItem(this.companyService.findCount(company));
		System.out.println(pagelist.getCountItem());
		req.setAttribute("pages", pagelist);
		req.setAttribute("pager", pagelist.getRows());
		return "company/list";
	}

	@RequestMapping(value = "/review.action")
	public String review(String busid,String mid, HttpServletRequest request, Model model) throws Exception{
		Company company = new Company();
		company.setGuid(busid);
		company = companyService.view(company);
		model.addAttribute("company",company);
		model.addAttribute("mid",mid);
		return "company/review";
	}

	@RequestMapping(value = "/check.action")
	public String check(String guid,String status,String mid,HttpServletRequest request, Model model) throws Exception{
		Company company = new Company();
		company.setGuid(guid);
		company.setStatus(status);
		this.companyService.update(company);
		company = companyService.view(company);
		Message message = new Message();
		message.setGuid(mid);
		if("2".equals(status)){
			message.setState("已审核通过");
		}else{
			message.setState("待审核");
		}
		messageService.update(message);
		model.addAttribute("company",company);
		model.addAttribute("mid",mid);
		return "company/review";
	}
	@RequestMapping(value = "/presidentList.action")
	public String presidentList(HttpServletRequest request, Model model) throws Exception{
		Users user = (Users) request.getSession().getAttribute("user");
		Areapresident areapresident = new Areapresident();
		areapresident.setUserid(user.getGuid());
		areapresident = areapresidentService.view(areapresident);
		String[] array = areapresident.getArea().split(",");
		String[] arrayList = new String[array.length];
		List<Company> companyList = new ArrayList<Company>();
		for (int i = 0; i < array.length; i++){
			String p = array[i].substring(0,2);
			arrayList[i]=p;
			Company company = new Company();
			company.setProvince(p);
			List<Company>  clist= companyService.find("findIn",company);
			companyList.addAll(clist);
		}

		model.addAttribute("companyList",companyList);
		return "company/presidentList";
	}
	@RequestMapping(value = "/listPresident.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String listPresident(HttpServletRequest request) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = usersService.view(user);
		Areapresident areapresident = new Areapresident();
		areapresident.setUserid(user.getGuid());
		areapresident = areapresidentService.view(areapresident);
		String[] array = areapresident.getArea().split(",");
		String[] arrayList = new String[array.length];
		List<Company> companyList = new ArrayList<Company>();
		for (int i = 0; i < array.length; i++){
			String p = array[i].substring(0,2);
			arrayList[i]=p;
			Company company = new Company();
			company.setProvince(p);
			List<Company>  clist= companyService.find("findIn",company);
			companyList.addAll(clist);
		}

		JSONObject content = new JSONObject();
		content.put("companyList", companyList);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}

	@RequestMapping(value = "/aud.action")
	public View aud(HttpServletRequest req){
		String guid = req.getParameter("guid");
		String status  = req.getParameter("status");
		
		
		try {
			Company company = new Company();
			company.setGuid(guid);
			company = this.companyService.view(company);
			
			if(!"1".equals(company.getStatus())){
				return new SuccessFailView(false,"该公司已经审核 ，不能再次审核");
			}
			company.setGuid(guid);
			company.setStatus(status);
			this.companyService.update(company);
		} catch (Exception e) {
			e.printStackTrace();
			return new SuccessFailView(false,"审核失败，请联系管理员");
		}
		
		return new SuccessFailView(true,"审核成功");
	}

}
