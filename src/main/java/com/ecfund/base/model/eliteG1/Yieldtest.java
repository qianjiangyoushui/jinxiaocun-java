package com.ecfund.base.model.eliteG1;

import java.util.Date;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-07 16:56
 * @filename Yieldtest.java
 * 
 */
 
public class Yieldtest implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String seedfileid;
    
    private Date testdate;
    
    private String level;
    
    private String project;
    
    private BigDecimal munumber;
    
    private Integer plantnumber;
    
    private Integer rhizomenumber;
    
    private Integer bigamount;
    
    private BigDecimal bigweight;
    
    private Integer mediumamount;
    
    private BigDecimal mediumweight;
    
    private Integer minamount;
    
    private BigDecimal minweight;
    
    private Integer amount;
    
    private BigDecimal weight;
    
    private BigDecimal permu;
    
    private BigDecimal avgperstrain;
    
    private String testperson;
    
    private Date createdate;
    
    private String operatorid;
    
    private int num; //取样点数
    
    private BigDecimal batchSum;
    
    private int strainamount;//批次种植株数
    
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getSeedfileid() {
        return seedfileid;
    }

    public void setSeedfileid(String seedfileid) {
        this.seedfileid = seedfileid;
    }
    public Date getTestdate() {
        return testdate;
    }

    public void setTestdate(Date testdate) {
        this.testdate = testdate;
    }
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
    public BigDecimal getMunumber() {
        return munumber;
    }

    public void setMunumber(BigDecimal munumber) {
        this.munumber = munumber;
    }
    public Integer getPlantnumber() {
        return plantnumber;
    }

    public void setPlantnumber(Integer plantnumber) {
        this.plantnumber = plantnumber;
    }
    public Integer getRhizomenumber() {
        return rhizomenumber;
    }

    public void setRhizomenumber(Integer rhizomenumber) {
        this.rhizomenumber = rhizomenumber;
    }
    public Integer getBigamount() {
        return bigamount;
    }

    public void setBigamount(Integer bigamount) {
        this.bigamount = bigamount;
    }
    public BigDecimal getBigweight() {
        return bigweight;
    }

    public void setBigweight(BigDecimal bigweight) {
        this.bigweight = bigweight;
    }
    public Integer getMediumamount() {
        return mediumamount;
    }

    public void setMediumamount(Integer mediumamount) {
        this.mediumamount = mediumamount;
    }
    public BigDecimal getMediumweight() {
        return mediumweight;
    }

    public void setMediumweight(BigDecimal mediumweight) {
        this.mediumweight = mediumweight;
    }
    public Integer getMinamount() {
        return minamount;
    }

    public void setMinamount(Integer minamount) {
        this.minamount = minamount;
    }
    public BigDecimal getMinweight() {
        return minweight;
    }

    public void setMinweight(BigDecimal minweight) {
        this.minweight = minweight;
    }
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    public BigDecimal getPermu() {
        return permu;
    }

    public void setPermu(BigDecimal permu) {
        this.permu = permu;
    }
    public BigDecimal getAvgperstrain() {
        return avgperstrain;
    }

    public void setAvgperstrain(BigDecimal avgperstrain) {
        this.avgperstrain = avgperstrain;
    }
    public String getTestperson() {
        return testperson;
    }

    public void setTestperson(String testperson) {
        this.testperson = testperson;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public String getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid;
    }

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	

	public BigDecimal getBatchSum() {
		return batchSum;
	}

	public void setBatchSum(BigDecimal batchSum) {
		this.batchSum = batchSum;
	}

	public int getStrainamount() {
		return strainamount;
	}

	public void setStrainamount(int strainamount) {
		this.strainamount = strainamount;
	}
}