package com.ecfund.base.dao.eliteG1;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.eliteG1.Greenhouses;
import com.ecfund.base.model.g0.Seedbed;
import com.ecfund.base.model.seedfile.Seedfile;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-31 14:51
 * @filename GreenhousesDAO.java
 * 
 */

@Repository
public class GreenhousesDAO extends BaseDAO<Greenhouses> {

	
	public List<Greenhouses> findpagelist(Greenhouses greenhouses,int start,int pagesize){
		return this.sqlSessionTemplate.selectList(Seedfile.class.getSimpleName()+".findg0", greenhouses, new RowBounds(start,pagesize));
	}
	
	public List<Greenhouses> findList(Greenhouses greenhouses, int start, int pageSize) {
		return this.sqlSessionTemplate.selectList(Greenhouses.class.getSimpleName() + ".findList", greenhouses,
				new RowBounds(start, pageSize));
	}
	
	public Greenhouses findHouses(Greenhouses greenhouses) {
		return this.sqlSessionTemplate.selectOne(Greenhouses.class.getSimpleName() + ".findList", greenhouses);
	}
}