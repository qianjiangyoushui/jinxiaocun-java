package com.ecfund.base.dao.users;

import com.ecfund.base.model.users.Roles;
import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-14 16:53
 * @filename RolesDAO.java
 *
 */

@Repository
public class RolesDAO extends BaseDAO<Roles> {
    public Roles findMenuList(Roles roles){
        return sqlSessionTemplate.selectOne(Roles.class.getSimpleName()
                + ".findMenus", roles);
    }

}