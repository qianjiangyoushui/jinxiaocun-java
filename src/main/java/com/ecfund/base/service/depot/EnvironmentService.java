package com.ecfund.base.service.depot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.dao.depot.EnvironmentDAO;
import com.ecfund.base.model.depot.Environment;
import com.ecfund.base.model.eliteG1.Lookimages;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.Page;


/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-24 09:29
 * @filename EnvironmentService.java
 * 
 */

@Service
public class EnvironmentService extends BaseService<Environment> {

	@Autowired
	private EnvironmentDAO environmentDAO;

	@Autowired
	public void setBaseDAO(EnvironmentDAO environmentDAO) {
		super.setBaseDAO(environmentDAO);
	}
	public List findmonthList(Environment environment) throws Exception{
		return environmentDAO.findmonthList(environment);
	}
}