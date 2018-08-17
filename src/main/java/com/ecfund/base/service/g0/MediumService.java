package com.ecfund.base.service.g0;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.model.g0.Medium;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.dao.g0.MediumDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-02 16:13
 * @filename MediumService.java
 * 
 */

@Service
public class MediumService extends BaseService<Medium> {

	@Autowired
	private MediumDAO mediumDAO;

	@Autowired
	private LogsService logService;
	
	@Autowired
	public void setBaseDAO(MediumDAO mediumDAO) {
		super.setBaseDAO(mediumDAO);
	}
	
	@Transactional
	public void savemedium(Medium medium,Users user) throws Exception{
		this.insert(medium);
		
		Logs log=new Logs();
		log.setDescription("新增培养基");
		log.setOperatedate(new Date(System.currentTimeMillis()));
		log.setOperatorid(user.getGuid());
		log.setRelatedid(medium.getSeedfileid());
		
		logService.insert(log);
	}

}