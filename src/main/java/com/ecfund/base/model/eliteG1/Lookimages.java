package com.ecfund.base.model.eliteG1;

import java.util.Date;
import java.util.List;

import com.ecfund.base.model.publics.Upimage;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-16 11:29
 * @filename Lookimages.java
 * 
 */
 
public class Lookimages implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String seedfileid;
    
    private String description;
    
    private Date createdate;
    
    private String operatorid;
    
    private List<Upimage> upimage;
    
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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

	public List<Upimage> getUpimage() {
		return upimage;
	}

	public void setUpimage(List<Upimage> upimage) {
		this.upimage = upimage;
	}
    
    
}