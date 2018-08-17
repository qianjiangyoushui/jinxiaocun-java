package com.ecfund.base.model.tasks;

import java.util.Date;

import com.ecfund.base.model.workorder.Workorder;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-24 10:08
 * @filename Tasks.java
 * 
 */
 
public class Tasks implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String userid;
    
    private String workorderid;
    
    private String status;
    
    private String description;
    
    private Date enddate;
    
    private String startdate;
    
    private String name;
    
    private Workorder workorder;
    
    private String date;
    
    
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getWorkorderid() {
        return workorderid;
    }

    public void setWorkorderid(String workorderid) {
        this.workorderid = workorderid;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Workorder getWorkorder() {
		return workorder;
	}

	public void setWorkorder(Workorder workorder) {
		this.workorder = workorder;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}