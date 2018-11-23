package com.ecfund.base.model.g2g3;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.seedfile.Seedfile;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-05 11:18
 * @filename Plots.java
 * 
 */
 
public class Plots implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String farmid;
    
    private String plotname;

    private String plotcode;

    private BigDecimal plantarea;
    
    private String irrigatetype;
    
    private String irrigbrand;
    
    private Integer wellsamount;
    
    private String wellsdisc;
    
    private BigDecimal distvillage;
    
    private BigDecimal distroad;
    
    private BigDecimal distother;
    
    private Date createdate;
    
    private String operatorid;
    
    private String url;
    
    private String companyid;
    
    private List<Seedfile> seedfiles;
    
    private List<Upimage> images;
    
    private List<Thecrop> thecrops;
    
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getFarmid() {
        return farmid;
    }

    public void setFarmid(String farmid) {
        this.farmid = farmid;
    }
    public String getPlotname() {
        return plotname;
    }

    public void setPlotname(String plotname) {
        this.plotname = plotname;
    }
    public String getIrrigatetype() {
        return irrigatetype;
    }

    public void setIrrigatetype(String irrigatetype) {
        this.irrigatetype = irrigatetype;
    }
    public String getIrrigbrand() {
        return irrigbrand;
    }

    public void setIrrigbrand(String irrigbrand) {
        this.irrigbrand = irrigbrand;
    }
    public Integer getWellsamount() {
        return wellsamount;
    }

    public void setWellsamount(Integer wellsamount) {
        this.wellsamount = wellsamount;
    }
    public String getWellsdisc() {
        return wellsdisc;
    }

    public void setWellsdisc(String wellsdisc) {
        this.wellsdisc = wellsdisc;
    }
    public BigDecimal getDistvillage() {
        return distvillage;
    }

    public void setDistvillage(BigDecimal distvillage) {
        this.distvillage = distvillage;
    }
    public BigDecimal getDistroad() {
        return distroad;
    }

    public void setDistroad(BigDecimal distroad) {
        this.distroad = distroad;
    }
    public BigDecimal getDistother() {
        return distother;
    }

    public void setDistother(BigDecimal distother) {
        this.distother = distother;
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

	public List<Thecrop> getThecrops() {
		return thecrops;
	}

	public void setThecrops(List<Thecrop> thecrops) {
		this.thecrops = thecrops;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

    public String getPlotcode() {
        return plotcode;
    }

    public void setPlotcode(String plotcode) {
        this.plotcode = plotcode;
    }

    public BigDecimal getPlantarea() {
        return plantarea;
    }

    public void setPlantarea(BigDecimal plantarea) {
        this.plantarea = plantarea;
    }
}