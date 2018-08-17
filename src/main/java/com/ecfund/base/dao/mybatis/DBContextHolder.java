package com.ecfund.base.dao.mybatis;


/**
 * 把当前使用的数据源保存在当前线程中,放在contextHolder中 
 * @author liugy
 *
 */
public class DBContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDBType(String dbType) {
		contextHolder.set(dbType);
	}

	public static String getDBType() {
		return contextHolder.get();
	}

	public static void clearDBType() {
		contextHolder.remove();
	}
}
