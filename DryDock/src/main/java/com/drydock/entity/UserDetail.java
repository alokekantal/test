package com.drydock.entity;

import java.util.Date;
import java.util.List;

public class UserDetail {
	private long user_id;
	private String userCode;
	private String passcode;
	private String firstname;
	private String lastname;
	private String address;
	private String phonenumber;
	private String personalMailid;
	private String email1;
	private String email2;
	private String phoneNo1;
	private String phoneNo2;
	private long currentReportTo;
	private String useruid;
	private String uidtype;
	private String imagePath;
	private long orgId;
	private long deptId;
	private long designationId;
	private long createid;
	private Date createdate;
	private long updateid;
	private Date updatedate;
	private Integer isactive;
	private List<Long> roleList;
	private String userType;
	private long shipid;
	private String reportingToName;
	private String roleListStr;
	
	private UserDetail reportingToUserObject;
	
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPersonalMailid() {
		return personalMailid;
	}
	public void setPersonalMailid(String personalMailid) {
		this.personalMailid = personalMailid;
	}
	public long getCurrentReportTo() {
		return currentReportTo;
	}
	public void setCurrentReportTo(long currentReportTo) {
		this.currentReportTo = currentReportTo;
	}
	public String getUseruid() {
		return useruid;
	}
	public void setUseruid(String useruid) {
		this.useruid = useruid;
	}
	public String getUidtype() {
		return uidtype;
	}
	public void setUidtype(String uidtype) {
		this.uidtype = uidtype;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public long getDeptId() {
		return deptId;
	}
	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}
	public long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(long designationId) {
		this.designationId = designationId;
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
	public List<Long> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Long> roleList) {
		this.roleList = roleList;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public long getShipid() {
		return shipid;
	}
	public void setShipid(long shipid) {
		this.shipid = shipid;
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
	public String getReportingToName() {
		return reportingToName;
	}
	public void setReportingToName(String reportingToName) {
		this.reportingToName = reportingToName;
	}
	public String getRoleListStr() {
		return roleListStr;
	}
	public void setRoleListStr(String roleListStr) {
		this.roleListStr = roleListStr;
	}
	public UserDetail getReportingToUserObject() {
		return reportingToUserObject;
	}
	public void setReportingToUserObject(UserDetail reportingToUserObject) {
		this.reportingToUserObject = reportingToUserObject;
	}
	
	
	
}
