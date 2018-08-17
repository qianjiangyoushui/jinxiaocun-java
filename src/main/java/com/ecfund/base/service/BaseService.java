package com.ecfund.base.service;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import com.ecfund.base.common.ListPageBean;
import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.common.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author jiaxd
 * @version 2015年12月9日 下午3:51:44
 */
public class BaseService <T extends Serializable>{

	private BaseDAO<T> baseDAO;

	public BaseDAO<T> getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO<T> baseDAO) {
		this.baseDAO = baseDAO;
	}

	/**
	 * 新增操作
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public String insert(T entity) throws Exception {
		return baseDAO.insert(entity);
	}

	/**
	 * 修改操作
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void update(T entity) throws Exception {
		baseDAO.update(entity);
	}

	/**
	 * 根据json字符串进行添加或更改操作
	 * 
	 * @param json
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void update(Class c, String jsonArray) throws Exception {
		JSONArray array = JSONArray.fromObject(jsonArray);
		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = (JSONObject) array.get(i);
			T entity = (T) JSONObject.toBean(obj, c);
			Method method = c.getDeclaredMethod("getGuid");
			String tmp = (String) method.invoke(entity);
			if (null == tmp || "".equals(tmp)) {
				// 新增操作
				insert(entity);
			} else {
				// 更改操作
				update(entity);
			}
		}
	}

	/**
	 * 删除操作
	 * 
	 * @param entity
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delete(T entity) throws Exception {
		Class c = entity.getClass();
		String[] ids = null;
		// 反射获取多选删除时GUID的值
		Method method = c.getDeclaredMethod("getGuid");
		String tmp = (String) method.invoke(entity);
		if (null != tmp) {
			ids = tmp.split(",");
			if (ids.length == 1) {
				// 单选删除
				baseDAO.delete(entity);
			} else {
				// 多选删除
				baseDAO.mulDel(ids);
			}
		}
		return "数据删除成功";
	}

	/**
	 * 获取分页列表信息
	 * 
	 * @param entity
	 * @param page
	 *            当前页数
	 * @param limit
	 *            每页显示数量
	 * @return
	 * @throws Exception
	 */
	public Page find(T entity, int start, int pageCount)
			throws Exception {
		Page pager = new Page();
		pager.setRows(baseDAO.find(entity, start, pageCount));
		pager.setCountItem(findCount(entity));
		return pager;
	}

	/**
	 * 明确指定传入参数进行判断,获取分页列表信息
	 * 
	 * @param prop
	 * @param entity
	 * @param start
	 * @param pageCount
	 * @return
	 * @throws Exception
	 */
	public ListPageBean find(String[] prop, T entity, int start, int pageCount)
			throws Exception {
		entity = convertEntity(prop, entity);
		ListPageBean pager = new ListPageBean();
		pager.setRows(baseDAO.find(entity, start, pageCount));
		pager.setTotal(findCount(prop, entity));
		return pager;
	}

	/**
	 * 获取记录集
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public List<T> find(T entity) throws Exception {
		return baseDAO.find(entity);
	}

	/**
	 * 明确指定传入参数进行判断,获取记录集
	 * 
	 * @param prop
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public List<T> find(String[] prop, T entity) throws Exception {
		entity = convertEntity(prop, entity);
		return baseDAO.find(entity);
	}

	/**
	 * 获取指定的记录集数量
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int findCount(T entity) throws Exception {
		return baseDAO.findCount(entity);
	}

	/**
	 * 明确指定传入参数进行判断，获取指定的记录集数量
	 * 
	 * @param prop
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int findCount(String[] prop, T entity) throws Exception {
		entity = convertEntity(prop, entity);
		return baseDAO.findCount(entity);
	}

	/**
	 * 获取单条的记录信息
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public T view(T entity) throws Exception {
		return baseDAO.view(entity);
	}

	/**
	 * 明确指定传入参数进行判断，获取单条的记录信息
	 * 
	 * @param prop
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public T view(String[] prop, T entity) throws Exception {
		entity = convertEntity(prop, entity);
		return baseDAO.view(entity);
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
		return baseDAO.find(sqlID, entity);
	}

	/**
	 * 明确指定传入参数进行判断，根据sql语句的id获取该sql语句查询的结果内容
	 * 
	 * @param prop
	 * @param sqlID
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public List<T> find(String[] prop, String sqlID, T entity) throws Exception {
		entity = convertEntity(prop, entity);
		return baseDAO.find(sqlID, entity);
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
		return baseDAO.view(sqlID, entity);
	}

	/**
	 * 明确指定传入参数进行判断，根据SQL语句的ID获取该sql语句查询结果内容，返回单条记录
	 * 
	 * @param prop
	 * @param sqlID
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public T view(String[] prop, String sqlID, T entity) throws Exception {
		entity = convertEntity(prop, entity);
		return baseDAO.view(sqlID, entity);
	}

	/**
	 * 按照指定的参数对entity赋值
	 * 
	 * @param prop
	 * @param entity
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private T convertEntity(String[] prop, T entity) throws Exception {
		Class c = entity.getClass();
		Object newEntity = Class.forName(c.getName()).newInstance();
		for (String str : prop) {
			// 对entity中的值进行获取，然后复制到newEntity中
			String methodName = StringUtils.convertFirstUp(str);
			Method getMethod = c.getDeclaredMethod("get" + methodName);
			Method setMethod = c.getDeclaredMethod("set" + methodName,
					getMethod.getReturnType());
			setMethod.invoke(newEntity,
					new Object[] { getMethod.invoke(entity) });
		}
		return (T) newEntity;
	}


}
