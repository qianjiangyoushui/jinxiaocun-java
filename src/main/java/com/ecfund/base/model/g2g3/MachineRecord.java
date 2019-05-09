package com.ecfund.base.model.g2g3;

import com.alibaba.fastjson.annotation.JSONField;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;

import java.util.Date;
import java.util.List;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-04-22 16:08
 * @filename MachineRecord.java
 *
 */

public class MachineRecord implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;
    private String[] guids;

    @JSONField(format="yyyy-MM-dd")
    private Date repairdate;

    private String content;
    private String month;
    private String day;

    private String companyid;

    private String departid;

    private String userid;

    private String farmguid;

    private String farmname;

    private Date createdate;

    private String year;

    private Date sort;
    private List<Upimage> images;

    private Users user;
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public Date getRepairdate() {
        return repairdate;
    }

    public void setRepairdate(Date repairdate) {
        this.repairdate = repairdate;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }
    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getFarmguid() {
        return farmguid;
    }

    public void setFarmguid(String farmguid) {
        this.farmguid = farmguid;
    }
    public String getFarmname() {
        return farmname;
    }

    public void setFarmname(String farmname) {
        this.farmname = farmname;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getSort() {
        return sort;
    }

    public void setSort(Date sort) {
        this.sort = sort;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Upimage> getImages() {
        return images;
    }

    public void setImages(List<Upimage> images) {
        this.images = images;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String[] getGuids() {
        return guids;
    }

    public void setGuids(String[] guids) {
        this.guids = guids;
    }
}