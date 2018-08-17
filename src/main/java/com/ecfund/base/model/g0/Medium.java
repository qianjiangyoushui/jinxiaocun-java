package com.ecfund.base.model.g0;

import java.util.Date;

import com.ecfund.base.model.publics.Dictionary;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-08-02 16:12
 * @filename Medium.java
 * 
 */
 
public class Medium implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
    private String guid;
    
    private String seedfileid;
    
    private String mediumname;
    
    private String comefrom;
    
    private String scale;
    
    private Date createdate;
    
    private String operatorid;
    
    private Dictionary come;//培养基来源
    
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
    public String getMediumname() {
        return mediumname;
    }

    public void setMediumname(String mediumname) {
        this.mediumname = mediumname;
    }
    public String getComefrom() {
        return comefrom;
    }

    public void setComefrom(String comefrom) {
        this.comefrom = comefrom;
    }
    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
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

	public Dictionary getCome() {
		return come;
	}

	public void setCome(Dictionary come) {
		this.come = come;
	}
    
}