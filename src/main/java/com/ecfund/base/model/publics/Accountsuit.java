package com.ecfund.base.model.publics;

import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-06-03 09:21
 * @filename Accountsuit.java
 *
 */

public class Accountsuit implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String name;

    private String financialyear;

    private Date startdate;

    private Date enddate;

    private Date createdate;

    private String companyid;

    private String companyname;
    private Integer current;
    private Integer sort;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getFinancialyear() {
        return financialyear;
    }

    public void setFinancialyear(String financialyear) {
        this.financialyear = financialyear;
    }
    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
    public Integer getSort() {
        return sort;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}