package com.ecfund.base.common.aop;

import org.apache.log4j.Logger;

import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.util.common.SpringApplicationContextHolder;



public class TemplateMsgSend {
	Logger log = Logger.getLogger(TemplateMsgSend.class);
	/**
	 * 更新日志
	 */
	public void seedfileUpdate(Seedfile seedfile){
		LogsService logsService = (LogsService) SpringApplicationContextHolder.getSpringBean("logsService");
		
	}
	
}
