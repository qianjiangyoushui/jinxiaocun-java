package com.ecfund.base.util.templates;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecfund.base.model.system.TableEntity;
import com.ecfund.base.model.system.TfMakeDefault;
import com.ecfund.base.util.common.StringUtils;
import com.ecfund.base.util.db.DBOperation;
import com.ecfund.base.util.freemarker.FreemarkerUtils;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date 2012-8-7 上午10:52:40
 * @filename CreateTemplatesImpl.java
 * @author HMILYLD
 * 
 */

public class CreateTemplatesImpl implements ICreateTemplates {

	private final String path = "/templates";

	@Override
	public String createSQL(TableEntity entity) throws Exception {
		return null;
	}

	@Override
	public String createSQLMap(TfMakeDefault entity, String tablename)
			throws Exception {
		DBOperation db = new DBOperation();
		List<Map<String, Object>> colList = db.getCol(db.getConn(), tablename);
		Map<String, Object> root = new HashMap<String, Object>();
		String upperTableName = StringUtils.convertLowToUp(tablename, true);
		root.put("tablename", upperTableName);
		root.put("table", tablename);
		root.put("colList", colList);
		FreemarkerUtils freemarkerUtils = new FreemarkerUtils();
		return freemarkerUtils.generateFile(path + "/MakeSQLMap.ftl", root);
	}

	@Override
	public String createDAO(TfMakeDefault entity, String tablename)
			throws Exception {
		DBOperation db = new DBOperation();
		List<Map<String, Object>> colList = db.getCol(db.getConn(), tablename);
		Map<String, Object> root = new HashMap<String, Object>();
		String upperTableName = StringUtils.convertLowToUp(tablename, true);
		root.put("tablename", upperTableName);
		root.put("colList", colList);
		root.put("entityPackage", entity.getEntityPath());
		root.put("daoPackage", entity.getDaoPath());
		FreemarkerUtils freemarkerUtils = new FreemarkerUtils();
		return freemarkerUtils.generateFile(path + "/MakeDAO.ftl", root);
	}

	@Override
	public String createService(TfMakeDefault entity, String tablename)
			throws Exception {
		DBOperation db = new DBOperation();
		List<Map<String, Object>> colList = db.getCol(db.getConn(), tablename);
		Map<String, Object> root = new HashMap<String, Object>();
		String upperTableName = StringUtils.convertLowToUp(tablename, true);
		String firstLowerTableName = StringUtils.convertLowToUp(tablename,
				false);
		root.put("tablename", upperTableName);
		root.put("tableLower", firstLowerTableName);
		root.put("colList", colList);
		root.put("entityPackage", entity.getEntityPath());
		root.put("daoPackage", entity.getDaoPath());
		root.put("servicePackage", entity.getServicePath());
		FreemarkerUtils freemarkerUtils = new FreemarkerUtils();
		return freemarkerUtils.generateFile(path + "/MakeService.ftl", root);
	}

	@Override
	public String createAction(TfMakeDefault entity, String tablename)
			throws Exception {
		DBOperation db = new DBOperation();
		List<Map<String, Object>> colList = db.getCol(db.getConn(), tablename);
		Map<String, Object> root = new HashMap<String, Object>();
		String upperTableName = StringUtils.convertLowToUp(tablename, true);
		String firstLowerTableName = StringUtils.convertLowToUp(tablename,
				false);
		root.put("tablename", upperTableName);
		root.put("tableLower", firstLowerTableName);
		root.put("colList", colList);
		root.put("entityPackage", entity.getEntityPath());
		root.put("servicePackage", entity.getServicePath());
		root.put("actionPackage", entity.getActionPath());
		FreemarkerUtils freemarkerUtils = new FreemarkerUtils();
		return freemarkerUtils.generateFile(path + "/MakeAction.ftl", root);
	}

	@Override
	public String createFtlIndex(TfMakeDefault entity, String tablename)
			throws Exception {
		DBOperation db = new DBOperation();
		List<Map<String, Object>> colList = db.getCol(db.getConn(), tablename);
		Map<String, Object> root = new HashMap<String, Object>();
		String upperTableName = StringUtils.convertLowToUp(tablename, true);
		root.put("tablename", upperTableName);
		root.put("colList", colList);
		FreemarkerUtils freemarkerUtils = new FreemarkerUtils();
		return freemarkerUtils.generateFile(path + "/MakePageIndex.ftl", root);
	}

	@Override
	public String createFtlInfo(TfMakeDefault entity, String tablename)
			throws Exception {

		return null;
	}

	@Override
	public String createEntity(TfMakeDefault entity, String tablename)
			throws Exception {
		DBOperation db = new DBOperation();
		List<Map<String, Object>> colList = db.getCol(db.getConn(), tablename);
		Map<String, Object> root = new HashMap<String, Object>();
		String upperTableName = StringUtils.convertLowToUp(tablename, true);
		root.put("tablename", upperTableName);
		root.put("colList", colList);
		root.put("entityPackage", entity.getEntityPath());
		FreemarkerUtils freemarkerUtils = new FreemarkerUtils();
		return freemarkerUtils.generateFile(path + "/MakeEntity.ftl", root);
	}

	@Override
	public String createJS(TfMakeDefault entity, String tablename)
			throws Exception {
		DBOperation db = new DBOperation();
		List<Map<String, Object>> colList = db.getCol(db.getConn(), tablename);
		Map<String, Object> root = new HashMap<String, Object>();
		String upperTableName = StringUtils.convertLowToUp(tablename, true);
		root.put("tablename", upperTableName);
		root.put("colList", colList);
		FreemarkerUtils freemarkerUtils = new FreemarkerUtils();
		return freemarkerUtils.generateFile(path + "/MakeJS.ftl", root);
	}

}
