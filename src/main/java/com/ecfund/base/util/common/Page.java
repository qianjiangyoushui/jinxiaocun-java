package com.ecfund.base.util.common;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable {
	private int begin;// 起始位置
	private int pageNo = 1;// 当前页
	private int pageSize =100;// 每行显示条数 
	private int pageCount;// 总页数
	private int countItem;// 总条数
	
	public List<?> rows;

	public Page(){
		
	}

	public Page(int pageNo){
		super();
		this.pageNo=pageNo;
	}

	public Page(List<?> rows,int pageNo,int pageSize){
		super();
		this.rows=rows;
		this.pageNo=pageNo;
		this.pageSize=pageSize;
	}

	public int getBegin() {
		return (pageNo - 1) * pageSize;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		if (countItem % pageSize == 0) {
			pageCount = countItem / pageSize;
		} else {
			pageCount = countItem / pageSize + 1;
		}
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCountItem() {
		return countItem;
	}

	public void setCountItem(int countItem) {
		this.countItem = countItem;
	}

//	private String pageInfo;// 页面显示信息
	private int prevPage;// 上一页
	private int nextPage;// 下一页

//	public String getPageInfo() {// 页面显示信息
//		pageInfo = "";
//		pageInfo += "<input type=\"hidden\" name =\"pageNo\"/>";
//		pageInfo += script();
//		pageInfo += pageFirstNextPrevLast();
//		pageInfo += pageNoInfo();
//		return pageInfo;
//	}

//	public void setPageInfo(String pageInfo) {
//		this.pageInfo = pageInfo;
//	}

	public int getPrevPage() {

		return pageNo - 1;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return pageNo + 1;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

//	public String pageNoInfo() {
//		return "[第" + getPageNo() + "页/共" + getPageCount() + "页]";
//	}

//	public String pageFirstNextPrevLast() {
//		String info = "";
//		// 首页
//		String first = "<a href=\"javascript:subPage(1)\">首页</a>";
//		// 尾页
//		String last = "<a href=\"javascript:subPage(" + getPageCount() + ")\">尾页</a>";
//		// 上一页
//		String prev = "<a href=\"javascript:subPage(" + getPrevPage() + ")\">上一页</a>";
//		// 下一页
//		String next = "<a href=\"javascript:subPage(" + getNextPage() + ")\">下一页</a>";
//		if (pageNo != 1) {
//			info += first;
//			info += prev;
//		}
//		if (pageNo != pageCount) {
//			info += next;
//			info += last;
//
//		}
//		return info;
//	}

	
	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
//	public String script() {
//		return " <script type=\"text/javascript\">" + "function subPage(pageNo){"
//				+ "document.bookForm.elements['pageNo'].value=pageNo;" + "document.bookForm.submit();" + "}"
//				+ "</script>";
//	}
}
