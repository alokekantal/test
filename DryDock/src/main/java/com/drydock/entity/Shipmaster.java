package com.drydock.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.drydock.entity.ShipAttachment;

public class Shipmaster {
	private long ship_id;
	private String shipno;
	private long orgId;
	private String name;
	private String description;
	private String vesselname;
	private long v_imo_no;
	private long mmsi_no;
	private String call_sign;
	private String official_no;
	private long v_type;
	private long owner_imo_no;
	private String owner_name;
	private String sat_f_77;
	private String sat_c;
	private String fleet_broadband;
	private String sat_c_emailID;
	private String emailID;
	private String shipClass;
	private String class_notations;
	private String Classi_Id_No;
	private String flag;
	private String port_of_registry;
	private String year_built;
	private Long keel_laying_date;
	private Long vessel_delivery_date;
	private String hull_type;
	private BigDecimal length_overall;
	private BigDecimal breadth_MLD;
	private BigDecimal depth;
	private BigDecimal summer_draft_M;
	private String summer_DWT_MT;
	private String international_GRT;
	private String international_NRT;
	private long life_boat_cap;
	private String v_short_name;
	private String account_code_old;
	private String account_code_new;
	private String tel_fac_code;
	private long createid;
	private Date createdate;
	private long updateid;
	private Date updatedate;
	private Integer isactive;
	private BigDecimal maxEmailSizeInMB;
	private BigDecimal dailyDataLimitInMB;
	
	private String email1;
	private String email2;
	private String phoneNo;
	private String phoneNo1;
	private String phoneNo2;
	
	private String assignedUsers;
	
	private String vesselTypeDesc;
	
	private List<ShipAttachment> shipAttachmentList;
	
