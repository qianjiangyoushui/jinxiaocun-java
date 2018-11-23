package com.ecfund.base.model.publics;

import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-27 17:08
 * @filename Upimage.java
 * 
 */
 
public class Upimage implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String url;
    private String url2;

    private String imagename;
    
    private String imagetype;
    
    private Date uploaddate;
    
    private String operatorid;
    
    private String relatedid;
    
    private Date takedate;
    
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }
    public String getImagetype() {
        return imagetype;
    }

    public void setImagetype(String imagetype) {
        this.imagetype = imagetype;
    }
    public Date getUploaddate() {
        return uploaddate;
    }

    public void setUploaddate(Date uploaddate) {
        this.uploaddate = uploaddate;
    }
    public String getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid;
    }
    public String getRelatedid() {
        return relatedid;
    }

    public void setRelatedid(String relatedid) {
        this.relatedid = relatedid;
    }
    public Date getTakedate() {
        return takedate;
    }

    public void setTakedate(Date takedate) {
        this.takedate = takedate;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }
}