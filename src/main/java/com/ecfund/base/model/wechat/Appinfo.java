package com.ecfund.base.model.wechat;



/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-15 10:01
 * @filename Appinfo.java
 *
 */

public class Appinfo implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String appid;

    private String ip;

    private Integer loginDuration;

    private String qcloudAppid;

    private String secret;

    private Integer sessionDuration;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public Integer getLoginDuration() {
        return loginDuration;
    }

    public void setLoginDuration(Integer loginDuration) {
        this.loginDuration = loginDuration;
    }
    public String getQcloudAppid() {
        return qcloudAppid;
    }

    public void setQcloudAppid(String qcloudAppid) {
        this.qcloudAppid = qcloudAppid;
    }
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
    public Integer getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(Integer sessionDuration) {
        this.sessionDuration = sessionDuration;
    }
}
