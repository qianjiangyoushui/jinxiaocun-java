package com.ecfund.base.model.g0;

import java.util.Date;
import java.util.List;

import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.seedfile.Seedfile;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-25 22:58
 * @filename Seedbed.java
 * 
 */

public class Seedbed implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String guid;

	private String seedbedname;

	private Integer amount;

	private String description;

	private Date createdate;

	private String operatorid;

	private String companyid;

	private String url;
	
	private List<Seedfile> seedfiles;
	
	private List<Upimage> images;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getSeedbedname() {
		return seedbedname;
	}

	public void setSeedbedname(String seedbedname) {
		this.seedbedname = seedbedname;
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

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
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