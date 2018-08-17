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
import com.ecfund.base.model.eliteG1.Pest;
import com.ecfund.base.model.eliteG1.Plantprotect;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.dao.eliteG1.PlantprotectDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-03 16:11
 * @filename PlantprotectService.java
 * 
 */

@Service
public class PlantprotectService extends BaseService<Plantprotect> {

	//@Autowired
	//private PlantprotectDAO plantprotectDAO;
	
	@Autowired
	private UpimageService upimageService;


	@Autowired
	public void setBaseDAO(PlantprotectDAO plantprotectDAO) {
		super.setBaseDAO(plantprotectDAO);
	}
	@Autowired
	private LogsService logService;
	

	@Transactional
	public void savePlantProtect(Plantprotect plantprotect, Users user, JSONArray imageids) throws Exception {
		
		String pid = this.insert(plantprotect);

		for(int i=0;i<imageids.size();i++){
			Upimage image = new Upimage();
			image.setGuid(imageids.get(i).toString());
			image.setRelatedid(pid);
			upimageService.update(image);
		}
		
		Logs log=new Logs();
		log.setDescription("新增植保方案记录");
		log.setOperatedate(new Date(System.currentTimeMillis()));
		log.setOperatorid(user.getGuid());
		log.setRelatedid(plantprotect.getSeedfileid());
		
		logService.insert(log);
	}
	@Transactional
    public void batchsavePlantProtect(Plantprotect plantprotect, Users user, JSONArray imageids, JSONArray batchArray)throws Exception  {
		for(int j=0;j<batchArray.size();j++){
			plantprotect.setSeedfileid(batchArray.get(j).toString());
			String pid = this.insert(plantprotect);
			for(int i=0;i<imageids.size();i++){
				Upimage image = new Upimage();
				image.setGuid(imageids.get(i).toString());
				image.setRelatedid(pid);
				upimageService.update(image);
			}
			Logs log=new Logs();
			log.setDescription("新增植保方案记录");
			log.setOperatedate(new Date(System.currentTimeMillis()));
			log.setOperatorid(user.getGuid());
			log.setRelatedid(plantprotect.getSeedfileid());
			logService.insert(log);
		}
    }
}