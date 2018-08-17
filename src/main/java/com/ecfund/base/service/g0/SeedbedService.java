package com.ecfund.base.service.g0;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.common.UUIDCreate;
import com.mysql.jdbc.StringUtils;

import sun.misc.BASE64Decoder;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.dao.g0.SeedbedDAO;
import com.ecfund.base.model.g0.Seedbed;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-25 23:01
 * @filename SeedbedService.java
 * 
 */

@Service
public class SeedbedService extends BaseService<Seedbed> {

	@Autowired
	private SeedbedDAO seedbedDAO;

	@Autowired
	private UpimageService upimageService;

	@Autowired
	public void setBaseDAO(SeedbedDAO seedbedDAO) {
		super.setBaseDAO(seedbedDAO);
	}

	@Transactional
	public void saveseedbed(Seedbed seedbed, Users user, JSONArray imageids) throws Exception {
		String seedbedid = this.insert(seedbed);

		for (int i = 0; i < imageids.size(); i++) {
			// 第一张图作为主图
			if (i == 0) {
				Upimage img = new Upimage();
				img.setRelatedid(imageids.get(0).toString());// 查询关联的缩略图
				img = upimageService.view(img);
				Seedbed bed = new Seedbed();
				bed.setGuid(seedbedid);
				bed.setUrl(img.getUrl());
				this.update(bed);
			}

			Upimage image = new Upimage();
			image.setGuid(imageids.get(i).toString());
			image.setRelatedid(seedbedid);
			upimageService.update(image);
		}
	}

	public Page getPagelist(Seedbed seedbed, Page page) throws Exception {
		Page pages = new Page();
		pages.setRows(seedbedDAO.findList(seedbed, page.getBegin(), page.getPageSize()));
		pages.setPageNo(page.getPageNo());
		pages.setCountItem(this.findCount(seedbed));
		return pages;
	}

	public Seedbed findDetail(Seedbed seedbed) throws Exception {
		return seedbedDAO.findDetail(seedbed);
	}

	@Transactional
	public void updateseedbed(Seedbed seedbed, String imageids) throws Exception {
		this.update(seedbed);
		if (!StringUtils.isNullOrEmpty(imageids)) {
			String[] imageid = imageids.split(",");
			for (int i = 0; i < imageid.length; i++) {
				Upimage image = new Upimage();
				image.setGuid(imageid[i]);
				image.setRelatedid(seedbed.getGuid());
				upimageService.update(image);
			}
		}
	}
}