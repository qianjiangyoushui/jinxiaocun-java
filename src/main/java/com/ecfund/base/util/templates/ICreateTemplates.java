package com.ecfund.base.util.templates;

import com.ecfund.base.model.system.TableEntity;
import com.ecfund.base.model.system.TfMakeDefault;

/**
 * 
 * 根据数据库内容创建文件内容接口
 * 
 * @date 2012-8-7 上午9:35:46
 * @filename DBCreateArray.java
 * @author HMILYLD
 * 
 */

public interface ICreateTemplates {

	/**
	 * 根据模板内容获取创建sql数据表的语句
	 * 
	 * @param entity
	 * @return 组合后的数据库生成语句
	 * @throws Exception
	 */
	public String createSQL(TableEntity entity) throws Exception;

	/**
	 * 创建sqlmap文件
	 * 
	 * @return
	 */
	public String createSQLMap(TfMakeDefault entity, String tablename)
			throws Exception;

	/**
	 * 创建dao文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createDAO(TfMakeDefault entity, String tablename)
			throws Exception;

	/**
	 * 创建service文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createService(TfMakeDefault entity, String tablename)
			throws Exception;

	/**
	 * 创建action文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createAction(TfMakeDefault entity, String tablename)
			throws Exception;

	/**
	 * 创建ftl首页文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createFtlIndex(TfMakeDefault entity, String tablename)
			throws Exception;

	/**
	 * 创建FTL信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createFtlInfo(TfMakeDefault entity, String tablename)
			throws Exception;

	/**
	 * 创建eneity实体类
	 * 
	 * @param entity
	 * @param tablename
	 * @return
	 * @throws Exception
	 */
	public String createEntity(TfMakeDefault entity, String tablename)
			throws Exception;

	/**
	 * 创建页面使用的js文件,默认单grid
	 * 
	 * @param entity
	 * @param tablename
	 * @return
	 * @throws Exception
	 */
	public String createJS(TfMakeDefault entity, String tablename)
			throws Exception;
}
