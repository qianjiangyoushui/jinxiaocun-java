package com.ecfund.base.model.users;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-18 18:07
 * @filename Company.java
 * 
 */

public class Company implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String guid;

	private String companyname;

	private String contactperson;

	private String province;

	private String city;

	private String area;

	private String town;

	private BigDecimal plantarea;

	private BigDecimal plantyears;

	private Date registdate;

	private String operatorid;
	
	private String status;

	private String parentids;
	private String parentid;
	private String orgtype;
	private int order;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
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

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public BigDecimal getPlantarea() {
		return plantarea;
	}

	public void setPlantarea(BigDecimal plantarea) {
		this.plantarea = plantarea;
	}

	public BigDecimal getPlantyears() {
		return plantyears;
	}

	public void setPlantyears(BigDecimal plantyears) {
		this.plantyears = plantyears;
	}

	public Date getRegistdate() {
		return registdate;
	}

	public void setRegistdate(Date registdate) {
		this.registdate = registdate;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}