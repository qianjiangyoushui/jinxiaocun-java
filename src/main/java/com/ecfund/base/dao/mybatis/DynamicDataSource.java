package com.ecfund.base.dao.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 继承自AbstractRoutingDataSource的类可以用来选着使用哪个数据源
 * 根据determineCurrentLookupKey返回的内容来判断
 * 当前的做法是把保存在线程中的DBContextHolder.getCustomerType()值来判断。
 * 
 * @author liugy
 * 
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return DBContextHolder.getDBType();
	}

}
