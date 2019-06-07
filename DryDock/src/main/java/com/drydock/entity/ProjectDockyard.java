package com.drydock.entity;
import java.util.Date;

public class ProjectDockyard {
	private long id;
	private long orgid;
	private long shipid;
	private long projectid;
	private Long dockyardId;
	private String contactDetails;
	private String remarks;
	private Long defaultCurrencyId;
	private String defaultCurrencyDesc;
	private String dockyardDesc;
	
	private long createid;
	private Date createdate;
	private long updateid;
	private Date updatedate;
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
	public long getCreateid() {
		return createid;
	}
	public void setCreateid(long createid) {
		this.createid = createid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public long getUpdateid() {
		return updateid;
	}
	public void setUpdateid(long updateid) {
		this.updateid = updateid;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public Integer getIsactive() {
		return isactive;
	}
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	public Long getDockyardId() {
		return dockyardId;
	}
	public void setDockyardId(Long dockyardId) {
		this.dockyardId = dockyardId;
	}
	public String getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getDefaultCurrencyId() {
		return defaultCurrencyId;
	}
	public void setDefaultCurrencyId(Long defaultCurrencyId) {
		this.defaultCurrencyId = defaultCurrencyId;
	}
	public String getDefaultCurrencyDesc() {
		return defaultCurrencyDesc;
	}
	public void setDefaultCurrencyDesc(String defaultCurrencyDesc) {
		this.defaultCurrencyDesc = defaultCurrencyDesc;
	}
	public String getDockyardDesc() {
		return dockyardDesc;
	}
	public void setDockyardDesc(String dockyardDesc) {
		this.dockyardDesc = dockyardDesc;
	}
	
}
