package com.ecfund.base.service.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.service.BaseService;

import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.dao.publics.LogsDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-25 16:57
 * @filename LogsService.java
 * 
 */

@Service
public class LogsService extends BaseService<Logs> {

	@Autowired
	private LogsDAO logsDAO;

	@Autowired
	public void setBaseDAO(LogsDAO logsDAO) {
		super.setBaseDAO(logsDAO);
	}

}