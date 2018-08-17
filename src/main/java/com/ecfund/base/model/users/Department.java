package com.ecfund.base.model.users;


/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-18 18:08
 * @filename Department.java
 * 
 */
 
public class Department implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String userid;
    
    private String companyid;
    
    private String departid;
    
    private String departname;// 部门名称
    
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }
    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }

	public String getDepartname() {
		return departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}
}