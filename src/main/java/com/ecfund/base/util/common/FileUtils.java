package com.ecfund.base.util.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 文件操作类
 * 
 * @date 2012-8-6 上午10:26:46
 * @filename FileUtils.java
 * @author HMILYLD
 * 
 */

public class FileUtils {

	/**
	 * 获取当前的classes路径
	 * 
	 * @return
	 */
	public String getPath() {
		return this.getClass().getResource("/").getPath();
	}

	/**
	 * 根据路径，获取该路径下的指定类型的文件
	 * 
	 * @param path
	 *            路径
	 * @param type
	 *            文件类型，例如png
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, String>> getFilesList(String path,
			String type) throws Exception {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		File f = new File(path);
		File[] files = f.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().indexOf(type) > -1) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", files[i].getName());
				map.put("lastUpdate", sdf.format(files[i].lastModified()));
				map.put("size", convertFileSize(files[i].length()));
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 转换文件大小
	 * 
	 * @param size
	 *            文件大小
	 * @return
	 */
	public static String convertFileSize(long size) {
		BigDecimal f = new BigDecimal(size);
		if (size < 1024) {
			return size + " B";
		} else if (size < (1024 * 1024)) {
			double d = f.divide(new BigDecimal(1024), 2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			return String.valueOf(d) + " K";
		} else if (size < (1024 * 1024 * 1024)) {
			double d = f.divide(new BigDecimal(1024 * 1024), 2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			return String.valueOf(d) + " M";
		} else {
			double d = f.divide(new BigDecimal(1024 * 1024 * 1024), 2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			return String.valueOf(d) + " G";
		}
	}

	/**
	 * 已字符串形式返回文件内容
	 * 
	 * @param path
	 * @param lineSplit
	 * @return
	 * @throws Exception
	 */
	public String read(String path, String lineSplit) throws Exception {
		File file = new File(path);
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String tmp = null;
		StringBuffer sb = new StringBuffer();
		while ((tmp = br.readLine()) != null) {
			sb.append(tmp + lineSplit);
		}
		br.close();
		reader.close();
		return sb.toString();
	}

	public static void main(String[] args) {
		FileUtils utils = new FileUtils();
		//System.out.println(utils.getPath());
	}
}