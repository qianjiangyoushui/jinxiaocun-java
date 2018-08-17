package com.ecfund.base.service.depot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.service.BaseService;

import com.ecfund.base.model.depot.Depot;
import com.ecfund.base.dao.depot.DepotDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-08 13:32
 * @filename DepotService.java
 * 
 */

@Service
public class DepotService extends BaseService<Depot> {

	@Autowired
	private DepotDAO depotDAO;

	@Autowired
	public void setBaseDAO(DepotDAO depotDAO) {
		super.setBaseDAO(depotDAO);
	}

}