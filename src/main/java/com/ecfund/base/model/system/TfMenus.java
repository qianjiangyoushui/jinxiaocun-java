package com.ecfund.base.model.system;

import java.util.Date;
import java.util.List;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2012-08-08 22:26
 * @filename TfMenus.java
 * @author Hmilyld
 * 
 */

public class TfMenus implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String guid;

	private String name;

	private String code;

	private String url;

	private String parent;

	private String tree;

	private String icon;

	private String description;

	private Date writeDate;

	private Integer sort;

	private String memo;

	private String leaf;

	private String roleGuid;

	private Boolean checked;
	
	private List<TfMenus> children;
	
	private String del = "0";

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public List<TfMenus> getChildren() {
		return children;
	}

	public void setChildren(List<TfMenus> children) {
		this.children = children;
	}


	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getRoleGuid() {
		return roleGuid;
	}

	public void setRoleGuid(String roleGuid) {
		this.roleGuid = roleGuid;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TfMenus [guid=");
		builder.append(guid);
		builder.append(", name=");
		builder.append(name);
		builder.append(", code=");
		builder.append(code);
		builder.append(", url=");
		builder.append(url);
		builder.append(", parent=");
		builder.append(parent);
		builder.append(", tree=");
		builder.append(tree);
		builder.append(", icon=");
		builder.append(icon);
		builder.append(", description=");
		builder.append(description);
		builder.append(", writeDate=");
		builder.append(writeDate);
		builder.append(", sort=");
		builder.append(sort);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", leaf=");
		builder.append(leaf);
		builder.append(", roleGuid=");
		builder.append(roleGuid);
		builder.append(", checked=");
		builder.append(checked);
		builder.append(", children=");
		builder.append(children);
		builder.append(", del=");
		builder.append(del);
		builder.append("]");
		return builder.toString();
	}

}