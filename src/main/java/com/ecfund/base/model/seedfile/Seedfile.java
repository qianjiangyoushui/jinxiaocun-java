package com.ecfund.base.model.seedfile;

import java.util.Date;

import com.ecfund.base.model.eliteG1.Greenhouses;
import com.ecfund.base.model.g0.Seedbed;
import com.ecfund.base.model.g2g3.Plots;
import com.ecfund.base.model.publics.Dictionary;

import java.math.BigDecimal;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-25 09:32
 * @filename Seedfile.java
 * 
 */

public class Seedfile implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String guid;

	private String companyid;

	private String batch;

	private String variety;

	private String level;

	private String seedid;

	private String placeid;

	private Integer years;

	private Date startdate;

	private Date enddate;

	private Integer bottleamount;

	private Integer strainamount;

	private Integer stubble;

	private Date stopwaterdate;

	private Date killdate;

	private Date rewarddate;

	private Date stopmuck;

	private Date buddingdate;

	private BigDecimal muamount;

	private String type;

	private String code;

	private String description;

	private String visible;

	private Date createdate;

	private String operatorid;
	
	private String isproduction;
	
	private String source;
	
	private Seedbed seedbed;//苗床
	
	private Dictionary  varietys;//品种
	
	private Dictionary levels;//扩繁级别
	
	private Greenhouses greenhouses; //大棚
	
	private Seedfile parentseed;//苗来源
	private Plots plots;//所属地块

	public Plots getPlots() {
		return plots;
	}

	public void setPlots(Plots plots) {
		this.plots = plots;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSeedid() {
		return seedid;
	}

	public void setSeedid(String seedid) {
		this.seedid = seedid;
	}

	public String getPlaceid() {
		return placeid;
	}

	public void setPlaceid(String placeid) {
		this.placeid = placeid;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
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

	public Integer getBottleamount() {
		return bottleamount;
	}

	public void setBottleamount(Integer bottleamount) {
		this.bottleamount = bottleamount;
	}

	public Integer getStrainamount() {
		return strainamount;
	}

	public void setStrainamount(Integer strainamount) {
		this.strainamount = strainamount;
	}

	public Integer getStubble() {
		return stubble;
	}

	public void setStubble(Integer stubble) {
		this.stubble = stubble;
	}

	public Date getStopwaterdate() {
		return stopwaterdate;
	}

	public void setStopwaterdate(Date stopwaterdate) {
		this.stopwaterdate = stopwaterdate;
	}

	public Date getKilldate() {
		return killdate;
	}

	public void setKilldate(Date killdate) {
		this.killdate = killdate;
	}

	public Date getRewarddate() {
		return rewarddate;
	}

	public void setRewarddate(Date rewarddate) {
		this.rewarddate = rewarddate;
	}

	public Date getStopmuck() {
		return stopmuck;
	}

	public void setStopmuck(Date stopmuck) {
		this.stopmuck = stopmuck;
	}

	public Date getBuddingdate() {
		return buddingdate;
	}

	public void setBuddingdate(Date buddingdate) {
		this.buddingdate = buddingdate;
	}

	public BigDecimal getMuamount() {
		return muamount;
	}

	public void setMuamount(BigDecimal muamount) {
		this.muamount = muamount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
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

	public Seedbed getSeedbed() {
		return seedbed;
	}

	public void setSeedbed(Seedbed seedbed) {
		this.seedbed = seedbed;
	}

	public Dictionary getVarietys() {
		return varietys;
	}

	public void setVarietys(Dictionary varietys) {
		this.varietys = varietys;
	}

	public Dictionary getLevels() {
		return levels;
	}

	public void setLevels(Dictionary levels) {
		this.levels = levels;
	}

	public Greenhouses getGreenhouses() {
		return greenhouses;
	}

	public void setGreenhouses(Greenhouses greenhouses) {
		this.greenhouses = greenhouses;
	}

	public Seedfile getParentseed() {
		return parentseed;
	}

	public void setParentseed(Seedfile parentseed) {
		this.parentseed = parentseed;
	}

	public String getIsproduction() {
		return isproduction;
	}

	public void setIsproduction(String isproduction) {
		this.isproduction = isproduction;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}