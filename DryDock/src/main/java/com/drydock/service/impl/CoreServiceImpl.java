package com.drydock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.dto.UserInfoDTO;
import com.drydock.entity.ApplicationVesselDocType;
import com.drydock.entity.CurrencyMaster;
import com.drydock.entity.Department;
import com.drydock.entity.Designation;
import com.drydock.entity.DockyardMaster;
import com.drydock.entity.Function;
import com.drydock.entity.Organization;
import com.drydock.entity.OrganizationVesselDocType;
import com.drydock.entity.Role;
import com.drydock.entity.Shipmaster;
import com.drydock.entity.UserCurrentShip;
import com.drydock.entity.UserDetail;
import com.drydock.entity.VesselTypeMaster;
import com.drydock.repository.CoreRepository;
import com.drydock.service.CoreService;

@Component
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class CoreServiceImpl implements CoreService{

	@Autowired
	CoreRepository coreRepository;
	
	/**
	 * Arnabi Starts
	 */
	@Override
	public List<Organization> loadOrganizationList(BasicInfoDTO info) throws Exception {

		try{
			 return coreRepository.loadOrganizationList(info);
		}
		catch(Exception e){
			throw e;
		}
	
	
	}
	
	@Override
	public Organization loadOrganization(Long orgId,BasicInfoDTO info) throws Exception {
		try{
			 return coreRepository.loadOrganization(orgId,info);
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void saveOrganization(Organization org,BasicInfoDTO info) throws Exception {

		try{
			coreRepository.saveOrganization(org,info);
			
		}

		catch(Exception e){
			throw e;
		}
		
		}

	@Override
	public List<Department> loadDepartmentList(BasicInfoDTO info)
			throws Exception {

		try{
			 return coreRepository.loadDepartmentList(info);
		}
		catch(Exception e){
			throw e;
		}
	
	
	}

	@Override
	public Department loadDepartment(Long deptId,BasicInfoDTO info) throws Exception {
		try{
			 return coreRepository.loadDepartment(deptId,info);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void saveDepartment(Department dept, BasicInfoDTO info)
			throws Exception {
		try{
			  coreRepository.saveDepartment(dept,info);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public List<Role> loadRoleList(BasicInfoDTO info) throws Exception {
		try{
			 return coreRepository.loadRoleList(info);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void addRole(Role role, BasicInfoDTO info) throws Exception {
		try{
			 coreRepository.addRole(role, info);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public void assignRoleToDepartment(Long roleId, List<Long> deptIdList,
			BasicInfoDTO info) throws Exception{
		try{
			 coreRepository.assignRoleToDepartment(roleId,deptIdList,info);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public void assignDepartmentToRole(Long deptId, List<Long> roleIdList,BasicInfoDTO info) throws Exception{
		try{
			 coreRepository.assignDepartmentToRole(deptId,roleIdList,info);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public List<Function> loadFunctionList(BasicInfoDTO info) throws Exception {
		try{
			return coreRepository.loadFunctionList(info);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public void assignFunctionToRole(Long roleId, List<Long> functionIdList,
			BasicInfoDTO info) throws Exception{
		try{
			 coreRepository.assignFunctionToRole(roleId,functionIdList,info);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public Role loadRole(Long roleId, BasicInfoDTO info) throws Exception{
		try{
			 return coreRepository.loadRole(roleId,info);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public void deleteRole(Long roleId, BasicInfoDTO info) throws Exception{
		try{
			 coreRepository.deleteRole(roleId,info);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public List<Function> loadFunctionListUserWise(BasicInfoDTO info) throws Exception{
		return coreRepository.loadFunctionListUserWise(info);
	}
	
	/**
	 * Arnabi Ends
	 */
	
	/**
	 * Rupak Starts
	 */
	@Override
	public UserDetail getUser(String userCode, String passcode) throws Exception {
		return coreRepository.getUser(userCode, passcode);
	}
	@Override
	public UserDetail getUserbyUserId(long user_id, BasicInfoDTO info) throws Exception {
		return coreRepository.getUserbyUserId(user_id, info);
	}
	
	@Override
	public Object loadSAUser(Long orgId, BasicInfoDTO info) throws Exception{
		UserDetail userdetail = (UserDetail) coreRepository.loadSAUser(orgId, info);
		return coreRepository.getUserbyUserId(userdetail.getUser_id(), info);
	}

	@Override
	public void saveUser(UserDetail userDetails, BasicInfoDTO info) throws Exception {
		coreRepository.saveUser(userDetails, info);
	}
	
	@Override
	public List<UserDetail> loadUserList(BasicInfoDTO info) throws Exception {
		return coreRepository.loadUserList(info);
	}
	
	@Override
	public Object loadUserUnderLoggedInUser(BasicInfoDTO info) throws Exception {
		return coreRepository.loadUserUnderLoggedInUser(info);
	}
	
	@Override
	public Object loadAllUserUnderLoggedInUser(BasicInfoDTO info) throws Exception {
		return coreRepository.loadAllUserUnderLoggedInUser(info);
	}
	
	@Override
	public Object checkLoginCodeExists(String userCode, BasicInfoDTO info) throws Exception {
		return coreRepository.checkLoginCodeExists(userCode, info);
	}
	
	@Override
	public void forgotPassword(UserDetail userDetails, String password, BasicInfoDTO info) throws Exception {
		coreRepository.userUpdatePassword(userDetails, password, info);
	}
	
	@Override
	public Object loadDesignationList(BasicInfoDTO info) throws Exception {
		return coreRepository.loadDesignationList(info);
	}
	
	@Override
	public void saveDesignation(Designation designation, BasicInfoDTO info) throws Exception{
		coreRepository.saveDesignation(designation, info); 
	}
	
	@Override
	public Object loadDesignationById(long designationId, BasicInfoDTO info) throws Exception {
		return coreRepository.loadDesignationById(designationId, info);
	}
	
	@Override
	public Object loadShipListForCreation(BasicInfoDTO info) throws Exception {
		return coreRepository.loadShipListForCreation(info);
	}
	
	@Override
	public Object loadShipDetailsById(long shipId, BasicInfoDTO info) throws Exception {
		return coreRepository.loadShipDetailsById(shipId, info);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor=Exception.class)
	public void saveShip(Shipmaster shipmaster, BasicInfoDTO info) throws Exception{
		coreRepository.saveShip(shipmaster, info); 
	}

	@Override
	public Object loadShipListForAssignment(BasicInfoDTO info) throws Exception {
		return coreRepository.loadShipListForAssignment(info);
	}

	@Override
	public void addUserToShip(long shipId, List<Long> userIdList, BasicInfoDTO info) throws Exception {
		coreRepository.addUserToShip(shipId, userIdList, info);
	}
	
	@Override
	public void removeUserFromShip(long shipId, List<Long> userIdList, BasicInfoDTO info) throws Exception {
		coreRepository.removeUserFromShip(shipId, userIdList, info);
	}
	
	@Override
	public void saveUserShipMapping(long shipId, List<Long> removedUserIdList, List<Long> addedUserIdList, BasicInfoDTO info) throws Exception {
		coreRepository.removeUserFromShip(shipId, removedUserIdList, info);
		coreRepository.addUserToShip(shipId, addedUserIdList, info);
	}

	@Override
	public Object loadUserAssignedToShip(long shipId, BasicInfoDTO info) throws Exception{
		return coreRepository.loadUserAssignedToShip(shipId, info);
	}
	
	@Override
	public void saveUserList(List<UserDetail> userDetailsList, boolean excelUploadFlag, BasicInfoDTO info) throws Exception {
		for (UserDetail userDetail : userDetailsList) {
			if(excelUploadFlag){
				userDetail.setCurrentReportTo(userDetail.getReportingToUserObject().getUser_id());
			}
			coreRepository.saveUser(userDetail, info);
		}
	}
	
	@Override
	public List<String> loadAllUserCodeList(BasicInfoDTO info) throws Exception {
		return coreRepository.loadAllUserCodeList(info);
	}
	
	@Override
	public void saveDepartmentList(List<Department> departmentList, BasicInfoDTO info) throws Exception {
		for (Department department : departmentList) {
			coreRepository.saveDepartment(department, info);
		}
	}

	@Override
	public Object getMasterVesselType(String vesselType) throws Exception {
		return coreRepository.getMasterVesselType(vesselType);
	}
	
	@Override
	public List<VesselTypeMaster> getMasterVesselTypeList() throws Exception {
		return coreRepository.getMasterVesselTypeList();
	}

	@Override
	public Long getorgIdFromorgName(String orgName) throws Exception {
		return coreRepository.getorgIdFromorgName(orgName);
	}

	@Override
	public Object loadDockyardById(long dockyardId, BasicInfoDTO info)
			throws Exception {
		return coreRepository.loadDockyardById(dockyardId, info);
	}

	@Override
	public Object loadDockyardList(BasicInfoDTO info) throws Exception {
		return coreRepository.loadDockyardList(info);
	}

	@Override
	public void saveDockyard(DockyardMaster dockyard, BasicInfoDTO info)
			throws Exception {
		coreRepository.saveDockyard(dockyard, info);
	}

	@Override
	public void updateUserCurrentShipEntry(long shipId, BasicInfoDTO info)
			throws Exception {
		coreRepository.updateUserCurrentShipEntry(shipId, info);
	}

	@Override
	public UserCurrentShip loadUserCurrentShip(Long user_id, BasicInfoDTO info)
			throws Exception {
		return coreRepository.loadUserCurrentShip(user_id, info);
	}

	@Override
	public List<ApplicationVesselDocType> loadApplicationVesselDocTypeList(
			BasicInfoDTO info) throws Exception {
		return coreRepository.loadApplicationVesselDocTypeList(info);
	}

	@Override
	public void saveApplicationVesselDocTypeList(
			List<ApplicationVesselDocType> applicationVesselDocTypeList,
			BasicInfoDTO info) throws Exception {
		coreRepository.saveApplicationVesselDocTypeList(applicationVesselDocTypeList, info);
		
	}

	@Override
	public List<OrganizationVesselDocType> loadOrganizationVesselDocTypeList(
			BasicInfoDTO info) throws Exception {
		return coreRepository.loadOrganizationVesselDocTypeList(info);
	}

	@Override
	public void saveOrganizationVesselDocTypeList(
			List<OrganizationVesselDocType> organizationVesselDocTypeList,
			BasicInfoDTO info) throws Exception {
		coreRepository.saveOrganizationVesselDocTypeList(organizationVesselDocTypeList, info);
		
	}

	@Override
	public List<CurrencyMaster> loadCurrencyList(BasicInfoDTO info)
			throws Exception {
		return coreRepository.loadCurrencyList(info);
	}
	
	/**
	 * Rupak Ends
	 */
	
	
}
