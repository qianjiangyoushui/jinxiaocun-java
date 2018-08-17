package com.ecfund.base.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.service.BaseService;

import com.ecfund.base.model.users.Department;
import com.ecfund.base.dao.users.DepartmentDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-19 10:48
 * @filename DepartmentService.java
 * 
 */

@Service
public class DepartmentService extends BaseService<Department> {

	@Autowired
	private DepartmentDAO departmentDAO;

	@Autowired
	public void setBaseDAO(DepartmentDAO departmentDAO) {
		super.setBaseDAO(departmentDAO);
	}

}