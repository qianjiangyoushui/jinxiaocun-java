package com.ecfund.base.service.depot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.dao.depot.AccidentDAO;
import com.ecfund.base.model.depot.Accident;
import com.ecfund.base.model.depot.Outinstorage;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.UpimageService;


/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-24 09:24
 * @filename AccidentService.java
 * 
 */

@Service
public class AccidentService extends BaseService<Accident> {

	@Autowired
	private AccidentDAO accidentDAO;
	@Autowired
	private UpimageService upimageService;
	@Autowired
	public void setBaseDAO(AccidentDAO accidentDAO) {
		super.setBaseDAO(accidentDAO);
	}
	@Transactional
	public void save(Accident accident, Users user, JSONArray imageids) throws Exception {
		String guid = this.insert(accident);
			for(int i=0;i<imageids.size();i++){
				Upimage image = new Upimage();
				image.setGuid(imageids.get(i).toString());
				image.setRelatedid(guid);
				upimageService.update(image);
			}
		}
}