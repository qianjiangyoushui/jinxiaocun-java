package com.ecfund.base.model.storage;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-07-11 09:28
 * @filename Sendproduct.java
 *
 */

public class Sendproduct implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String companyid;

    private String suitid;

    private String code;

    private String busiuserid;

    private String busiusername;

    private String customerid;

    private String customername;

    @JSONField(format = "yyyy-MM-dd")
    private Date senddate;

    private Date createdate;

    private String address;

    private String receivename;

    private String phone;

    private String remark;

    private String saleorderid;

    private String saleordercode;

    private BigDecimal amount;

    private BigDecimal summoney;

    private Integer status;
    private Integer sort;

    private List<Sendproductdetail> detailList;
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
    public String getReceivename() {
        return receivename;
    }

    public void setReceivename(String receivename) {
        this.receivename = receivename;
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
    public String getSaleorderid() {
        return saleorderid;
    }

    public void setSaleorderid(String saleorderid) {
        this.saleorderid = saleorderid;
    }
    public String getSaleordercode() {
        return saleordercode;
    }

    public void setSaleordercode(String saleordercode) {
        this.saleordercode = saleordercode;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<Sendproductdetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Sendproductdetail> detailList) {
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

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }
}