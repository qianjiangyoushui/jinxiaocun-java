package com.ecfund.base.service.eliteG1;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.model.eliteG1.Irrigation;
import com.ecfund.base.model.eliteG1.Watering;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.dao.eliteG1.WateringDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-29 09:53
 * @filename WateringService.java
 * 
 */

@Service
public class WateringService extends BaseService<Watering> {

	//@Autowired
	//private WateringDAO wateringDAO;

	@Autowired
	public void setBaseDAO(WateringDAO wateringDAO) {
		super.setBaseDAO(wateringDAO);
	}
	
	@Autowired
	private UpimageService upimageService;
	
	@Autowired
	private LogsService logService;
	
	@Transactional
	public void saveWatering(Watering watering, Users user, JSONArray imageids) throws Exception {
		
		String wateringid = this.insert(watering);

		for(int i=0;i<imageids.size();i++){
			Upimage image = new Upimage();
			image.setGuid(imageids.get(i).toString());
			image.setRelatedid(wateringid);
			upimageService.update(image);
		}
		
		Logs log=new Logs();
		log.setDescription("新增灌溉记录");
		log.setOperatedate(new Date(System.currentTimeMillis()));
		log.setOperatorid(user.getGuid());
		log.setRelatedid(watering.getSeedfileid());
		
		logService.insert(log);
	}

	@Transactional
    public void batchsaveWatering(Watering watering, Users user, JSONArray imageids, JSONArray batchArray) throws  Exception{
		for(int j=0;j<batchArray.size();j++){
			watering.setSeedfileid(batchArray.get(j).toString());
			String wateringid = this.insert(watering);

			for(int i=0;i<imageids.size();i++){
				Upimage image = new Upimage();
				image.setGuid(imageids.get(i).toString());
				image.setRelatedid(wateringid);
				upimageService.update(image);
			}

			Logs log=new Logs();
			log.setDescription("新增灌溉记录");
			log.setOperatedate(new Date(System.currentTimeMillis()));
			log.setOperatorid(user.getGuid());
			log.setRelatedid(watering.getSeedfileid());

			logService.insert(log);
		}
    }
}