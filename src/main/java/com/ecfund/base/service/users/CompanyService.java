package com.ecfund.base.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.service.BaseService;

import com.ecfund.base.model.users.Company;
import com.ecfund.base.dao.users.CompanyDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-19 10:47
 * @filename CompanyService.java
 * 
 */

@Service
public class CompanyService extends BaseService<Company> {

	 @Autowired
	 private CompanyDAO companyDAO;

	@Autowired
	public void setBaseDAO(CompanyDAO companyDAO) {
		super.setBaseDAO(companyDAO);
	}

}