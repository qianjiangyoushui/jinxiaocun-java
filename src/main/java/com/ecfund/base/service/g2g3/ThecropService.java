package com.ecfund.base.service.g2g3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.service.BaseService;

import com.ecfund.base.model.g2g3.Thecrop;
import com.ecfund.base.dao.g2g3.ThecropDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-18 09:45
 * @filename ThecropService.java
 * 
 */

@Service
public class ThecropService extends BaseService<Thecrop> {

	@Autowired
	private ThecropDAO thecropDAO;

	@Autowired
	public void setBaseDAO(ThecropDAO thecropDAO) {
		super.setBaseDAO(thecropDAO);
	}

}