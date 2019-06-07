package com.drydock.entity;

public class ProjectCurrencyConversion {
	private long id;
	private long orgid;
	private long shipid;
	private long projectid;
	private Long fromcurrencyid;
	private Long tocurrencyid;
	private Float conversionRate;

	private long createid;
	private long createdate;
	private long updateid;
	private long updatedate;
	private Integer isactive;
	
	private String fromcurrencyDesc;
	private String tocurrencyDesc;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOrgid() {
		return orgid;
	}
	public void setOrgid(long orgid) {
		this.orgid = orgid;
	}
	public long getShipid() {
		return shipid;
	}
	public void setShipid(long shipid) {
		this.shipid = shipid;
	}
	public long getProjectid() {
		return projectid;
	}
	public void setProjectid(long projectid) {
		this.projectid = projectid;
	}
	public Long getFromcurrencyid() {
		return fromcurrencyid;
	}
	public void setFromcurrencyid(Long fromcurrencyid) {
		this.fromcurrencyid = fromcurrencyid;
	}
	public Long getTocurrencyid() {
		return tocurrencyid;
	}
	public void setTocurrencyid(Long tocurrencyid) {
		this.tocurrencyid = tocurrencyid;
	}
	public Float getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(Float conversionRate) {
		this.conversionRate = conversionRate;
	}
	public long getCreateid() {
		return createid;
	}
	public void setCreateid(long createid) {
		this.createid = createid;
	}
	public long getCreatedate() {
		return createdate;
	}
	public void setCreatedate(long createdate) {
		this.createdate = createdate;
	}
	public long getUpdateid() {
		return updateid;
	}
	public void setUpdateid(long updateid) {
		this.updateid = updateid;
	}
	public long getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(long updatedate) {
		this.updatedate = updatedate;
	}
	public Integer getIsactive() {
		return isactive;
	}
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	public String getFromcurrencyDesc() {
		return fromcurrencyDesc;
	}
	public void setFromcurrencyDesc(String fromcurrencyDesc) {
		this.fromcurrencyDesc = fromcurrencyDesc;
	}
	public String getTocurrencyDesc() {
		return tocurrencyDesc;
	}
	public void setTocurrencyDesc(String tocurrencyDesc) {
		this.tocurrencyDesc = tocurrencyDesc;
	}

}
