package com.ecfund.base.util.common;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecfund.base.model.system.TfMenus;
import com.ecfund.base.service.system.TfMenusService;
import com.ecfund.base.util.common.BeanFactory;
import com.ecfund.base.util.common.StringUtils;
import com.ecfund.base.util.json.JSONUtils;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date 2012-9-8 下午3:55:23
 * @filename TreeUtils.java
 * @author HMILYLD
 * @param <T>
 * @param <T>
 * 
 */

public class TreeUtils<T> {

	private String key;

	private String text;

	private String leaf;

	private String iconCls;

	private String[] param;

	private String children;

	private String t;

	private String checked;

	private Boolean defaultLeaf;

	private String defaultIcon;

	private Boolean defaultCheck;

	/**
	 * 转换为树结构内容,不包含子项
	 * 
	 * @param key
	 * @param text
	 * @param leaf
	 * @param iconCls
	 * @param param
	 */
	public TreeUtils(String key, String text, String leaf, String iconCls,
			String[] param) {
		super();
		this.key = key;
		this.text = text;
		this.leaf = leaf;
		this.iconCls = iconCls;
		this.param = param;
	}

	/**
	 * 转换为树结构内容,包含子项
	 * 
	 * @param key
	 * @param text
	 * @param leaf
	 * @param iconCls
	 * @param children
	 * @param param
	 */
	public TreeUtils(String key, String text, String leaf, String iconCls,
			String children, String[] param) {
		super();
		this.key = key;
		this.text = text;
		this.leaf = leaf;
		this.iconCls = iconCls;
		this.param = param;
		this.children = children;
	}

	/**
	 * 转换为树结构内容,包含子项,全部带checkbox
	 * 
	 * @param key
	 * @param text
	 * @param leaf
	 * @param iconCls
	 */
	public TreeUtils(String key, String text, String leaf, String iconCls,
			String children, String checked, String[] param) {
		super();
		this.key = key;
		this.text = text;
		this.leaf = leaf;
		this.iconCls = iconCls;
		this.param = param;
		this.checked = checked;
		this.children = children;
	}

	public void setLeafType(String t) {
		this.t = t;
	}

	public void setDefaultLeaf(Boolean b) {
		this.defaultLeaf = b;
	}

	public void setDefaultIcon(String icon) {
		this.defaultIcon = icon;
	}

	public void setDefaultCheck(Boolean defaultCheck) {
		this.defaultCheck = defaultCheck;
	}

	/**
	 * 转换一个对象到json数据类型，用以返回到树结构中
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public String convert(List<T> list) throws Exception {
		List<Map<String, Object>> tmp = convertObj(list);
		return JSONUtils.fromArray(tmp);
	}

	/**
	 * 转换对象到树结构
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map<String, Object>> convertObj(List<T> list) throws Exception {
		if (list == null) {
			return null;
		}
		List<Map<String, Object>> tmp = new ArrayList<Map<String, Object>>();
		for (T obj : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			Class c = obj.getClass();
			// 获取该值下对应的内容
			if (null != key) {
				Method method = c.getDeclaredMethod("get"
						+ StringUtils.convertFirstUp(key));
				map.put("id", (String) method.invoke(obj));
			}
			if (null != text) {
				Method method = c.getDeclaredMethod("get"
						+ StringUtils.convertFirstUp(text));
				map.put("text", (String) method.invoke(obj));
			}
			if (null != leaf) {
				Method method = c.getDeclaredMethod("get"
						+ StringUtils.convertFirstUp(leaf));
				Object o = method.invoke(obj);
				String value = (o == null) ? null : o.toString();
				map.put("leaf", t.equals(value));
			} else {
				map.put("leaf", defaultLeaf);
			}
			if (null != iconCls) {
				Method method = c.getDeclaredMethod("get"
						+ StringUtils.convertFirstUp(iconCls));
				map.put("iconCls", (String) method.invoke(obj));
			} else if (null != defaultIcon) {
				map.put("iconCls", defaultIcon);
			}
			if (null != param) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				for (int i = 0; i < param.length; i++) {
					String str = param[i];
					Method method = c.getDeclaredMethod("get"
							+ StringUtils.convertFirstUp(str));
					paramMap.put(param[i], method.invoke(obj));
				}
				map.put("param", paramMap);
			}
			if (null != children) {
				Method method = c.getDeclaredMethod("get"
						+ StringUtils.convertFirstUp(children));
				List childrenList = (List) method.invoke(obj);
				map.put("children", convertObj(childrenList));
			}
			if (null != checked) {
				Method method = c.getDeclaredMethod("get"
						+ StringUtils.convertFirstUp(checked));
				Boolean bol = (Boolean) method.invoke(obj);
				map.put("checked", bol == null ? false : bol);
			} else if (null != defaultCheck) {
				map.put("checked", defaultCheck);
			}
			tmp.add(map);
		}
		return tmp;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		TfMenusService tfMenusService = (TfMenusService) BeanFactory
				.getBean("tfMenusService");
		List<TfMenus> list = tfMenusService.find(new TfMenus());
		TreeUtils<TfMenus> utils = new TreeUtils<TfMenus>("guid", "name", null,
				null, new String[] { "url" });
		String tmp = utils.convert(list);
		System.out.println(tmp);
	}

}
