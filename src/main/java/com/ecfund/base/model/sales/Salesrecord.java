package com.ecfund.base.model.sales;

import java.math.BigDecimal;
import java.util.Date;

import com.ecfund.base.model.seedfile.Seedfile;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-08 15:34
 * @filename Salesrecord.java
 * 
 */
 
public class Salesrecord implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String clientid;
    
    private String seedid;
    
    private String companyid;
    
    private String batch;
    
    private BigDecimal salesamount;
    
    private Date salesdate;
    
    private String carlicense;
    
    private String depotid;
    
    private Date createdate;
    
    private String operatorid;
    
    private Seedfile seedfile;
    
    private Clients client;
    private String seedbatch;
    private String depotcode;
    
    public String getSeedbatch() {
		return seedbatch;
	}

	public void setSeedbatch(String seedbatch) {
		this.seedbatch = seedbatch;
	}

	public String getDepotcode() {
		return depotcode;
	}

	public void setDepotcode(String depotcode) {
		this.depotcode = depotcode;
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
    public String getSeedid() {
        return seedid;
    }

    public void setSeedid(String seedid) {
        this.seedid = seedid;
    }
    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }
    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
    public BigDecimal getSalesamount() {
        return salesamount;
    }

    public void setSalesamount(BigDecimal salesamount) {
        this.salesamount = salesamount;
    }
    public Date getSalesdate() {
        return salesdate;
    }

    public void setSalesdate(Date salesdate) {
        this.salesdate = salesdate;
    }
    public String getCarlicense() {
        return carlicense;
    }

    public void setCarlicense(String carlicense) {
        this.carlicense = carlicense;
    }
    public String getDepotid() {
        return depotid;
    }

    public void setDepotid(String depotid) {
        this.depotid = depotid;
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

	public Seedfile getSeedfile() {
		return seedfile;
	}

	public void setSeedfile(Seedfile seedfile) {
		this.seedfile = seedfile;
	}

	public Clients getClient() {
		return client;
	}

	public void setClient(Clients client) {
		this.client = client;
	}
	
}