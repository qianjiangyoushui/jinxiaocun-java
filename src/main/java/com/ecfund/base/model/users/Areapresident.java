package com.ecfund.base.model.users;



/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-09-30 10:52
 * @filename Areapresident.java
 *
 */

public class Areapresident implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String userid;

    private String area;

    private String remark;

    private String bank;

    private Integer sort;

    private Users users;

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
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
