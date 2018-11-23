package com.ecfund.base.model.eliteG1;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-07 14:58
 * @filename Irrigation.java
 * 
 */
 
public class Irrigation implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String seedfileid;
    @JSONField(format = "yyyy-MM-dd")
    private Date irrigatedate;
    
    private String irrigatestyle;
    
    private String irrigatewater;
    
    private String speed;
    
    private BigDecimal rainfall;
    
    private String humidity;
    
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

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public BigDecimal getRainfall() {
		return rainfall;
	}

	public void setRainfall(BigDecimal rainfall) {
		this.rainfall = rainfall;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
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