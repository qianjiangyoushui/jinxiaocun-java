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
import com.ecfund.base.model.eliteG1.Manure;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.dao.eliteG1.ManureDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-07 11:15
 * @filename ManureService.java
 * 
 */

@Service
public class ManureService extends BaseService<Manure> {

	//@Autowired
	//private ManureDAO manureDAO;

	@Autowired
	public void setBaseDAO(ManureDAO manureDAO) {
		super.setBaseDAO(manureDAO);
	}
	
	@Autowired
	private UpimageService upimageService;
	
	@Autowired
	private LogsService logService;
	
	
	@Transactional
	public void saveManure(Manure manure, Users user, JSONArray imageids) throws Exception {
		
		String manureid = this.insert(manure);

		for(int i=0;i<imageids.size();i++){
			Upimage image = new Upimage();
			image.setGuid(imageids.get(i).toString());
			image.setRelatedid(manureid);
			upimageService.update(image);
		}
		
		Logs log=new Logs();
		log.setDescription("新增施肥记录");
		log.setOperatedate(new Date(System.currentTimeMillis()));
		log.setOperatorid(user.getGuid());
		log.setRelatedid(manure.getSeedfileid());
		
		logService.insert(log);
	}
	@Transactional
	public void batchsaveManure(Manure manure, Users user, JSONArray imageids,JSONArray batch) throws Exception {

		for(int j=0;j<batch.size();j++){
			manure.setSeedfileid(batch.get(j).toString());
			String manureid = this.insert(manure);
			for(int i=0;i<imageids.size();i++){
				Upimage image = new Upimage();
				image.setGuid(imageids.get(i).toString());
				image.setRelatedid(manureid);
				upimageService.update(image);
			}

			Logs log=new Logs();
			log.setDescription("新增施肥记录");
			log.setOperatedate(new Date(System.currentTimeMillis()));
			log.setOperatorid(user.getGuid());
			log.setRelatedid(manure.getSeedfileid());
			logService.insert(log);
		}
	}

}