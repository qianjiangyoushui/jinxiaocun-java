package com.ecfund.base.util.common;

import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringEscapeUtils;


/**
 * 
 * 字符串操作类
 * 
 * @date 2012-8-7 上午9:38:05
 * @filename StringUtils.java
 * @author HMILYLD
 * 
 */

public class StringUtils extends org.apache.commons.lang3.StringUtils{

	/**
	 * 将obj类型转换为String，如果为null，则返回null
	 * 
	 * @param obj
	 * @return
	 */
	public static String getObj(Object obj) {
		return (obj == null) ? null : obj.toString();
	}

	/**
	 * 转换第一个字符为大写
	 * 
	 * @param str
	 * @return
	 */
	public static String convertFirstUp(String str) {
		if (null == str || "".equals(str)) {
			return null;
		}
		String tmp = str.substring(0, 1).toUpperCase();
		str = (tmp + str.substring(1));
		return str;
	}

	/**
	 * 将字符串按照_分隔开,_后的第一个字符转换为大写,其余字符均为小写
	 * 
	 * @param str
	 * @param first
	 *            是否转换第一个字母为大写
	 * @return
	 */
	public static String convertLowToUp(String str, boolean frist) {
		StringBuffer sb = new StringBuffer();
		if (frist) {
			str = "_" + str;
		}
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '_') {
				// 判断是否还存在下一个
				if (i + 1 != c.length) {
					// 不存在下一个了
					sb.append(new String(new char[] { c[i + 1] }).toUpperCase());
					i++;
				}
			} else {
				sb.append(new String(new char[] { c[i] }).toLowerCase());
			}
		}
		return sb.toString();
	}

	/**
	 * 默认生成10位的数字随机数
	 * 
	 * @return
	 */
	public static String randStr() {
		return randStr(10);
	}

	/**
	 * 生成指定位数的随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String randStr(int length) {
		Random rand = new Random();
		String str = "";
		for (int i = 0; i < length; i++) {
			str += String.valueOf(rand.nextInt(10));
		}
		return str;
	}

	/**
	 * 将一个字符串数组按照指定的分割符拼接成字符串
	 * 
	 * @param array
	 * @param split
	 * @return
	 */
	public static String convertStrArray(String[] array, String split) {
		StringBuffer sb = new StringBuffer();
		int size = array.length;
		for (int i = 0; i < size; i++) {
			sb.append(array[i]);
			if ((i + 1) != size) {
				sb.append(split);
			}
		}
		return sb.toString();
	}

	/**
	 * 转换一个中文字符到指定编码格式
	 * 
	 * @param str
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String convertChs(String str, String charset)
			throws Exception {
		return URLEncoder.encode(str, charset);
	}

	/**
	 * 转换一个中文字符到UTF8编码格式
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String convertChs(String str) throws Exception {
		return convertChs(str, "UTF-8");
	}

	/**
	 * 下载文件时，对文件名进行编码
	 * 
	 * @param name
	 *            文件名称
	 * @param browser
	 *            浏览器类型
	 * @return
	 * @throws Exception
	 */
	public static String convertFileName(String name, String browser)
			throws Exception {
		if ("Firefox".equals(browser)) {
			return new String(name.getBytes("UTF-8"), "ISO-8859-1");
		} else {
			return convertChs(name);
		}
	}

	/**
	 * 转换list中的项，将各项指定的col以及英文逗号分隔开
	 * 
	 * @param list
	 * @param col
	 * @return
	 * @throws Exception
	 */
	public static String convertListToString(List<?> list, String col)
			throws Exception {
		if (list.size() != 0) {
			StringBuffer sb = new StringBuffer();
			for (Object o : list) {
				Class<? extends Object> c = o.getClass();
				String methodName = StringUtils.convertFirstUp(col);
				Method getMethod = c.getDeclaredMethod("get" + methodName);
				sb.append(getMethod.invoke(o).toString() + ",");
			}
			return sb.substring(0, sb.length() - 1);
		} else {
			return "";
		}
	}

	/**
	 * 转换list中的项，将各项指定的col以数组形式返回
	 * 
	 * @param list
	 * @param col
	 * @return
	 * @throws Exception
	 */
	public static String[] convertListToArray(List<?> list, String col)
			throws Exception {
		return convertListToString(list, col).split(",");
	}

	/**
	 * 删除一个数组中的重复项，并返回字符串
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String delRepeatItem(String[] str) throws Exception {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			if (!list.contains(str[i])) {
				list.add(str[i]);
			}
		}
		String[] type = new String[] {};
		return convertStrArray(list.toArray(type), ",");
	}

	/**
	 * 转换字符串中的数字，将1，2，3，4转换为一，二等，最多转换到10
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String convertNumToStr(String str) throws Exception {
		if (null == str) {
			return null;
		} else {
			String[] array = new String[] { "一", "二", "三", "四", "五", "六", "七",
					"八", "九", "十" };
			for (int i = 0; i < array.length; i++) {
				str = str.replace(Integer.toString(i + 1), array[i]);
			}
			return str;
		}
	}

	public static String convertStrToNum(String str) throws Exception {
		if (null == str) {
			return null;
		} else {
			String[] array = new String[] { "一", "二", "三", "四", "五", "六", "七",
					"八", "九", "十" };
			for (int i = 0; i < array.length; i++) {
				str = str.replace(array[i], Integer.toString(i + 1));
			}
			return str;
		}
	}
	public static boolean isEmpty(Object str) {
		return str == null || "".equals(str);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] array = new String[] { "1", "2", "3", "4" };
		//System.out.println(convertStrArray(array, "-"));
	}
	public static boolean isNotBlankAndEmpty(String s) {
		if (isNotBlank(s) && isNotEmpty(s)) {
			return true;
		}
		return false;
	}
}
