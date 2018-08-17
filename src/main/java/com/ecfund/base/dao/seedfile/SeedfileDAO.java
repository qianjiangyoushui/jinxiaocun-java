package com.ecfund.base.dao.seedfile;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.seedfile.Seedfile;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-25 09:35
 * @filename SeedfileDAO.java
 * 
 */

@Repository
public class SeedfileDAO extends BaseDAO<Seedfile> {
	
	public List<Seedfile> findpagelist(Seedfile seedfile,int start,int pagesize){
		return this.sqlSessionTemplate.selectList(Seedfile.class.getSimpleName()+".findg0", seedfile, new RowBounds(start,pagesize));
	}
	public List<Seedfile> findpageG2G3list(Seedfile seedfile,int start,int pagesize){
		return this.sqlSessionTemplate.selectList(Seedfile.class.getSimpleName()+".findg2g3", seedfile, new RowBounds(start,pagesize));
	}
	public List<Seedfile> findhxm(Seedfile seedfile,int start,int pagesize){
		return this.sqlSessionTemplate.selectList(Seedfile.class.getSimpleName()+".findhxm", seedfile, new RowBounds(start,pagesize));
	}
	
	public List<Seedfile> findg1pagelist(Seedfile seedfile,int start,int pagesize){
		return this.sqlSessionTemplate.selectList(Seedfile.class.getSimpleName()+".findg1", seedfile, new RowBounds(start,pagesize));
	}
	
	public Seedfile findg0(Seedfile seedfile){
		return this.sqlSessionTemplate.selectOne(Seedfile.class.getSimpleName()+".findg0", seedfile);
	}
	
	public Seedfile getSeedfileByGuid(Seedfile seedfile){
		return this.sqlSessionTemplate.selectOne(Seedfile.class.getSimpleName()+".findg1",seedfile);
	}
	
	/**
	 * 查找销售批次
	 * @param seedfile
	 * @return
	 */
	public List<Seedfile> findsales(Seedfile seedfile){
		return this.sqlSessionTemplate.selectList(Seedfile.class.getSimpleName()+".findsales",seedfile);
	}
}