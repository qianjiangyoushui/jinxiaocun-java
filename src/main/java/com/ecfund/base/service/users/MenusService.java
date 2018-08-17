package com.ecfund.base.service.users;

import com.ecfund.base.dao.users.MenusDAO;
import com.ecfund.base.model.users.Menus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.service.BaseService;


/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-14 16:41
 * @filename MenusService.java
 *
 */

@Service
public class MenusService extends BaseService<Menus> {

    //@Autowired
    //private MenusDAO menusDAO;

    @Autowired
    public void setBaseDAO(MenusDAO menusDAO) {
        super.setBaseDAO(menusDAO);
    }

}
