package com.ecfund.base.model.publics;


import java.util.Date;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-06-13 09:48
 * @filename Dingrm.java
 *
 */

public class Dingrm implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String roleid;

    private String menuid;

    private String remark;

    private Date createdate;

    private String[] guids;

    private int tabindex;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String[] getGuids() {
        return guids;
    }

    public void setGuids(String[] guids) {
        this.guids = guids;
    }

    public int getTabindex() {
        return tabindex;
    }

    public void setTabindex(int tabindex) {
        this.tabindex = tabindex;
    }
}