package com.ecfund.base.model.depot;

import java.util.Date;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-24 09:26
 * @filename Environment.java
 * 
 */
 
public class Environment implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String depotid;
    
    private Date recorddate;
    
    private BigDecimal temperature;
    
    private BigDecimal humidity;
    
    private String operatorid;
    
    private Date createdate;
    private Date startdate;
    private Date enddate;
    
    public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

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
    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }
    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }
    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }
    public String getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}