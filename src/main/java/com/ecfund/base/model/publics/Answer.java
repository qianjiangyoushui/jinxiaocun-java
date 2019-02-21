package com.ecfund.base.model.publics;


import com.alibaba.fastjson.annotation.JSONField;
import com.ecfund.base.model.users.WxUser;

import java.util.Date;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-01-14 14:45
 * @filename Answer.java
 *
 */

public class Answer implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String wxUserId;

    private String questionId;

    private String content;
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date createdate;

    private Integer status;

    private Integer sort;

    private WxUser user;
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getWxUserId() {
        return wxUserId;
    }

    public void setWxUserId(String wxUserId) {
        this.wxUserId = wxUserId;
    }
    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
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
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public WxUser getUser() {
        return user;
    }

    public void setUser(WxUser user) {
        this.user = user;
    }
}