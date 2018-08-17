package com.ecfund.base.model.depot;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-08 14:58
 * @filename Depot.java
 * 
 */

public class Depot implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String guid;

	private String depotcode;

	private BigDecimal reserves;

	private String description;

	private Date createdate;

	private String operatorid;

	private String companyid;

	private String principal;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getDepotcode() {
		return depotcode;
	}

	public void setDepotcode(String depotcode) {
		this.depotcode = depotcode;
	}

	public BigDecimal getReserves() {
		return reserves;
	}

	public void setReserves(BigDecimal reserves) {
		this.reserves = reserves;
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

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
}