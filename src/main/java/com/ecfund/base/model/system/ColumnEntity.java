package com.ecfund.base.model.system;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date 2012-8-6 上午10:18:21
 * @filename ColumnEntity.java
 * @author HMILYLD
 * 
 */

public class ColumnEntity {
	private String name;

	private String type;

	private String isNull;

	private String defaultValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
