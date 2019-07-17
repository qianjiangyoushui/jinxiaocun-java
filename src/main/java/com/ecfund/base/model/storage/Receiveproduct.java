package com.ecfund.base.model.storage;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-07-11 09:23
 * @filename Receiveproduct.java
 *
 */

public class Receiveproduct implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String companyid;

    private String suitid;

    private String busiuserid;

    private String busiusername;

    private String code;

    private String supplyid;

    private String supplyname;

    @JSONField(format = "yyyy-MM-dd")
    private Date senddate;

    private Date createdate;

    private String address;

    private String sendname;

    private String phone;

    private String remark;

    private String purchaseid;

    private String purchasecode;

    private BigDecimal amount;

    private BigDecimal summoney;

    private Integer status;
    private Integer sort;

    private List<Receiveproductdetail> detailList;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }
    public String getSuitid() {
        return suitid;
    }

    public void setSuitid(String suitid) {
        this.suitid = suitid;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getPurchaseid() {
        return purchaseid;
    }

    public void setPurchaseid(String purchaseid) {
        this.purchaseid = purchaseid;
    }
    public String getPurchasecode() {
        return purchasecode;
    }

    public void setPurchasecode(String purchasecode) {
        this.purchasecode = purchasecode;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getSummoney() {
        return summoney;
    }

    public void setSummoney(BigDecimal summoney) {
        this.summoney = summoney;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Receiveproductdetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Receiveproductdetail> detailList) {
        this.detailList = detailList;
    }

    public String getBusiuserid() {
        return busiuserid;
    }

    public void setBusiuserid(String busiuserid) {
        this.busiuserid = busiuserid;
    }

    public String getBusiusername() {
        return busiusername;
    }

    public void setBusiusername(String busiusername) {
        this.busiusername = busiusername;
    }
}