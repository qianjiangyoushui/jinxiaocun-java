package com.ecfund.base.dao.mybatis;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date 2012-4-23 下午7:57:32
 * @filename MySQLDialect.java
 * @author HMILYLD
 * 
 */

public class MySQLDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int skipResults, int maxResults) {
		sql = sql.trim();
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect.append(sql);
		pagingSelect.append(" limit " + skipResults + " ," + maxResults);
		return pagingSelect.toString();
	}

}
