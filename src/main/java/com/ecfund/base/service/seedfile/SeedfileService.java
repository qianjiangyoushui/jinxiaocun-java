package com.ecfund.base.service.seedfile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.common.UUIDCreate;

import sun.misc.BASE64Decoder;

import com.ecfund.base.model.g0.Seedbed;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.dao.seedfile.SeedfileDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-25 09:36
 * @filename SeedfileService.java
 * 
 */

@Service
public class SeedfileService extends BaseService<Seedfile> {

	@Autowired
	private SeedfileDAO seedfileDAO;
	@Autowired
	private UpimageService upimageService;
	
	@Autowired
	private LogsService logService;

	@Autowired
	public void setBaseDAO(SeedfileDAO seedfileDAO) {
		super.setBaseDAO(seedfileDAO);
	}

	
	public Page findpagelist(Seedfile seedfile,int start,int pagesize) throws Exception{
		Page page=new Page();
		page.setRows(seedfileDAO.findpagelist(seedfile, start, pagesize));
		page.setCountItem(this.findCount(seedfile));
		return page;
	}
	
	public Page findpageG2G3list(Seedfile seedfile,int start,int pagesize) throws Exception{
		Page page=new Page();
		page.setRows(seedfileDAO.findpageG2G3list(seedfile, start, pagesize));
		page.setCountItem(this.findCount(seedfile));
		return page;
	}
	
	public Seedfile findg0(Seedfile seedfile) throws Exception{
		return seedfileDAO.findg0(seedfile);
	}
	
	public Page findg1pagelist(Seedfile seedfile,int start,int pagesize) throws Exception{
		Page page=new Page();
		page.setRows(seedfileDAO.findg1pagelist(seedfile, start, pagesize));
		page.setCountItem(this.findCount(seedfile));
		return page;
	}
	public Page findhxm(Seedfile seedfile,int start,int pagesize) throws Exception{
		Page page=new Page();
		page.setRows(seedfileDAO.findhxm(seedfile, start, pagesize));
		page.setCountItem(this.findCount(seedfile));
		return page;
	}
	
	public Seedfile getSeedfileByGuid(Seedfile seedfile){
		return this.seedfileDAO.getSeedfileByGuid(seedfile);
	}
	
	@Transactional
	public void save(Seedfile seedfile, Users user, JSONArray imageids) throws Exception {
		
		String seedfileid = this.insert(seedfile);

		for(int i=0;i<imageids.size();i++){
			Upimage image = new Upimage();
			image.setGuid(imageids.get(i).toString());
			image.setRelatedid(seedfileid);
			upimageService.update(image);
		}
		}
	
	@Transactional
	public void updateInfo(Seedfile seedfile, String remark,Users users) throws Exception{
		this.update(seedfile);
		//添加 logs
		Logs log=new Logs();
		log.setDescription("修改"+remark);
		log.setOperatedate(new Date(System.currentTimeMillis()));
		log.setOperatorid(users.getGuid());
		log.setRelatedid(seedfile.getGuid());
		logService.insert(log);
	}
	
	/**
	 * 查找销售批次
	 * @param seedfile
	 * @return
	 * @throws Exception
	 */
	public List<Seedfile> findsales(Seedfile seedfile) throws Exception{
		return seedfileDAO.findsales(seedfile);
	}
}