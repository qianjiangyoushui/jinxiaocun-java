package com.ecfund.base.model.eliteG1;

import java.util.Date;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-29 09:52
 * @filename Watering.java
 * 
 */
 
public class Watering implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String seedfileid;
    
    private Date irrigatedate;
    
    private String irrigatestyle;
    
    private String irrigatewater;
    
    private String irrigatelength;
    
    private String remark;
    
    private String recordperson;
    
    private String principal;
    
    private Date createdate;
    
    private String operatorid;
    
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getSeedfileid() {
        return seedfileid;
    }

    public void setSeedfileid(String seedfileid) {
        this.seedfileid = seedfileid;
    }
    public Date getIrrigatedate() {
        return irrigatedate;
    }

    public void setIrrigatedate(Date irrigatedate) {
        this.irrigatedate = irrigatedate;
    }
    public String getIrrigatestyle() {
        return irrigatestyle;
    }

    public void setIrrigatestyle(String irrigatestyle) {
        this.irrigatestyle = irrigatestyle;
    }
    public String getIrrigatewater() {
        return irrigatewater;
    }

    public void setIrrigatewater(String irrigatewater) {
        this.irrigatewater = irrigatewater;
    }
    public String getIrrigatelength() {
        return irrigatelength;
    }

    public void setIrrigatelength(String irrigatelength) {
        this.irrigatelength = irrigatelength;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRecordperson() {
        return recordperson;
    }

    public void setRecordperson(String recordperson) {
        this.recordperson = recordperson;
    }
    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public String getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid;
    }
}