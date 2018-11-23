package com.ecfund.base.common.aop;

import com.ecfund.base.model.publics.Message;
import com.ecfund.base.model.users.Areapresident;
import com.ecfund.base.model.users.Company;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.publics.MessageService;
import com.ecfund.base.service.users.AreapresidentService;
import com.ecfund.base.util.common.MessageUitls;
import org.apache.log4j.Logger;

import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.util.common.SpringApplicationContextHolder;

import java.util.Calendar;
import java.util.List;


public class TemplateMsgSend {
	Logger log = Logger.getLogger(TemplateMsgSend.class);
	/**
	 * 更新日志
	 */
	public void seedfileUpdate(Seedfile seedfile){
		LogsService logsService = (LogsService) SpringApplicationContextHolder.getSpringBean("logsService");
		
	}
	public void payMsg(Users users, Company company){
		MessageService messageService = (MessageService) SpringApplicationContextHolder.getSpringBean("messageService");
		AreapresidentService areapresidentService = (AreapresidentService) SpringApplicationContextHolder.getSpringBean("areapresidentService");
		try {
			Areapresident areapresident = new Areapresident();
			String area = company.getProvince().substring(0,2);
			areapresident.setArea(area);
			List<Areapresident> areapresidentList = areapresidentService.find("findDetail",areapresident);
			for (Areapresident president :areapresidentList) {
				Message message = new Message();
				message.setUserid(president.getUserid());
				message.setTittle("种薯企业注册账号待审核");
				message.setDes(company.getCompanyname());
				message.setCreatedate(Calendar.getInstance().getTime());
				message.setBusid(company.getGuid());
				message.setState("待审核");
				message.setUrl("company/review.action");
				messageService.insert(message);
				String sndmsg = "您有区域内的种植企业("+company.getCompanyname()+")注册可追溯系统申请，请你打开质量追溯APP点击底部消息内查看审核。";
				MessageUitls.sendMessage(president.getUsers().getTelphone(), sndmsg, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
