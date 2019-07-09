package com.ecfund.base.model.purchase;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-06-17 10:10
 * @filename Supplier.java
 *
 */

public class Supplier implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String companyid;

    private String departid;

    private String type;

    private String name;

    private String stype;
    private String stypename;

    private String zjCode;

    private String address;

    private String phone;

    private String bankaccount;

    private String bankname;

    private String dutycode;

    private String remark;

    private Integer disable;

    private String sort;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }
    public String getZjCode() {
        return zjCode;
    }

    public void setZjCode(String zjCode) {
        this.zjCode = zjCode;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }
    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
    public String getDutycode() {
        return dutycode;
    }

    public void setDutycode(String dutycode) {
        this.dutycode = dutycode;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getStypename() {
        return stypename;
    }

    public void setStypename(String stypename) {
        this.stypename = stypename;
    }
}