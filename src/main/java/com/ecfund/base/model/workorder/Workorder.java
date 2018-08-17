package com.ecfund.base.model.workorder;

import java.util.Date;
import java.util.List;

import com.ecfund.base.model.tasks.Tasks;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-24 09:23
 * @filename Workorder.java
 * 
 */
 
public class Workorder implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String ordername;
    
    private Date startdate;
    
    private Date enddate;
    
    private String principal;
    
    private String taskrequest;
    
    private Date createdate;
    
    private String userid;
    
    private String status;
    
    private String partic;
    
    private String username;
    
    
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }
    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
    public String getTaskrequest() {
        return taskrequest;
    }

    public void setTaskrequest(String taskrequest) {
        this.taskrequest = taskrequest;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getPartic() {
		return partic;
	}

	public void setPartic(String partic) {
		this.partic = partic;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}