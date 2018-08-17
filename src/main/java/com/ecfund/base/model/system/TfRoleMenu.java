package com.ecfund.base.model.system;

/**
 * 
 * 这里是文件说明注释
 * 
 * @date 2012-4-22 下午4:54:00
 * @filename TfRoleMenu.java
 * @author HMILYLD
 * 
 */

public class TfRoleMenu implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6247467386703447985L;

	public String roleGuid;

	public String menuGuid;

	public String getRoleGuid() {
		return roleGuid;
	}

	public void setRoleGuid(String roleGuid) {
		this.roleGuid = roleGuid;
	}

	public String getMenuGuid() {
		return menuGuid;
	}

	public void setMenuGuid(String menuGuid) {
		this.menuGuid = menuGuid;
	}
}
