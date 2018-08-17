package com.ecfund.base.action.wap.users;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import com.ecfund.base.model.users.Company;
import com.ecfund.base.service.users.CompanyService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.common.SuccessFailView;
import com.opensymphony.xwork2.ModelDriven;


@Controller
@RequestMapping("/company")
public class CompanyAction {
	
	@Autowired
	private CompanyService companyService;
	
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
