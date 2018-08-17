package com.ecfund.base.model.system;

/**
 * 
 * 角色和人员关系类
 * 
 * @date 2012-09-17 17:56
 * @filename TfRoleUser.java
 * @author Hmilyld
 * 
 */
 
public class TfRoleUser implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String roleGuid;
    
    private String userGuid;
    
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
}