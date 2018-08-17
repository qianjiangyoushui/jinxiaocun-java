package com.ecfund.base.dao.mybatis;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date 2012-4-23 下午7:55:14
 * @filename OracleDialect.java
 * @author HMILYLD
 * 
 */

public class OracleDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.trim();
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect
				.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ ) where rownum_ > ").append(offset)
				.append(" and rownum_ <= ").append(offset + limit);
		return pagingSelect.toString();
	}

}
