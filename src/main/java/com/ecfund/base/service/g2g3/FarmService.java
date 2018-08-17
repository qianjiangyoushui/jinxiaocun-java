package com.ecfund.base.service.g2g3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.util.common.Page;
import com.mysql.jdbc.StringUtils;
import com.ecfund.base.model.g2g3.Farm;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.dao.g2g3.FarmDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-05 11:04
 * @filename FarmService.java
 * 
 */

@Service
public class FarmService extends BaseService<Farm> {

	@Autowired
	private FarmDAO farmDAO;

	@Autowired
	private UpimageService upimageService;

	@Autowired
	public void setBaseDAO(FarmDAO farmDAO) {
		super.setBaseDAO(farmDAO);
	}

	public Farm findDetail(Farm farm) throws Exception {
		return farmDAO.findDetail(farm);
	}

	@Transactional
	public void saveFarm(Farm farm, Users user, JSONArray imageids) throws Exception {
		String farmid = this.insert(farm);

		for (int i = 0; i < imageids.size(); i++) {
			if (i == 0) {
				Upimage img = new Upimage();
				img.setRelatedid(imageids.get(0).toString());// 查询关联的缩略图
				img = upimageService.view(img);

				Farm f = new Farm();
				f.setGuid(farmid);
				f.setUrl(img.getUrl());
				this.update(f);
			}

			Upimage image = new Upimage();
			image.setGuid(imageids.getString(i).toString());
			image.setRelatedid(farmid);
			upimageService.update(image);
		}
	}

	public Page findPageList(Farm farm, int start, int pageSize) throws Exception {
		Page page = new Page();
		page.setRows(farmDAO.findPageList(farm, start, pageSize));
		page.setCountItem(this.findCount(farm));
		return page;
	}

	@Transactional
	public void updatefarm(Farm farm, String imageid) throws Exception {
		this.update(farm);
		if (!StringUtils.isNullOrEmpty(imageid)) {
			String[] imageids = imageid.split(",");
			for (int i = 0; i < imageids.length; i++) {
				Upimage image = new Upimage();
				image.setGuid(imageids[i]);
				image.setRelatedid(farm.getGuid());
				upimageService.update(image);
			}
		}
	}

}