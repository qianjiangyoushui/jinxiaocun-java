package com.ecfund.base.util.common;

/**
 * 
 * 通用杂项工具类
 * 
 * @date 2013-2-27 下午4:47:46
 * @filename Functions.java
 * @author HMILYLD
 * 
 */

public class Functions {

	/**
	 * 监测字符串长度，如达不到指定的长度，则在前面补充0
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String joinZero(String str, int length) throws Exception {
		if (null == str) {
			return null;
		} else {
			if (str.length() < length) {
				int size = length - str.length();
				for (int i = 0; i < size; i++) {
					str = ("0" + str);
				}
			}
			return str;
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(Functions.joinZero("1234", -1));

	}

}