	public long getShip_id() {
		return ship_id;
	}
	public void setShip_id(long ship_id) {
		this.ship_id = ship_id;
	}
	public String getShipno() {
		return shipno;
	}
	public void setShipno(String shipno) {
		this.shipno = shipno;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVesselname() {
		return vesselname;
	}
	public void setVesselname(String vesselname) {
		this.vesselname = vesselname;
	}
	public long getV_imo_no() {
		return v_imo_no;
	}
	public void setV_imo_no(long v_imo_no) {
		this.v_imo_no = v_imo_no;
	}
	public long getMmsi_no() {
		return mmsi_no;
	}
	public void setMmsi_no(long mmsi_no) {
		this.mmsi_no = mmsi_no;
	}
	public String getCall_sign() {
		return call_sign;
	}
	public void setCall_sign(String call_sign) {
		this.call_sign = call_sign;
	}
	public String getOfficial_no() {
		return official_no;
	}
	public void setOfficial_no(String official_no) {
		this.official_no = official_no;
	}
	public long getV_type() {
		return v_type;
	}
	public void setV_type(long v_type) {
		this.v_type = v_type;
	}
	public long getOwner_imo_no() {
		return owner_imo_no;
	}
	public void setOwner_imo_no(long owner_imo_no) {
		this.owner_imo_no = owner_imo_no;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getSat_f_77() {
		return sat_f_77;
	}
	public void setSat_f_77(String sat_f_77) {
		this.sat_f_77 = sat_f_77;
	}
	public String getSat_c() {
		return sat_c;
	}
	public void setSat_c(String sat_c) {
		this.sat_c = sat_c;
	}
	public String getFleet_broadband() {
		return fleet_broadband;
	}
	public void setFleet_broadband(String fleet_broadband) {
		this.fleet_broadband = fleet_broadband;
	}
	public String getSat_c_emailID() {
		return sat_c_emailID;
	}
	public void setSat_c_emailID(String sat_c_emailID) {
		this.sat_c_emailID = sat_c_emailID;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getShipClass() {
		return shipClass;
	}
	public void setShipClass(String shipClass) {
		this.shipClass = shipClass;
	}
	public String getClass_notations() {
		return class_notations;
	}
	public void setClass_notations(String class_notations) {
		this.class_notations = class_notations;
	}
	public String getClassi_Id_No() {
		return Classi_Id_No;
	}
	public void setClassi_Id_No(String classi_Id_No) {
		Classi_Id_No = classi_Id_No;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getPort_of_registry() {
		return port_of_registry;
	}
	public void setPort_of_registry(String port_of_registry) {
		this.port_of_registry = port_of_registry;
	}
	public String getYear_built() {
		return year_built;
	}
	public void setYear_built(String year_built) {
		this.year_built = year_built;
	}
	public String getHull_type() {
		return hull_type;
	}
	public void setHull_type(String hull_type) {
		this.hull_type = hull_type;
	}
	public BigDecimal getLength_overall() {
		return length_overall;
	}
	public void setLength_overall(BigDecimal length_overall) {
		this.length_overall = length_overall;
	}
	public BigDecimal getBreadth_MLD() {
		return breadth_MLD;
	}
	public void setBreadth_MLD(BigDecimal breadth_MLD) {
		this.breadth_MLD = breadth_MLD;
	}
	public BigDecimal getDepth() {
		return depth;
	}
	public void setDepth(BigDecimal depth) {
		this.depth = depth;
	}
	public BigDecimal getSummer_draft_M() {
		return summer_draft_M;
	}
	public void setSummer_draft_M(BigDecimal summer_draft_M) {
		this.summer_draft_M = summer_draft_M;
	}
	public String getSummer_DWT_MT() {
		return summer_DWT_MT;
	}
	public void setSummer_DWT_MT(String summer_DWT_MT) {
		this.summer_DWT_MT = summer_DWT_MT;
	}
	public String getInternational_GRT() {
		return international_GRT;
	}
	public void setInternational_GRT(String international_GRT) {
		this.international_GRT = international_GRT;
	}
	public String getInternational_NRT() {
		return international_NRT;
	}
	public void setInternational_NRT(String international_NRT) {
		this.international_NRT = international_NRT;
	}
	public long getLife_boat_cap() {
		return life_boat_cap;
	}
	public void setLife_boat_cap(long life_boat_cap) {
		this.life_boat_cap = life_boat_cap;
	}
	public String getV_short_name() {
		return v_short_name;
	}
	public void setV_short_name(String v_short_name) {
		this.v_short_name = v_short_name;
	}
	public String getAccount_code_old() {
		return account_code_old;
	}
	public void setAccount_code_old(String account_code_old) {
		this.account_code_old = account_code_old;
	}
	public String getAccount_code_new() {
		return account_code_new;
	}
	public void setAccount_code_new(String account_code_new) {
		this.account_code_new = account_code_new;
	}
	public String getTel_fac_code() {
		return tel_fac_code;
	}
	public void setTel_fac_code(String tel_fac_code) {
		this.tel_fac_code = tel_fac_code;
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
	public Long getKeel_laying_date() {
		return keel_laying_date;
	}
	public void setKeel_laying_date(Long keel_laying_date) {
		this.keel_laying_date = keel_laying_date;
	}
	public Long getVessel_delivery_date() {
		return vessel_delivery_date;
	}
	public void setVessel_delivery_date(Long vessel_delivery_date) {
		this.vessel_delivery_date = vessel_delivery_date;
	}
	public BigDecimal getMaxEmailSizeInMB() {
		return maxEmailSizeInMB;
	}
	public void setMaxEmailSizeInMB(BigDecimal maxEmailSizeInMB) {
		this.maxEmailSizeInMB = maxEmailSizeInMB;
	}
	public BigDecimal getDailyDataLimitInMB() {
		return dailyDataLimitInMB;
	}
	public void setDailyDataLimitInMB(BigDecimal dailyDataLimitInMB) {
		this.dailyDataLimitInMB = dailyDataLimitInMB;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPhoneNo1() {
		return phoneNo1;
	}
	public void setPhoneNo1(String phoneNo1) {
		this.phoneNo1 = phoneNo1;
	}
	public String getPhoneNo2() {
		return phoneNo2;
	}
	public void setPhoneNo2(String phoneNo2) {
		this.phoneNo2 = phoneNo2;
	}
	public String getAssignedUsers() {
		return assignedUsers;
	}
	public void setAssignedUsers(String assignedUsers) {
		this.assignedUsers = assignedUsers;
	}
	
	public List<ShipAttachment> getShipAttachmentList() {
		return shipAttachmentList;
	}
	public void setShipAttachmentList(List<ShipAttachment> shipAttachmentList) {
		this.shipAttachmentList = shipAttachmentList;
	}
	public String getVesselTypeDesc() {
		return vesselTypeDesc;
	}
	public void setVesselTypeDesc(String vesselTypeDesc) {
		this.vesselTypeDesc = vesselTypeDesc;
	}
	
}
