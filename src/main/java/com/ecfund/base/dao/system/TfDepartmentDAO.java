package com.ecfund.base.dao.system;

import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.system.TfDepartment;
import com.ecfund.base.util.common.Functions;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2012-09-11 14:06
 * @filename TfDepartmentDAO.java
 * @author Hmilyld
 * 
 */

@Repository
public class TfDepartmentDAO extends BaseDAO<TfDepartment> {

	public void findAll(TfDepartment entity) throws Exception {
		sqlSessionTemplate.update(entity.getClass().getSimpleName()
				+ ".findall", entity);
	}

	/**
	 * 通过人员ID查询出年度考核责任部门Guid
	 * 
	 * @param tfUserDept
	 * @param start
	 * @param pageCount
	 * @return
	 */
	public TfDepartment findGuidByUser(TfDepartment entity) {
		return sqlSessionTemplate.selectOne("TfDepartment.findByUserGuid",
				entity);
	}

	/**
	 * 根据人员信息返回该人员所属公司的id,
	 * 
	 * @author 孙山伟
	 * @version 创建时间：2012-11-29 上午12:04:10
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public String gsGuid(TfDepartment entity) throws Exception {
		TfDepartment tfd = new TfDepartment();
		tfd = findGuidByUser(entity);
		String guid = tfd.getFulltree().split("-")[1];
		return guid;
	}

	/**
	 * 根据编码查询部门组织机构信息，自动对编码进行前置补0
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public TfDepartment findByCode(String code) throws Exception {
		code = Functions.joinZero(code, 8);
		TfDepartment dept = new TfDepartment();
		dept.setCode(code);
		return view(dept);
	}


}
