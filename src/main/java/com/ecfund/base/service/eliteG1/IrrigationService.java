package com.ecfund.base.service.eliteG1;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ecfund.base.dao.publics.GrowthrecordDAO;
import com.ecfund.base.dao.seedfile.SeedfileDAO;
import com.ecfund.base.model.publics.Growthrecord;
import com.ecfund.base.model.seedfile.Seedfile;
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
import com.ecfund.base.model.eliteG1.Irrigation;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.dao.eliteG1.IrrigationDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-07 15:01
 * @filename IrrigationService.java
 * 
 */

@Service
public class IrrigationService extends BaseService<Irrigation> {

	//@Autowired
	//private IrrigationDAO irrigationDAO;

	@Autowired
	public void setBaseDAO(IrrigationDAO irrigationDAO) {
		super.setBaseDAO(irrigationDAO);
	}
	
	@Autowired
	private UpimageService upimageService;
	
	@Autowired
	private LogsService logService;
	@Autowired
	private GrowthrecordDAO growthrecordDAO;
	@Autowired
	private SeedfileDAO seedfileDAO;
	
	@Transactional
	public void saveIrrigation(Irrigation irrigation, Users user, JSONArray imageids) throws Exception {
		
		String irrigationid = this.insert(irrigation);

		for(int i=0;i<imageids.size();i++){
			Upimage image = new Upimage();
			image.setGuid(imageids.get(i).toString());
			image.setRelatedid(irrigationid);
			upimageService.update(image);
		}
		
		Logs log=new Logs();
		log.setDescription("新增灌溉记录");
		log.setOperatedate(new Date(System.currentTimeMillis()));
		log.setOperatorid(user.getGuid());
		log.setRelatedid(irrigation.getSeedfileid());
		
		logService.insert(log);
	}
	@Transactional
	public void batchsaveIrrigation(Irrigation irrigation, Users user, JSONArray imageids,JSONArray batch) throws Exception {

		for(int j=0;j<batch.size();j++){
			irrigation.setSeedfileid(batch.get(j).toString());
			String irrigationid = this.insert(irrigation);

			for(int i=0;i<imageids.size();i++){
				Upimage image = new Upimage();
				image.setGuid(imageids.get(i).toString());
				image.setRelatedid(irrigationid);
				upimageService.update(image);
			}

			Logs log=new Logs();
			log.setDescription("新增灌溉记录");
			log.setOperatedate(new Date(System.currentTimeMillis()));
			log.setOperatorid(user.getGuid());
			log.setRelatedid(irrigation.getSeedfileid());
			logService.insert(log);
		}
	}
	@Transactional
    public String[] batchsaveFertilization_wechat(Irrigation irrigation, Users user, String[] batchArray) throws Exception{
		String[] result = new String[batchArray.length];
		for(int j=0;j<batchArray.length;j++){
			irrigation.setSeedfileid(batchArray[j]);
			String manureid = this.insert(irrigation);
			result[j]=manureid;
			Logs log=new Logs();
			log.setDescription("新增浇水记录");
			log.setOperatedate(new Date(System.currentTimeMillis()));
			log.setOperatorid(user.getGuid());
			log.setRelatedid(irrigation.getSeedfileid());
			logService.insert(log);
			Seedfile seedfile = new Seedfile();
			seedfile.setGuid(batchArray[j]);
			seedfile = seedfileDAO.view(seedfile);
			Growthrecord growthrecord = new Growthrecord();
			growthrecord.setBatchcode(seedfile.getBatch());
			growthrecord.setBatchid(seedfile.getGuid());
			growthrecord.setCompanyid(seedfile.getCompanyid());
			growthrecord.setUserid(user.getGuid());
			growthrecord.setVisible(1);
			growthrecord.setType(seedfile.getType());
			growthrecord.setStep("浇水");
			growthrecord.setContent("排灌水");
			growthrecord.setCreatedate(Calendar.getInstance().getTime());
			String month = growthrecord.getCreatedate().getMonth()+1+"";
			String day = growthrecord.getCreatedate().getDate()+"";
			growthrecord.setDay(day);
			growthrecord.setMonth(month);
			String guid = growthrecordDAO.insert(growthrecord);
			result[j] = guid;
		}
		return result;
    }
}