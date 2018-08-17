package com.ecfund.base.util.db;

import java.sql.Types;

/**
 * 
 * 数据库类型对应类，转换数据库类型为java类型
 * 
 * @date 2012-2-13 下午03:10:34
 * @filename Types.java
 * @author 胡志刚
 * 
 */
public class SQLTypes {

	/**
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static String[] getType(int type) throws Exception {
		String[] str = new String[3];
		if (Types.BLOB == type) {
			str[0] = "VARCHAR";
			str[1] = "String";
			str[2] = "";
		} else if (Types.CLOB == type) {
			// BOOLEAN字段
			str[0] = "VARCHAR";
			str[1] = "String";
			str[2] = "";
		} else if (Types.BOOLEAN == type) {
			// BOOLEAN字段
			str[0] = "BOOLEAN";
			str[1] = "Boolean";
			str[2] = "";
		} else if (Types.CHAR == type) {
			// CHAR字段
			str[0] = "CHAR";
			str[1] = "Char";
			str[2] = "";
		} else if (Types.DATE == type) {
			// DATE字段
			str[0] = "DATE";
			str[1] = "Date";
			str[2] = "java.util.Date";
		} else if (Types.DECIMAL == type) {
			// DECIMAL字段
			str[0] = "DECIMAL";
			str[1] = "BigDecimal";
			str[2] = "java.math.BigDecimal";
		} else if (Types.DOUBLE == type) {
			// DOUBLE字段
			str[0] = "DOUBLE";
			str[1] = "Double";
			str[2] = "";
		} else if (Types.FLOAT == type) {
			// FLOAT字段
			str[0] = "FLOAT";
			str[1] = "Float";
			str[2] = "";
		} else if (Types.INTEGER == type) {
			// INTEGER字段
			str[0] = "INTEGER";
			str[1] = "Integer";
			str[2] = "";
		} else if (Types.NUMERIC == type) {
			// NUMERIC字段
			str[0] = "DECIMAL";
			str[1] = "BigDecimal";
			str[2] = "java.math.BigDecimal";
		} else if (Types.TIME == type) {
			// TIME字段
			str[0] = "DATE";
			str[1] = "Date";
			str[2] = "java.util.Date";
		} else if (Types.TIMESTAMP == type) {
			// TIMESTAMP字段
			str[0] = "DATE";
			str[1] = "Date";
			str[2] = "java.util.Date";
		} else if (Types.VARCHAR == type) {
			// VARCHAR字段
			str[0] = "VARCHAR";
			str[1] = "String";
			str[2] = "";
		} else {
			throw new Exception("数据类型错误!");
		}
		return str;
	}
}
