package com.drydock.dto;

import java.util.List;

public class UserInfoDTO {

	private Long userId;
	private String identifyString;
	private String userFirstName;
	private String userCode;
	private List userRoleList;
	private String currentLocale;
	private String organizationName;
	private long orgId;
	private long deptId;
	private String token;
	private String userType;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getIdentifyString() {
		return identifyString;
	}
	public void setIdentifyString(String identifyString) {
		this.identifyString = identifyString;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public List getUserRoleList() {
		return userRoleList;
	}
	public void setUserRoleList(List userRoleList) {
		this.userRoleList = userRoleList;
	}
	public String getCurrentLocale() {
		return currentLocale;
	}
	public void setCurrentLocale(String currentLocale) {
		this.currentLocale = currentLocale;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
