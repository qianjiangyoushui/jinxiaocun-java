package com.ecfund.base.service.eliteG1;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.dao.eliteG1.ManureDAO;
import com.ecfund.base.dao.g2g3.PlotsDAO;
import com.ecfund.base.dao.publics.GrowthrecordDAO;
import com.ecfund.base.dao.seedfile.SeedfileDAO;
import com.ecfund.base.model.eliteG1.Manure;
import com.ecfund.base.model.g2g3.Plots;
import com.ecfund.base.model.publics.Growthrecord;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.service.publics.UpimageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-07 11:15
 * @filename ManureService.java
 * 
 */

@Service
public class ManureService extends BaseService<Manure> {

	//@Autowired
	//private ManureDAO manureDAO;

	@Autowired
	public void setBaseDAO(ManureDAO manureDAO) {
		super.setBaseDAO(manureDAO);
	}
	
	@Autowired
	private UpimageService upimageService;
	
	@Autowired
	private LogsService logService;
	@Autowired
	private GrowthrecordDAO growthrecordDAO;
	@Autowired
	private SeedfileDAO seedfileDAO;
	@Autowired
	private PlotsDAO plotsDAO;
	
	
	@Transactional
	public void saveManure(Manure manure, Users user, JSONArray imageids) throws Exception {
		
		String manureid = this.insert(manure);

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
		log.setRelatedid(manure.getSeedfileid());
		
		logService.insert(log);
	}
	@Transactional
	public void batchsaveManure(Manure manure, Users user, JSONArray imageids,JSONArray batch) throws Exception {

		for(int j=0;j<batch.size();j++){
			manure.setSeedfileid(batch.get(j).toString());
			String manureid = this.insert(manure);
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
			log.setRelatedid(manure.getSeedfileid());
			logService.insert(log);
		}
	}
	@Transactional
    public String[] batchsaveFertilization_wechat(Manure manure, Users user, String[] batchArray) throws Exception{
		String[] result = new String[batchArray.length];
		for(int j=0;j<batchArray.length;j++){
			manure.setSeedfileid(batchArray[j]);
			String manureid = this.insert(manure);
			result[j]=manureid;
			Logs log=new Logs();
			log.setDescription("新增施肥记录");
			log.setOperatedate(new Date(System.currentTimeMillis()));
			log.setOperatorid(user.getGuid());
			log.setRelatedid(manure.getSeedfileid());
			logService.insert(log);
			Seedfile seedfile = new Seedfile();
			seedfile.setGuid(batchArray[j]);
			seedfile = seedfileDAO.view(seedfile);
			Plots plots = new Plots();
			plots.setGuid(seedfile.getPlaceid());
			plots = plotsDAO.view( plots);
			Growthrecord growthrecord = new Growthrecord();
			growthrecord.setBatchcode(seedfile.getBatch());
			growthrecord.setBatchid(seedfile.getGuid());
			growthrecord.setCompanyid(seedfile.getCompanyid());
			growthrecord.setUserid(user.getGuid());
			growthrecord.setVisible(1);
			growthrecord.setType(seedfile.getType());
			growthrecord.setStep("施肥");
			growthrecord.setPlot(plots.getPlotname());
			growthrecord.setContent(manure.getMuck());
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