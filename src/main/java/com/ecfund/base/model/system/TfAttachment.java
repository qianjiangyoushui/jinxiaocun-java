package com.ecfund.base.model.system;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2012-10-23 18:21
 * @filename TfAttachment.java
 * @author Hmilyld
 * 
 */

public class TfAttachment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String guid;

	private String[] ids;

	private String url;

	private String likeUrl;

	private String original;

	private String filetype;

	private String userGuid;

	private String userName;

	private String modelType;

	private Date writeDate;

	private BigDecimal sort;

	private String memo;
	
	private String del = "0";

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public BigDecimal getSort() {
		return sort;
	}

	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLikeUrl() {
		return likeUrl;
	}

	public void setLikeUrl(String likeUrl) {
		this.likeUrl = likeUrl;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}
}