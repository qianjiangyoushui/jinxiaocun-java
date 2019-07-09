package com.ecfund.base.model.purchase;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-06-03 09:25
 * @filename Purchaseapply.java
 *
 */

public class Purchaseapply implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String compnayid;

    private String suitid;

    private String applycode;
    private String category;
    private String categoryname;

    @JSONField(format = "yyyy-MM-dd")
    private Date applydate;

    private String reason;

    private String usedepart;

    private String userid;

    private String username;
    @JSONField(format = "yyyy-MM-dd")
    private Date createdate;

    private BigDecimal summount;

    private BigDecimal summoney;
    private BigDecimal premoney;

    private String capital;

    private String statusinfo;
    private String processInstanceId;
    private Integer status;

    private Integer sort;

    private List<Purchaseapplydetail> detailList;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getCompnayid() {
        return compnayid;
    }

    public void setCompnayid(String compnayid) {
        this.compnayid = compnayid;
    }
    public String getSuitid() {
        return suitid;
    }

    public void setSuitid(String suitid) {
        this.suitid = suitid;
    }
    public String getApplycode() {
        return applycode;
    }

    public void setApplycode(String applycode) {
        this.applycode = applycode;
    }
    public Date getApplydate() {
        return applydate;
    }

    public void setApplydate(Date applydate) {
        this.applydate = applydate;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getUsedepart() {
        return usedepart;
    }

    public void setUsedepart(String usedepart) {
        this.usedepart = usedepart;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public BigDecimal getSummount() {
        return summount;
    }

    public void setSummount(BigDecimal summount) {
        this.summount = summount;
    }
    public BigDecimal getSummoney() {
        return summoney;
    }

    public void setSummoney(BigDecimal summoney) {
        this.summoney = summoney;
    }
    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
    public String getStatusinfo() {
        return statusinfo;
    }

    public void setStatusinfo(String statusinfo) {
        this.statusinfo = statusinfo;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public List<Purchaseapplydetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Purchaseapplydetail> detailList) {
        this.detailList = detailList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public BigDecimal getPremoney() {
        return premoney;
    }

    public void setPremoney(BigDecimal premoney) {
        this.premoney = premoney;
    }
}