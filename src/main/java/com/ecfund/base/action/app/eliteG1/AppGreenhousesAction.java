package com.ecfund.base.action.app.eliteG1;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecfund.base.common.Message;
import com.ecfund.base.model.eliteG1.Greenhouses;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.eliteG1.GreenhousesService;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.util.common.DTOBuilder;
import com.ecfund.base.util.common.DateUtil;
import com.ecfund.base.util.common.JsonUtils;
import com.ecfund.base.util.common.Page;

/**
 * app大棚接口
 * 
 * @author wf
 *
 */

@Controller
@RequestMapping("/app_greenhouses")
public class AppGreenhousesAction {

	@Autowired
	private GreenhousesService greenhousesService;
	
	@Autowired
	private DictionaryService dictService;
	
	@Autowired
	private SeedfileService seedfileService;
	
	
	@RequestMapping(value = "/index.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void index(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response){
		Message message=new Message();
		try {
			Greenhouses greenhouses = new Greenhouses();
			Page listpage =greenhousesService.find(greenhouses,page.getBegin(), page.getPageSize());
			listpage.setPageNo(page.getPageNo());
			message.setPage("listpage", listpage);
			message.sendMsg(response, message.getJsonOne());
		} catch (Exception e) {
			e.printStackTrace();
			message.setFlag(Message.FALSE);
			message.sendMsg(response, message.getJsonOne());
		}
	}
	
	
	@RequestMapping(value = "/list.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void list(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response,Model model) {
		Message message=new Message();
		try {
			Seedfile seedfile=(Seedfile) DTOBuilder.getDTO(request,Seedfile.class);
			seedfile=getSeedfilePara(request,seedfile);
			Page listpage =seedfileService.find(seedfile,page.getBegin(), page.getPageSize());
			listpage.setPageNo(page.getPageNo());
			message.setPage("listpage", listpage);
			message.sendMsg(response, message.getJsonOne());
		} catch (Exception e) {
			e.printStackTrace();
			message.setFlag(Message.FALSE);
			message.sendMsg(response, message.getJsonOne());
		}
	}
	
	public Seedfile getSeedfilePara(HttpServletRequest request,Seedfile seedfile){
		Users user=(Users)request.getSession().getAttribute("user");
		seedfile.setCompanyid(user.getCompany().getGuid());
		seedfile.setLevel("3");
		seedfile.setType("3");
		seedfile.setVisible("1");
		if(DateUtil.isNullOrEmpty(seedfile.getYears()) ){
			seedfile.setYears(Calendar.getInstance().get(Calendar.YEAR));
		}
		return  seedfile;
	}
	
	
	@RequestMapping(value = "/save.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void save(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
		Message message=new Message();
		try {
			Seedfile seedfile = (Seedfile) JsonUtils.json2obj(request.getParameter("seedfile"), Seedfile.class);
			seedfile=getSeedfile(request,seedfile);
			seedfileService.insert(seedfile);
			message.sendMsg(response, message.getJsonOne());
		} catch (Exception e) {
			e.printStackTrace();
			message.setFlag(Message.FALSE);
			message.sendMsg(response, message.getJsonOne());
		}
	}
	
	public Seedfile getSeedfile(HttpServletRequest request,Seedfile seedfile){
		Users user=(Users)request.getSession().getAttribute("user");
		seedfile.setOperatorid(user.getGuid());
		seedfile.setCompanyid(user.getCompany().getGuid());
		seedfile.setCreatedate(new Date());
		seedfile.setLevel("3");
		seedfile.setType("3");
		seedfile.setVisible("1");
		return  seedfile;
	}
	
	
	@RequestMapping(value = "/saveHouse.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void saveHouse(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
		Message message=new Message();
		try {
			Greenhouses greenhouses = (Greenhouses) JsonUtils.json2obj(request.getParameter("greenhouses"), Greenhouses.class);
			Users user = (Users) request.getSession().getAttribute("user");
			greenhouses.setCompanyid(user.getCompany().getGuid());
			greenhouses.setCreatedate(new Date(System.currentTimeMillis()));
			greenhouses.setOperatorid(user.getGuid());
			this.greenhousesService.insert(greenhouses);
			message.sendMsg(response, message.getJsonOne());
		} catch (Exception e) {
			e.printStackTrace();
			message.setFlag(Message.FALSE);
			message.sendMsg(response, message.getJsonOne());
		}
	}
}
