package com.ecfund.base.model.funds;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-07-12 09:02
 * @filename Receivebill.java
 *
 */

public class Receivebill implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String companyid;

    private String suitid;

    private String busiuserid;

    private String busiusername;

    private String code;

    private String type;

    private String customerid;

    private String customername;

    private String paytype;

    private String paytypename;

    private String account;

    private BigDecimal paymoney;

    private BigDecimal money;

    private BigDecimal rebate;

    private BigDecimal rebatemoney;

    private String businessid;

    private String businesscode;

    private String remark;

    private Integer status;
    @JSONField(format = "yyyy-MM-dd")
    private Date createdate;

    @JSONField(format = "yyyy-MM-dd")
    private Date updatedate;

    private Integer sort;

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
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
    public String getPaytypename() {
        return paytypename;
    }

    public void setPaytypename(String paytypename) {
        this.paytypename = paytypename;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public BigDecimal getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(BigDecimal paymoney) {
        this.paymoney = paymoney;
    }
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    public BigDecimal getRebate() {
        return rebate;
    }

    public void setRebate(BigDecimal rebate) {
        this.rebate = rebate;
    }
    public BigDecimal getRebatemoney() {
        return rebatemoney;
    }

    public void setRebatemoney(BigDecimal rebatemoney) {
        this.rebatemoney = rebatemoney;
    }
    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }
    public String getBusinesscode() {
        return businesscode;
    }

    public void setBusinesscode(String businesscode) {
        this.businesscode = businesscode;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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