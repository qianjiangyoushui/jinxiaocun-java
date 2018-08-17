package com.ecfund.base.dao.system;




import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.system.TfUserDept;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2012-09-12 10:19
 * @filename TfUserDeptDAO.java
 * @author Hmilyld
 * 
 */

@Repository
public class TfUserDeptDAO extends BaseDAO<TfUserDept> {
	/**
	 * 删除，不能使用默认的删除
	 * @param entity
	 * @throws Exception
	 */
	public void del(TfUserDept entity) throws Exception {
		sqlSessionTemplate.update(TfUserDept.class.getSimpleName() + ".del",
				entity);
	}
}