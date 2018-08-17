package com.ecfund.base.service.eliteG1;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Decoder;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.util.common.UUIDCreate;
import com.ecfund.base.model.eliteG1.Aphid;
import com.ecfund.base.model.g0.Seedbed;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.dao.eliteG1.AphidDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-07 10:33
 * @filename AphidService.java
 * 
 */

@Service
public class AphidService extends BaseService<Aphid> {

	//@Autowired
	//private AphidDAO aphidDAO;
	
	@Autowired
	private UpimageService upimageService;

	@Autowired
	public void setBaseDAO(AphidDAO aphidDAO) {
		super.setBaseDAO(aphidDAO);
	}
	
	@Autowired
	private LogsService logService;
	
	
	@Transactional
	public void saveAphid(Aphid aphid, Users user, JSONArray imageids) throws Exception {
		
		String aphidid = this.insert(aphid);

		for(int i=0;i<imageids.size();i++){
			Upimage image = new Upimage();
			image.setGuid(imageids.get(i).toString());
			image.setRelatedid(aphidid);
			upimageService.update(image);
		}
		
		Logs log=new Logs();
		log.setDescription("新增蚜虫记录");
		log.setOperatedate(new Date(System.currentTimeMillis()));
		log.setOperatorid(user.getGuid());
		log.setRelatedid(aphid.getSeedfileid());
		
		logService.insert(log);
	}
	
	

}