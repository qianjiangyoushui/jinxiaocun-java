package com.ecfund.base.common;

import java.util.List;

/** 
 * @author jiaxd
 * @version 2015年12月9日 下午3:53:17
 */
public class ListPageBean {
	/**
	 * 返回的list值
	 */
	public List<?> rows;
	/**
	 * 当前页
	 */
	public int indexPage;
	/**
	 * 总数
	 */
	public int total;
	/**
	 * 每页显示数
	 */
	public int pageCount;

	/**
	 * 状态值
	 */
	private String flag;

	/**
	 * 无参构造方法
	 */
	public ListPageBean() {

	}

	/**
	 * 有参数构造方法
	 * 
	 * @param list
	 *            list集合
	 * @param totalCount
	 *            总数
	 */
	public ListPageBean(List<?> rows, int total) {
		this.rows = rows;
		this.total = total;
	}

	public ListPageBean(List<?> rows) {
		this.rows = rows;
	}

	public ListPageBean(List<?> rows, int indexPage, int total, int pageCount) {
		this.rows = rows;
		this.total = total;
		this.indexPage = indexPage;
		this.pageCount = pageCount;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public int getIndexPage() {
		return indexPage;
	}

	public void setIndexPage(int indexPage) {
		this.indexPage = indexPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}


}
