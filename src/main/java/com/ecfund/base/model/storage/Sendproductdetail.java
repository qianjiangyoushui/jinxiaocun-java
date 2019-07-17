package com.ecfund.base.model.storage;


import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-07-11 09:31
 * @filename Sendproductdetail.java
 *
 */

public class Sendproductdetail implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String appyid;

    private String productid;

    private String productname;

    private String imgurl;

    private String normal;

    private String unit;

    private BigDecimal amount;

    private BigDecimal price;

    private BigDecimal summoney;

    private Date createdate;

    private Integer sort;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getAppyid() {
        return appyid;
    }

    public void setAppyid(String appyid) {
        this.appyid = appyid;
    }
    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }
    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getSummoney() {
        return summoney;
    }

    public void setSummoney(BigDecimal summoney) {
        this.summoney = summoney;
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
}