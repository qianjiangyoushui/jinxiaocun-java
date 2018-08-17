package com.ecfund.base.model.sales;

import java.math.BigDecimal;
import java.util.Date;

import com.ecfund.base.model.publics.Upimage;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-07 14:14
 * @filename Clients.java
 * 
 */

public class Clients implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String guid;

	private String clientname;

	private String tlephone;

	private BigDecimal plantarea;

	private String province;

	private String city;

	private String area;

	private String street;

	private String companyid;

	private Date createdate;

	private String operatorid;
	
	private String url;//头像地址
	
	private Upimage image;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getTlephone() {
		return tlephone;
	}

	public void setTlephone(String tlephone) {
		this.tlephone = tlephone;
	}

	public BigDecimal getPlantarea() {
		return plantarea;
	}

	public void setPlantarea(BigDecimal plantarea) {
		this.plantarea = plantarea;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
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

	public Upimage getImage() {
		return image;
	}

	public void setImage(Upimage image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}