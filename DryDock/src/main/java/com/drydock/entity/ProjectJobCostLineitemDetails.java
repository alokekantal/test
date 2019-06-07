package com.drydock.entity;

public class ProjectJobCostLineitemDetails {
	private long id;
	private long orgid;
	private long shipid;
	private long projectid;
	private long jobid;
	private long lineitemid;
	private long quoteCurrencyid;
	private long dockyardId;
	private String unit;
	private Float unitPrice;
	private Float unitQuantity;
	private Float costQuoteCurrency;
	private Float costProjectCurrency;
	
	private long createid;
	private long createdate;
	private long updateid;
	private long updatedate;
	private Integer isactive;
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
	public long getJobid() {
		return jobid;
	}
	public void setJobid(long jobid) {
		this.jobid = jobid;
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
	public long getLineitemid() {
		return lineitemid;
	}
	public void setLineitemid(long lineitemid) {
		this.lineitemid = lineitemid;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Float getUnitQuantity() {
		return unitQuantity;
	}
	public void setUnitQuantity(Float unitQuantity) {
		this.unitQuantity = unitQuantity;
	}
	public Float getCostQuoteCurrency() {
		return costQuoteCurrency;
	}
	public void setCostQuoteCurrency(Float costQuoteCurrency) {
		this.costQuoteCurrency = costQuoteCurrency;
	}
	public Float getCostProjectCurrency() {
		return costProjectCurrency;
	}
	public void setCostProjectCurrency(Float costProjectCurrency) {
		this.costProjectCurrency = costProjectCurrency;
	}
	public long getQuoteCurrencyid() {
		return quoteCurrencyid;
	}
	public void setQuoteCurrencyid(long quoteCurrencyid) {
		this.quoteCurrencyid = quoteCurrencyid;
	}
	public long getDockyardId() {
		return dockyardId;
	}
	public void setDockyardId(long dockyardId) {
		this.dockyardId = dockyardId;
	}
	
		
}
