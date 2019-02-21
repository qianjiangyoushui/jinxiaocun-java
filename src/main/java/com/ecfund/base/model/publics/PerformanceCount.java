package com.ecfund.base.model.publics;

import com.alibaba.fastjson.annotation.JSONField;
import com.ecfund.base.model.seedfile.Seedfile;

import java.util.Date;

public class PerformanceCount {
    private String batchCode;//批次码
    private String batchid;//批次码
    private String companyName;//公司名称
    private String userName;//用户名
    private String address;//种植地点
    @JSONField(format="yyyy-MM-dd")
    private Date createDate;//种植日期
    private int growthDays;//生长天数
    private int uploadDays;//上传天数
    private Seedfile seedfile;//批次

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getGrowthDays() {
        return growthDays;
    }

    public void setGrowthDays(int growthDays) {
        this.growthDays = growthDays;
    }

    public int getUploadDays() {
        return uploadDays;
    }

    public void setUploadDays(int uploadDays) {
        this.uploadDays = uploadDays;
    }

    public Seedfile getSeedfile() {
        return seedfile;
    }

    public void setSeedfile(Seedfile seedfile) {
        this.seedfile = seedfile;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }
}
