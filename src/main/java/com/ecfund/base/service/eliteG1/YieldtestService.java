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

import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.util.common.UUIDCreate;

import sun.misc.BASE64Decoder;

import com.ecfund.base.model.eliteG1.Yieldtest;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.dao.eliteG1.YieldtestDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-07 16:58
 * @filename YieldtestService.java
 * 
 */

@Service
public class YieldtestService extends BaseService<Yieldtest> {

	@Autowired
	private YieldtestDAO yieldtestDAO;
	@Autowired
	private UpimageService upimageService;
	@Autowired
	private LogsService logService;
	@Autowired
	public void setBaseDAO(YieldtestDAO yieldtestDAO) {
		super.setBaseDAO(yieldtestDAO);
	}
	@Transactional
	public void save(Yieldtest yieldtest, Users user, JSONArray imageids) throws Exception {
		yieldtest.setCreatedate(new Date());
		yieldtest.setOperatorid(user.getGuid());
		String seedfileid = this.insert(yieldtest);

		for(int i=0;i<imageids.size();i++){
			Upimage image = new Upimage();
			image.setGuid(imageids.get(i).toString());
			image.setRelatedid(seedfileid);
			upimageService.update(image);
		}
	}
	
	public Yieldtest findSum(Yieldtest yieldtest){
		return this.yieldtestDAO.findSum(yieldtest);
	}
	
	@Transactional
	public void saveYieldtest(Yieldtest yieldtest, Users user, JSONArray imageids) throws Exception {
		
		String yieldid = this.insert(yieldtest);

		for(int i=0;i<imageids.size();i++){
			Upimage image = new Upimage();
			image.setGuid(imageids.get(i).toString());
			image.setRelatedid(yieldid);
			upimageService.update(image);
		}
		
		Logs log=new Logs();
		log.setDescription("新增测产记录");
		log.setOperatedate(new Date(System.currentTimeMillis()));
		log.setOperatorid(user.getGuid());
		log.setRelatedid(yieldtest.getSeedfileid());
		
		logService.insert(log);
	}
}