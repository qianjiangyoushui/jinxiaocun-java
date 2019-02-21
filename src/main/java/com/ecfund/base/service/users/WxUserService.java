package com.ecfund.base.service.users;

import com.ecfund.base.dao.users.WxUserDAO;
import com.ecfund.base.model.users.WxUser;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-01-10 11:38
 * @filename WxUserService.java
 *
 */

@Service
public class WxUserService extends BaseService<WxUser> {

    //@Autowired
    //private WxUserDAO wxUserDAO;

    @Autowired
    public void setBaseDAO(WxUserDAO wxUserDAO) {
        super.setBaseDAO(wxUserDAO);
    }

}
