package com.drydock.entity;

import java.util.List;

public class ProjectJobCostLineitem {
	private long id;
	private long orgid;
	private long shipid;
	private long projectid;
	private long jobid;
	private String lineitem;
	
	private long createid;
	private long createdate;
	private long updateid;
	private long updatedate;
	private Integer isactive;
	
	private List<ProjectJobCostLineitemDetails> detailsList;
	
	
	
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
	public String getLineitem() {
		return lineitem;
	}
	public void setLineitem(String lineitem) {
		this.lineitem = lineitem;
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
	public List<ProjectJobCostLineitemDetails> getDetailsList() {
		return detailsList;
	}
	public void setDetailsList(List<ProjectJobCostLineitemDetails> detailsList) {
		this.detailsList = detailsList;
	}
	
		
}
