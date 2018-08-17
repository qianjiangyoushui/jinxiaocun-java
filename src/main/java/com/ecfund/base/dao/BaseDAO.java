package com.ecfund.base.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecfund.base.common.UUIDCreate;


/** 
 * @version 2015年12月9日 下午3:01:12
 */
public class BaseDAO <T extends Serializable>{

	@Autowired
	public SqlSessionTemplate sqlSessionTemplate;

	private Class<T> entityClass;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseDAO() {
		super();
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	/**
	 * 新增操作
	 * 
	 * @param entity
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String insert(T entity) throws Exception {
		Class c = entity.getClass();
		// 反射注入默认的guid及writeDate的值
		try {
			Method method = c.getDeclaredMethod("setGuid", String.class);
			method.invoke(entity, new Object[] { getUUID() });
			method = c.getDeclaredMethod("setWriteDate", Date.class);
			method.invoke(entity, new Object[] { new Date() });
		} catch (NoSuchMethodException ex) {
			// 无此属性方法
		}
		sqlSessionTemplate.insert(entityClass.getSimpleName() + ".insert",
				entity);
		// 执行完毕后返回该实体的主键值，异常为无此方法
		try {
			Method method = c.getDeclaredMethod("getGuid");
			return (String) method.invoke(entity);
		} catch (NoSuchMethodException ex) {
			// 无此属性方法
			return null;
		}
	}

	/**
	 * 修改操作
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void update(T entity) throws Exception {
		sqlSessionTemplate.update(entityClass.getSimpleName() + ".update",
				entity);
	}

	/**
	 * 删除操作
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void delete(T entity) throws Exception {
		sqlSessionTemplate.delete(entityClass.getSimpleName() + ".delete",
				entity);
	}

	/**
	 * 多选删除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void mulDel(String[] ids) throws Exception {
		sqlSessionTemplate.delete(entityClass.getSimpleName() + ".mulDel", ids);
	}

	/**
	 * 获取分页列表信息
	 * 
	 * @param entity
	 * @param offset
	 *            偏移量
	 * @param limit
	 *            获取记录数
	 * @return
	 * @throws Exception
	 */
	public List<T> find(T entity, int offset, int limit) throws Exception {
		return sqlSessionTemplate.selectList(entityClass.getSimpleName()
				+ ".find", entity, new RowBounds(offset, limit));
	}

	/**
	 * 获取记录集
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public List<T> find(T entity) throws Exception {
		return sqlSessionTemplate.selectList(entityClass.getSimpleName()
				+ ".find", entity);
	}

	/**
	 * 获取指定的记录集数量
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int findCount(T entity) throws Exception {
		return sqlSessionTemplate.selectOne(entityClass.getSimpleName()
				+ ".findCount", entity);
	}

	/**
	 * 获取单条的记录信息
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public T view(T entity) throws Exception {
		return sqlSessionTemplate.selectOne(entityClass.getSimpleName()
				+ ".find", entity);
	}

	/**
	 * 根据sql语句的id获取该sql语句查询的结果内容
	 * 
	 * @param sqlID
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public List<T> find(String sqlID, T entity) throws Exception {
		return sqlSessionTemplate.selectList(entityClass.getSimpleName() + "."
				+ sqlID, entity);
	}

	/**
	 * 根据SQL语句的ID获取该sql语句查询结果内容，返回单条记录
	 * 
	 * @param sqlID
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public T view(String sqlID, T entity) throws Exception {
		return sqlSessionTemplate.selectOne(entityClass.getSimpleName() + "."
				+ sqlID, entity);
	}

	/**
	 * 获取GUID
	 * 
	 * @return
	 */
	protected String getUUID() {
		return UUIDCreate.get();
	}


}
