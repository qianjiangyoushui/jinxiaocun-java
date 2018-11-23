package com.ecfund.base.service.users;

import com.ecfund.base.dao.users.RolesDAO;
import com.ecfund.base.dao.users.UserRoleDAO;
import com.ecfund.base.dao.users.UsersDAO;
import com.ecfund.base.model.users.Areapresident;
import com.ecfund.base.dao.users.AreapresidentDAO;
import com.ecfund.base.model.users.Roles;
import com.ecfund.base.model.users.UserRole;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-09-30 10:54
 * @filename AreapresidentService.java
 *
 */

@Service
public class AreapresidentService extends BaseService<Areapresident> {

    @Autowired
    private AreapresidentDAO areapresidentDAO;
   @Autowired
    private UsersDAO usersDAO;
   @Autowired
    private UserRoleDAO userRoleDAO;
   @Autowired
    private RolesDAO rolesDAO;

    @Autowired
    public void setBaseDAO(AreapresidentDAO areapresidentDAO) {
        super.setBaseDAO(areapresidentDAO);
    }

    @Transactional
    public void addPresident(String username, String telphone, String password, String area) throws Exception{
        Roles roles = new Roles();
        roles.setCode("area");
        roles = rolesDAO.view(roles);
        Users users = new Users();
        users.setTelphone(telphone);
        Users u = usersDAO.view(users);
        Areapresident areapresident = new Areapresident();
        if(null==u){
            users.setPassword(MD5Utils.encryString(password));
            users.setRegistdate(Calendar.getInstance().getTime());
            users.setUsername(username);
            String userGuid = usersDAO.insert(users);
            areapresident.setArea(area);
            areapresident.setUserid(userGuid);
            this.insert(areapresident);
            UserRole userRole = new UserRole();
            userRole.setUserGuid(userGuid);
            userRole.setRoleGuid(roles.getGuid());
            userRoleDAO.insert(userRole);
        }else{
            areapresident.setArea(area);
            areapresident.setUserid(u.getGuid());
            this.insert(areapresident);
            UserRole userRole = new UserRole();
            userRole.setUserGuid(u.getGuid());
            userRole.setRoleGuid(roles.getGuid());
            userRoleDAO.insert(userRole);
        }
    }

    @Transactional
    public void deletePresident(Areapresident areapresident) throws Exception{
        Roles roles = new Roles();
        roles.setCode("area");
        roles = rolesDAO.view(roles);
        UserRole userRole = new UserRole();
        userRole.setUserGuid(areapresident.getUserid());
        userRole.setRoleGuid(roles.getGuid());
        userRoleDAO.delete(userRole);
        delete(areapresident);
    }
}
