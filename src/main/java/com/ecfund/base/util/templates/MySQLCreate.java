package com.ecfund.base.util.templates;

import java.util.HashMap;
import java.util.Map;

import com.ecfund.base.model.system.TableEntity;
import com.ecfund.base.util.freemarker.FreemarkerUtils;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date 2012-8-7 上午10:54:37
 * @filename MySQLCreate.java
 * @author HMILYLD
 * 
 */
public class MySQLCreate extends CreateTemplatesImpl {
	
	@Override
	public String createSQL(TableEntity entity) throws Exception {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("dbName", entity.getDbName());
		root.put("comment", entity.getComment());
		root.put("key", entity.getKey());
		root.put("col", entity.getCol());
		FreemarkerUtils freeMarkerUtils = new FreemarkerUtils();
		return freeMarkerUtils.generateFile("CreateDB_MYSQL.ftl", root);
	}
	
}
