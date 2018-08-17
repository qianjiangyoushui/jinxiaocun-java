package com.ecfund.base.dao.g0;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.g0.Seedbed;
import com.ecfund.base.model.seedfile.Seedfile;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-25 23:01
 * @filename SeedbedDAO.java
 * 
 */

@Repository
public class SeedbedDAO extends BaseDAO<Seedbed> {

	public List<Seedbed> findList(Seedbed seedbed, int start, int pageSize) {
		return this.sqlSessionTemplate.selectList(Seedbed.class.getSimpleName() + ".findSeedbed", seedbed,
				new RowBounds(start, pageSize));
	}
	
	public Seedbed findDetail(Seedbed seedbed){
		return this.sqlSessionTemplate.selectOne(Seedbed.class.getSimpleName() + ".findSeedbed",seedbed);
	}
}