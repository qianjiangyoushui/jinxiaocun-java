package com.ecfund.base.service.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.service.BaseService;

import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.dao.publics.DictionaryDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-24 10:12
 * @filename DictionaryService.java
 * 
 */

@Service
public class DictionaryService extends BaseService<Dictionary> {

	@Autowired
	private DictionaryDAO dictionaryDAO;

	@Autowired
	public void setBaseDAO(DictionaryDAO dictionaryDAO) {
		super.setBaseDAO(dictionaryDAO);
	}

}