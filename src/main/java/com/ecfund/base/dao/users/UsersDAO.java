package com.ecfund.base.dao.users;

import com.ecfund.base.util.common.MD5Utils;
import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.users.Users;

/**
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2017-07-19 10:46
 * @filename UsersDAO.java
 */

@Repository
public class UsersDAO extends BaseDAO<Users> {

    public Users findByOpenid(String openid) {
        try {
            Users sessioninfo = new Users();
            sessioninfo.setOpenid(openid);
            return this.view(sessioninfo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Users login(String userName, String passwd) {
        try {
            Users user = new Users();
            user.setPassword(MD5Utils.encryString(passwd));
            user.setTelphone(userName);
            return this.view(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Users findRoleList(Users users){
        return sqlSessionTemplate.selectOne(Users.class.getSimpleName()
                + ".findRoles", users);
    }


}