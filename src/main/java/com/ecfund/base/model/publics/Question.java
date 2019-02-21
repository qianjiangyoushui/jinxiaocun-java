package com.ecfund.base.model.publics;


import com.alibaba.fastjson.annotation.JSONField;
import com.ecfund.base.model.users.WxUser;

import java.util.Date;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-01-11 15:24
 * @filename Question.java
 *
 */

public class Question implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String wxuserid;

    private String content;
    @JSONField(format = "yyyy-MM-dd")
    private Date createdate;

    private String longitude;

    private String latitude;

    private String province;

    private String city;

    private String imagesurl;

    private String simageurl;

    private String sort;

    private WxUser user;

    private int answerCount;
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getWxuserid() {
        return wxuserid;
    }

    public void setWxuserid(String wxuserid) {
        this.wxuserid = wxuserid;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getImagesurl() {
        return imagesurl;
    }

    public void setImagesurl(String imagesurl) {
        this.imagesurl = imagesurl;
    }
    public String getSimageurl() {
        return simageurl;
    }

    public void setSimageurl(String simageurl) {
        this.simageurl = simageurl;
    }
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public WxUser getUser() {
        return user;
    }

    public void setUser(WxUser user) {
        this.user = user;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }
}
