package com.ecfund.base.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.system.TfMenus;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2012-08-21 19:55
 * @filename TfMenusDAO.java
 * @author Hmilyld
 * 
 */

@Repository
public class TfMenusDAO extends BaseDAO<TfMenus> {

	public List<TfMenus> findByRole(TfMenus entity) throws Exception {
		return sqlSessionTemplate.selectList("TfMenus.findByRole", entity);
	}
	
	/**
	 * 根据角色guid获取菜单权限列表
	 * @param guids 根据角色guid在tf_role_menu表中获取的菜单guid集合
	 * @return 菜单权限列表
	 */
	public List<TfMenus> findMenuByRoleGuid(List<String> guids) {
		return sqlSessionTemplate.selectList("TfMenus.findMenuByRoleGuid", guids);
	}


}