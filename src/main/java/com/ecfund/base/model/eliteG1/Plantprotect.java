package com.ecfund.base.model.eliteG1;

import java.util.Date;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-03 16:10
 * @filename Plantprotect.java
 * 
 */
 
public class Plantprotect implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String seedfileid;
    
    private Date dodate;
    
    private String usetype;
    
    private String drugkind;
    
    private String drugdose;
    
    private String drugdoseunit;
    
    private String waterdose;
    
    private String drugusetype;
    
    private String startdate;
    
    private String enddate;
    
    private String uploadperson;
    
    private Date createdate;
    
    private String operatorid;
    
    private String name;
    
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
    public Date getDodate() {
        return dodate;
    }

    public void setDodate(Date dodate) {
        this.dodate = dodate;
    }
    public String getUsetype() {
        return usetype;
    }

    public void setUsetype(String usetype) {
        this.usetype = usetype;
    }
    public String getDrugkind() {
        return drugkind;
    }

    public void setDrugkind(String drugkind) {
        this.drugkind = drugkind;
    }
   
    public String getDrugdose() {
		return drugdose;
	}

	public void setDrugdose(String drugdose) {
		this.drugdose = drugdose;
	}

	public String getWaterdose() {
		return waterdose;
	}

	public void setWaterdose(String waterdose) {
		this.waterdose = waterdose;
	}

	public String getDrugusetype() {
        return drugusetype;
    }

    public void setDrugusetype(String drugusetype) {
        this.drugusetype = drugusetype;
    }
   
    public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getUploadperson() {
        return uploadperson;
    }

    public void setUploadperson(String uploadperson) {
        this.uploadperson = uploadperson;
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

	public String getDrugdoseunit() {
		return drugdoseunit;
	}

	public void setDrugdoseunit(String drugdoseunit) {
		this.drugdoseunit = drugdoseunit;
	}
}