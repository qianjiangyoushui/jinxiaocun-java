package com.ecfund.base.service.sales;

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

import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.util.common.UUIDCreate;

import sun.misc.BASE64Decoder;

import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.sales.Clients;
import com.ecfund.base.model.users.Users;
import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.dao.sales.ClientsDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-07 14:15
 * @filename ClientsService.java
 * 
 */

@Service
public class ClientsService extends BaseService<Clients> {

	@Autowired
	private ClientsDAO clientsDAO;
	
	@Autowired
	private UpimageService upimageService;

	@Autowired
	public void setBaseDAO(ClientsDAO clientsDAO) {
		super.setBaseDAO(clientsDAO);
	}
	
	public void saveClient(Clients client,JSONArray imageids) throws Exception{
		String clientid=this.insert(client);
		
		for(int i=0;i<imageids.size();i++){
			Upimage image = new Upimage();
			image.setGuid(imageids.getString(i).toString());
			image.setRelatedid(clientid);
			upimageService.update(image);
		}
		
	}

}