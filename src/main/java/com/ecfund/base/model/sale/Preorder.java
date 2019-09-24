package com.ecfund.base.model.sale;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-06-27 11:49
 * @filename Preorder.java
 *
 */

public class Preorder implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String compnayid;

    private String suitid;

    private String code;
    private String customerid;

    private String category;
    private String categoryname;

    private String customername;
    private String saletype;

    private String saletypename;

    @JSONField(format = "yyyy-MM-dd")
    private Date senddate;

    private String billcode;

    private String address;

    private String contactname;

    private String contactphone;

    private String remark;

    private String userid;

    private String username;

    private Date createdate;

    private BigDecimal summount;

    private BigDecimal summoney;

    private BigDecimal premoney;

    private String capital;

    private String statusinfo;

    private String processInstanceId;

    private Integer status;

    private Integer sort;

    private List<Preorderdetail> detailList;

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
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getSaletype() {
        return saletype;
    }

    public void setSaletype(String saletype) {
        this.saletype = saletype;
    }
    public String getSaletypename() {
        return saletypename;
    }

    public void setSaletypename(String saletypename) {
        this.saletypename = saletypename;
    }
    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }
    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }
    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
    public BigDecimal getPremoney() {
        return premoney;
    }

    public void setPremoney(BigDecimal premoney) {
        this.premoney = premoney;
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

    public List<Preorderdetail> getDetailList() {
        return detailList;
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

    public void setDetailList(List<Preorderdetail> detailList) {
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
}