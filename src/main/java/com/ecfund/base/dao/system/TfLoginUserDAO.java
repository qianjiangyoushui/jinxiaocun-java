package com.ecfund.base.dao.system;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.system.TfLoginUser;
import com.ecfund.base.model.system.TfRoleUser;
import com.ecfund.base.model.system.TfUserDept;
import com.ecfund.base.util.common.Functions;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2012-09-11 13:34
 * @filename TfLoginUserDAO.java
 * @author Hmilyld
 * 
 */

@Repository
public class TfLoginUserDAO extends BaseDAO<TfLoginUser> {
	/**
	 * 在角色下删除人员时，对应的人员信息也删除操作
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void delUserToRole(TfLoginUser entity) throws Exception {
		sqlSessionTemplate.delete("TfLoginUser.delUserToRole", entity);
	}

	/**
	 * 
	 * @param tfLoginUser
	 *            新增操作，
	 */
	public void insertUserAndDeptToUser(TfLoginUser tfLoginUser,
			TfUserDept tfUserDept, TfRoleUser tfRoleUser) {
		sqlSessionTemplate.insert("TfLoginUser.insert", tfLoginUser);
		sqlSessionTemplate.insert("TfUserDept.insert", tfUserDept);
		String roleGuid = tfRoleUser.getRoleGuid();
		if (roleGuid == null || "".equals(roleGuid)) {
			sqlSessionTemplate.insert("TfRoleUser.insert", tfRoleUser);
		} else {
			String[] rGuid = roleGuid.split(",");
			for (String str : rGuid) {
				tfRoleUser.setRoleGuid(str);
				sqlSessionTemplate.insert("TfRoleUser.insert", tfRoleUser);
			}
		}
	}

	/**
	 * 通过角色ID查询人员数量 ，返回为int类型
	 * 
	 * @param entity
	 * @return
	 */
	public int findUserByRoleGuidCount(TfLoginUser entity) {
		return sqlSessionTemplate.selectOne("TfLoginUser.findByRoleGuidCount",
				entity);
	}

	/**
	 * 通过角色Guid查询人员信息
	 * 
	 * @param entity
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<TfLoginUser> findUserByRoleGuid(TfLoginUser entity, int offset,
			int limit) {
		return sqlSessionTemplate.selectList("TfLoginUser.findByRoleGuid",
				entity, new RowBounds(offset, limit));
	}

	/**
	 * 通过部门Id来查询人员信息
	 * 
	 * @param tfUserDept
	 * @param start
	 * @param pageCount
	 * @return
	 */
	public List<TfLoginUser> findUserByDept(TfLoginUser entity, int offset,
			int limit) {
		return sqlSessionTemplate.selectList("TfLoginUser.findByDeptGuid",
				entity, new RowBounds(offset, limit));
	}

	/**
	 * 通过部门Id来查询人员信息，无分页
	 * 
	 * @param tfUserDept
	 * @param start
	 * @param pageCount
	 * @return
	 */
	public List<TfLoginUser> findUserByDept(TfLoginUser entity) {
		return sqlSessionTemplate.selectList("TfLoginUser.findByDeptGuid",
				entity);
	}

	/**
	 * 按照部门来查询没有分配角色的人员信息，无分页
	 * 
	 * @param entity
	 * @return
	 */
	public List<TfLoginUser> findNotRoleByDeptGuid(TfLoginUser entity) {
		return sqlSessionTemplate.selectList(
				"TfLoginUser.findNotRoleByDeptGuid", entity);
	}

	/**
	 * 通过部门Id来查询对应的人员总数，返回int类型
	 * 
	 * @param tfUserDept
	 * @return
	 */
	public int findUserByDeptGuidCount(TfLoginUser entity) {
		return sqlSessionTemplate.selectOne("TfLoginUser.findByDeptGuidCount",
				entity);
	}

	// 更新人员信息表是同时更新人员与角色的信息表
	public void updateUserAndRoleUser(TfLoginUser entity, TfRoleUser tfRoleUser)
			throws Exception {
		sqlSessionTemplate.update("TfLoginUser.update", entity);
		sqlSessionTemplate.update("TfRoleUser.update", tfRoleUser);
	}

	/**
	 * 根据编码查询人员信息，自动对编码进行前置补0
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public TfLoginUser findByCode(String code) throws Exception {
		code = Functions.joinZero(code, 8);
		TfLoginUser user = new TfLoginUser();
		user.setCode(code);
		return view(user);
	}
	

	/**
	 * @param t
	 * @return
	 * 通过管理机关部门查找当前部门下的所有员工
	 */
	public List<TfLoginUser> findUserByDeptGuid(TfLoginUser t) {
		return sqlSessionTemplate.selectList("TfLoginUser.findUserByDeptGuid",t);
	}

}
