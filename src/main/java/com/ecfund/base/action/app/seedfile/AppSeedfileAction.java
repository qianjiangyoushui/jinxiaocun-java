package com.ecfund.base.action.app.seedfile;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecfund.base.common.Message;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.DTOBuilder;
import com.ecfund.base.util.common.DateUtil;
import com.ecfund.base.util.common.JsonUtils;
import com.ecfund.base.util.common.Page;

@Controller
@RequestMapping("/app_seedfile")
public class AppSeedfileAction{


	@Autowired
	private SeedfileService seedfileService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping(value = "/list.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void list(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
			Message message=new Message();
		try {
			Seedfile seedfile=new Seedfile();
			seedfile=getSeedfilePara(request,seedfile);
			Page listpage =seedfileService.findhxm(seedfile,page.getBegin(), page.getPageSize());
			listpage.setPageNo(page.getPageNo());
			message.setPage("listpage", listpage);
			message.setObj("years", DateUtil.getSysYear());
			message.setObj("curryear",seedfile.getYears());
			message.setObj("dictionary",dictionaryService.find(null));
			message.sendMsg(response, message.getJsonOne());
		} catch (Exception e) {
			e.printStackTrace();
			message.setFlag(Message.FALSE);
			message.sendMsg(response, message.getJsonOne());
		}

	}

	@RequestMapping(value = "/edit.action", method = RequestMethod.POST)
	@ResponseBody
	public void edit(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
			Message message=new Message();
		try {
			Seedfile seedfile = (Seedfile) JsonUtils.json2obj(request.getParameter("seedfile"), Seedfile.class);
//			Seedfile seedfile=(Seedfile) DTOBuilder.getDTO(request,Seedfile.class);
			seedfile=seedfileService.view(seedfile);
			message.setObj("seedfile", seedfile);
			message.sendMsg(response, message.getJsonOne());
		} catch (Exception e) {
			e.printStackTrace();
			message.setFlag(Message.FALSE);
			message.sendMsg(response, message.getJsonOne());
		}
	}

	@RequestMapping(value = "/save.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void save(Seedfile seedfile,Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
		Message message=new Message();
		try {
//			Seedfile seedfile = (Seedfile) JsonUtils.json2obj(request.getParameter("seedfile"), Seedfile.class);
//			 seedfile=(Seedfile) DTOBuilder.getDTO(request,Seedfile.class);
			seedfile=getSeedfile(request,seedfile);
			seedfileService.insert(seedfile);
			message.sendMsg(response, message.getJsonOne());
		} catch (Exception e) {
			e.printStackTrace();
			message.setFlag(Message.FALSE);
			message.sendMsg(response, message.getJsonOne());
		}
	}

	
	@RequestMapping(value = "/detail.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void detail(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
		Message message=new Message();
		try {
			Seedfile seedfile = (Seedfile) JsonUtils.json2obj(request.getParameter("seedfile"), Seedfile.class);
			seedfile=seedfileService.view(seedfile);
			message.setObj("seedfile", seedfile);
			message.sendMsg(response, message.getJsonOne());
		} catch (Exception e) {
			e.printStackTrace();
			message.setFlag(Message.FALSE);
			message.sendMsg(response, message.getJsonOne());
		}
	}
	@RequestMapping(value = "/update.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void update(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
		Message message=new Message();
		try {
			Seedfile seedfile = (Seedfile) JsonUtils.json2obj(request.getParameter("seedfile"), Seedfile.class);
			seedfile=getSeedfile(request,seedfile);
			seedfileService.update(seedfile);
			message.sendMsg(response, message.getJsonOne());
		} catch (Exception e) {
			e.printStackTrace();
			message.setFlag(Message.FALSE);
			message.sendMsg(response, message.getJsonOne());
		}
	}
	
	
	public Seedfile getSeedfile(HttpServletRequest request,Seedfile seedfile){
		Users user=getUserByid(request);
		seedfile.setOperatorid(user.getGuid());
		seedfile.setCompanyid(user.getCompany().getGuid());
		seedfile.setCreatedate(new Date());
		seedfile.setLevel("1");
		seedfile.setType("1");
		seedfile.setVisible("1");
		return  seedfile;
	}
	public Seedfile getSeedfilePara(HttpServletRequest request,Seedfile seedfile){
		Users user=getUserByid(request);
		seedfile.setCompanyid(user.getCompany().getGuid());
		seedfile.setLevel("1");
		seedfile.setType("1");
		seedfile.setVisible("1");
//		if(DateUtil.isNullOrEmpty(seedfile.getYears()) ){
//			seedfile.setYears(Calendar.getInstance().get(Calendar.YEAR));
//		}
		return  seedfile;
	}
	
	public Users getUserByid(HttpServletRequest request){
		Users users=new Users();
		users.setGuid(request.getParameter("userid"));
		try {
			users=usersService.view(users);
			if(null==users){
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
}
