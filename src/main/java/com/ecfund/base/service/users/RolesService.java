package com.ecfund.base.service.users;


import com.ecfund.base.dao.users.RolesDAO;
import com.ecfund.base.model.users.Roles;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-09-11 16:15
 * @filename RolesService.java
 *
 */

@Service
public class RolesService extends BaseService<Roles> {

    //@Autowired
    //private RolesDAO rolesDAO;

    @Autowired
    public void setBaseDAO(RolesDAO rolesDAO) {
        super.setBaseDAO(rolesDAO);
    }

}
