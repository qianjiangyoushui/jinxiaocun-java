package com.ecfund.base.service.eliteG1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ecfund.base.dao.publics.GrowthrecordDAO;
import com.ecfund.base.dao.seedfile.SeedfileDAO;
import com.ecfund.base.model.publics.Growthrecord;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.service.publics.GrowthrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.dao.eliteG1.FertilizationDAO;
import com.ecfund.base.model.eliteG1.Manure;
import com.ecfund.base.model.fertilization.Fertilization;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.service.publics.UpimageService;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-25 15:11
 * @filename FertilizationService.java
 * 
 */

@Service
public class FertilizationService extends BaseService<Fertilization> {

	//@Autowired
	//private FertilizationDAO fertilizationDAO;

	@Autowired
	public void setBaseDAO(FertilizationDAO fertilizationDAO) {
		super.setBaseDAO(fertilizationDAO);
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
	public void saveFertilization(Fertilization fertilization, Users user, JSONArray imageids) throws Exception {
		
		String manureid = this.insert(fertilization);

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
		log.setRelatedid(fertilization.getSeedfileid());
		
		logService.insert(log);
	}
	@Transactional
    public void batchsaveFertilization(Fertilization fertilization, Users user, JSONArray imageids, JSONArray batchArray) throws Exception {
		for(int j=0;j<batchArray.size();j++){
			fertilization.setSeedfileid(batchArray.get(j).toString());
			String manureid = this.insert(fertilization);
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
			log.setRelatedid(fertilization.getSeedfileid());
			logService.insert(log);
		}
    }
    @Transactional
    public String[] batchsaveFertilization_wechat(Fertilization fertilization, Users user,String[] batchArray) throws Exception {
		String[] result = new String[batchArray.length];
		for(int j=0;j<batchArray.length;j++){
			fertilization.setSeedfileid(batchArray[j]);
			String manureid = this.insert(fertilization);
			result[j]=manureid;
			Logs log=new Logs();
			log.setDescription("新增施肥记录");
			log.setOperatedate(new Date(System.currentTimeMillis()));
			log.setOperatorid(user.getGuid());
			log.setRelatedid(fertilization.getSeedfileid());
			logService.insert(log);
			Seedfile seedfile = new Seedfile();
			seedfile.setGuid(batchArray[j]);
			seedfile = seedfileDAO.view(seedfile);
			Growthrecord growthrecord = new Growthrecord();
			growthrecord.setBatchcode(seedfile.getBatch());
			growthrecord.setBatchid(seedfile.getGuid());
			growthrecord.setCompanyid(seedfile.getCompanyid());
			growthrecord.setVisible(1);
			growthrecord.setType(seedfile.getType());
			growthrecord.setStep("施肥");
			growthrecord.setContent(fertilization.getMuck());
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