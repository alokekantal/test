package com.drydock.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.entity.ApplicationComponent;
import com.drydock.entity.ApplicationJob;
import com.drydock.entity.ApplicationJobMaterialDetails;
import com.drydock.entity.ApplicationVesselDocType;
import com.drydock.entity.CurrencyMaster;
import com.drydock.entity.Department;
import com.drydock.entity.DeptRole;
import com.drydock.entity.Designation;
import com.drydock.entity.DockyardMaster;
import com.drydock.entity.Function;
import com.drydock.entity.Organization;
import com.drydock.entity.OrganizationComponent;
import com.drydock.entity.OrganizationJob;
import com.drydock.entity.OrganizationJobMaterialDetails;
import com.drydock.entity.OrganizationVesselDocType;
import com.drydock.entity.Project;
import com.drydock.entity.Role;
import com.drydock.entity.RoleFunction;
import com.drydock.entity.ShipAttachment;
import com.drydock.entity.Shipmaster;
import com.drydock.entity.UserCurrentShip;
import com.drydock.entity.UserDetail;
import com.drydock.entity.UserRole;
import com.drydock.entity.UserShip;
import com.drydock.entity.VesselTypeMaster;
import com.drydock.mapper.ApplicationVesselDocTypeMapper;
import com.drydock.mapper.DepartmentMapper;
import com.drydock.mapper.DeptRoleMapper;
import com.drydock.mapper.DesignationMapper;
import com.drydock.mapper.DockyardMasterMapper;
import com.drydock.mapper.FunctionMapper;
import com.drydock.mapper.OrganizationMapper;
import com.drydock.mapper.OrganizationVesselDocTypeMapper;
import com.drydock.mapper.RoleFunctionMapper;
import com.drydock.mapper.RoleMapper;
import com.drydock.mapper.ShipAttachmentMapper;
import com.drydock.mapper.ShipmasterMapper;
import com.drydock.mapper.UserCurrentShipMapper;
import com.drydock.mapper.UserDetailMapper;
import com.drydock.mapper.UserRoleMapper;
import com.drydock.mapper.UserShipMapper;
import com.drydock.mapper.VesselTypeMasterMapper;
import com.drydock.mapper.CurrencyMasterMapper;
import com.drydock.repository.CoreRepository;
import com.drydock.repository.SequenceValueGenerator;
import com.drydock.util.DrydockConstant;
import com.drydock.util.DrydockQueryConstant;
import com.drydock.util.DrydockWebUtil;

