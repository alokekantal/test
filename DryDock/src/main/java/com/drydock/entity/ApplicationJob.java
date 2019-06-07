package com.drydock.entity;
import java.util.Date;
import java.util.List;

public class ApplicationJob {
	private long id;
	private String description;
	private String jobno;
	private String accountno;
	private String specification;
	private String location;
	private String detailedSpecification;
	private String totalArea;
	private String checkboxes;
	private long createid;
	private Date createdate;
	private long updateid;
	private Date updatedate;
	private Integer isactive;
	
	private String make;
	private String model;
	private String makeModelDescription;
	private String equipment;	
	private long jobdate;
	private String vesselType;
	private String vesselAge;	
	
	
	private Long shipcomponentid;
	private List<ApplicationJobMaterialDetails> jobMaterialDetailsList;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
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
	public Long getShipcomponentid() {
		return shipcomponentid;
	}
	public void setShipcomponentid(Long shipcomponentid) {
		this.shipcomponentid = shipcomponentid;
	}
	public List<ApplicationJobMaterialDetails> getJobMaterialDetailsList() {
		return jobMaterialDetailsList;
	}
	public void setJobMaterialDetailsList(
			List<ApplicationJobMaterialDetails> jobMaterialDetailsList) {
		this.jobMaterialDetailsList = jobMaterialDetailsList;
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
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public long getJobdate() {
		return jobdate;
	}
	public void setJobdate(long jobdate) {
		this.jobdate = jobdate;
	}
	public String getVesselType() {
		return vesselType;
	}
	public void setVesselType(String vesselType) {
		this.vesselType = vesselType;
	}
	public String getVesselAge() {
		return vesselAge;
	}
	public void setVesselAge(String vesselAge) {
		this.vesselAge = vesselAge;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
}
