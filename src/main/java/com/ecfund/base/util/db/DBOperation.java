package com.ecfund.base.util.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.ecfund.base.util.common.BeanFactory;
import com.ecfund.base.util.common.StringUtils;


/**
 * 
 * 创建数据库所用工具类，主要用户获取各类创建数据库时的SQL语句
 * 
 * @date 2012-8-6 上午10:10:05
 * @filename DBCreateSqlUtils.java
 * @author HMILYLD
 * 
 */

public class DBOperation {

	/**
	 * 获取一个数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getConn() throws Exception {
		return DataSourceUtils.getConnection((DataSource) BeanFactory
				.getBean("dataSource"));
	}

	/**
	 * 执行SQL语句
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public boolean create(String sql) throws Exception {
		JdbcTemplate jt = (JdbcTemplate) BeanFactory.getBean("jdbcTemplate");
		try {
			jt.update(sql);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 获取指定表的列内容,包含列名称,格式等
	 * 
	 * @param table
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getCol(Connection conn, String table)
			throws Exception {
		// 执行sql语句
		Statement stm = conn.createStatement();
		ResultSet rst = stm.executeQuery("select * from " + table);
		ResultSetMetaData metaRst = rst.getMetaData();
		int columnCount = metaRst.getColumnCount() + 1;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 1; i < columnCount; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", SQLTypes.getType(metaRst.getColumnType(i)));
			// 列名全转换为大写
			map.put("colname", metaRst.getColumnName(i).toUpperCase());
			// 属性名根据列名进行转换,_后的字符更改为大写,首字符小写
			map.put("propname",
					StringUtils.convertLowToUp(metaRst.getColumnName(i), false));
			map.put("propnameUp",
					StringUtils.convertLowToUp(metaRst.getColumnName(i), true));
			list.add(map);
		}
		return list;
	}
}
