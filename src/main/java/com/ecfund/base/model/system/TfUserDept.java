package com.ecfund.base.model.system;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2012-09-12 23:33
 * @filename TfUserDept.java
 * @author Hmilyld
 * 
 */
 
public class TfUserDept implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String userGuid;
    
    private String deptGuid;
    
    private String isPrimay;
    
    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }
    public String getDeptGuid() {
        return deptGuid;
    }

    public void setDeptGuid(String deptGuid) {
        this.deptGuid = deptGuid;
    }
    public String getIsPrimay() {
        return isPrimay;
    }

    public void setIsPrimay(String isPrimay) {
        this.isPrimay = isPrimay;
    }
}