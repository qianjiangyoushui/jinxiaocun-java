package com.ecfund.base.model.publics;

import java.util.Date;
import java.util.Date;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-09-28 16:38
 * @filename Message.java
 *
 */

public class Message implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String tittle;

    private String userid;

    private String des;

    private Date createdate;

    private Date updatedate;

    private String state;

    private String url;

    private String busid;

    private String remark;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getBusid() {
        return busid;
    }

    public void setBusid(String busid) {
        this.busid = busid;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}