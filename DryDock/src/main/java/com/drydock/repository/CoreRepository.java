package com.drydock.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.drydock.dto.BasicInfoDTO;
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

@Component
public interface CoreRepository {

	
	/**
	 * Arnabi Starts
	 */
	
	public List<Organization> loadOrganizationList(BasicInfoDTO info) throws Exception;

	public Organization loadOrganization(Long orgId,BasicInfoDTO info) throws Exception;

	public void saveOrganization(Organization org,BasicInfoDTO info) throws Exception;

	public List<Department> loadDepartmentList(BasicInfoDTO info) throws Exception;

	public Department loadDepartment(Long deptId,BasicInfoDTO info) throws Exception;
	
	public Long saveDepartment(Department dept, BasicInfoDTO info) throws Exception;
	
	public List<Role> loadRoleList(BasicInfoDTO info) throws Exception;
	public Long addRole(Role role,  BasicInfoDTO info) throws Exception;
	
	public void assignRoleToDepartment(Long roleId, List<Long> deptIdList,
			BasicInfoDTO info) throws Exception;
	
	public void assignDepartmentToRole(Long deptId, List<Long> roleIdList,BasicInfoDTO info) throws Exception;
	
	public List<Function> loadFunctionList(BasicInfoDTO info) throws Exception;
	
	public List<Function> loadFunctionListUserWise(BasicInfoDTO info) throws Exception;
	
	public void assignFunctionToRole(Long roleId, List<Long> functionIdList,
			BasicInfoDTO info) throws Exception;
	
	public Role loadRole(Long roleId, BasicInfoDTO info) throws Exception;
	
	public void deleteRole(Long roleId, BasicInfoDTO info) throws Exception;
	
	/**
	 * Arnabi Ends
	 */
	
	/**
	 * Rupak Starts
	 */
	public UserDetail getUser(String userCode, String passcode) throws Exception;

	public void saveUser(UserDetail userDetails, BasicInfoDTO info) throws Exception;

	public UserDetail getUserbyUserId(long user_id, BasicInfoDTO info) throws Exception;

	public List<UserDetail> loadUserList(BasicInfoDTO info) throws Exception;

	public Object checkLoginCodeExists(String userCode, BasicInfoDTO info) throws Exception;

	public Object loadDesignationList(BasicInfoDTO info) throws Exception;

	public void saveDesignation(Designation designation, BasicInfoDTO info) throws Exception;

	public Object loadDesignationById(long designationId, BasicInfoDTO info) throws Exception;

	public Object loadShipListForCreation(BasicInfoDTO info) throws Exception;

	public Object loadShipDetailsById(long shipId, BasicInfoDTO info) throws Exception;

	public void saveShip(Shipmaster shipmaster, BasicInfoDTO info) throws Exception;

	public Object loadShipListForAssignment(BasicInfoDTO info) throws Exception;

	public void addUserToShip(long shipId, List<Long> userIdList, BasicInfoDTO info) throws Exception;

	public void removeUserFromShip(long shipId, List<Long> userIdList, BasicInfoDTO info) throws Exception;

	public Object loadUserUnderLoggedInUser(BasicInfoDTO info) throws Exception;

	public Object loadUserAssignedToShip(long shipId, BasicInfoDTO info) throws Exception;

	public void userUpdatePassword(UserDetail userDetails, String password, BasicInfoDTO info) throws Exception;

	public List<String> loadAllUserCodeList(BasicInfoDTO info) throws Exception;

	public Object getMasterVesselType(String vesselType) throws Exception;
	
	public List<VesselTypeMaster> getMasterVesselTypeList() throws Exception;

	public Long getorgIdFromorgName(String orgName) throws Exception;

	public Object loadSAUser(Long orgId, BasicInfoDTO info) throws Exception;

	public Object loadDockyardById(long dockyardId, BasicInfoDTO info) throws Exception;

	public Object loadDockyardList(BasicInfoDTO info) throws Exception;

	public void saveDockyard(DockyardMaster dockyard, BasicInfoDTO info) throws Exception;

	public void updateUserCurrentShipEntry(long shipId, BasicInfoDTO info) throws Exception;

	public UserCurrentShip loadUserCurrentShip(Long userId, BasicInfoDTO info) throws Exception;

	public Object loadAllUserUnderLoggedInUser(BasicInfoDTO info) throws Exception;

	public List<ApplicationVesselDocType> loadApplicationVesselDocTypeList(
			BasicInfoDTO info) throws Exception;

	public void saveApplicationVesselDocTypeList(
			List<ApplicationVesselDocType> applicationVesselDocTypeList,
			BasicInfoDTO info) throws Exception;

	public List<OrganizationVesselDocType> loadOrganizationVesselDocTypeList(
			BasicInfoDTO info) throws Exception;

	public void saveOrganizationVesselDocTypeList(
			List<OrganizationVesselDocType> organizationVesselDocTypeList,
			BasicInfoDTO info) throws Exception;

	public List<CurrencyMaster> loadCurrencyList(BasicInfoDTO info) throws Exception;

	Object getMasterVesselTypeById(Long vesselType) throws Exception;

	

	

		
	
	/**
	 * Rupak Ends
	 */

	
	
}
