package com.ecfund.base.model.eliteG1;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.seedfile.Seedfile;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-31 14:49
 * @filename Greenhouses.java
 * 
 */
 
public class Greenhouses implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String companyid;
    
    private String housename;
    
    private BigDecimal distance;
    
    private BigDecimal areas;
    
    private Integer amount;
    
    private String description;
    
    private Date createdate;
    
    private String operatorid;
    
    private String url;
    
    private List<Seedfile> seedfiles;
    
    private List<Upimage> images;
    
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
   
    public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getHousename() {
        return housename;
    }

    public void setHousename(String housename) {
        this.housename = housename;
    }
    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
    public BigDecimal getAreas() {
        return areas;
    }

    public void setAreas(BigDecimal areas) {
        this.areas = areas;
    }
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

	public List<Seedfile> getSeedfiles() {
		return seedfiles;
	}

	public void setSeedfiles(List<Seedfile> seedfiles) {
		this.seedfiles = seedfiles;
	}

	public List<Upimage> getImages() {
		return images;
	}

	public void setImages(List<Upimage> images) {
		this.images = images;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}