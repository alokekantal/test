package com.drydock.entity;

import java.util.List;

public class ReportDTO {
	private boolean vesselParticulars;
	private boolean shipno;
	private boolean orgId;
	private boolean name;
	private boolean description;
	private boolean vesselname;
	private boolean v_imo_no;
	private boolean mmsi_no;
	private boolean call_sign;
	private boolean official_no;
	private boolean v_type;
	private boolean owner_imo_no;
	private boolean owner_name;
	private boolean sat_f_77;
	private boolean sat_c;
	private boolean fleet_broadband;
	private boolean sat_c_emailID;
	private boolean emailID;
	private boolean shipClass;
	private boolean class_notations;
	private boolean Classi_Id_No;
	private boolean flag;
	private boolean port_of_registry;
	private boolean year_built;
	private boolean keel_laying_date;
	private boolean vessel_delivery_date;
	private boolean hull_type;
	private boolean length_overall;
	private boolean breadth_MLD;
	private boolean depth;
	private boolean summer_draft_M;
	private boolean summer_DWT_MT;
	private boolean international_GRT;
	private boolean international_NRT;
	private boolean life_boat_cap;
	private boolean v_short_name;
	private boolean account_code_old;
	private boolean account_code_new;
	private boolean tel_fac_code;
	private boolean maxEmailSizeInMB;
	private boolean dailyDataLimitInMB;	
	private boolean email1;
	private boolean email2;
	private boolean phoneNo;
	private boolean phoneNo1;
	private boolean phoneNo2;
	
	private boolean projectDetail;
	private boolean preamble;
	private boolean projectAttachments;
	private boolean ddParameter;
	
	private boolean specificationOfRepairs;
	private boolean jobGeneralInfo;
	private boolean jobno;
	private boolean make;
	private boolean model;
	private boolean makeModelDescription;
	private boolean externalReference;
	private boolean component;
	private boolean location;
	private boolean equipment;
	private boolean jobDescription;
	private boolean jobSpecificationDetail;
	private boolean jobSpecificationAttachment;
	private boolean jobDate;
	
	private boolean checkBox;
	private boolean toBeInclude;
	private boolean materials;
	private boolean theWorkToBeSurveyedAlsoBy;
	
	private boolean meterialDetail;
	private boolean meterialDetailMake;
	private boolean meterialDetailModel;
	private boolean meterialDetailPartNo;
	private boolean meterialDetailPartName;
	private boolean meterialDetailQuantity;
	private boolean meterialDetailUOM;
	private boolean meterialDetailDrawingNo;
	private boolean meterialDetailRemark;
	
	private boolean progressReport;
	private boolean progressReportReportingDate;
	private boolean progressReportExecutionDate;
	private boolean progressReportWorkDoneForTheDay;
	private boolean progressReportAttachments;
	
	
	private String selectedJobNo;
	private List<ReportDTO> jobList;
	
	private boolean execPhotosReps;
	private boolean jobComment;
	
