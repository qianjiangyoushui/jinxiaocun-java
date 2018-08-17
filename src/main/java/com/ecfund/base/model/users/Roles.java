package com.ecfund.base.model.users;


import java.util.Date;
import java.util.List;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-14 16:44
 * @filename Roles.java
 *
 */

public class Roles implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String name;

    private String description;

    private String deptGuid;

    private Date writeDate;

    private Integer sort;

    private String memo;

    private String code;

    private String del;

    private Date updateDate;

    private List<Menus> menuList;

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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDeptGuid() {
        return deptGuid;
    }

    public void setDeptGuid(String deptGuid) {
        this.deptGuid = deptGuid;
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
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<Menus> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menus> menuList) {
        this.menuList = menuList;
    }
}
