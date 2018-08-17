package com.ecfund.base.model.sales;

import java.util.Date;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-09 17:09
 * @filename Saledservice.java
 * 
 */
 
public class Saledservice implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String clientid;
    
    private String salesid;
    
    private String traintype;
    
    private String tlantdensity;
    
    private String isdisinfecte;
    
    private String isremoved;
    
    private String rottenrate;
    
    private String ismedicine;
    
    private String medicinetype;
    
    private String isdisinfect;
    
    private String disinfecttype;
    
    private String watering;
    
    private String manure;
    
    private String pests;
    
    private String recordby;
    
    private Date lastupdate;
    
    private Date createdate;
    
    private String operatorid;
    
    private String batch;
    
    public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }
    public String getSalesid() {
        return salesid;
    }

    public void setSalesid(String salesid) {
        this.salesid = salesid;
    }
    public String getTraintype() {
        return traintype;
    }

    public void setTraintype(String traintype) {
        this.traintype = traintype;
    }
    public String getTlantdensity() {
        return tlantdensity;
    }

    public void setTlantdensity(String tlantdensity) {
        this.tlantdensity = tlantdensity;
    }
    public String getIsdisinfecte() {
        return isdisinfecte;
    }

    public void setIsdisinfecte(String isdisinfecte) {
        this.isdisinfecte = isdisinfecte;
    }
    public String getIsremoved() {
        return isremoved;
    }

    public void setIsremoved(String isremoved) {
        this.isremoved = isremoved;
    }
    public String getRottenrate() {
        return rottenrate;
    }

    public void setRottenrate(String rottenrate) {
        this.rottenrate = rottenrate;
    }
    public String getIsmedicine() {
        return ismedicine;
    }

    public void setIsmedicine(String ismedicine) {
        this.ismedicine = ismedicine;
    }
    public String getMedicinetype() {
        return medicinetype;
    }

    public void setMedicinetype(String medicinetype) {
        this.medicinetype = medicinetype;
    }
    public String getIsdisinfect() {
        return isdisinfect;
    }

    public void setIsdisinfect(String isdisinfect) {
        this.isdisinfect = isdisinfect;
    }
    public String getDisinfecttype() {
        return disinfecttype;
    }

    public void setDisinfecttype(String disinfecttype) {
        this.disinfecttype = disinfecttype;
    }
    public String getWatering() {
        return watering;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }
    public String getManure() {
        return manure;
    }

    public void setManure(String manure) {
        this.manure = manure;
    }
    public String getPests() {
        return pests;
    }

    public void setPests(String pests) {
        this.pests = pests;
    }
    public String getRecordby() {
        return recordby;
    }

    public void setRecordby(String recordby) {
        this.recordby = recordby;
    }
    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
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