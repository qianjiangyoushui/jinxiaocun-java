package com.ecfund.base.model.purchase;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.Date;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-06-17 09:27
 * @filename Purchasebiling.java
 *
 */

public class Purchasebiling implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String compnayid;

    private String suitid;

    private String applycode;

    private String category;

    private String categoryname;

    @JSONField(format = "yyyy-MM-dd")
    private Date applydate;

    private String remark;

    private String usedepart;

    private String userid;

    private String username;
    @JSONField(format = "yyyy-MM-dd")
    private Date createdate;

    private String purchaseapplycode;

    private BigDecimal summount;

    private BigDecimal summoney;

    private BigDecimal paymoney;

    private String capital;

    private String billtype;

    private String billtypename;

    private String supplyid;

    private String supplyname;

    private String bankname;

    private String bankaccount;

    private String applyid;

    private String statusinfo;

    private String processInstanceId;

    private Integer status;

    private Integer sort;

    private List<Bilingdetail> detailList;

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
    public Date getApplydate() {
        return applydate;
    }

    public void setApplydate(Date applydate) {
        this.applydate = applydate;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
    public String getPurchaseapplycode() {
        return purchaseapplycode;
    }

    public void setPurchaseapplycode(String purchaseapplycode) {
        this.purchaseapplycode = purchaseapplycode;
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
    public BigDecimal getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(BigDecimal paymoney) {
        this.paymoney = paymoney;
    }
    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }
    public String getBilltypename() {
        return billtypename;
    }

    public void setBilltypename(String billtypename) {
        this.billtypename = billtypename;
    }
    public String getSupplyid() {
        return supplyid;
    }

    public void setSupplyid(String supplyid) {
        this.supplyid = supplyid;
    }
    public String getSupplyname() {
        return supplyname;
    }

    public void setSupplyname(String supplyname) {
        this.supplyname = supplyname;
    }
    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }
    public String getApplyid() {
        return applyid;
    }

    public void setApplyid(String applyid) {
        this.applyid = applyid;
    }
    public String getStatusinfo() {
        return statusinfo;
    }

    public void setStatusinfo(String statusinfo) {
        this.statusinfo = statusinfo;
    }
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
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

    public List<Bilingdetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Bilingdetail> detailList) {
        this.detailList = detailList;
    }
}