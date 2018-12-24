package com.ecfund.base.service.eliteG1;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ecfund.base.dao.g2g3.PlotsDAO;
import com.ecfund.base.dao.publics.GrowthrecordDAO;
import com.ecfund.base.dao.seedfile.SeedfileDAO;
import com.ecfund.base.model.g2g3.Plots;
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
import com.ecfund.base.model.eliteG1.Pest;
import com.ecfund.base.model.eliteG1.Plantprotect;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.dao.eliteG1.PlantprotectDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-03 16:11
 * @filename PlantprotectService.java
 * 
 */

@Service
public class PlantprotectService extends BaseService<Plantprotect> {

	//@Autowired
	//private PlantprotectDAO plantprotectDAO;
	
	@Autowired
	private UpimageService upimageService;
	@Autowired
	private GrowthrecordDAO growthrecordDAO;
	@Autowired
	private SeedfileDAO seedfileDAO;
	@Autowired
	private PlotsDAO plotsDAO;


	@Autowired
	public void setBaseDAO(PlantprotectDAO plantprotectDAO) {
		super.setBaseDAO(plantprotectDAO);
	}
	@Autowired
	private LogsService logService;
	

	@Transactional
	public void savePlantProtect(Plantprotect plantprotect, Users user, JSONArray imageids) throws Exception {
		
		String pid = this.insert(plantprotect);

		for(int i=0;i<imageids.size();i++){
			Upimage image = new Upimage();
			image.setGuid(imageids.get(i).toString());
			image.setRelatedid(pid);
			upimageService.update(image);
		}
		
		Logs log=new Logs();
		log.setDescription("新增植保方案记录");
		log.setOperatedate(new Date(System.currentTimeMillis()));
		log.setOperatorid(user.getGuid());
		log.setRelatedid(plantprotect.getSeedfileid());
		
		logService.insert(log);
	}
	@Transactional
    public void batchsavePlantProtect(Plantprotect plantprotect, Users user, JSONArray imageids, JSONArray batchArray)throws Exception  {
		for(int j=0;j<batchArray.size();j++){
			plantprotect.setSeedfileid(batchArray.get(j).toString());
			String pid = this.insert(plantprotect);
			for(int i=0;i<imageids.size();i++){
				Upimage image = new Upimage();
				image.setGuid(imageids.get(i).toString());
				image.setRelatedid(pid);
				upimageService.update(image);
			}
			Logs log=new Logs();
			log.setDescription("新增植保方案记录");
			log.setOperatedate(new Date(System.currentTimeMillis()));
			log.setOperatorid(user.getGuid());
			log.setRelatedid(plantprotect.getSeedfileid());
			logService.insert(log);
		}
    }
	@Transactional
	public String[] batchsaveFertilization_wechat(Plantprotect plantprotect, Users user, String[] batchArray,int visible) throws Exception{
		String[] result = new String[batchArray.length];
		for(int j=0;j<batchArray.length;j++){
			plantprotect.setSeedfileid(batchArray[j]);
			String manureid = this.insert(plantprotect);
			result[j]=manureid;
			Logs log=new Logs();
			log.setDescription("新增植保记录");
			log.setOperatedate(new Date(System.currentTimeMillis()));
			log.setOperatorid(user.getGuid());
			log.setRelatedid(plantprotect.getSeedfileid());
			logService.insert(log);
			Seedfile seedfile = new Seedfile();
			seedfile.setGuid(batchArray[j]);
			seedfile = seedfileDAO.view(seedfile);
			Plots plots = new Plots();
			plots.setGuid(seedfile.getPlaceid());
			plots = plotsDAO.view(plots);
			Growthrecord growthrecord = new Growthrecord();
			growthrecord.setBatchcode(seedfile.getBatch());
			growthrecord.setBatchid(seedfile.getGuid());
			growthrecord.setCompanyid(seedfile.getCompanyid());
			growthrecord.setUserid(user.getGuid());
			growthrecord.setVisible(visible);
			growthrecord.setType(seedfile.getType());
			growthrecord.setStep("植保");
			growthrecord.setPlot(plots.getPlotname());
			growthrecord.setContent("植保操作："+plantprotect.getDrugkind());
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