package com.ecfund.base.service.g2g3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.UpimageService;
import com.mysql.jdbc.StringUtils;
import com.ecfund.base.model.g2g3.Plots;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.dao.g2g3.PlotsDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-05 11:19
 * @filename PlotsService.java
 * 
 */

@Service
public class PlotsService extends BaseService<Plots> {

	@Autowired
	private PlotsDAO plotsDAO;
	
	@Autowired
	private UpimageService upimageService;

	@Autowired
	public void setBaseDAO(PlotsDAO plotsDAO) {
		super.setBaseDAO(plotsDAO);
	}
	
	public void saveplot(Plots plot,Users user, JSONArray imageids) throws Exception{
		String plotid=this.insert(plot);
		
		for(int i=0;i<imageids.size();i++){
			
			if(i==0){
				Upimage img = new Upimage();
				img.setRelatedid(imageids.get(0).toString());//查询关联的缩略图
				img=upimageService.view(img);
				
				Plots p=new Plots();
				p.setGuid(plotid);
				p.setUrl(img.getUrl());
				this.update(p);
			}
			Upimage image = new Upimage();
			image.setGuid(imageids.get(i).toString());
			image.setRelatedid(plotid);
			upimageService.update(image);
		}
		
	}

	public Plots findPlots(Plots plot)  throws Exception{
		return plotsDAO.findPlots(plot);
	}
	
	public void updatePlot(Plots plot,String imageid) throws Exception{
		this.update(plot);
		if(!StringUtils.isNullOrEmpty(imageid)){
			String[] imageids = imageid.split(",");
			for (int i = 0; i < imageids.length; i++) {
				Upimage image = new Upimage();
				image.setGuid(imageids[i]);
				image.setRelatedid(plot.getGuid());
				upimageService.update(image);
			}
		}
	}
}