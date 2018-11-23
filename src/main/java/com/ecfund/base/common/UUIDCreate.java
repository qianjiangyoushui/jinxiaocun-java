package com.ecfund.base.common;

import java.util.Calendar;
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
		Calendar calendar = Calendar.getInstance();
		String month = calendar.get(Calendar.MONTH)+1+"";
		String day = calendar.get(Calendar.DAY_OF_MONTH)+"";
		System.out.println(month+"#"+day);
	}



}
