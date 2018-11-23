package com.ecfund.base.service.eliteG1;

import java.util.Calendar;
import java.util.Date;

import com.ecfund.base.dao.publics.GrowthrecordDAO;
import com.ecfund.base.dao.seedfile.SeedfileDAO;
import com.ecfund.base.model.publics.Growthrecord;
import com.ecfund.base.model.seedfile.Seedfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.model.eliteG1.Eppo;
import com.ecfund.base.model.eliteG1.Plantprotect;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.dao.eliteG1.EppoDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-25 16:47
 * @filename EppoService.java
 * 
 */

@Service
public class EppoService extends BaseService<Eppo> {

	//@Autowired
	//private EppoDAO eppoDAO;

	@Autowired
	public void setBaseDAO(EppoDAO eppoDAO) {
		super.setBaseDAO(eppoDAO);
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
	public void saveEppo(Eppo eppo, Users user, JSONArray imageids) throws Exception {
		
		String pid = this.insert(eppo);

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
		log.setRelatedid(eppo.getSeedfileid());
		
		logService.insert(log);
	}

	@Transactional
	public void batchSaveEppo(Eppo eppo, Users user, JSONArray imageids,JSONArray batch) throws Exception {
		for(int j=0;j<batch.size();j++){
			eppo.setSeedfileid(batch.get(j).toString());
			String pid = this.insert(eppo);
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
			log.setRelatedid(eppo.getSeedfileid());

			logService.insert(log);
		}
	}

	@Transactional
    public String[] batchsaveFertilization_wechat(Eppo eppo, Users user, String[] batchArray)throws Exception {
		String[] result = new String[batchArray.length];
		for(int j=0;j<batchArray.length;j++){
			eppo.setSeedfileid(batchArray[j]);
			String manureid = this.insert(eppo);
			result[j]=manureid;
			Logs log=new Logs();
			log.setDescription("新增植保记录");
			log.setOperatedate(new Date(System.currentTimeMillis()));
			log.setOperatorid(user.getGuid());
			log.setRelatedid(eppo.getSeedfileid());
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
			growthrecord.setStep("植保");
			growthrecord.setContent(eppo.getDrugkind());
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