package com.drydock.entity;
import java.util.Date;

public class ProjectAttachment {
	private long id;
	private long orgid;
	private long shipid;
	private long projectid;
	private String attachmentName;
	private String relativepath;
	private String attachmentDescription;
	private String attachmentType;
	
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
	public String getRelativepath() {
		return relativepath;
	}
	public void setRelativepath(String relativepath) {
		this.relativepath = relativepath;
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
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getAttachmentDescription() {
		return attachmentDescription;
	}
	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}
	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	
}
