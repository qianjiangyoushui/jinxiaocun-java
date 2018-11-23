package com.ecfund.base.model.g0;



import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-09-04 09:47
 * @filename Bottlestore.java
 *
 */

public class Bottlestore implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String companyid;

    private String batchid;

    private String year;

    private Integer type;

    @JSONField(format = "yy/MM/dd")
    private Date indate;

    private Integer month;

    private String varietycode;

    private Integer inamount;

    private Integer outamount;

    private Integer useamount;

    private String source;

    private String username;

    private Integer sort;

    private String remark;

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
    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }
    public String getVarietycode() {
        return varietycode;
    }

    public void setVarietycode(String varietycode) {
        this.varietycode = varietycode;
    }
    public Integer getInamount() {
        return inamount;
    }

    public void setInamount(Integer inamount) {
        this.inamount = inamount;
    }
    public Integer getOutamount() {
        return outamount;
    }

    public void setOutamount(Integer outamount) {
        this.outamount = outamount;
    }
    public Integer getUseamount() {
        return useamount;
    }

    public void setUseamount(Integer useamount) {
        this.useamount = useamount;
    }
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}