package com.ecfund.base.model.users;


import java.util.Date;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-14 17:02
 * @filename UserRole.java
 *
 */

public class UserRole implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String roleGuid;

    private String userGuid;

    private Date updateDate;

    public String getRoleGuid() {
        return roleGuid;
    }

    public void setRoleGuid(String roleGuid) {
        this.roleGuid = roleGuid;
    }
    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
