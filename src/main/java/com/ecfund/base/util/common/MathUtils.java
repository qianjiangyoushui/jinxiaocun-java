package com.ecfund.base.util.common;

import java.math.BigDecimal;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date 2013-2-28 上午10:52:28
 * @filename MathUtils.java
 * @author HMILYLD
 * 
 */

public class MathUtils {

	public static int convertDouble(Object obj) throws Exception {
		double d;
		if (obj instanceof Double) {
			d = (Double) obj;
		} else {
			d = new BigDecimal(obj.toString()).doubleValue();
		}
		return (int) d;
	}

	public static double convertDouble2(Object obj) throws Exception {
		double d;
		if (obj instanceof Double) {
			d = (Double) obj;
		} else {
			d = new BigDecimal(obj.toString()).doubleValue();
		}
		return d;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
