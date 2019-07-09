package com.ecfund.base.model.storage;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-06-24 10:25
 * @filename Instockorder.java
 *
 */

public class Instockorder implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String suitid;

    private String companyid;

    private String stockid;

    private String stockname;

    private String positionid;

    private String positionname;

    private String code;
    private Integer intype;

    private String intypename;
    private String supplyname;

    private String supplyid;

    private String receivename;

    private String userid;

    private String username;

    private BigDecimal amount;

    private BigDecimal detailnum;

    private BigDecimal money;

    private String capital;

    private String remark;

    @JSONField(format = "yyyy-MM-dd")
    private Date indate;

    private Date createdate;

    private Integer sort;

    private Integer status;

    private List<Inoderdetail> detailList;
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getSuitid() {
        return suitid;
    }

    public void setSuitid(String suitid) {
        this.suitid = suitid;
    }
    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }
    public String getStockid() {
        return stockid;
    }

    public void setStockid(String stockid) {
        this.stockid = stockid;
    }
    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }
    public String getPositionid() {
        return positionid;
    }

    public void setPositionid(String positionid) {
        this.positionid = positionid;
    }
    public String getPositionname() {
        return positionname;
    }

    public void setPositionname(String positionname) {
        this.positionname = positionname;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getSupplyname() {
        return supplyname;
    }

    public void setSupplyname(String supplyname) {
        this.supplyname = supplyname;
    }
    public String getSupplyid() {
        return supplyid;
    }

    public void setSupplyid(String supplyid) {
        this.supplyid = supplyid;
    }
    public String getReceivename() {
        return receivename;
    }

    public void setReceivename(String receivename) {
        this.receivename = receivename;
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
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getDetailnum() {
        return detailnum;
    }

    public void setDetailnum(BigDecimal detailnum) {
        this.detailnum = detailnum;
    }
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
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

    public List<Inoderdetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Inoderdetail> detailList) {
        this.detailList = detailList;
    }

    public Integer getIntype() {
        return intype;
    }

    public void setIntype(Integer intype) {
        this.intype = intype;
    }

    public String getIntypename() {
        return intypename;
    }

    public void setIntypename(String intypename) {
        this.intypename = intypename;
    }
}