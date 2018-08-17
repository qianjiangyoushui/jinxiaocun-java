package com.ecfund.base.model.depot;

import java.math.BigDecimal;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-23 11:06
 * @filename Outinstorage.java
 * 
 */
 
public class Outinstorage implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String depotid;
    
    private String batch;
    
    private BigDecimal outamount;
    
    private String outuse;
    
    private Date outdate;
    
    private BigDecimal inamount;
    
    private String storagemethod;
    
    private Date indate;
    
    private String description;
    
    private String type;
    
    private Date createdate;
    
    private String operatorid;
    private String  batchcode;
    
    public String getBatchcode() {
		return batchcode;
	}

	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
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
    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
    public BigDecimal getOutamount() {
        return outamount;
    }

    public void setOutamount(BigDecimal outamount) {
        this.outamount = outamount;
    }
    public String getOutuse() {
        return outuse;
    }

    public void setOutuse(String outuse) {
        this.outuse = outuse;
    }
    public Date getOutdate() {
        return outdate;
    }

    public void setOutdate(Date outdate) {
        this.outdate = outdate;
    }
    public BigDecimal getInamount() {
        return inamount;
    }

    public void setInamount(BigDecimal inamount) {
        this.inamount = inamount;
    }
    public String getStoragemethod() {
        return storagemethod;
    }

    public void setStoragemethod(String storagemethod) {
        this.storagemethod = storagemethod;
    }
    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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