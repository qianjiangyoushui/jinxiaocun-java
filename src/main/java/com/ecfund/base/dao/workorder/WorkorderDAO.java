package com.ecfund.base.dao.workorder;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.eliteG1.Greenhouses;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.workorder.Workorder;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-24 09:25
 * @filename WorkorderDAO.java
 * 
 */

@Repository
public class WorkorderDAO extends BaseDAO<Workorder> {

	public List<Workorder> findpagelist(Workorder workorder,int start,int pagesize){
		return this.sqlSessionTemplate.selectList(Workorder.class.getSimpleName()+".find", workorder, new RowBounds(start,pagesize));
	}
	
	public List<Workorder> findList(Workorder workorder,int start,int pagesize){
		return this.sqlSessionTemplate.selectList(Workorder.class.getSimpleName()+".findList", workorder, new RowBounds(start,pagesize));
	}
}