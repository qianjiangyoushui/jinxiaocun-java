package com.ecfund.base.model.publics;

import java.util.Date;

import com.ecfund.base.model.users.Users;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-18 18:10
 * @filename Logs.java
 * 
 */

public class Logs implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String guid;

	private Date operatedate;

	private String operatorid;

	private String description;

	private String relatedid;
	
	private String username;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Date getOperatedate() {
		return operatedate;
	}

	public void setOperatedate(Date operatedate) {
		this.operatedate = operatedate;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRelatedid() {
		return relatedid;
	}

	public void setRelatedid(String relatedid) {
		this.relatedid = relatedid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}