package com.ecfund.base.model.depot;

import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-24 09:21
 * @filename Accident.java
 * 
 */
 
public class Accident implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String depotid;
    
    private Date reportdate;
    
    private String fumigation;
    
    private String isdisinfect;
    
    private String temprange;
    
    private String ischange;
    
    private String changereason;
    
    private BigDecimal instock;
    
    private Date occurdate;
    
    private String occurpoint;
    
    private String occurtype;
    
    private String placechange;
    
    private String changedetail;
    
    private String reportname;
    
    private Date createdate;
    
    private String operatorid;
    
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getDepotid() {
        return depotid;
    }

    public void setDepotid(String depotid) {
        this.depotid = depotid;
    }
    public Date getReportdate() {
        return reportdate;
    }

    public void setReportdate(Date reportdate) {
        this.reportdate = reportdate;
    }
    public String getFumigation() {
        return fumigation;
    }

    public void setFumigation(String fumigation) {
        this.fumigation = fumigation;
    }
    public String getIsdisinfect() {
        return isdisinfect;
    }

    public void setIsdisinfect(String isdisinfect) {
        this.isdisinfect = isdisinfect;
    }
    public String getTemprange() {
        return temprange;
    }

    public void setTemprange(String temprange) {
        this.temprange = temprange;
    }
    public String getIschange() {
        return ischange;
    }

    public void setIschange(String ischange) {
        this.ischange = ischange;
    }
    public String getChangereason() {
        return changereason;
    }

    public void setChangereason(String changereason) {
        this.changereason = changereason;
    }
    public BigDecimal getInstock() {
        return instock;
    }

    public void setInstock(BigDecimal instock) {
        this.instock = instock;
    }
    public Date getOccurdate() {
        return occurdate;
    }

    public void setOccurdate(Date occurdate) {
        this.occurdate = occurdate;
    }
    public String getOccurpoint() {
        return occurpoint;
    }

    public void setOccurpoint(String occurpoint) {
        this.occurpoint = occurpoint;
    }
    public String getOccurtype() {
        return occurtype;
    }

    public void setOccurtype(String occurtype) {
        this.occurtype = occurtype;
    }
    public String getPlacechange() {
        return placechange;
    }

    public void setPlacechange(String placechange) {
        this.placechange = placechange;
    }
    public String getChangedetail() {
        return changedetail;
    }

    public void setChangedetail(String changedetail) {
        this.changedetail = changedetail;
    }
    public String getReportname() {
        return reportname;
    }

    public void setReportname(String reportname) {
        this.reportname = reportname;
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