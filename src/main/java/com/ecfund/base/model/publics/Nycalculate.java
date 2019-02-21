package com.ecfund.base.model.publics;


import java.math.BigDecimal;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-02-19 15:22
 * @filename Nycalculate.java
 *
 */

public class Nycalculate implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String format;

    private String name;

    private BigDecimal perPrice;

    private Integer state;

    private BigDecimal sumPrice;

    private BigDecimal sumuseage;

    private BigDecimal sumusewater;

    private Integer times;

    private String unit;

    private BigDecimal useage;

    private BigDecimal water;

    private Integer sort;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPerPrice() {
        return perPrice;
    }

    public void setPerPrice(BigDecimal perPrice) {
        this.perPrice = perPrice;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }
    public BigDecimal getSumuseage() {
        return sumuseage;
    }

    public void setSumuseage(BigDecimal sumuseage) {
        this.sumuseage = sumuseage;
    }
    public BigDecimal getSumusewater() {
        return sumusewater;
    }

    public void setSumusewater(BigDecimal sumusewater) {
        this.sumusewater = sumusewater;
    }
    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public BigDecimal getUseage() {
        return useage;
    }

    public void setUseage(BigDecimal useage) {
        this.useage = useage;
    }
    public BigDecimal getWater() {
        return water;
    }

    public void setWater(BigDecimal water) {
        this.water = water;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}