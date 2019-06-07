package com.drydock.entity;
import java.util.Date;

public class JobComment {
	private long id;
	private long jobid;
	private String jobComment;
	private long createid;
	private long createdate;
	private long updateid;
	private long updatedate;
	private String updatedBy;
	private Integer isactive;
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
	public String getJobComment() {
		return jobComment;
	}
	public void setJobComment(String jobComment) {
		this.jobComment = jobComment;
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
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
}
