package com.ecfund.base.dao.sales;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.sales.Salesrecord;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-07 14:17
 * @filename SalesrecordDAO.java
 * 
 */

@Repository
public class SalesrecordDAO extends BaseDAO<Salesrecord> {

	public List<Salesrecord> findlist(Salesrecord sales, int start, int pageSize) {
		return this.sqlSessionTemplate.selectList(Salesrecord.class.getSimpleName() + ".findlist", sales,
				new RowBounds(start, pageSize));
	}
}