package com.ecfund.base.model.g0;

import java.util.Date;
import java.util.Date;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-17 15:04
 * @filename Culturemedium.java
 *
 */

public class Culturemedium implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String guid;

    private String companyguid;

    private String name;

    private String trait;

    private String code;

    private String formula;

    private Date createdate;

    private Date updatedate;

    private String remark;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getCompanyguid() {
        return companyguid;
    }

    public void setCompanyguid(String companyguid) {
        this.companyguid = companyguid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
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
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}