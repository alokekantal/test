package com.drydock.entity;
import java.math.BigDecimal;
import java.util.List;

public class Job {
	private long id;
	private long orgid;
	private long shipid;
	private long projectid;
	private String description;
	private long shipcomponentid;
	private String status;
	private String equipment;	
	
	private long createid;
	private long createdate;
	private long updateid;
	private long updatedate;
	private Integer isactive;
	private String jobLastUpdatedByName;

	private long jobdate;
	private String jobno;
	private String accountno;
	private String specification;
	private String location;
	private String detailedSpecification;
	private String totalArea;
	private String checkboxes;
	private BigDecimal estimatedBudget;
	private String currency;
	private String approvalFlag;
	private String approvalComment;
	private long approvalLastUpdatedBy;
	private String approvalLastUpdatedByName;
	private long approvalLastUpdatedOn;
	
	private Long previousJobId;
	private Long nextJobId;
	
	private List<JobAttachment> jobAttachmentList;
	private JobComment jobComment;
	private List<JobComment> jobCommentList;
	private List<JobMaterialDetails> jobMaterialDetailsList;
	private List<JobProgressReport> jobProgressReportList;
	private JobProgressReport jobProgressReport;
	
	private String make;
	private String model;
	private String makeModelDescription;
	private String externalReference;
	private String jobCloserRemark;
	
	private OrganizationComponent orgComponent;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getShipcomponentid() {
		return shipcomponentid;
	}
	public void setShipcomponentid(long shipcomponentid) {
		this.shipcomponentid = shipcomponentid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getCreateid() {
		return createid;
	}
	public void setCreateid(long createid) {
		this.createid = createid;
	}
	public long getUpdateid() {
		return updateid;
	}
	public void setUpdateid(long updateid) {
		this.updateid = updateid;
	}
	public Integer getIsactive() {
		return isactive;
	}
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	public String getJobno() {
		return jobno;
	}
	public void setJobno(String jobno) {
		this.jobno = jobno;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDetailedSpecification() {
		return detailedSpecification;
	}
	public void setDetailedSpecification(String detailedSpecification) {
		this.detailedSpecification = detailedSpecification;
	}
	public String getTotalArea() {
		return totalArea;
	}
	public void setTotalArea(String totalArea) {
		this.totalArea = totalArea;
	}
	public String getCheckboxes() {
		return checkboxes;
	}
	public void setCheckboxes(String checkboxes) {
		this.checkboxes = checkboxes;
	}
	public List<JobAttachment> getJobAttachmentList() {
		return jobAttachmentList;
	}
	public void setJobAttachmentList(List<JobAttachment> jobAttachmentList) {
		this.jobAttachmentList = jobAttachmentList;
	}
	public JobComment getJobComment() {
		return jobComment;
	}
	public void setJobComment(JobComment jobComment) {
		this.jobComment = jobComment;
	}
	public List<JobComment> getJobCommentList() {
		return jobCommentList;
	}
	public void setJobCommentList(List<JobComment> jobCommentList) {
		this.jobCommentList = jobCommentList;
	}
	public BigDecimal getEstimatedBudget() {
		return estimatedBudget;
	}
	public void setEstimatedBudget(BigDecimal estimatedBudget) {
		this.estimatedBudget = estimatedBudget;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public List<JobMaterialDetails> getJobMaterialDetailsList() {
		return jobMaterialDetailsList;
	}
	public void setJobMaterialDetailsList(List<JobMaterialDetails> jobMaterialDetailsList) {
		this.jobMaterialDetailsList = jobMaterialDetailsList;
	}
	public List<JobProgressReport> getJobProgressReportList() {
		return jobProgressReportList;
	}
	public void setJobProgressReportList(List<JobProgressReport> jobProgressReportList) {
		this.jobProgressReportList = jobProgressReportList;
	}
	public JobProgressReport getJobProgressReport() {
		return jobProgressReport;
	}
	public void setJobProgressReport(JobProgressReport jobProgressReport) {
		this.jobProgressReport = jobProgressReport;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMakeModelDescription() {
		return makeModelDescription;
	}
	public void setMakeModelDescription(String makeModelDescription) {
		this.makeModelDescription = makeModelDescription;
	}
	public String getExternalReference() {
		return externalReference;
	}
	public void setExternalReference(String externalReference) {
		this.externalReference = externalReference;
	}
	public long getCreatedate() {
		return createdate;
	}
	public void setCreatedate(long createdate) {
		this.createdate = createdate;
	}
	public long getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(long updatedate) {
		this.updatedate = updatedate;
	}
	public long getJobdate() {
		return jobdate;
	}
	public void setJobdate(long jobdate) {
		this.jobdate = jobdate;
	}
	public String getApprovalFlag() {
		return approvalFlag;
	}
	public void setApprovalFlag(String approvalFlag) {
		this.approvalFlag = approvalFlag;
	}
	public String getApprovalComment() {
		return approvalComment;
	}
	public void setApprovalComment(String approvalComment) {
		this.approvalComment = approvalComment;
	}
	public long getApprovalLastUpdatedBy() {
		return approvalLastUpdatedBy;
	}
	public void setApprovalLastUpdatedBy(long approvalLastUpdatedBy) {
		this.approvalLastUpdatedBy = approvalLastUpdatedBy;
	}
	public long getApprovalLastUpdatedOn() {
		return approvalLastUpdatedOn;
	}
	public void setApprovalLastUpdatedOn(long approvalLastUpdatedOn) {
		this.approvalLastUpdatedOn = approvalLastUpdatedOn;
	}
	public String getApprovalLastUpdatedByName() {
		return approvalLastUpdatedByName;
	}
	public void setApprovalLastUpdatedByName(String approvalLastUpdatedByName) {
		this.approvalLastUpdatedByName = approvalLastUpdatedByName;
	}
	public String getJobLastUpdatedByName() {
		return jobLastUpdatedByName;
	}
	public void setJobLastUpdatedByName(String jobLastUpdatedByName) {
		this.jobLastUpdatedByName = jobLastUpdatedByName;
	}
	public Long getPreviousJobId() {
		return previousJobId;
	}
	public void setPreviousJobId(Long previousJobId) {
		this.previousJobId = previousJobId;
	}
	public Long getNextJobId() {
		return nextJobId;
	}
	public void setNextJobId(Long nextJobId) {
		this.nextJobId = nextJobId;
	}
	public String getJobCloserRemark() {
		return jobCloserRemark;
	}
	public void setJobCloserRemark(String jobCloserRemark) {
		this.jobCloserRemark = jobCloserRemark;
	}
	public OrganizationComponent getOrgComponent() {
		return orgComponent;
	}
	public void setOrgComponent(OrganizationComponent orgComponent) {
		this.orgComponent = orgComponent;
	}
}