	public boolean isShipno() {
		return shipno;
	}
	public void setShipno(boolean shipno) {
		this.shipno = shipno;
	}
	public boolean isOrgId() {
		return orgId;
	}
	public void setOrgId(boolean orgId) {
		this.orgId = orgId;
	}
	public boolean isName() {
		return name;
	}
	public void setName(boolean name) {
		this.name = name;
	}
	public boolean isDescription() {
		return description;
	}
	public void setDescription(boolean description) {
		this.description = description;
	}
	public boolean isVesselname() {
		return vesselname;
	}
	public void setVesselname(boolean vesselname) {
		this.vesselname = vesselname;
	}
	public boolean isV_imo_no() {
		return v_imo_no;
	}
	public void setV_imo_no(boolean v_imo_no) {
		this.v_imo_no = v_imo_no;
	}
	public boolean isMmsi_no() {
		return mmsi_no;
	}
	public void setMmsi_no(boolean mmsi_no) {
		this.mmsi_no = mmsi_no;
	}
	public boolean isCall_sign() {
		return call_sign;
	}
	public void setCall_sign(boolean call_sign) {
		this.call_sign = call_sign;
	}
	public boolean isOfficial_no() {
		return official_no;
	}
	public void setOfficial_no(boolean official_no) {
		this.official_no = official_no;
	}
	public boolean isV_type() {
		return v_type;
	}
	public void setV_type(boolean v_type) {
		this.v_type = v_type;
	}
	public boolean isOwner_imo_no() {
		return owner_imo_no;
	}
	public void setOwner_imo_no(boolean owner_imo_no) {
		this.owner_imo_no = owner_imo_no;
	}
	public boolean isOwner_name() {
		return owner_name;
	}
	public void setOwner_name(boolean owner_name) {
		this.owner_name = owner_name;
	}
	public boolean isSat_f_77() {
		return sat_f_77;
	}
	public void setSat_f_77(boolean sat_f_77) {
		this.sat_f_77 = sat_f_77;
	}
	public boolean isSat_c() {
		return sat_c;
	}
	public void setSat_c(boolean sat_c) {
		this.sat_c = sat_c;
	}
	public boolean isFleet_broadband() {
		return fleet_broadband;
	}
	public void setFleet_broadband(boolean fleet_broadband) {
		this.fleet_broadband = fleet_broadband;
	}
	public boolean isSat_c_emailID() {
		return sat_c_emailID;
	}
	public void setSat_c_emailID(boolean sat_c_emailID) {
		this.sat_c_emailID = sat_c_emailID;
	}
	public boolean isEmailID() {
		return emailID;
	}
	public void setEmailID(boolean emailID) {
		this.emailID = emailID;
	}
	public boolean isShipClass() {
		return shipClass;
	}
	public void setShipClass(boolean shipClass) {
		this.shipClass = shipClass;
	}
	public boolean isClass_notations() {
		return class_notations;
	}
	public void setClass_notations(boolean class_notations) {
		this.class_notations = class_notations;
	}
	public boolean isClassi_Id_No() {
		return Classi_Id_No;
	}
	public void setClassi_Id_No(boolean classi_Id_No) {
		Classi_Id_No = classi_Id_No;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isPort_of_registry() {
		return port_of_registry;
	}
	public void setPort_of_registry(boolean port_of_registry) {
		this.port_of_registry = port_of_registry;
	}
	public boolean isYear_built() {
		return year_built;
	}
	public void setYear_built(boolean year_built) {
		this.year_built = year_built;
	}

	public boolean isHull_type() {
		return hull_type;
	}
	public void setHull_type(boolean hull_type) {
		this.hull_type = hull_type;
	}
	public boolean isLength_overall() {
		return length_overall;
	}
	public void setLength_overall(boolean length_overall) {
		this.length_overall = length_overall;
	}
	public boolean isBreadth_MLD() {
		return breadth_MLD;
	}
	public void setBreadth_MLD(boolean breadth_MLD) {
		this.breadth_MLD = breadth_MLD;
	}
	public boolean isDepth() {
		return depth;
	}
	public void setDepth(boolean depth) {
		this.depth = depth;
	}
	public boolean isSummer_draft_M() {
		return summer_draft_M;
	}
	public void setSummer_draft_M(boolean summer_draft_M) {
		this.summer_draft_M = summer_draft_M;
	}
	public boolean isSummer_DWT_MT() {
		return summer_DWT_MT;
	}
	public void setSummer_DWT_MT(boolean summer_DWT_MT) {
		this.summer_DWT_MT = summer_DWT_MT;
	}
	public boolean isInternational_GRT() {
		return international_GRT;
	}
	public void setInternational_GRT(boolean international_GRT) {
		this.international_GRT = international_GRT;
	}
	public boolean isInternational_NRT() {
		return international_NRT;
	}
	public void setInternational_NRT(boolean international_NRT) {
		this.international_NRT = international_NRT;
	}
	public boolean isLife_boat_cap() {
		return life_boat_cap;
	}
	public void setLife_boat_cap(boolean life_boat_cap) {
		this.life_boat_cap = life_boat_cap;
	}
	public boolean isV_short_name() {
		return v_short_name;
	}
	public void setV_short_name(boolean v_short_name) {
		this.v_short_name = v_short_name;
	}
	public boolean isAccount_code_old() {
		return account_code_old;
	}
	public void setAccount_code_old(boolean account_code_old) {
		this.account_code_old = account_code_old;
	}
	public boolean isAccount_code_new() {
		return account_code_new;
	}
	public void setAccount_code_new(boolean account_code_new) {
		this.account_code_new = account_code_new;
	}
	public boolean isTel_fac_code() {
		return tel_fac_code;
	}
	public void setTel_fac_code(boolean tel_fac_code) {
		this.tel_fac_code = tel_fac_code;
	}
	
	public boolean isMaxEmailSizeInMB() {
		return maxEmailSizeInMB;
	}
	public void setMaxEmailSizeInMB(boolean maxEmailSizeInMB) {
		this.maxEmailSizeInMB = maxEmailSizeInMB;
	}
	public boolean isDailyDataLimitInMB() {
		return dailyDataLimitInMB;
	}
	public void setDailyDataLimitInMB(boolean dailyDataLimitInMB) {
		this.dailyDataLimitInMB = dailyDataLimitInMB;
	}
	public boolean isEmail1() {
		return email1;
	}
	public void setEmail1(boolean email1) {
		this.email1 = email1;
	}
	public boolean isEmail2() {
		return email2;
	}
	public void setEmail2(boolean email2) {
		this.email2 = email2;
	}
	public boolean isPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(boolean phoneNo) {
		this.phoneNo = phoneNo;
	}
	public boolean isPhoneNo1() {
		return phoneNo1;
	}
	public void setPhoneNo1(boolean phoneNo1) {
		this.phoneNo1 = phoneNo1;
	}
	public boolean isPhoneNo2() {
		return phoneNo2;
	}
	public void setPhoneNo2(boolean phoneNo2) {
		this.phoneNo2 = phoneNo2;
	}
	public boolean isPreamble() {
		return preamble;
	}
	public void setPreamble(boolean preamble) {
		this.preamble = preamble;
	}
	public boolean isProjectAttachments() {
		return projectAttachments;
	}
	public void setProjectAttachments(boolean projectAttachments) {
		this.projectAttachments = projectAttachments;
	}
	public boolean isJobno() {
		return jobno;
	}
	public void setJobno(boolean jobno) {
		this.jobno = jobno;
	}
	public boolean isMake() {
		return make;
	}
	public void setMake(boolean make) {
		this.make = make;
	}
	public boolean isModel() {
		return model;
	}
	public void setModel(boolean model) {
		this.model = model;
	}
	public boolean isComponent() {
		return component;
	}
	public void setComponent(boolean component) {
		this.component = component;
	}
	public boolean isLocation() {
		return location;
	}
	public void setLocation(boolean location) {
		this.location = location;
	}
	public boolean isEquipment() {
		return equipment;
	}
	public void setEquipment(boolean equipment) {
		this.equipment = equipment;
	}
	public boolean isJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(boolean jobDescription) {
		this.jobDescription = jobDescription;
	}
	public boolean isJobSpecificationDetail() {
		return jobSpecificationDetail;
	}
	public void setJobSpecificationDetail(boolean jobSpecificationDetail) {
		this.jobSpecificationDetail = jobSpecificationDetail;
	}
	public boolean isJobSpecificationAttachment() {
		return jobSpecificationAttachment;
	}
	public void setJobSpecificationAttachment(boolean jobSpecificationAttachment) {
		this.jobSpecificationAttachment = jobSpecificationAttachment;
	}
	public boolean isCheckBox() {
		return checkBox;
	}
	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}
	
