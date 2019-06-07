package com.drydock.entity;

import java.util.List;

public class JobProgressReport {
	private long id;
	private long jobid;
	private long reportingDate;
	private long executionDate;
	private String workDone;
	private String jobAttachmentIds;
	
	private long createid;
	private long createdate;
	private long updateid;
	private long updatedate;
	private Integer isactive;
	
	private List<JobAttachment> jobAttachmentList;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getJobid() {
		return jobid;
	}
	public void setJobid(long jobid) {
		this.jobid = jobid;
	}
	public long getReportingDate() {
		return reportingDate;
	}
	public void setReportingDate(long reportingDate) {
		this.reportingDate = reportingDate;
	}
	public long getExecutionDate() {
		return executionDate;
	}
	public void setExecutionDate(long executionDate) {
		this.executionDate = executionDate;
	}
	public String getWorkDone() {
		return workDone;
	}
	public void setWorkDone(String workDone) {
		this.workDone = workDone;
	}
	
	public String getJobAttachmentIds() {
		return jobAttachmentIds;
	}
	public void setJobAttachmentIds(String jobAttachmentIds) {
		this.jobAttachmentIds = jobAttachmentIds;
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
	public List<JobAttachment> getJobAttachmentList() {
		return jobAttachmentList;
	}
	public void setJobAttachmentList(List<JobAttachment> jobAttachmentList) {
		this.jobAttachmentList = jobAttachmentList;
	}	
}
