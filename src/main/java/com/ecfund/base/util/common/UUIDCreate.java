package com.ecfund.base.util.common;

import java.util.UUID;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date Feb 6, 2012 2:08:56 PM
 * @filename UUIDCreate.java
 * @author 胡志刚
 * 
 */
public class UUIDCreate {

	public static String get() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "").toUpperCase();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			//System.out.println(get());
		}
	}

}