	public boolean isProgressReport() {
		return progressReport;
	}
	public void setProgressReport(boolean progressReport) {
		this.progressReport = progressReport;
	}
	public boolean isVesselParticulars() {
		return vesselParticulars;
	}
	public void setVesselParticulars(boolean vesselParticulars) {
		this.vesselParticulars = vesselParticulars;
	}
	public boolean isKeel_laying_date() {
		return keel_laying_date;
	}
	public void setKeel_laying_date(boolean keel_laying_date) {
		this.keel_laying_date = keel_laying_date;
	}
	public boolean isVessel_delivery_date() {
		return vessel_delivery_date;
	}
	public void setVessel_delivery_date(boolean vessel_delivery_date) {
		this.vessel_delivery_date = vessel_delivery_date;
	}
	public boolean isProjectDetail() {
		return projectDetail;
	}
	public void setProjectDetail(boolean projectDetail) {
		this.projectDetail = projectDetail;
	}
	public boolean isSpecificationOfRepairs() {
		return specificationOfRepairs;
	}
	public void setSpecificationOfRepairs(boolean specificationOfRepairs) {
		this.specificationOfRepairs = specificationOfRepairs;
	}
	public boolean isToBeInclude() {
		return toBeInclude;
	}
	public void setToBeInclude(boolean toBeInclude) {
		this.toBeInclude = toBeInclude;
	}
	public boolean isMaterials() {
		return materials;
	}
	public void setMaterials(boolean materials) {
		this.materials = materials;
	}
	public boolean isTheWorkToBeSurveyedAlsoBy() {
		return theWorkToBeSurveyedAlsoBy;
	}
	public void setTheWorkToBeSurveyedAlsoBy(boolean theWorkToBeSurveyedAlsoBy) {
		this.theWorkToBeSurveyedAlsoBy = theWorkToBeSurveyedAlsoBy;
	}
	
