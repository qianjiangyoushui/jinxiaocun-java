package com.ecfund.base.dao.tasks;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.eliteG1.Greenhouses;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.tasks.Tasks;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-24 10:09
 * @filename TasksDAO.java
 * 
 */

@Repository
public class TasksDAO extends BaseDAO<Tasks> {

	public List<Tasks> findList(Tasks tasks,int start,int pagesize){
		return this.sqlSessionTemplate.selectList(Tasks.class.getSimpleName()+".findList", tasks, new RowBounds(start,pagesize));
	}
	
	public Tasks getTasks(Tasks tasks){
		return this.sqlSessionTemplate.selectOne(Tasks.class.getSimpleName()+".findList", tasks);
	}
}