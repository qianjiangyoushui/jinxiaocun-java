package com.ecfund.base.dao.eliteG1;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.eliteG1.Greenhouses;
import com.ecfund.base.model.eliteG1.Lookimages;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-16 10:29
 * @filename LookimagesDAO.java
 * 
 */

@Repository
public class LookimagesDAO extends BaseDAO<Lookimages> {

	
	public List<Lookimages> findList(Lookimages lookimages, int start, int pageSize) {
		return this.sqlSessionTemplate.selectList(Lookimages.class.getSimpleName() + ".findList", lookimages,
				new RowBounds(start, pageSize));
	}
	
	
	public Lookimages getlook(Lookimages lookimages) {
		return this.sqlSessionTemplate.selectOne(Lookimages.class.getSimpleName() + ".findList",lookimages);
	}
}