	public boolean isProgressReportReportingDate() {
		return progressReportReportingDate;
	}
	public void setProgressReportReportingDate(boolean progressReportReportingDate) {
		this.progressReportReportingDate = progressReportReportingDate;
	}
	public boolean isProgressReportExecutionDate() {
		return progressReportExecutionDate;
	}
	public void setProgressReportExecutionDate(boolean progressReportExecutionDate) {
		this.progressReportExecutionDate = progressReportExecutionDate;
	}
	public boolean isProgressReportWorkDoneForTheDay() {
		return progressReportWorkDoneForTheDay;
	}
	public void setProgressReportWorkDoneForTheDay(
			boolean progressReportWorkDoneForTheDay) {
		this.progressReportWorkDoneForTheDay = progressReportWorkDoneForTheDay;
	}
	public boolean isProgressReportAttachments() {
		return progressReportAttachments;
	}
	public void setProgressReportAttachments(boolean progressReportAttachments) {
		this.progressReportAttachments = progressReportAttachments;
	}
	public boolean isMeterialDetail() {
		return meterialDetail;
	}
	public void setMeterialDetail(boolean meterialDetail) {
		this.meterialDetail = meterialDetail;
	}
	public boolean isMeterialDetailMake() {
		return meterialDetailMake;
	}
	public void setMeterialDetailMake(boolean meterialDetailMake) {
		this.meterialDetailMake = meterialDetailMake;
	}
	public boolean isMeterialDetailModel() {
		return meterialDetailModel;
	}
	public void setMeterialDetailModel(boolean meterialDetailModel) {
		this.meterialDetailModel = meterialDetailModel;
	}
	public boolean isMeterialDetailPartNo() {
		return meterialDetailPartNo;
	}
	public void setMeterialDetailPartNo(boolean meterialDetailPartNo) {
		this.meterialDetailPartNo = meterialDetailPartNo;
	}
	public boolean isMeterialDetailPartName() {
		return meterialDetailPartName;
	}
	public void setMeterialDetailPartName(boolean meterialDetailPartName) {
		this.meterialDetailPartName = meterialDetailPartName;
	}
	public boolean isMeterialDetailQuantity() {
		return meterialDetailQuantity;
	}
	public void setMeterialDetailQuantity(boolean meterialDetailQuantity) {
		this.meterialDetailQuantity = meterialDetailQuantity;
	}
	public boolean isMeterialDetailUOM() {
		return meterialDetailUOM;
	}
	public void setMeterialDetailUOM(boolean meterialDetailUOM) {
		this.meterialDetailUOM = meterialDetailUOM;
	}
	public boolean isMeterialDetailDrawingNo() {
		return meterialDetailDrawingNo;
	}
	public void setMeterialDetailDrawingNo(boolean meterialDetailDrawingNo) {
		this.meterialDetailDrawingNo = meterialDetailDrawingNo;
	}
	public boolean isMeterialDetailRemark() {
		return meterialDetailRemark;
	}
	public void setMeterialDetailRemark(boolean meterialDetailRemark) {
		this.meterialDetailRemark = meterialDetailRemark;
	}
	public boolean isJobGeneralInfo() {
		return jobGeneralInfo;
	}
	public void setJobGeneralInfo(boolean jobGeneralInfo) {
		this.jobGeneralInfo = jobGeneralInfo;
	}
	public boolean isMakeModelDescription() {
		return makeModelDescription;
	}
	public void setMakeModelDescription(boolean makeModelDescription) {
		this.makeModelDescription = makeModelDescription;
	}
	public boolean isExternalReference() {
		return externalReference;
	}
	public void setExternalReference(boolean externalReference) {
		this.externalReference = externalReference;
	}
	public boolean isJobDate() {
		return jobDate;
	}
	public void setJobDate(boolean jobDate) {
		this.jobDate = jobDate;
	}
	
	public String getSelectedJobNo() {
		return selectedJobNo;
	}
	public void setSelectedJobNo(String selectedJobNo) {
		this.selectedJobNo = selectedJobNo;
	}
	public List<ReportDTO> getJobList() {
		return jobList;
	}
	public void setJobList(List<ReportDTO> jobList) {
		this.jobList = jobList;
	}
	public boolean isDdParameter() {
		return ddParameter;
	}
	public void setDdParameter(boolean ddParameter) {
		this.ddParameter = ddParameter;
	}
	public boolean isExecPhotosReps() {
		return execPhotosReps;
	}
	public void setExecPhotosReps(boolean execPhotosReps) {
		this.execPhotosReps = execPhotosReps;
	}
	public boolean isJobComment() {
		return jobComment;
	}
	public void setJobComment(boolean jobComment) {
		this.jobComment = jobComment;
	}
	
}
