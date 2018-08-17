package com.ecfund.base.model.eliteG1;

import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-03 11:20
 * @filename Pest.java
 * 
 */
 
public class Pest implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String seedfileid;
    
    private Date happendate;
    
    private String pesttype;
    
    private String placecode;
    
    private BigDecimal probability;
    
    private String principal;
    
    private Date createdate;
    
    private String operatorid;
    
    private String name;//录入人名称
    
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
    public Date getHappendate() {
        return happendate;
    }

    public void setHappendate(Date happendate) {
        this.happendate = happendate;
    }
    public String getPesttype() {
        return pesttype;
    }

    public void setPesttype(String pesttype) {
        this.pesttype = pesttype;
    }
    public String getPlacecode() {
        return placecode;
    }

    public void setPlacecode(String placecode) {
        this.placecode = placecode;
    }
    public BigDecimal getProbability() {
        return probability;
    }

    public void setProbability(BigDecimal probability) {
        this.probability = probability;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}