package com.ecfund.base.model.system;

import java.util.List;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date 2012-8-6 上午10:12:29
 * @filename CreateSQLEntity.java
 * @author HMILYLD
 * 
 */

public class TableEntity {
	private String dbName;

	private String key;

	private String comment;

	private List<ColumnEntity> col;

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<ColumnEntity> getCol() {
		return col;
	}

	public void setCol(List<ColumnEntity> col) {
		this.col = col;
	}
}
