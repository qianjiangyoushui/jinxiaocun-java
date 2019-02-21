package com.ecfund.base.dao.users;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.users.WxUser;
import org.springframework.stereotype.Repository;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-01-10 11:37
 * @filename WxUserDAO.java
 *
 */

@Repository
public class WxUserDAO extends BaseDAO<WxUser> {

    public WxUser viewByGuid(String guid)throws Exception{
        WxUser wxUser = new WxUser();
        wxUser.setGuid(guid);
        return this.view(wxUser);
    }
}