package com.drydock.entity;
import java.util.Date;
import java.util.List;

public class Project {
	private long id;
	private String description;
	private long orgid;
	private long shipid;
	private String status;
	private Date startdate;
	private Date enddate;
	
	private long createid;
	private Date createdate;
	private long updateid;
	private Date updatedate;
	private Integer isactive;
	private String closerComment;
	private String closerAttachmentRelativePath;
	private String closerAttachmentName;
	
	
	
	private String dockyard;
	private String preamble;
	
	private Long estimatedStart;
	private Long estimatedfinish;
	private Long actualStart;
	private Long actualfinish;
	private Long currencyMasterId;
	private List<ProjectAttachment> projectAttachmentList;
	private List<ProjectDockyard> projectDockyardList;
	private List<ProjectCurrencyConversion> projectCurrencyConversionList;
	private List<Job> projectJobList;
	private List<ProjectJobCostLineitem> lineitemList;
	private Shipmaster shipDetails;
	
	private String currencyMasterDesc;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getDockyard() {
		return dockyard;
	}
	public void setDockyard(String dockyard) {
		this.dockyard = dockyard;
	}
	public List<ProjectAttachment> getProjectAttachmentList() {
		return projectAttachmentList;
	}
	public void setProjectAttachmentList(
			List<ProjectAttachment> projectAttachmentList) {
		this.projectAttachmentList = projectAttachmentList;
	}
	public String getPreamble() {
		return preamble;
	}
	public void setPreamble(String preamble) {
		this.preamble = preamble;
	}
	public Long getEstimatedStart() {
		return estimatedStart;
	}
	public void setEstimatedStart(Long estimatedStart) {
		this.estimatedStart = estimatedStart;
	}
	public Long getEstimatedfinish() {
		return estimatedfinish;
	}
	public void setEstimatedfinish(Long estimatedfinish) {
		this.estimatedfinish = estimatedfinish;
	}
	public Long getActualStart() {
		return actualStart;
	}
	public void setActualStart(Long actualStart) {
		this.actualStart = actualStart;
	}
	public Long getActualfinish() {
		return actualfinish;
	}
	public void setActualfinish(Long actualfinish) {
		this.actualfinish = actualfinish;
	}
	public Long getCurrencyMasterId() {
		return currencyMasterId;
	}
	public void setCurrencyMasterId(Long currencyMasterId) {
		this.currencyMasterId = currencyMasterId;
	}
	public List<ProjectDockyard> getProjectDockyardList() {
		return projectDockyardList;
	}
	public void setProjectDockyardList(List<ProjectDockyard> projectDockyardList) {
		this.projectDockyardList = projectDockyardList;
	}
	public List<ProjectCurrencyConversion> getProjectCurrencyConversionList() {
		return projectCurrencyConversionList;
	}
	public void setProjectCurrencyConversionList(
			List<ProjectCurrencyConversion> projectCurrencyConversionList) {
		this.projectCurrencyConversionList = projectCurrencyConversionList;
	}
	public List<Job> getProjectJobList() {
		return projectJobList;
	}
	public void setProjectJobList(List<Job> projectJobList) {
		this.projectJobList = projectJobList;
	}
	public List<ProjectJobCostLineitem> getLineitemList() {
		return lineitemList;
	}
	public void setLineitemList(List<ProjectJobCostLineitem> lineitemList) {
		this.lineitemList = lineitemList;
	}
	public String getCloserComment() {
		return closerComment;
	}
	public void setCloserComment(String closerComment) {
		this.closerComment = closerComment;
	}
	public String getCloserAttachmentRelativePath() {
		return closerAttachmentRelativePath;
	}
	public void setCloserAttachmentRelativePath(String closerAttachmentRelativePath) {
		this.closerAttachmentRelativePath = closerAttachmentRelativePath;
	}
	public String getCloserAttachmentName() {
		return closerAttachmentName;
	}
	public void setCloserAttachmentName(String closerAttachmentName) {
		this.closerAttachmentName = closerAttachmentName;
	}
	public Shipmaster getShipDetails() {
		return shipDetails;
	}
	public void setShipDetails(Shipmaster shipDetails) {
		this.shipDetails = shipDetails;
	}
	public String getCurrencyMasterDesc() {
		return currencyMasterDesc;
	}
	public void setCurrencyMasterDesc(String currencyMasterDesc) {
		this.currencyMasterDesc = currencyMasterDesc;
	}
	
}
