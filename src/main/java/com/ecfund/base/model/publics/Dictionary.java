package com.ecfund.base.model.publics;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-18 18:11
 * @filename Dictionary.java
 * 
 */

public class Dictionary implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String guid;

	private String dictname;

	private String dictid;

	private String belongsid;

	private String belongsname;

	private String keyvalue;

	private String sort;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getDictname() {
		return dictname;
	}

	public void setDictname(String dictname) {
		this.dictname = dictname;
	}

	public String getDictid() {
		return dictid;
	}

	public void setDictid(String dictid) {
		this.dictid = dictid;
	}

	public String getBelongsid() {
		return belongsid;
	}

	public void setBelongsid(String belongsid) {
		this.belongsid = belongsid;
	}

	public String getBelongsname() {
		return belongsname;
	}

	public void setBelongsname(String belongsname) {
		this.belongsname = belongsname;
	}

	public String getKeyvalue() {
		return keyvalue;
	}

	public void setKeyvalue(String keyvalue) {
		this.keyvalue = keyvalue;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
}