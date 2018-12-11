package com.ecfund.base.model.publics;


import com.ecfund.base.model.users.Users;

import java.util.Date;
import java.util.List;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-24 11:16
 * @filename Growthrecord.java
 *
 */

public class Growthrecord implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String[] guids;

    private String companyid;

    private String userid;

    private String batchid;

    private String batchcode;

    private String content;

    private String step;

    private String type;

    private String month;

    private String day;

    private Integer visible;

    private Date createdate;

    private String plot;

    private Integer sort;

    private List<Upimage> images;

    private Users user;
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
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<Upimage> getImages() {
        return images;
    }

    public void setImages(List<Upimage> images) {
        this.images = images;
    }

    public String getBatchcode() {
        return batchcode;
    }

    public void setBatchcode(String batchcode) {
        this.batchcode = batchcode;
    }

    public String[] getGuids() {
        return guids;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setGuids(String[] guids) {
        this.guids = guids;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
