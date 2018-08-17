package com.ecfund.base.service.eliteG1;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.model.eliteG1.Greenhouses;
import com.ecfund.base.model.eliteG1.Irrigation;
import com.ecfund.base.model.eliteG1.Lookimages;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.dao.eliteG1.LookimagesDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-16 10:29
 * @filename LookimagesService.java
 * 
 */

@Service
public class LookimagesService extends BaseService<Lookimages> {

	@Autowired
	private LookimagesDAO lookimagesDAO;
	
	@Autowired
	private UpimageService upimageService;
	
	@Autowired
	private LogsService logService;
	

	@Autowired
	public void setBaseDAO(LookimagesDAO lookimagesDAO) {
		super.setBaseDAO(lookimagesDAO);
	}
	
	public Page findlist(Lookimages lookimages,int start,int pagesize) throws Exception{
		Page page=new Page();
		page.setRows(lookimagesDAO.findList(lookimages, start, pagesize));
		page.setCountItem(this.findCount(lookimages));
		return page;
	}
	
	@Transactional
	public void saveLookimages(Lookimages lookimages, Users user, JSONArray imageids) throws Exception {
		
		String lookimageid = this.insert(lookimages);

		for(int i=0;i<imageids.size();i++){
			Upimage image = new Upimage();
			image.setGuid(imageids.get(i).toString());
			image.setRelatedid(lookimageid);
			upimageService.update(image);
		}
		
		Logs log=new Logs();
		log.setDescription("新增长势图片记录");
		log.setOperatedate(new Date(System.currentTimeMillis()));
		log.setOperatorid(user.getGuid());
		log.setRelatedid(lookimages.getSeedfileid());
		
		logService.insert(log);
	}
	
	public Lookimages getlook(Lookimages lookimages){
		return this.lookimagesDAO.getlook(lookimages);
	}


}