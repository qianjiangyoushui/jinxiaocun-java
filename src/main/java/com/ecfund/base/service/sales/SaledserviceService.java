package com.ecfund.base.service.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.sales.Saledservice;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.dao.sales.SaledserviceDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-09 17:17
 * @filename SaledserviceService.java
 * 
 */

@Service
public class SaledserviceService extends BaseService<Saledservice> {

	@Autowired
	private SaledserviceDAO saledserviceDAO;
	@Autowired
	private UpimageService upimageService;
	@Autowired
	public void setBaseDAO(SaledserviceDAO saledserviceDAO) {
		super.setBaseDAO(saledserviceDAO);
	}
	@Transactional
	public void save(Saledservice saledservice, Users user, JSONArray imageids) throws Exception {
		
		String seedfileid = this.insert(saledservice);

		for(int i=0;i<imageids.size();i++){
			Upimage image = new Upimage();
			image.setGuid(imageids.get(i).toString());
			image.setRelatedid(seedfileid);
			upimageService.update(image);
		}
		}
}