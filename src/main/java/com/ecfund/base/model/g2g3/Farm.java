package com.ecfund.base.model.g2g3;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ecfund.base.model.publics.Upimage;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-05 11:03
 * @filename Farm.java
 * 
 */
 
public class Farm implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String companyid;
    
    private String farmname;

    private String farmcode;
    
    private BigDecimal leasearea;
    
    private BigDecimal plantarea;
    
    private String soiltype;
    
    private String arableland;
    
    private BigDecimal rainfall;
    
    private Integer nofrost;
    
    private Integer transformer;
    
    private String transfordesc;
    
    private String mechanical;
    
    private String staffing;
    
    private BigDecimal altitude;

    private String latitude;
    private String longitude;
    
    private BigDecimal ph;
    
    private Integer leasestart;
    
    private Integer leaseend;
    
    private Date createdate;
    
    private String operatorid;
    
    private String url;

    private Integer farmtype;//农场类型2是客户农场，1，自有农场
    
    private List<Plots> plots;//地块信息
    
    private List<Upimage> images;//示意图
    
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
    public String getFarmname() {
        return farmname;
    }

    public void setFarmname(String farmname) {
        this.farmname = farmname;
    }
    public BigDecimal getLeasearea() {
        return leasearea;
    }

    public void setLeasearea(BigDecimal leasearea) {
        this.leasearea = leasearea;
    }
    public BigDecimal getPlantarea() {
        return plantarea;
    }

    public void setPlantarea(BigDecimal plantarea) {
        this.plantarea = plantarea;
    }
    public String getSoiltype() {
        return soiltype;
    }

    public void setSoiltype(String soiltype) {
        this.soiltype = soiltype;
    }
    public String getArableland() {
        return arableland;
    }

    public void setArableland(String arableland) {
        this.arableland = arableland;
    }
    public BigDecimal getRainfall() {
        return rainfall;
    }

    public void setRainfall(BigDecimal rainfall) {
        this.rainfall = rainfall;
    }
    public Integer getNofrost() {
        return nofrost;
    }

    public void setNofrost(Integer nofrost) {
        this.nofrost = nofrost;
    }
    public Integer getTransformer() {
        return transformer;
    }

    public void setTransformer(Integer transformer) {
        this.transformer = transformer;
    }
    public String getTransfordesc() {
        return transfordesc;
    }

    public void setTransfordesc(String transfordesc) {
        this.transfordesc = transfordesc;
    }
    public String getMechanical() {
        return mechanical;
    }

    public void setMechanical(String mechanical) {
        this.mechanical = mechanical;
    }
    public String getStaffing() {
        return staffing;
    }

    public void setStaffing(String staffing) {
        this.staffing = staffing;
    }
    public BigDecimal getAltitude() {
        return altitude;
    }

    public void setAltitude(BigDecimal altitude) {
        this.altitude = altitude;
    }
    public BigDecimal getPh() {
        return ph;
    }

    public void setPh(BigDecimal ph) {
        this.ph = ph;
    }
    public Integer getLeasestart() {
        return leasestart;
    }

    public void setLeasestart(Integer leasestart) {
        this.leasestart = leasestart;
    }
    public Integer getLeaseend() {
        return leaseend;
    }

    public void setLeaseend(Integer leaseend) {
        this.leaseend = leaseend;
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

	public List<Plots> getPlots() {
		return plots;
	}

	public void setPlots(List<Plots> plots) {
		this.plots = plots;
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

    public String getFarmcode() {
        return farmcode;
    }

    public void setFarmcode(String farmcode) {
        this.farmcode = farmcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getFarmtype() {
        return farmtype;
    }

    public void setFarmtype(Integer farmtype) {
        this.farmtype = farmtype;
    }
}