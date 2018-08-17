package com.ecfund.base.model.system;


import java.util.Date;

/**
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @author Hmilyld
 * @date 2012-09-11 13:32
 * @filename TfLoginUser.java
 */

public class TfLoginUser implements java.io.Serializable, UserFeature {

    private static final long serialVersionUID = 1L;

    private String guid;

    private String code;

    private String likeCode;

    private String likeCodeRight;

    private String loginName;

    private String likeLoginName;

    private String likeLoginNameRight;

    private String loginPass;

    private String realName;

    private String likeRealName;

    private String sex;

    private String deptGuid;// 所属部门guid，用于查询部门下的人员信息

    private String likeDeptGuid;// 所属部门guid，用于查找该部门下所有人员信息

    private String companyGuid;// 该人员所属公司ID

    private String zsCompanyGuid;// 该人员所属直属公司ID

    private String isPrimay;// 是否是虚拟部门

    private Integer isLock;

    private Date lastLoginDate;

    private String lastLoginIp;

    private Date writeDate;

    private Date updateDate;

    private Integer sort;

    private String memo;

    private String roleName;// 角色名称

    private String roleGuid;// 角色guid,用于查询关联角色信息表

    private String roleCode;// 角色编码

    private TfDepartment department;

    private String deptName;

    private String deptFullname;

    private String likeRoleName;

    private String del = "0";

    private String newPass;

    private String confirmPass;

    private String meta1;

    private String meta2;

    private String meta3;

    private String meta4;

    private String meta5;

    private String iconCls;

    private Boolean checked;

    private String openid;//微信公众号openid

    public String getGuid() {
        return guid;
    }

    public void setGuid( String guid ) {
        this.guid = guid;
    }

    @Override
    public String getLoginname() {
        return getLoginName();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName( String loginName ) {
        this.loginName = loginName;
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass( String loginPass ) {
        this.loginPass = loginPass;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock( Integer isLock ) {
        this.isLock = isLock;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate( Date lastLoginDate ) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp( String lastLoginIp ) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate( Date writeDate ) {
        this.writeDate = writeDate;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort( Integer sort ) {
        this.sort = sort;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo( String memo ) {
        this.memo = memo;
    }

    public String getDeptGuid() {
        return deptGuid;
    }

    public void setDeptGuid( String deptGuid ) {
        this.deptGuid = deptGuid;
    }

    public String getIsPrimay() {
        return isPrimay;
    }

    public void setIsPrimay( String isPrimay ) {
        this.isPrimay = isPrimay;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName( String realName ) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex( String sex ) {
        this.sex = sex;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName( String roleName ) {
        this.roleName = roleName;
    }

    public String getRoleGuid() {
        return roleGuid;
    }

    public void setRoleGuid( String roleGuid ) {
        this.roleGuid = roleGuid;
    }

    public TfDepartment getDepartment() {
        return department;
    }

    public void setDepartment( TfDepartment department ) {
        this.department = department;
    }

    public String getDel() {
        return del;
    }

    public void setDel( String del ) {
        this.del = del;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass( String newPass ) {
        this.newPass = newPass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass( String confirmPass ) {
        this.confirmPass = confirmPass;
    }

    public String getCompanyGuid() {
        return companyGuid;
    }

    public void setCompanyGuid( String companyGuid ) {
        this.companyGuid = companyGuid;
    }

    public String getMeta1() {
        return meta1;
    }

    public void setMeta1( String meta1 ) {
        this.meta1 = meta1;
    }

    public String getMeta2() {
        return meta2;
    }

    public void setMeta2( String meta2 ) {
        this.meta2 = meta2;
    }

    public String getMeta3() {
        return meta3;
    }

    public void setMeta3( String meta3 ) {
        this.meta3 = meta3;
    }

    public String getMeta4() {
        return meta4;
    }

    public void setMeta4( String meta4 ) {
        this.meta4 = meta4;
    }

    public String getMeta5() {
        return meta5;
    }

    public void setMeta5( String meta5 ) {
        this.meta5 = meta5;
    }

    public String getLikeDeptGuid() {
        return likeDeptGuid;
    }

    public void setLikeDeptGuid( String likeDeptGuid ) {
        this.likeDeptGuid = likeDeptGuid;
    }

    public String getLikeLoginName() {
        return likeLoginName;
    }

    public void setLikeLoginName( String likeLoginName ) {
        this.likeLoginName = likeLoginName;
    }

    public String getLikeRoleName() {
        return likeRoleName;
    }

    public void setLikeRoleName( String likeRoleName ) {
        this.likeRoleName = likeRoleName;
    }

    public String getCode() {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
    }

    public String getLikeCode() {
        return likeCode;
    }

    public void setLikeCode( String likeCode ) {
        this.likeCode = likeCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode( String roleCode ) {
        this.roleCode = roleCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName( String deptName ) {
        this.deptName = deptName;
    }

    public String getDeptFullname() {
        return deptFullname;
    }

    public void setDeptFullname( String deptFullname ) {
        this.deptFullname = deptFullname;
    }

    public String getLikeRealName() {
        return likeRealName;
    }

    public void setLikeRealName( String likeRealName ) {
        this.likeRealName = likeRealName;
    }

    public String getLikeCodeRight() {
        return likeCodeRight;
    }

    public void setLikeCodeRight( String likeCodeRight ) {
        this.likeCodeRight = likeCodeRight;
    }

    public String getLikeLoginNameRight() {
        return likeLoginNameRight;
    }

    public void setLikeLoginNameRight( String likeLoginNameRight ) {
        this.likeLoginNameRight = likeLoginNameRight;
    }

    public String getZsCompanyGuid() {
        return zsCompanyGuid;
    }

    public void setZsCompanyGuid( String zsCompanyGuid ) {
        this.zsCompanyGuid = zsCompanyGuid;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls( String iconCls ) {
        this.iconCls = iconCls;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate( Date updateDate ) {
        this.updateDate = updateDate;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked( Boolean checked ) {
        this.checked = checked;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid( String openid ) {
        this.openid = openid;
    }

}