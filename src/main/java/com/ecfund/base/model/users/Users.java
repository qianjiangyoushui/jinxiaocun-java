package com.ecfund.base.model.users;

import java.util.Date;
import java.util.List;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-18 18:07
 * @filename Users.java
 * 
 */

public class Users implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String guid;

	private String username;

	private String telphone;

	private String password;

	private Date registdate;
	
	private Department depart;
	
	private Company company;
	
	private String companyid;//检索条件
	private Integer sex;
	private Date createdate;
	private String nicename;
	private String openid;
	private String skey;
	private String avatarUrl;

	private List<Roles> rolesList;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistdate() {
		return registdate;
	}

	public void setRegistdate(Date registdate) {
		this.registdate = registdate;
	}

	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getNicename() {
		return nicename;
	}

	public void setNicename(String nicename) {
		this.nicename = nicename;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public List<Roles> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Roles> rolesList) {
		this.rolesList = rolesList;
	}
}