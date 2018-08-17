package com.ecfund.base.util.common;

import java.security.MessageDigest;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date 2012-9-24 上午11:13:11
 * @filename MD5Utils.java
 * @author HMILYLD
 * 
 */

public class MD5Utils {
	/**
	 * MD5加密
	 * 
	 * @param EnStr
	 * @return
	 * @throws Exception
	 */
	public static String encryString(String EnStr) throws Exception {
		try {
			MessageDigest alga = java.security.MessageDigest.getInstance("MD5");
			alga.update(EnStr.getBytes());
			byte[] msg = alga.digest();
			return byte2hex(msg);
		} catch (Exception e) {
			throw new Exception("进行MD5加密出现错误！");
		}
	}

	private static String byte2hex(byte[] b) {
		// 二行制转字符串
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
//		return hs.toString();
	}
	public static void main(String[] args){
		try{
			String s = encryString("admin");
			//System.out.println(s);
		}catch(Exception e){}
	}
}
