package com.ecfund.base.dao.depot;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.depot.Environment;
import com.ecfund.base.model.eliteG1.Lookimages;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-24 09:28
 * @filename EnvironmentDAO.java
 * 
 */

@Repository
public class EnvironmentDAO extends BaseDAO<Environment> {

	public List<Environment> findmonthList(Environment environment) {
		return this.sqlSessionTemplate.selectList(Environment.class.getSimpleName() + ".findmonthList", environment);
	}
	
}