package com.ecfund.base.dao.g2g3;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.g2g3.Farm;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-05 11:03
 * @filename FarmDAO.java
 * 
 */

@Repository
public class FarmDAO extends BaseDAO<Farm> {

	public Farm findDetail(Farm farm) {
		return this.sqlSessionTemplate.selectOne(Farm.class.getSimpleName() + ".finddetail", farm);
	}

	public List<Farm> findPageList(Farm farm, int start, int pageSize) {
		return this.sqlSessionTemplate.selectList(Farm.class.getSimpleName() + ".finddetail", farm,
				new RowBounds(start, pageSize));
	}
}