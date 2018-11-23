package com.ecfund.base.model.eliteG1;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-07 11:15
 * @filename Manure.java
 * 
 */
 
public class Manure implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String seedfileid;
    @JSONField(format = "yyyy-MM-dd")
    private Date manuredate;
    
    private String muck;
    
    private String muckfactory;
    
    private String content;
    
    private BigDecimal dosage;
    
    private String dosageunit;
    
    private String usetype;
    
    private String byperson;
    
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
    public Date getManuredate() {
        return manuredate;
    }

    public void setManuredate(Date manuredate) {
        this.manuredate = manuredate;
    }
    public String getMuck() {
        return muck;
    }

    public void setMuck(String muck) {
        this.muck = muck;
    }
    public String getMuckfactory() {
        return muckfactory;
    }

    public void setMuckfactory(String muckfactory) {
        this.muckfactory = muckfactory;
    }
   
    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getDosage() {
        return dosage;
    }

    public void setDosage(BigDecimal dosage) {
        this.dosage = dosage;
    }
    public String getUsetype() {
        return usetype;
    }

    public void setUsetype(String usetype) {
        this.usetype = usetype;
    }
    public String getByperson() {
        return byperson;
    }

    public void setByperson(String byperson) {
        this.byperson = byperson;
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

	public String getDosageunit() {
		return dosageunit;
	}

	public void setDosageunit(String dosageunit) {
		this.dosageunit = dosageunit;
	}
    
}