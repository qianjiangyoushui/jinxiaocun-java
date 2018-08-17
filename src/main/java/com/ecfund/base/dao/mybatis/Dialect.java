package com.ecfund.base.dao.mybatis;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date 2012-4-23 下午7:54:17
 * @filename Dialect.java
 * @author HMILYLD
 * 
 */

public abstract class Dialect {

	public static enum Type {
		MYSQL, ORACLE
	}

	public abstract String getLimitString(String sql, int skipResults,
			int maxResults);

}