@Component
public class CoreRepositoryImpl implements CoreRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	SequenceValueGenerator sequenceValueGenerator;
	
	@Autowired
	ProjectRepositoryImpl projectRepositoryImpl;
	
	/**
	 * Arnabi Starts
	 */
	@Override
	public List<Organization> loadOrganizationList(BasicInfoDTO info) throws Exception {
		List<Organization> orgList= new ArrayList<Organization>();
		try{
			orgList =(List<Organization>) namedParameterJdbcTemplate.query("select * from organization", new OrganizationMapper());
			
		}
		catch(Exception e){
			
		}
		return orgList;
		
		
		
	}
	

	@Override
	public Organization loadOrganization(Long orgId,BasicInfoDTO info) throws Exception {

		Organization org= new Organization();
		try{
			
			Map parameters = new HashMap();
			parameters.put("orgId", orgId);
			org =(Organization) namedParameterJdbcTemplate.queryForObject("select * from organization where org_id=:orgId",parameters, new OrganizationMapper());
			
		}
		catch(Exception e){
			
		}
		return org;
				
		
	
	}


	@Override
	public void saveOrganization(Organization organization,BasicInfoDTO info) throws Exception {
		boolean freshOrganization = false;
		try{
			String insertSql="";
			//			Map parameters = new HashMap();
			//			GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
			MapSqlParameterSource parameters = new MapSqlParameterSource();

			if(organization.getOrg_id()==0)
			{
				freshOrganization = true;
				Long orgidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.ORGANIZATION_SEQ);

				insertSql = "insert into organization (org_id, org_name, org_mail, address, phoneno, faxno, org_reg_no, create_id, create_date, update_id, update_date, is_active,org_desc,email1,email2,phoneno1,phoneno2) values (:org_id, :orgName, :orgMail, :address, :phoneNo, :faxNo, :orgRegNumber, :createid, :createdate, :updateid, :updatedate, :isactive,:orgDesc,:email1,:email2,:phoneno1,:phoneno2)";
				parameters.addValue("org_id", orgidSeq);
				parameters.addValue("createid", info.getUserId());
				parameters.addValue("createdate", new Date());
				organization.setOrg_id(orgidSeq);
			}
			else{
				insertSql = "update organization set  org_name= :orgName, org_mail =:orgMail, address = :address,phoneno= :phoneNo, faxno=:faxNo, org_reg_no=:orgRegNumber, create_id=:createid,create_date= :createdate, update_id=:updateid, update_date = :updatedate,is_active= :isactive,org_desc=:orgDesc,email1=:email1,email2=:email2,phoneno1=:phoneno1,phoneno2=:phoneno2 where org_id=:org_id";
				parameters.addValue("org_id", organization.getOrg_id());

				parameters.addValue("createid", organization.getCreateid());

				parameters.addValue("createdate", organization.getCreatedate());
			}
			parameters.addValue("orgName", organization.getOrgName());
			parameters.addValue("orgMail", organization.getOrgMail());
			parameters.addValue("address", organization.getAddress());
			parameters.addValue("phoneNo", organization.getPhoneNo());
			parameters.addValue("faxNo", organization.getFaxNo());
			parameters.addValue("orgRegNumber", organization.getOrgRegNumber());
			parameters.addValue("orgDesc", organization.getOrgDesc());
			parameters.addValue("updateid", info.getUserId());
			parameters.addValue("updatedate", new Date());
			parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.addValue("email1", organization.getEmail1());
			parameters.addValue("email2", organization.getEmail2());
			parameters.addValue("phoneno1", organization.getPhoneNo1());
			parameters.addValue("phoneno2", organization.getPhoneNo2());
			namedParameterJdbcTemplate.update(insertSql, parameters);

			//create Super Admin
			if(freshOrganization && null!=organization.getUserDetail()){

				UserDetail user = organization.getUserDetail();
				user.setOrgId(organization.getOrg_id());
				Department dept = new Department();
				dept.setDept_id(0);
				dept.setDeptMail(user.getPersonalMailid());
				dept.setDeptName(DrydockConstant.DEFAULT_DEPT);
				dept.setOrgId(organization.getOrg_id());
				Long id= saveDepartment(dept, info);
				user.setDeptId(id);
				user.setUserType(DrydockConstant.USER_TYPE_OFFSHORE);
				List<Long> deptIdList = new ArrayList<Long>();
				if(id!=null)
					deptIdList.add(id);
				List<Long> funcIdList = new ArrayList<>();
				//funcIdList.add(1l);
				funcIdList.add(3l);
				funcIdList.add(4l);
				funcIdList.add(5l);
				funcIdList.add(6l);
				funcIdList.add(7l);
				funcIdList.add(8l);
				funcIdList.add(10l); 
				funcIdList.add(11l); 
				funcIdList.add(12l); 
				funcIdList.add(13l); 
				funcIdList.add(15l);
				funcIdList.add(16l); 
				funcIdList.add(18l); 
				funcIdList.add(19l); 
				Role role =new Role();
				role.setRole_id(0);
				role.setCode(DrydockConstant.SUPER_ADMIN_ROLE_CODE);
				role.setCreatedate(new Date());
				role.setCreateid(info.getUserId());
				role.setDescription(DrydockConstant.SUPER_ADMIN_ROLE_DESC);
				role.setIsactive(DrydockConstant.IS_ACTIVE_FLAG);
				role.setOrgId((long) parameters.getValue("org_id"));
				role.setDeptIdList(deptIdList);
				role.setFuncIdList(funcIdList);
				Long roleid= addRole(role, info);
				List<Long> roleIdList = new ArrayList<Long>();
				roleIdList.add(roleid);
				user.setRoleList(roleIdList);

				Designation designation= new Designation();
				designation.setDesignation_id(0);
				designation.setCode(DrydockConstant.DEFAULT_DESIGNATION_CODE);
				designation.setDesignation(DrydockConstant.DEFAULT_DESIGNATION_DESC);
				designation.setDescription(DrydockConstant.DEFAULT_DESIGNATION_DESC);
				designation.setOrgId(organization.getOrg_id());
				saveDesignation(designation, info);
				user.setDesignationId(designation.getDesignation_id());
				saveUser(user, info);
			}

			if(!freshOrganization && null!=organization.getUserDetail()){
				saveUser(organization.getUserDetail(), info);
			}
			
			if(freshOrganization && organization.getCopyFromOrgId()>0)
			{
				copyDeptRoleFromDiffOrg(organization.getCopyFromOrgId(),organization.getOrg_id(), info);
			}
			
			if(freshOrganization)
			{
				Map<Long, Long> hmpAppCompOrgCompId = new HashMap<>();
				List<ApplicationComponent> appComponentList = projectRepositoryImpl.loadApplicationComponentList(info);
				for (ApplicationComponent applicationComponent : appComponentList) {
					OrganizationComponent orgComponent = new OrganizationComponent();
					orgComponent.setApplicationcomponentid(applicationComponent.getId());
					orgComponent.setCode(applicationComponent.getCode());
					orgComponent.setDescription(applicationComponent.getDescription());
					orgComponent.setParentcode(applicationComponent.getParentcode());
					orgComponent.setOrgid(organization.getOrg_id());
					
					projectRepositoryImpl.saveOrganizationComponent(orgComponent, info);
					hmpAppCompOrgCompId.put(applicationComponent.getId(), orgComponent.getId());
				}
				
				List<ApplicationJob> appJobList = projectRepositoryImpl.loadApplicationJobList(info);
				for (ApplicationJob applicationJob : appJobList) {
					OrganizationJob orgJob = new OrganizationJob();
					
					orgJob.setApplicationjobid(applicationJob.getId());
					orgJob.setShipcomponentid(hmpAppCompOrgCompId.get(applicationJob.getShipcomponentid()));
					orgJob.setDescription(applicationJob.getDescription());
					orgJob.setAccountno(applicationJob.getAccountno());
					orgJob.setSpecification(applicationJob.getSpecification());
					orgJob.setDetailedSpecification(applicationJob.getDetailedSpecification());
					orgJob.setLocation(applicationJob.getLocation());
					orgJob.setTotalArea(applicationJob.getTotalArea());					
					orgJob.setOrgid(organization.getOrg_id());
					
					orgJob.setMake(applicationJob.getMake());
					orgJob.setModel(applicationJob.getModel());
					orgJob.setMakeModelDescription(applicationJob.getMakeModelDescription());
					orgJob.setEquipment(applicationJob.getEquipment());
					orgJob.setJobdate(applicationJob.getJobdate());
					orgJob.setVesselType(applicationJob.getVesselType());
					orgJob.setVesselAge(applicationJob.getVesselAge());
					
					
					projectRepositoryImpl.saveOrganizationJob(orgJob, info);
					
					List<ApplicationJobMaterialDetails> appJobMaterals = projectRepositoryImpl.loadApplicationJobMaterialDetailsList(applicationJob.getId(), info);
					List<OrganizationJobMaterialDetails> orgJobMaterals = new ArrayList<>();
					for (ApplicationJobMaterialDetails applicationJobMaterialDetails : appJobMaterals) {
						OrganizationJobMaterialDetails orgJobMaterial = new OrganizationJobMaterialDetails();
						orgJobMaterial.setMake(applicationJobMaterialDetails.getMake());
						orgJobMaterial.setModel(applicationJobMaterialDetails.getModel());
						orgJobMaterial.setDrawingNo(applicationJobMaterialDetails.getDrawingNo());
						orgJobMaterial.setPartName(applicationJobMaterialDetails.getPartName());
						orgJobMaterial.setPartNo(applicationJobMaterialDetails.getPartNo());
						orgJobMaterial.setQuantity(applicationJobMaterialDetails.getQuantity());
						orgJobMaterial.setRemarks(applicationJobMaterialDetails.getRemarks());
						orgJobMaterial.setUom(applicationJobMaterialDetails.getUom());
						
						orgJobMaterals.add(orgJobMaterial);
					}
					projectRepositoryImpl.saveOrganizationJobMaterialDetailsList(orgJob.getId(), orgJobMaterals, info);
				}
				
				List<ApplicationVesselDocType> applicationVesselDocTypeList = loadApplicationVesselDocTypeList(info);
				List<OrganizationVesselDocType> organizationVesselDocTypeList = new ArrayList<>();
				for (ApplicationVesselDocType applicationVesselDocType : applicationVesselDocTypeList) {
					OrganizationVesselDocType organizationVesselDocType = new OrganizationVesselDocType();
					organizationVesselDocTypeList.add(organizationVesselDocType);
					organizationVesselDocType.setOrgId(organization.getOrg_id());
					organizationVesselDocType.setVesselDocDescription(applicationVesselDocType.getVesselDocDescription());
				}
				saveOrganizationVesselDocTypeList(organizationVesselDocTypeList, info);
			}
			
		}
		catch(Exception e){
			throw e;
		}
		
	}
	
	public void copyDeptRoleFromDiffOrg(Long orgId,Long newOrgId, BasicInfoDTO info)
			throws Exception {

		try{
			List<Department> deptList = new ArrayList<Department>();
			List<DeptRole> deptRoleList= new ArrayList<DeptRole>();
			List<Role> roleList = new ArrayList<Role>();
			Map<String, Object> param = new HashMap<>();
			param.put("orgId", orgId);
			param.put("deptName", DrydockConstant.DEFAULT_DEPT);
			deptList =(List<Department>) namedParameterJdbcTemplate.query("select * from department where orgid=:orgId and dept_name not like :deptName",param, new DepartmentMapper());
			param.remove("deptName");
			param.put("roleCode", DrydockConstant.SUPER_ADMIN_ROLE_CODE);
			roleList =namedParameterJdbcTemplate.query("select * from role where orgid=:orgId and code not like :roleCode",param, new RoleMapper());
			
			Map<Long,Long> newRoleIdMap= new HashMap<Long, Long>();
			if(null!=roleList && !roleList.isEmpty()){
				for (Role role : roleList) {
					Long oldId= role.getRole_id();
					role.setRole_id(0);
					role.setOrgId(newOrgId);
					Long newId=addRole(role, info);
					newRoleIdMap.put(oldId, newId);
				}
				
				List<RoleFunction> roleFuncList =(List<RoleFunction>) namedParameterJdbcTemplate.query("select * from role_function where roleid in (select role_id from role where orgid=:orgId and code not like :roleCode)",param, new RoleFunctionMapper());
				MapSqlParameterSource parameters = new MapSqlParameterSource();

				if(null!=roleFuncList && !roleFuncList.isEmpty())
				{
					for (RoleFunction roleFunc : roleFuncList) {
						Long roleFuncidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.ROLEFUNCTION_SEQ);
						String insertSql = "insert into role_function (id,  roleid, functionid, create_id, create_date, update_id, update_date, is_active) values (:id, :roleid, :functionid, :createid, :createdate, :updateid, :updatedate, :isactive)";
						parameters.addValue("id", roleFuncidSeq);
						parameters.addValue("roleid", newRoleIdMap.get(roleFunc.getRoleId()));
						parameters.addValue("functionid", roleFunc.getFunctionId());
						parameters.addValue("createid", info.getUserId());
						parameters.addValue("createdate", new Date());
						parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);
						parameters.addValue("updateid", info.getUserId());
						parameters.addValue("updatedate", new Date());

						namedParameterJdbcTemplate.update(insertSql, parameters);
					}
				}
			}
			if(null!=deptList && !deptList.isEmpty())
			{
				for (Department department : deptList) {
					department.setDept_id(0);
					department.setOrgId(newOrgId);
					Long deptId = saveDepartment(department, info);


					Map pm = new HashMap();
					pm.put("deptid", deptId);
					deptRoleList =(List<DeptRole>) namedParameterJdbcTemplate.query("select * from department_role where deptid=:deptid",pm, new DeptRoleMapper());
					if(null!=deptRoleList && !deptRoleList.isEmpty()) {
						for (DeptRole deptRole : deptRoleList) {
							MapSqlParameterSource parameters = new MapSqlParameterSource();
							Long roleDeptidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.ROLEDEPT_SEQ);
							String insertSql = "insert into department_role (id, deptid, roleid,  create_id, create_date, update_id, update_date, is_active) values (:id, :deptid, :roleid, :createid, :createdate, :updateid, :updatedate, :isactive)";
							parameters.addValue("id", roleDeptidSeq);
							parameters.addValue("deptid", deptId);
							parameters.addValue("roleid", newRoleIdMap.get(deptRole.getRoleId()));
							parameters.addValue("createid", info.getUserId());
							parameters.addValue("createdate", new Date());
							parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);

							parameters.addValue("updateid", info.getUserId());
							parameters.addValue("updatedate", new Date());

							namedParameterJdbcTemplate.update(insertSql, parameters);
						}
					}
				}
			}
		}
		catch(Exception e){
			throw e;
		}
	}
		


	@Override
	public List<Department> loadDepartmentList(BasicInfoDTO info)
			throws Exception {

		List<Department> deptList = new ArrayList<Department>();
		try{
			
			Map parameters = new HashMap();
			parameters.put("orgId", info.getOrgId());
			parameters.put("defaultDeptName", DrydockConstant.DEFAULT_DEPT);
			deptList =(List<Department>) namedParameterJdbcTemplate.query("select * from department where orgid=:orgId and dept_name not in (:defaultDeptName)",parameters, new DepartmentMapper());
			
		}
		catch(Exception e){
			throw e;
		}
		return deptList;
		
		
		
	
	}


	@Override
	public Department loadDepartment(Long deptId,BasicInfoDTO info) throws Exception {

		Department dept= new Department();
		try{
			
			Map parameters = new HashMap();
			parameters.put("deptId", deptId);
			dept =(Department) namedParameterJdbcTemplate.queryForObject("select * from department where dept_id=:deptId",parameters, new DepartmentMapper());
			
		}
		catch(Exception e){
			
		}
		return dept;
		
	}
	
	@Override
	public Long saveDepartment(Department dept,BasicInfoDTO info) throws Exception {

		Long result = null;
		try{

			String sql="";
			
			//			Map parameters = new HashMap();
			//			GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			if(dept.getDept_id()==0)
			{
				Long deptidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.DEPARTMENT_SEQ);

				sql = "insert into department values (:dept_id, :dept_name, :dept_mail, :orgid, :createid, :createdate, :updateid, :updatedate, :isactive)";
				parameters.addValue("dept_id", deptidSeq);
				parameters.addValue("createid", info.getUserId());
				parameters.addValue("createdate", new Date());
			}
			else{
				sql = "update department set  dept_name= :dept_name, dept_mail =:dept_mail, orgid = :orgid, create_id=:createid,create_date= :createdate, update_id=:updateid, update_date = :updatedate,is_active= :isactive where dept_id=:dept_id";
				parameters.addValue("dept_id", dept.getDept_id());

				parameters.addValue("createid", dept.getCreateid());

				parameters.addValue("createdate", dept.getCreatedate());
			}
			parameters.addValue("dept_name", dept.getDeptName());
			parameters.addValue("dept_mail", dept.getDeptMail());
			if(info.getOrgId()==0){
				parameters.addValue("orgid", dept.getOrgId());
			}else{
				parameters.addValue("orgid", info.getOrgId());
			}
			parameters.addValue("updateid", info.getUserId());
			parameters.addValue("updatedate", new Date());
			parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			namedParameterJdbcTemplate.update(sql, parameters);
			/*namedParameterJdbcTemplate.execute(sql, parameters,new PreparedStatementCallback() {  
				@Override  
				public Object doInPreparedStatement(PreparedStatement ps)  
						throws SQLException, DataAccessException {  
					return ps.executeUpdate();  
				}  
			});*/  
			result=(Long) parameters.getValue("dept_id");
		}
		catch(Exception e){
			throw e;
		}
		
		return result;
	}
	
	@Override
	public List<Role> loadRoleList(BasicInfoDTO info) throws Exception {

		List<Role> roleList = new ArrayList<Role>();
		try{
			
			Map parameters = new HashMap();
			parameters.put("orgId", info.getOrgId());
			parameters.put("superAdminRoleCode", DrydockConstant.SUPER_ADMIN_ROLE_CODE);
			roleList =(List<Role>) namedParameterJdbcTemplate.query("select * from role where orgid=:orgId and code not in (:superAdminRoleCode)",parameters, new RoleMapper());
			
			if(null!=roleList && !roleList.isEmpty())
			{
				for (Role role : roleList) {
					parameters.clear();
					parameters.put("roleId", role.getRole_id());
					List<DeptRole> deptList = (List<DeptRole>) namedParameterJdbcTemplate.query("select * from department_role where roleid=:roleId",parameters, new DeptRoleMapper());
					if(null !=deptList && !deptList.isEmpty())
					{
						List<Long> idList = new ArrayList<Long>();
						for (DeptRole deptRole : deptList) {
							
							idList.add(deptRole.getDeptId());
						}
						role.setDeptIdList(idList);
					}
					
					List<RoleFunction> funcList = (List<RoleFunction>) namedParameterJdbcTemplate.query("select * from ROLE_FUNCTION where roleid=:roleId",parameters, new RoleFunctionMapper());
					if(null !=funcList && !funcList.isEmpty())
					{
						List<Long> idList = new ArrayList<Long>();
						for (RoleFunction roleFunction : funcList) {
							
							idList.add(roleFunction.getFunctionId());
						}
						role.setFuncIdList(idList);
					}
				}
			}
			
		}
		catch(Exception e){
			
		}
		return roleList;
		
	}
	
	
	@Override
	public Long addRole(Role role,BasicInfoDTO info) throws Exception {
		Long roleid = null;
		try{
			
			String insertSql="";
			//			Map parameters = new HashMap();
			//			GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
			
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			if(role.getRole_id()==0)
			{
				Long roleidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.ROLE_SEQ);
				roleid=roleidSeq;
				insertSql = "insert into role (role_id, code, description,  orgid, create_id, create_date, update_id, update_date, is_active) values (:role_id, :code, :description,  :orgid, :createid, :createdate, :updateid, :updatedate, :isactive)";
				parameters.addValue("role_id", roleidSeq);
				parameters.addValue("createid", info.getUserId());
				parameters.addValue("createdate", new Date());
			}
			else{
				insertSql = "update role set  code= :code, description =:description,  orgid = :orgid, create_id=:createid,create_date= :createdate, update_id=:updateid, update_date = :updatedate,is_active= :isactive where role_id=:role_id";
				parameters.addValue("role_id", role.getRole_id());

				parameters.addValue("createid", role.getCreateid());

				parameters.addValue("createdate", role.getCreatedate());
				roleid=role.getRole_id();
			}
			parameters.addValue("code", role.getCode());
			parameters.addValue("description", role.getDescription());
//			parameters.addValue("deptId", role.getDeptId());
			if(info.getOrgId()==0){
				parameters.addValue("orgid", role.getOrgId());
			}else{
				parameters.addValue("orgid", info.getOrgId());
			}
			
			parameters.addValue("updateid", info.getUserId());
			parameters.addValue("updatedate", new Date());
			parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			namedParameterJdbcTemplate.update(insertSql, parameters);
			if(null!=role.getDeptIdList())
				assignRoleToDepartment(roleid, role.getDeptIdList(), info);
			if(null!=role.getFuncIdList())
				assignFunctionToRole(roleid, role.getFuncIdList(), info);

			
		}
		catch(Exception e){
			throw e;
		}
		return roleid;
	}
	
	public void assignRoleToDepartment(Long roleId, List<Long> deptIdList,BasicInfoDTO info) throws Exception{

		try{

			String insertSql="";
			//			Map parameters = new HashMap();
			//			GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			if(null != roleId )
			{
			 Map<Long,DeptRole> dbDeptIdMap= new HashMap<Long, DeptRole>();
				Map pm = new HashMap();
				pm.put("roleId", roleId);
				List<DeptRole> deptRoleList =(List<DeptRole>) namedParameterJdbcTemplate.query("select * from department_role where roleid=:roleId",pm, new DeptRoleMapper());
				if(null!=deptRoleList && !deptRoleList.isEmpty()) {
					for (DeptRole deptRole : deptRoleList) {
						dbDeptIdMap.put(deptRole.getDeptId(),deptRole);
					}
				}
				
				if(null!=deptIdList && !deptIdList.isEmpty())
				{
					for (Long id : deptIdList) {
						if(!dbDeptIdMap.containsKey(id)){
							Long roleDeptidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.ROLEDEPT_SEQ);
							insertSql = "insert into department_role (id, deptid, roleid,  create_id, create_date, update_id, update_date, is_active) values (:id, :deptid, :roleid, :createid, :createdate, :updateid, :updatedate, :isactive)";
							parameters.addValue("id", roleDeptidSeq);
							parameters.addValue("deptid", id);
							parameters.addValue("roleid", roleId);
							parameters.addValue("createid", info.getUserId());
							parameters.addValue("createdate", new Date());
							parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);
						}
						
						else{
							insertSql = "update department_role set  deptid= :deptid, roleid =:roleid,  create_id=:createid,create_date= :createdate, update_id=:updateid, update_date = :updatedate,is_active= :isactive where id=:id";
							DeptRole temp = dbDeptIdMap.get(id);
							parameters.addValue("id", temp.getId());
							parameters.addValue("deptid", temp.getDeptId());
							parameters.addValue("roleid", temp.getRoleId());
							parameters.addValue("createid", temp.getCreateid());

							parameters.addValue("createdate", temp.getCreatedate());
							parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG); //Need to modified
						}
						
						
						parameters.addValue("updateid", info.getUserId());
						parameters.addValue("updatedate", new Date());
						
						namedParameterJdbcTemplate.update(insertSql, parameters);
						dbDeptIdMap.remove(id);
					}
					if(null!=dbDeptIdMap && !dbDeptIdMap.isEmpty())
					{
						Map<String, Object> param = new HashMap<>();
						for (Long deleteId : dbDeptIdMap.keySet()) {
							String delQry= "delete from department_role where deptid=:id and roleid=:roleId"; // in need to be implemented
							param.put("id", deleteId);
							param.put("roleId", roleId);
							namedParameterJdbcTemplate.update(delQry, param);
						}
					}
				}
				
			
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void assignFunctionToRole(Long roleId, List<Long> functionIdList,
			BasicInfoDTO info) throws Exception{

		try{

			String insertSql="";
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			if(null != roleId )
			{
			 Map<Long,RoleFunction> dbFuncId= new HashMap<Long, RoleFunction>();
				Map pm = new HashMap();
				pm.put("roleId", roleId);
				List<RoleFunction> roleFuncList =(List<RoleFunction>) namedParameterJdbcTemplate.query("select * from role_function where roleid=:roleId",pm, new RoleFunctionMapper());
				if(null!=roleFuncList && !roleFuncList.isEmpty()) {
					for (RoleFunction roleFunc : roleFuncList) {
						dbFuncId.put(roleFunc.getFunctionId(),roleFunc);
					}
				}
				
				if(null!=functionIdList && !functionIdList.isEmpty())
				{
					for (Long id : functionIdList) {
						if(!dbFuncId.containsKey(id)){
							Long roleFuncidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.ROLEFUNCTION_SEQ);
							insertSql = "insert into role_function (id,  roleid, functionid, create_id, create_date, update_id, update_date, is_active) values (:id, :roleid, :functionid, :createid, :createdate, :updateid, :updatedate, :isactive)";
							parameters.addValue("id", roleFuncidSeq);
							parameters.addValue("roleid", roleId);
							parameters.addValue("functionid", id);
							parameters.addValue("createid", info.getUserId());
							parameters.addValue("createdate", new Date());
							parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);
						}
						
						else{
							insertSql = "update role_function set  functionid= :functionid, roleid =:roleid, create_id=:createid,create_date= :createdate, update_id=:updateid, update_date = :updatedate,is_active= :isactive where id=:id";
							RoleFunction temp = dbFuncId.get(id);
							parameters.addValue("id", temp.getId());
							parameters.addValue("roleid", temp.getRoleId());
							parameters.addValue("functionid", temp.getFunctionId());
							parameters.addValue("createid", temp.getCreateid());

							parameters.addValue("createdate", temp.getCreatedate());
							parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG); //Need to modified
						}
						
						
						parameters.addValue("updateid", info.getUserId());
						parameters.addValue("updatedate", new Date());
						
						namedParameterJdbcTemplate.update(insertSql, parameters);
						
						dbFuncId.remove(id);
					}
					if(null!=dbFuncId && !dbFuncId.isEmpty())
					{
						Map<String, Object> param = new HashMap<>();
						for (Long deleteId : dbFuncId.keySet()) {
							String delQry= "delete from role_function where functionid=:id and roleid=:roleId"; // in need to be implemented
							param.put("id", deleteId);
							param.put("roleId", roleId);
							namedParameterJdbcTemplate.update(delQry, param);
						}
					}
				}
				
			
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void assignDepartmentToRole(Long deptId, List<Long> roleIdList,BasicInfoDTO info) throws Exception{

		try{

			String insertSql="";
			//			Map parameters = new HashMap();
			//			GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			if(null != deptId )
			{
			 Map<Long,DeptRole> dbRoleId= new HashMap<Long, DeptRole>();
				Map pm = new HashMap();
				pm.put("deptid", deptId);
				List<DeptRole> deptRoleList =(List<DeptRole>) namedParameterJdbcTemplate.query("select * from department_role where deptid=:deptid",pm, new DeptRoleMapper());
				if(null!=deptRoleList && !deptRoleList.isEmpty()) {
					for (DeptRole deptRole : deptRoleList) {
						dbRoleId.put(deptRole.getRoleId(),deptRole);
					}
				}
				
				if(null!=roleIdList && !roleIdList.isEmpty())
				{
					for (Long id : roleIdList) {
						if(!dbRoleId.containsKey(id)){
							Long roleDeptidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.ROLEDEPT_SEQ);
							insertSql = "insert into department_role (id, deptid, roleid,  create_id, create_date, update_id, update_date, is_active) values (:id, :deptid, :roleid, :createid, :createdate, :updateid, :updatedate, :isactive)";
							parameters.addValue("id", roleDeptidSeq);
							parameters.addValue("deptid", id);
							parameters.addValue("roleid", deptId);
							parameters.addValue("createid", info.getUserId());
							parameters.addValue("createdate", new Date());
							parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);
						}
						
						else{
							insertSql = "update department_role set  deptid= :deptid, roleid =:roleid, create_id=:createid,create_date= :createdate, update_id=:updateid, update_date = :updatedate,is_active= :isactive where id=:id";
							DeptRole temp = dbRoleId.get(id);
							parameters.addValue("id", temp.getId());
							parameters.addValue("deptid", temp.getDeptId());
							parameters.addValue("roleid", temp.getRoleId());
							parameters.addValue("createid", temp.getCreateid());

							parameters.addValue("createdate", temp.getCreatedate());
							parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG); //Need to modified
						}
						
						
						parameters.addValue("updateid", info.getUserId());
						parameters.addValue("updatedate", new Date());
						
						namedParameterJdbcTemplate.update(insertSql, parameters);
						
					}
				}
				
			
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Function> loadFunctionList(BasicInfoDTO info) throws Exception {


		List<Function> funcList= new ArrayList<Function>();
		try{
			
			
			funcList =(List<Function>) namedParameterJdbcTemplate.query("select * from function where function_id not in (2,9,14)", new FunctionMapper());
			
		}
		catch(Exception e){
			
		}
		return funcList;
	}
	
	@Override
	public List<Function> loadFunctionListUserWise(BasicInfoDTO info) throws Exception {


		List<Function> funcList= new ArrayList<Function>();
		try{
			Map parameters = new HashMap();
			parameters.put("userId", info.getUserId());
			/*List<UserRole> userRoleList =(List<UserRole>) namedParameterJdbcTemplate.query(DrydockQueryConstant.USER_ROLE_ASSIGNMENT_LIST,parameters, new UserRoleMapper());
			if(null!=userRoleList && !userRoleList.isEmpty()){
				List<Long> roleIdList = new ArrayList<Long>();
				for (UserRole userRole : userRoleList) {
					roleIdList.add(userRole.getRoleId());
				}
				parameters.remove("userId");
				parameters.put("roleId", roleIdList);*/


				funcList =(List<Function>) namedParameterJdbcTemplate.query("select * from function where function_id in (select functionid from role_function where roleid in (SELECT roleid FROM USER_ROLE WHERE USERID=:userId))", parameters, new FunctionMapper());

//			}


		}
		catch(Exception e){
			
		}
		return funcList;
	}
	
	/*public List<Function> loadFunction(BasicInfoDTO info) throws Exception {


		List<Function> funcList= new ArrayList<Function>();
		try{
			
			
			funcList =(List<Function>) namedParameterJdbcTemplate.query("select * from function", new FunctionMapper());
			
		}
		catch(Exception e){
			
		}
		return funcList;
	}*/

	@Override
	public Role loadRole(Long roleId, BasicInfoDTO info) throws Exception{


		Role role= null;
		try{

			Map parameters = new HashMap();
			parameters.put("roleId", roleId);
			role =(Role) namedParameterJdbcTemplate.queryForObject("select * from role where role_id=:roleId",parameters, new RoleMapper());
			if(null != role){
				List<DeptRole> deptList = (List<DeptRole>) namedParameterJdbcTemplate.query("select * from department_role where roleid=:roleId",parameters, new DeptRoleMapper());
				if(null !=deptList && !deptList.isEmpty())
				{
					List<Long> idList = new ArrayList<Long>();
					for (DeptRole deptRole : deptList) {

						idList.add(deptRole.getDeptId());
					}
					role.setDeptIdList(idList);
				}

				List<RoleFunction> funcList= new ArrayList<RoleFunction>();
				funcList =(List<RoleFunction>) namedParameterJdbcTemplate.query("select * from role_function where roleid =:roleId",parameters, new RoleFunctionMapper());

				if(null !=funcList && !funcList.isEmpty())
				{
					List<Long> funcIdList = new ArrayList<Long>();
					for (RoleFunction roleFunction : funcList) {

						funcIdList.add(roleFunction.getFunctionId());
					}
					role.setFuncIdList(funcIdList);
				}
			}
		}
		catch(Exception e){
			throw e;
		}
		return role;



	}
	
	public void deleteRole(Long roleId, BasicInfoDTO info) throws Exception{

		try{

			Map parameters = new HashMap();
			
			String delRoleQry= "delete from role where role_id=:roleId"; 
			parameters.put("roleId", roleId);
			namedParameterJdbcTemplate.update(delRoleQry, parameters);
			
			String delDeptRoleQry= "delete from department_role where id=:roleId"; 
			namedParameterJdbcTemplate.update(delDeptRoleQry, parameters);
			
			String delRoleFuncQry= "delete from role_function where id=:roleId"; 
			namedParameterJdbcTemplate.update(delRoleFuncQry, parameters);
			
			String delRoleUserQry= "delete from user_role where roleid=:roleId"; 
			namedParameterJdbcTemplate.update(delRoleUserQry, parameters);

			
		}
		catch(Exception e){
			throw e;
		}

	}
	

	/**
	 * Arnabi Ends
	 */
	
	/**
	 * Rupak Starts
	 */

	@Override
	public UserDetail getUserbyUserId(long user_id, BasicInfoDTO info) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("user_id", user_id);
		UserDetail ud =  (UserDetail) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.USER_SELECT_BY_USER_ID, parameters, new UserDetailMapper());
		parameters.clear();
		parameters.put("userId", user_id);
		List<UserRole> userRoleList =(List<UserRole>) namedParameterJdbcTemplate.query(DrydockQueryConstant.USER_ROLE_ASSIGNMENT_LIST,parameters, new UserRoleMapper());
		List<Long> existingRoleList = new ArrayList<>();
		if(null!=userRoleList && !userRoleList.isEmpty()){
			for (UserRole userRole : userRoleList) {
				existingRoleList.add(userRole.getRoleId());
			}
		}
		ud.setRoleList(existingRoleList);
		return ud;
	}

	@Override
	public UserDetail getUser(String userCode, String passcode) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userCode", userCode.toUpperCase());
		parameters.put("passcode", DrydockWebUtil.encryptData(userCode.toUpperCase(), passcode));
		return (UserDetail) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.USER_SELECT_BY_USERCODE_PASSWORD, parameters, new UserDetailMapper());
	}

	@Override
	public Object loadSAUser(Long orgId, BasicInfoDTO info) throws Exception{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("superAdminRoleCode", DrydockConstant.SUPER_ADMIN_ROLE_CODE);
		parameters.put("orgId", orgId);
		return (UserDetail) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.SA_USER_SELECT_BY_ORGID, parameters, new UserDetailMapper());
		
	}
	@Override
	public void saveUser(UserDetail userDetails, BasicInfoDTO info) throws Exception {

		try{
			String sql=null;
			Map<String, Object> parameters = new HashMap<>();
			if(userDetails.getUser_id()>0){
				sql=DrydockQueryConstant.USER_UPDATE;
			}else{
				userDetails.setUser_id(sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.USER_SEQ));
				sql=DrydockQueryConstant.USER_INSERT;
				parameters.put("userType", userDetails.getUserType());
				parameters.put("shipid", userDetails.getShipid());
				parameters.put("userCode", userDetails.getUserCode().toUpperCase());
				parameters.put("passcode", DrydockWebUtil.encryptData(userDetails.getUserCode().toUpperCase(), userDetails.getPasscode()));
				if(info.getOrgId()==0){
					parameters.put("orgId", userDetails.getOrgId());
				}else{
					parameters.put("orgId", info.getOrgId());
				}
				parameters.put("createid", info.getUserId());
				parameters.put("createdate", new Date());
				parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
				
			}

			parameters.put("user_id", userDetails.getUser_id()); //create sequence
			parameters.put("firstname", userDetails.getFirstname());
			parameters.put("lastname", userDetails.getLastname());
			parameters.put("address", userDetails.getAddress());
			parameters.put("phonenumber", userDetails.getPhonenumber());
			parameters.put("personalMailid", userDetails.getPersonalMailid());
			parameters.put("currentReportTo", userDetails.getCurrentReportTo());
			parameters.put("useruid", userDetails.getUseruid());
			parameters.put("uidtype", userDetails.getUidtype());
			parameters.put("imagePath", userDetails.getImagePath());
			parameters.put("deptId", userDetails.getDeptId());
			parameters.put("designationId", userDetails.getDesignationId());
			parameters.put("updateid", info.getUserId());
			parameters.put("updatedate", new Date());
			parameters.put("email1", userDetails.getEmail1());
			parameters.put("email2", userDetails.getEmail2());
			parameters.put("phoneNo1", userDetails.getPhoneNo1());
			parameters.put("phoneNo2", userDetails.getPhoneNo2());

			namedParameterJdbcTemplate.execute(sql, parameters,new PreparedStatementCallback() {  
				@Override  
				public Object doInPreparedStatement(PreparedStatement ps)  
						throws SQLException, DataAccessException {  
					return ps.executeUpdate();  
				}  
			});  


			parameters.clear();
			parameters.put("userId", userDetails.getUser_id());
			List<UserRole> userRoleList =(List<UserRole>) namedParameterJdbcTemplate.query(DrydockQueryConstant.USER_ROLE_ASSIGNMENT_LIST,parameters, new UserRoleMapper());
			Map<Long, UserRole> hmpExistingList = new HashMap<>();
			if(null!=userRoleList && !userRoleList.isEmpty()){
				for (UserRole userRole : userRoleList) {
					hmpExistingList.put(userRole.getRoleId(), userRole);
				}
			}

			for (Long roleId : userDetails.getRoleList()) {
				if(hmpExistingList.containsKey(roleId)){
					hmpExistingList.remove(roleId);
				}else{
					Long userRoleId = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.USER_ROLE_SEQ);
					String insertSql=DrydockQueryConstant.ROLE_USER_MAPPING_INSERT;
					parameters.put("id", userRoleId); //create sequence
					parameters.put("roleId", roleId);
					parameters.put("createid", info.getUserId());
					parameters.put("createdate", new Date());
					parameters.put("updateid", info.getUserId());
					parameters.put("updatedate", new Date());
					parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);

					namedParameterJdbcTemplate.execute(insertSql, parameters,new PreparedStatementCallback() {  
						@Override  
						public Object doInPreparedStatement(PreparedStatement ps)  
								throws SQLException, DataAccessException {  
							return ps.executeUpdate();  
						}  
					});  
				}
			}

			for (UserRole userRole : hmpExistingList.values()) {
				parameters.put("roleId", userRole.getRoleId());
				parameters.put("userId", userRole.getUserId());
				String deleteSql=DrydockQueryConstant.ROLE_USER_MAPPING_DELETE;

				namedParameterJdbcTemplate.execute(deleteSql, parameters,new PreparedStatementCallback() {  
					@Override  
					public Object doInPreparedStatement(PreparedStatement ps)  
							throws SQLException, DataAccessException {  
						return ps.executeUpdate();  
					}  
				});  
			}
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public List<UserDetail> loadUserList(BasicInfoDTO info) throws Exception {

		List<UserDetail> userList = new ArrayList<>();
		try{
			Map parameters = new HashMap();
			parameters.put("orgId", info.getOrgId());
			userList =(List<UserDetail>) namedParameterJdbcTemplate.query(DrydockQueryConstant.USER_LIST_BY_ORGID,parameters, new UserDetailMapper());
		}
		catch(Exception e){
			throw e;
		}
		return userList;
	}
	
	@Override
	public Object loadUserUnderLoggedInUser(BasicInfoDTO info) throws Exception {

		List<UserDetail> userList = new ArrayList<>();
		try{
			Map parameters = new HashMap();
			parameters.put("orgId", info.getOrgId());
			parameters.put("parentUserId", info.getUserId());
			userList =(List<UserDetail>) namedParameterJdbcTemplate.query(DrydockQueryConstant.USER_LIST_BY_PARENTUSERID,parameters, new UserDetailMapper());
		}
		catch(Exception e){
			throw e;
		}
		return userList;
	}
	
	@Override
	public Object loadAllUserUnderLoggedInUser(BasicInfoDTO info) throws Exception {

		List<UserDetail> userList = new ArrayList<>();
		try{
			Set<Long> parentUserIds = new HashSet<>();
			parentUserIds.add(info.getUserId());
			Map parameters = new HashMap();
			parameters.put("orgId", info.getOrgId());
			while(null!=parentUserIds && !parentUserIds.isEmpty()){
				parameters.put("parentUserIds", parentUserIds);
				List<UserDetail> userListTemp =(List<UserDetail>) namedParameterJdbcTemplate.query(DrydockQueryConstant.USER_LIST_BY_MULTIPLE_PARENTUSERID,parameters, new UserDetailMapper());
				parentUserIds.clear();
				if(null!=userListTemp && !userListTemp.isEmpty()){
					for (UserDetail user : userListTemp) {
						parentUserIds.add(user.getUser_id());
						userList.add(user);
					}
				}
			}
		}
		catch(Exception e){
			throw e;
		}
		return userList;
	}
	
	@Override
	public void userUpdatePassword(UserDetail userDetails, String password, BasicInfoDTO info) throws Exception {

		try{
			Long userId = null;
			Map<String, Object> parameters = new HashMap<>();
			userId=userDetails.getUser_id();
			String sql=DrydockQueryConstant.USER_PASSWORD_UPDATE;
			
			parameters.put("passcode", DrydockWebUtil.encryptData(userDetails.getUserCode().toUpperCase(), password));
			parameters.put("user_id", userId); //create sequence
			if(null != info && info.getUserId() > 0)
				parameters.put("updateid", info.getUserId());
			else
				parameters.put("updateid", userId);
			parameters.put("updatedate", new Date());

			namedParameterJdbcTemplate.execute(sql, parameters,new PreparedStatementCallback() {  
				@Override  
				public Object doInPreparedStatement(PreparedStatement ps)  
						throws SQLException, DataAccessException {  
					return ps.executeUpdate();  
				}  
			});
		}
		catch(Exception e){
			throw e;
		}
	}
	
	
	@Override
	public Object checkLoginCodeExists(String userCode, BasicInfoDTO info) throws Exception {
		try{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userCode", userCode.toUpperCase());
		return (UserDetail) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.USER_SELECT_BY_USERCODE, parameters, new UserDetailMapper());
		}catch(Exception e){
			return null;
		}
	}
	
	@Override
	public Object loadDesignationList(BasicInfoDTO info) throws Exception {
		List<Designation> designationList = new ArrayList<>();
		try{
			Map parameters = new HashMap();
			parameters.put("orgId", info.getOrgId());
			parameters.put("defaultDesignationCode", DrydockConstant.DEFAULT_DESIGNATION_CODE);
			designationList =(List<Designation>) namedParameterJdbcTemplate.query(DrydockQueryConstant.DESIGNATION_LIST_BY_ORGID,parameters, new DesignationMapper());
		}
		catch(Exception e){
			throw e;
		}
		return designationList;
	}
	
	@Override
	public void saveDesignation(Designation designation, BasicInfoDTO info) throws Exception {

		try{
			Long designationId = null;
			Map<String, Object> parameters = new HashMap<>();
			String sql = null;
			int degId = 0;
			if(designation.getDesignation_id()>degId){
				sql=DrydockQueryConstant.DESIGNATION_UPDATE;
				designationId = designation.getDesignation_id();
			}else{
				sql=DrydockQueryConstant.DESIGNATION_INSERT;
				designationId = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.DESIGNATION_SEQ);
				if(info.getOrgId()==0){
					parameters.put("orgid", designation.getOrgId());
				}else{
					parameters.put("orgid", info.getOrgId());
				}
				parameters.put("createid", info.getUserId());
				parameters.put("createdate", new Date());
				parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
				designation.setDesignation_id(designationId);
				
			}
			
					parameters.put("designation_id", designationId); //create sequence
					parameters.put("code", designation.getCode());
					parameters.put("designation", designation.getDesignation());
					parameters.put("description", designation.getDescription());
					parameters.put("updateid", info.getUserId());
					parameters.put("updatedate", new Date());
			
			namedParameterJdbcTemplate.execute(sql, parameters,new PreparedStatementCallback() {  
			    @Override  
			    public Object doInPreparedStatement(PreparedStatement ps)  
			            throws SQLException, DataAccessException {  
			        return ps.executeUpdate();  
			    }  
			});  
		}
		catch(Exception e){
			throw e;
		}
	}
	
	
	@Override
	public Object loadDesignationById(long designationId, BasicInfoDTO info) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("designation_id", designationId);
		return (Designation) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.DERSIGNATION_SELECT_BY_ID, parameters, new DesignationMapper());
	}
	
	@Override
	public Object loadShipListForCreation(BasicInfoDTO info) throws Exception {
		List<Shipmaster> shipList = new ArrayList<>();
		try{
			Map parameters = new HashMap();
			parameters.put("orgId", info.getOrgId());
			if(info.getOrgId() == 0){
				shipList =(List<Shipmaster>) namedParameterJdbcTemplate.query(DrydockQueryConstant.SHIP_LIST_ALL,parameters, new ShipmasterMapper());
			}else{
				shipList =(List<Shipmaster>) namedParameterJdbcTemplate.query(DrydockQueryConstant.SHIP_LIST_BY_ORGID,parameters, new ShipmasterMapper());
			}
			
		}
		catch(Exception e){
			throw e;
		}
		return shipList;
	}
	
	@Override
	public Object loadShipDetailsById(long shipId, BasicInfoDTO info) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("ship_id", shipId);
		Shipmaster ship = (Shipmaster) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.SHIP_SELECT_BY_ID, parameters, new ShipmasterMapper());
		ship.setShipAttachmentList(loadShipAttachmentList(ship, info));
		return ship;
	}
	
	@Override
	public void saveShip(Shipmaster shipmaster, BasicInfoDTO info) throws Exception {

		try{
			boolean freshShip = false;
			Long shipId = null;
			Long orgId = null;
			String sql = null;
			Map<String, Object> parameters = new HashMap<>();
			if(shipmaster.getShip_id()>0){
				shipId = shipmaster.getShip_id();
				sql=DrydockQueryConstant.SHIP_UPDATE;
			}else{
				freshShip = true;
				shipId = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.SHIP_SEQ);
				shipmaster.setShip_id(shipId);
				sql=DrydockQueryConstant.SHIP_INSERT;
				if(info.getOrgId()==0){
					orgId = shipmaster.getOrgId();
				}else{
					orgId = info.getOrgId();
				}
				parameters.put("orgid", orgId);
				parameters.put("createid", info.getUserId());
				parameters.put("createdate", new Date());
				parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			}
			

			parameters.put("shipid", shipId); //create sequence
			parameters.put("shipno", shipmaster.getShipno());
			parameters.put("name", shipmaster.getName());
			parameters.put("description", shipmaster.getDescription());
			parameters.put("vesselname", shipmaster.getVesselname());
			parameters.put("v_imo_no", shipmaster.getV_imo_no());
			parameters.put("mmsi_no", shipmaster.getMmsi_no());
			parameters.put("call_sign", shipmaster.getCall_sign());
			parameters.put("official_no", shipmaster.getOfficial_no());
			parameters.put("v_type", shipmaster.getV_type());
			parameters.put("owner_imo_no", shipmaster.getOwner_imo_no());
			parameters.put("owner_name", shipmaster.getOwner_name());
			parameters.put("sat_f_77", shipmaster.getSat_f_77());
			parameters.put("sat_c", shipmaster.getSat_c());
			parameters.put("fleet_broadband", shipmaster.getFleet_broadband());
			parameters.put("sat_c_emailID", shipmaster.getSat_c_emailID());
			parameters.put("emailID", shipmaster.getEmailID());
			parameters.put("class", shipmaster.getShipClass());
			parameters.put("class_notations", shipmaster.getClass_notations());
			parameters.put("Classi_Id_No", shipmaster.getClassi_Id_No());
			parameters.put("flag", shipmaster.getFlag());
			parameters.put("port_of_registry", shipmaster.getPort_of_registry());
			parameters.put("year_built", shipmaster.getYear_built());
			parameters.put("keel_laying_date", shipmaster.getKeel_laying_date());
			parameters.put("vessel_delivery_date", shipmaster.getVessel_delivery_date());
			parameters.put("hull_type", shipmaster.getHull_type());
			parameters.put("length_overall", shipmaster.getLength_overall());
			parameters.put("breadth_MLD", shipmaster.getBreadth_MLD());
			parameters.put("depth", shipmaster.getDepth());
			parameters.put("summer_draft_M", shipmaster.getSummer_draft_M());
			parameters.put("summer_DWT_MT", shipmaster.getSummer_DWT_MT());
			parameters.put("international_GRT", shipmaster.getInternational_GRT());
			parameters.put("international_NRT", shipmaster.getInternational_NRT());
			parameters.put("life_boat_cap", shipmaster.getLife_boat_cap());
			parameters.put("v_short_name", shipmaster.getV_short_name());
			parameters.put("account_code_old", shipmaster.getAccount_code_old());
			parameters.put("account_code_new", shipmaster.getAccount_code_new());
			parameters.put("tel_fac_code", shipmaster.getTel_fac_code());
			parameters.put("updateid", info.getUserId());
			parameters.put("updatedate", new Date());
			parameters.put("maxEmailSizeInMB", shipmaster.getMaxEmailSizeInMB());
			parameters.put("dailyDataLimitInMB", shipmaster.getDailyDataLimitInMB());
			parameters.put("email1", shipmaster.getEmail1());
			parameters.put("email2", shipmaster.getEmail2());
			parameters.put("phoneno", shipmaster.getPhoneNo());
			parameters.put("phoneno1", shipmaster.getPhoneNo1());
			parameters.put("phoneno2", shipmaster.getPhoneNo2());

			namedParameterJdbcTemplate.execute(sql, parameters,new PreparedStatementCallback() {  
				@Override  
				public Object doInPreparedStatement(PreparedStatement ps)  
						throws SQLException, DataAccessException {  
					return ps.executeUpdate();  
				}  
			});  
			
			//ship attachment starts
			

			List<ShipAttachment> attachmentList = loadShipAttachmentList(shipmaster, info);
			Map<Long, ShipAttachment> hmpExistingAttachment = new HashMap<>();
			if(null != attachmentList && !attachmentList.isEmpty())
				for (ShipAttachment shipAttachment : attachmentList) {
					hmpExistingAttachment.put(shipAttachment.getId(), shipAttachment);
				}
			
			if(null!=shipmaster.getShipAttachmentList() && !shipmaster.getShipAttachmentList().isEmpty()){
				MapSqlParameterSource parameter = new MapSqlParameterSource();
				for (ShipAttachment shipAttachment : shipmaster.getShipAttachmentList()) {
					if(shipAttachment.getId()>0 && hmpExistingAttachment.containsKey(shipAttachment.getId())){
						sql = DrydockQueryConstant.SHIP_ATTACHMENT_UPDATE;
						parameter.addValue("id", shipAttachment.getId());
						parameter.addValue("createid", shipmaster.getCreateid());
						parameter.addValue("createdate", shipmaster.getCreatedate());
						hmpExistingAttachment.remove(shipAttachment.getId());
					}else{
						Long shipattachmentidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.SHIP_ATTACHMENT_SEQ);

						sql = DrydockQueryConstant.SHIP_ATTACHMENT_INSERT;
						parameter.addValue("id", shipattachmentidSeq);
						parameter.addValue("createid", info.getUserId());
						parameter.addValue("createdate", new Date());
					}
					parameter.addValue("orgId", shipmaster.getOrgId());
					parameter.addValue("shipId", shipmaster.getShip_id());
					parameter.addValue("updateid", info.getUserId());
					parameter.addValue("updatedate", new Date());
					parameter.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);

					parameter.addValue("attachmentName", shipAttachment.getAttachmentName());
					parameter.addValue("relativePath", shipAttachment.getRelativepath());
					parameter.addValue("attachmentType", shipAttachment.getAttachmentType());
					parameter.addValue("attachmentDescription", shipAttachment.getAttachmentDescription());
					parameter.addValue("vesselDocTypeId", shipAttachment.getVesselDocTypeId());
					
					namedParameterJdbcTemplate.update(sql, parameter);
				}
			}
			
			for (Long shipAttachmentId : hmpExistingAttachment.keySet()) {
				Map<String, Object> parameterMap = new HashMap<>();
				parameterMap.put("isactive", DrydockConstant.INACTIVE_FLAG);
				parameterMap.put("id", shipAttachmentId);

				namedParameterJdbcTemplate.update(DrydockQueryConstant.SHIP_ATTACHMENT_DELETE, parameterMap);  
			}
		
			
			//ship attachment ends
			
			
			if(freshShip){
				parameters.clear();
				parameters.put("superAdminRoleCode", DrydockConstant.SUPER_ADMIN_ROLE_CODE);
				parameters.put("orgid", orgId);
				List<Long> userIdList =(List<Long>) namedParameterJdbcTemplate.queryForList(DrydockQueryConstant.SA_USER_LIST, parameters, Long.class);
				if(null!=userIdList && !userIdList.isEmpty())
					addUserToShip(shipId, userIdList, info);
				
				Project defaultProject = new Project();
				defaultProject.setDescription(DrydockConstant.DEFAULT_PROJECT_DESCRIPTION);
				defaultProject.setOrgid(orgId);
				defaultProject.setShipid(shipId);
				
				projectRepositoryImpl.saveProject(defaultProject, false, info);
			}
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public Object loadShipListForAssignment(BasicInfoDTO info) throws Exception {
		List<Shipmaster> shipList = new ArrayList<>();
		try{
			Map parameters = new HashMap();
			parameters.put("userId", info.getUserId());
			if(DrydockConstant.USER_TYPE_SHIP.equalsIgnoreCase(info.getUserType())){
				shipList =(List<Shipmaster>) namedParameterJdbcTemplate.query(DrydockQueryConstant.SHIP_LIST_FOR_SHIP_USER,parameters, new ShipmasterMapper());
			}else{
				parameters.put("orgId", info.getOrgId());
				shipList =(List<Shipmaster>) namedParameterJdbcTemplate.query(DrydockQueryConstant.SHIP_LIST_FOR_ASSIGNMENT,parameters, new ShipmasterMapper());
			}
		}
		catch(Exception e){
			throw e;
		}
		return shipList;
	}
	
	@Override
	public void addUserToShip(long shipId, List<Long> userIdList, BasicInfoDTO info) throws Exception {
		try{
			for (Long userId : userIdList) {
				Map parameters = new HashMap();
				parameters.put("shipId", shipId);
				parameters.put("userId", userId);
				List<UserShip> userShipList =(List<UserShip>) namedParameterJdbcTemplate.query(DrydockQueryConstant.USER_SHIP_ASSIGNMENT_LIST_BY_USERID,parameters, new UserShipMapper());
				if(null==userShipList || userShipList.isEmpty()){
					Long id=sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.SHIP_USER_SEQ);
					String insertSql=DrydockQueryConstant.SHIP_USER_MAPPING_INSERT;
							parameters.put("id", id); //create sequence
							parameters.put("createid", info.getUserId());
							parameters.put("createdate", new Date());
							parameters.put("updateid", info.getUserId());
							parameters.put("updatedate", new Date());
							parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
							
							namedParameterJdbcTemplate.execute(insertSql, parameters,new PreparedStatementCallback() {  
							    @Override  
							    public Object doInPreparedStatement(PreparedStatement ps)  
							            throws SQLException, DataAccessException {  
							        return ps.executeUpdate();  
							    }  
							});  
				}
			}			
		}
		catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void removeUserFromShip(long shipId, List<Long> userIdList, BasicInfoDTO info) throws Exception {
		try{
			for (Long userId : userIdList) {
				Map parameters = new HashMap();
				parameters.put("shipId", shipId);
				parameters.put("userId", userId);
				String deleteSql=DrydockQueryConstant.SHIP_USER_MAPPING_DELETE;

				namedParameterJdbcTemplate.execute(deleteSql, parameters,new PreparedStatementCallback() {  
					@Override  
					public Object doInPreparedStatement(PreparedStatement ps)  
							throws SQLException, DataAccessException {
						return ps.executeUpdate();  
					}  
				});  
			}			
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public Object loadUserAssignedToShip(long shipId, BasicInfoDTO info) throws Exception {		
		List<UserDetail> userList = new ArrayList<>();
		try{
			Map parameters = new HashMap();
			parameters.put("shipId", shipId);
			String selectSql=DrydockQueryConstant.USER_ASSIGNED_TO_SHIP;
			List<UserShip> shipUserList =null;
			userList =(List<UserDetail>) namedParameterJdbcTemplate.query(DrydockQueryConstant.USER_SHIP_ASSIGNMENT_LIST,parameters, new UserDetailMapper());
		}
		catch(Exception e){
			throw e;
		}
		return userList;
	}
	
	@Override
	public List<String> loadAllUserCodeList(BasicInfoDTO info) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		List<String> userCodeList =(List<String>) namedParameterJdbcTemplate.queryForList(DrydockQueryConstant.SA_ALL_USER_CODE_LIST, parameters, String.class);
		return userCodeList;
	}


	@Override
	public Object getMasterVesselType(String vesselType) throws Exception {
		// TODO Auto-generated method stub
		VesselTypeMaster vtype = new VesselTypeMaster();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("code", vesselType);
			 vtype= (VesselTypeMaster) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.VESSEL_TYPE_MASTER, parameters, new VesselTypeMasterMapper());
		}
		catch(Exception e)
		{
			throw e;
		}
		return vtype;
	}
	
	@Override
	public Object getMasterVesselTypeById(Long vesselTypeId) throws Exception {
		// TODO Auto-generated method stub
		VesselTypeMaster vtype = new VesselTypeMaster();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("id", vesselTypeId);
			 vtype= (VesselTypeMaster) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.VESSEL_TYPE_MASTER_BY_ID, parameters, new VesselTypeMasterMapper());
		}
		catch(Exception e)
		{
			throw e;
		}
		return vtype;
	}
	
	@Override
	public List<VesselTypeMaster> getMasterVesselTypeList() throws Exception {
		// TODO Auto-generated method stub
		List<VesselTypeMaster> vtype = new ArrayList<VesselTypeMaster>();
		try{
			 vtype= (List<VesselTypeMaster>) namedParameterJdbcTemplate.query(DrydockQueryConstant.VESSEL_TYPE_MASTER_LIST, new VesselTypeMasterMapper());
		}
		catch(Exception e)
		{
			throw e;
		}
		return vtype;
	}


	@Override
	public Long getorgIdFromorgName(String orgName) throws Exception {
		
		List<Organization> orgList = new ArrayList<>();
		Long result = null;
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("orgName", orgName);
			 orgList=  namedParameterJdbcTemplate.query(DrydockQueryConstant.ORG_ID, parameters, new OrganizationMapper());
			 if(null!=orgList && !orgList.isEmpty())
			 {
				 result= orgList.get(0).getOrg_id();
			 }
		}
		catch(Exception e)
		{
			throw e;
		}
		return result;
	}


	@Override
	public Object loadDockyardById(long dockyardId, BasicInfoDTO info)
			throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("dockyardId", dockyardId);
		return (DockyardMaster) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.DOCKYARD_SELECT_BY_ID, parameters, new DockyardMasterMapper());
	}


	@Override
	public Object loadDockyardList(BasicInfoDTO info) throws Exception {
		List<DockyardMaster> dockyardList = new ArrayList<>();
		try{
			Map parameters = new HashMap();
			parameters.put("orgid", info.getOrgId());
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			dockyardList =(List<DockyardMaster>) namedParameterJdbcTemplate.query(DrydockQueryConstant.DOCKYARD_LIST_BY_ORGID,parameters, new DockyardMasterMapper());
		}
		catch(Exception e){
			throw e;
		}
		return dockyardList;
	}


	@Override
	public void saveDockyard(DockyardMaster dockyard, BasicInfoDTO info)
			throws Exception {

		try{
			Map<String, Object> parameters = new HashMap<>();
			String sql = null;
			long degId = 0;
			if(dockyard.getId()>degId){
				sql=DrydockQueryConstant.DOCKYARD_MASTER_UPDATE;
				parameters.put("createid", dockyard.getCreateid());
				parameters.put("createdate", dockyard.getCreatedate());
				parameters.put("isactive", dockyard.getIsactive());
				parameters.put("orgid", dockyard.getOrgId());
			}else{
				sql=DrydockQueryConstant.DOCKYARD_MASTER_INSERT;
				long designationId = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.DESIGNATION_SEQ);
				dockyard.setId(designationId);
				if(info.getOrgId()==0){
					parameters.put("orgid", dockyard.getOrgId());
				}else{
					parameters.put("orgid", info.getOrgId());
				}
				parameters.put("createid", info.getUserId());
				parameters.put("createdate", new Date());
				parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			}
			
					parameters.put("id", dockyard.getId()); //create sequence
					parameters.put("dockyard", dockyard.getDockyard());
					parameters.put("updateid", info.getUserId());
					parameters.put("updatedate", new Date());
			
			namedParameterJdbcTemplate.execute(sql, parameters,new PreparedStatementCallback() {  
			    @Override  
			    public Object doInPreparedStatement(PreparedStatement ps)  
			            throws SQLException, DataAccessException {  
			        return ps.executeUpdate();  
			    }  
			});  
		}
		catch(Exception e){
			throw e;
		}
	}


	@Override
	public void updateUserCurrentShipEntry(long shipId, BasicInfoDTO info) throws Exception {

		try{
			Map<String, Object> parameters = new HashMap<>();
			String sql = null;
			UserCurrentShip userCurrentShip = loadUserCurrentShip(info.getUserId(), info);
			if(null==userCurrentShip){
				sql=DrydockQueryConstant.USER_CURRENT_SHIP_INSERT;
			}else{
				sql=DrydockQueryConstant.USER_CURRENT_SHIP_UPDATE;
			}
			parameters.put("createdate", new Date());
			parameters.put("shipId", shipId);
			parameters.put("userId", info.getUserId());
			
			namedParameterJdbcTemplate.execute(sql, parameters,new PreparedStatementCallback() {  
			    @Override  
			    public Object doInPreparedStatement(PreparedStatement ps)  
			            throws SQLException, DataAccessException {  
			        return ps.executeUpdate();  
			    }  
			});  
		}
		catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public UserCurrentShip loadUserCurrentShip(Long userId,BasicInfoDTO info) throws Exception {
		UserCurrentShip userCurrentShip= null;
		try{
			
			Map parameters = new HashMap();
			parameters.put("userId", userId);
			userCurrentShip =(UserCurrentShip) namedParameterJdbcTemplate.queryForObject("select * from user_current_ship where userid=:userId",parameters, new UserCurrentShipMapper());
			
		}
		catch(Exception e){
			
		}
		return userCurrentShip;	
	}
	
	private List<ShipAttachment> loadShipAttachmentList(Shipmaster ship, BasicInfoDTO info) throws Exception {
	    List<ShipAttachment> shipAttachmentList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("orgId", ship.getOrgId());
			parameters.put("shipId", ship.getShip_id());
			shipAttachmentList =(List<ShipAttachment>) namedParameterJdbcTemplate.query(DrydockQueryConstant.SHIP_ATTACHMENT_LIST,parameters, new ShipAttachmentMapper());
		}
		catch(Exception e){
			throw e;
		}
		if(null!=shipAttachmentList && !shipAttachmentList.isEmpty())
			return shipAttachmentList;
		else
			return null;
	}


	@Override
	public List<ApplicationVesselDocType> loadApplicationVesselDocTypeList(
			BasicInfoDTO info) throws Exception {

		List<ApplicationVesselDocType> applicationVesselDocTypeList = new ArrayList<ApplicationVesselDocType>();
		try{
			Map parameters = new HashMap();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			applicationVesselDocTypeList =(List<ApplicationVesselDocType>) namedParameterJdbcTemplate.query(DrydockQueryConstant.LOAD_APPLICATION_VESSEL_DOC_TYPE_LIST,parameters, new ApplicationVesselDocTypeMapper());
		}
		catch(Exception e){
			throw e;
		}
		return applicationVesselDocTypeList;
	}


	@Override
	public void saveApplicationVesselDocTypeList(
			List<ApplicationVesselDocType> applicationVesselDocTypeList,
			BasicInfoDTO info) throws Exception {

		try{
			List<ApplicationVesselDocType> existingApplicationVesselDocTypeList = loadApplicationVesselDocTypeList(info);
			Map<Long, ApplicationVesselDocType> existingMap = new HashMap<>();
			for (ApplicationVesselDocType applicationVesselDocType : existingApplicationVesselDocTypeList) {
				existingMap.put(applicationVesselDocType.getId(), applicationVesselDocType);
			}
			Map<String, Object> parameters = new HashMap<>();
			String sql = null;
			for (ApplicationVesselDocType applicationVesselDocType : applicationVesselDocTypeList) {
				long degId = 0;
				if(applicationVesselDocType.getId()>degId){
					if(existingMap.containsKey(applicationVesselDocType.getId())){
						existingMap.remove(applicationVesselDocType.getId());
					}
					sql=DrydockQueryConstant.APPLICATION_VESSEL_DOC_TYPE_UPDATE;
					parameters.put("isactive", applicationVesselDocType.getIsactive());
				}else{
					sql=DrydockQueryConstant.APPLICATION_VESSEL_DOC_TYPE_INSERT;
					long applicationVesselDocTypeId = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.APPLICATION_VESSEL_DOC_TYPE_SEQ);
					applicationVesselDocType.setId(applicationVesselDocTypeId);
					parameters.put("createid", info.getUserId());
					parameters.put("createdate", new Date());
					parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
				}
				
						parameters.put("id", applicationVesselDocType.getId()); //create sequence
						parameters.put("vesselDocDescription", applicationVesselDocType.getVesselDocDescription());
						parameters.put("updateid", info.getUserId());
						parameters.put("updatedate", new Date());
				
				namedParameterJdbcTemplate.execute(sql, parameters,new PreparedStatementCallback() {  
				    @Override  
				    public Object doInPreparedStatement(PreparedStatement ps)  
				            throws SQLException, DataAccessException {  
				        return ps.executeUpdate();  
				    }  
				}); 
			}
			
			for (Long applicationVesselDocTypeId : existingMap.keySet()) {
				parameters.clear();
				parameters.put("id", applicationVesselDocTypeId);
				namedParameterJdbcTemplate.update(DrydockQueryConstant.DELETE_APPLICATION_VESSEL_DOC_TYPE, parameters);
			}
		}
		catch(Exception e){
			throw e;
		}
	}


	@Override
	public List<OrganizationVesselDocType> loadOrganizationVesselDocTypeList(
			BasicInfoDTO info) throws Exception {
		List<OrganizationVesselDocType> organizationVesselDocTypeList = new ArrayList<OrganizationVesselDocType>();
		try{
			Map parameters = new HashMap();
			parameters.put("orgId", info.getOrgId());
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			organizationVesselDocTypeList =(List<OrganizationVesselDocType>) namedParameterJdbcTemplate.query(DrydockQueryConstant.LOAD_ORGANIZATION_VESSEL_DOC_TYPE_LIST,parameters, new OrganizationVesselDocTypeMapper());
		}
		catch(Exception e){
			throw e;
		}
		return organizationVesselDocTypeList;
	}


	@Override
	public void saveOrganizationVesselDocTypeList(
			List<OrganizationVesselDocType> organizationVesselDocTypeList,
			BasicInfoDTO info) throws Exception {

		try{
			List<OrganizationVesselDocType> existingOrganizationVesselDocType = loadOrganizationVesselDocTypeList(info);
			Map<Long, OrganizationVesselDocType> existingMap = new HashMap<>();
			for (OrganizationVesselDocType organizationVesselDocType : existingOrganizationVesselDocType) {
				existingMap.put(organizationVesselDocType.getId(), organizationVesselDocType);
			}
			Map<String, Object> parameters = new HashMap<>();
			String sql = null;
			for (OrganizationVesselDocType organizationVesselDocType : organizationVesselDocTypeList) {
				long degId = 0;
				if(organizationVesselDocType.getId()>degId){
					if(existingMap.containsKey(organizationVesselDocType.getId())){
						existingMap.remove(organizationVesselDocType.getId());
					}
					sql=DrydockQueryConstant.ORGANIZATION_VESSEL_DOC_TYPE_UPDATE;
					parameters.put("isactive", organizationVesselDocType.getIsactive());
					parameters.put("orgid", organizationVesselDocType.getOrgId());
				}else{
					sql=DrydockQueryConstant.ORGANIZATION_VESSEL_DOC_TYPE_INSERT;
					long organizationVesselDocTypeId = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.ORGANIZATION_VESSEL_DOC_TYPE_SEQ);
					organizationVesselDocType.setId(organizationVesselDocTypeId);
					parameters.put("createid", info.getUserId());
					parameters.put("createdate", new Date());
					parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
					if(info.getOrgId()==0){
						parameters.put("orgid", organizationVesselDocType.getOrgId());
					}else{
						parameters.put("orgid", info.getOrgId());
					}
				}
				
						parameters.put("id", organizationVesselDocType.getId()); //create sequence
						parameters.put("vesselDocDescription", organizationVesselDocType.getVesselDocDescription());
						parameters.put("updateid", info.getUserId());
						parameters.put("updatedate", new Date());
				
				namedParameterJdbcTemplate.execute(sql, parameters,new PreparedStatementCallback() {  
				    @Override  
				    public Object doInPreparedStatement(PreparedStatement ps)  
				            throws SQLException, DataAccessException {  
				        return ps.executeUpdate();  
				    }  
				}); 
			}
			for (Long organizationVesselDocTypeId : existingMap.keySet()) {
				parameters.clear();
				parameters.put("id", organizationVesselDocTypeId);
				namedParameterJdbcTemplate.update(DrydockQueryConstant.DELETE_ORGANIZATION_VESSEL_DOC_TYPE, parameters);
			}
		}
		catch(Exception e){
			throw e;
		}
	}


	@Override
	public List<CurrencyMaster> loadCurrencyList(BasicInfoDTO info)
			throws Exception {
		List<CurrencyMaster> currencyList= new ArrayList<CurrencyMaster>();
		try{
			Map parameters = new HashMap();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			currencyList =(List<CurrencyMaster>) namedParameterJdbcTemplate.query(DrydockQueryConstant.CURRENCY_LIST, parameters, new CurrencyMasterMapper());
		}
		catch(Exception e){
			throw e;
		}
		return currencyList;
	}
	
	/**
	 * Rupak Ends
	 */
}
