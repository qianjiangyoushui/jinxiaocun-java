package com.ecfund.base.model.g2g3;

import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-18 09:43
 * @filename Thecrop.java
 * 
 */
 
public class Thecrop implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String plotid;
    
    private String plantcrops;
    
    private String medicate;
    
    private Date createdate;
    
    private String operatorid;
    
    private Integer years;
    
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getPlotid() {
        return plotid;
    }

    public void setPlotid(String plotid) {
        this.plotid = plotid;
    }
    public String getPlantcrops() {
        return plantcrops;
    }

    public void setPlantcrops(String plantcrops) {
        this.plantcrops = plantcrops;
    }
    public String getMedicate() {
        return medicate;
    }

    public void setMedicate(String medicate) {
        this.medicate = medicate;
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
    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }
}