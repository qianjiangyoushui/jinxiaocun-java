package com.ecfund.base.model.system;

import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2012-09-12 10:48
 * @filename TfRoles.java
 * @author Hmilyld
 * 
 */

public class TfRoles implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String guid;

	private String name;

	private String code;

	private String description;

	private String deptGuid;

	private Date writeDate;

	private Integer sort; //999超级管理员，只能有一个

	private String memo;

	private String likeRoleName;

	private String del = "0";

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDeptGuid() {
		return deptGuid;
	}

	public void setDeptGuid(String deptGuid) {
		this.deptGuid = deptGuid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getLikeRoleName() {
		return likeRoleName;
	}

	public void setLikeRoleName(String likeRoleName) {
		this.likeRoleName = likeRoleName;
	}
}