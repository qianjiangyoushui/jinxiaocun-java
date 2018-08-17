package com.ecfund.base.model.system;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2012-09-19 13:42
 * @filename TfDepartment.java
 * @author Hmilyld
 * 
 */

public class TfDepartment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String guid;

	private String[] ids;

	private String code;

	private String likeCode;

	private String name;

	private String levels;

	private String likeLevels;

	private String likeLeftLevels;

	private String likeRightLevels;

	private String likeFullLeftTree;// like百分号在左边

	private String likeFullRightTree;// like百分号在右边

	private String isIndependent;

	private String isVirtual;

	private String description;

	private String parent;

	private String manager;

	private String likeManager;

	private String managerName;

	private String fulltree;

	private String likeFullTree;

	private String fullname;

	private Date writeDate;

	private BigDecimal sort;

	private String memo;

	private String leaderShip;// 所属主管领导名称

	private String leaderShipId;// 所属主管领导guid

	private List<TfDepartment> children;

	private Boolean check;

	private String userName;

	private String userGuid;

	private String icons;

	private String leaf;// 用于控制该部门节点是否可以展开

	private String del = "0";

	private String khtype;

	private String type;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getIsIndependent() {
		return isIndependent;
	}

	public void setIsIndependent(String isIndependent) {
		this.isIndependent = isIndependent;
	}

	public String getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(String isVirtual) {
		this.isVirtual = isVirtual;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getFulltree() {
		return fulltree;
	}

	public void setFulltree(String fulltree) {
		this.fulltree = fulltree;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public BigDecimal getSort() {
		return sort;
	}

	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getLikeLevels() {
		return likeLevels;
	}

	public void setLikeLevels(String likeLevels) {
		this.likeLevels = likeLevels;
	}

	public String getLikeFullTree() {
		return likeFullTree;
	}

	public void setLikeFullTree(String likeFullTree) {
		this.likeFullTree = likeFullTree;
	}

	public String getLikeLeftLevels() {
		return likeLeftLevels;
	}

	public void setLikeLeftLevels(String likeLeftLevels) {
		this.likeLeftLevels = likeLeftLevels;
	}

	public String getLikeRightLevels() {
		return likeRightLevels;
	}

	public void setLikeRightLevels(String likeRightLevels) {
		this.likeRightLevels = likeRightLevels;
	}

	public String getLikeFullLeftTree() {
		return likeFullLeftTree;
	}

	public void setLikeFullLeftTree(String likeFullLeftTree) {
		this.likeFullLeftTree = likeFullLeftTree;
	}

	public String getLikeFullRightTree() {
		return likeFullRightTree;
	}

	public void setLikeFullRightTree(String likeFullRightTree) {
		this.likeFullRightTree = likeFullRightTree;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public List<TfDepartment> getChildren() {
		return children;
	}

	public void setChildren(List<TfDepartment> children) {
		this.children = children;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getLikeManager() {
		return likeManager;
	}

	public void setLikeManager(String likeManager) {
		this.likeManager = likeManager;
	}

	public String getLeaderShip() {
		return leaderShip;
	}

	public void setLeaderShip(String leaderShip) {
		this.leaderShip = leaderShip;
	}

	public String getLeaderShipId() {
		return leaderShipId;
	}

	public void setLeaderShipId(String leaderShipId) {
		this.leaderShipId = leaderShipId;
	}

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

	public String getLikeCode() {
		return likeCode;
	}

	public void setLikeCode(String likeCode) {
		this.likeCode = likeCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public String getIcons() {
		return icons;
	}

	public void setIcons(String icons) {
		this.icons = icons;
	}

	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getKhtype() {
		return khtype;
	}

	public void setKhtype(String khtype) {
		this.khtype = khtype;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}