package com.ecfund.base.common;

import java.util.UUID;

/** 
 * @author jiaxd
 * @version 2015年12月9日 下午3:49:10
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
