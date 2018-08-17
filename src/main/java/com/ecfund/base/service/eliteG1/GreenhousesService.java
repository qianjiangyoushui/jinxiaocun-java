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
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.common.UUIDCreate;
import com.ecfund.base.model.eliteG1.Greenhouses;
import com.ecfund.base.model.g0.Seedbed;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.dao.eliteG1.GreenhousesDAO;
import com.mysql.jdbc.StringUtils;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-31 14:52
 * @filename GreenhousesService.java
 * 
 */

@Service
public class GreenhousesService extends BaseService<Greenhouses> {

	@Autowired
	private GreenhousesDAO greenhousesDAO;
	
	@Autowired
	private UpimageService upimageService;

	@Autowired
	public void setBaseDAO(GreenhousesDAO greenhousesDAO) {
		super.setBaseDAO(greenhousesDAO);
	}
	
	
	@Transactional
	public void save(Greenhouses greenhouses, Users user, JSONArray imageids) throws Exception {
		
		String greenhouseid = this.insert(greenhouses);

		for(int i=0;i<imageids.size();i++){
			//第一张作为主图
			if(i==0){
				Upimage img = new Upimage();
				img.setRelatedid(imageids.get(0).toString());//查询关联的缩略图
				img=upimageService.view(img);
				
				Greenhouses house=new Greenhouses();
				house.setGuid(greenhouseid);
				house.setUrl(img.getUrl());
				this.update(house);
			}
			
			Upimage image = new Upimage();
			image.setGuid(imageids.get(i).toString());
			image.setRelatedid(greenhouseid);
			upimageService.update(image);
		}
	}


	
	public Page findpagelist(Greenhouses greenhouses,int start,int pagesize) throws Exception{
		Page page=new Page();
		page.setRows(greenhousesDAO.findpagelist(greenhouses, start, pagesize));
		page.setCountItem(this.findCount(greenhouses));
		return page;
	}
	
	public Page findlist(Greenhouses greenhouses,int start,int pagesize) throws Exception{
		Page page=new Page();
		page.setRows(greenhousesDAO.findList(greenhouses, start, pagesize));
		page.setCountItem(this.findCount(greenhouses));
		return page;
	}
	
	public Greenhouses findHouses(Greenhouses greenhouses) {
		return this.greenhousesDAO.findHouses(greenhouses);
	}
	
	
	@Transactional
	public void updateGreenhouses(Greenhouses greenhouses, String imageids) throws Exception {
		this.update(greenhouses);
		if (!StringUtils.isNullOrEmpty(imageids)) {
			String[] imageid = imageids.split(",");
			for (int i = 0; i < imageid.length; i++) {
				Upimage image = new Upimage();
				image.setGuid(imageid[i]);
				image.setRelatedid(greenhouses.getGuid());
				upimageService.update(image);
			}
		}
	}

}