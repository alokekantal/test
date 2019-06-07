package com.drydock.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.entity.ApplicationVesselDocType;
import com.drydock.entity.CurrencyMaster;
import com.drydock.entity.Department;
import com.drydock.entity.Designation;
import com.drydock.entity.DockyardMaster;
import com.drydock.entity.Function;
import com.drydock.entity.KeyValue;
import com.drydock.entity.Organization;
import com.drydock.entity.OrganizationVesselDocType;
import com.drydock.entity.Role;
import com.drydock.entity.Shipmaster;
import com.drydock.entity.UserDetail;
import com.drydock.entity.VesselTypeMaster;
import com.drydock.exception.ValidationException;
import com.drydock.service.CoreService;
import com.drydock.util.DrydockWebUtil;

@RestController
@RequestMapping("core")
public class CoreController {
	
	@Autowired
	CoreService coreService;
	
	/**
	 * Arnabi Starts
	 */
	@RequestMapping(value="/loadOrganizationList" , method = RequestMethod.POST)
	public Object loadOrganizationList(HttpServletRequest request,HttpServletResponse response) {
		List<Organization> orgList = new ArrayList<Organization>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			orgList= coreService.loadOrganizationList(info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return orgList;
	}
	
	@RequestMapping(value="/loadOrganization" , method = RequestMethod.POST)
	public Object loadOrganization(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("orgId") Long orgId) {
		Organization org = new Organization();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			org= coreService.loadOrganization(orgId,info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return org;
		
		//return "Success";
	}

	@RequestMapping("/saveOrganization")
	public Object saveOrganization(HttpServletRequest request,
	        HttpServletResponse response,@RequestBody Organization org) {
		
		Map<Integer,String> result = new HashMap<Integer,String>();
		
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.saveOrganization(org,info);
			return result.put(200, "Success");
		}
		catch(Exception e){
			e.printStackTrace();
			sendError(response, "Some error occured.");
			return null;
		}
	}
	
	@RequestMapping(value="/loadDepartmentList" , method = RequestMethod.POST)
	public Object loadDepartmentList(HttpServletRequest request,HttpServletResponse response) {
		List<Department> deptList = new ArrayList<Department>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			deptList= coreService.loadDepartmentList(info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return deptList;
		
		//return "Success";
	}
	
	@RequestMapping(value="/loadDepartment" , method = RequestMethod.POST)
	public Object loadDepartment(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("deptId") Long deptId) {
		Department dept = new Department();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			dept= coreService.loadDepartment(deptId,info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return dept;
		
		//return "Success";
	}

	@RequestMapping("/saveDepartment")
	public Object saveDepartment(HttpServletRequest request,
	        HttpServletResponse response,@RequestBody Department dept) {
		Map<Integer,String> result = new HashMap<Integer,String>();
		
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.saveDepartment(dept,info);
			result.put(200, "Success");
		}
		catch(Exception e){
			e.printStackTrace();
			return result.put(100, "Error");
		}
		
		return result;
	}
	
	@RequestMapping(value="/loadRoleList" , method = RequestMethod.POST)
	public Object loadRoleList(HttpServletRequest request,HttpServletResponse response) {
		List<Role> roleList = new ArrayList<Role>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			roleList= coreService.loadRoleList(info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return roleList;
		
		//return "Success";
	}
	
	@RequestMapping("/addRole")
	public Object addRole(HttpServletRequest request,
	        HttpServletResponse response,@RequestBody Role role) {
		Map<Integer,String> result = new HashMap<Integer,String>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.addRole(role,info);
			result.put(200, "Success");
		}
		catch(Exception e){
			e.printStackTrace();
			return result.put(100, "Error");
		}
		
		return result;
	}
	
	@RequestMapping(value="/loadRole" , method = RequestMethod.POST)
	public Object loadRole(HttpServletRequest request,HttpServletResponse response,@RequestParam("roleId") Long roleId) {
		Role role = new Role();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			role = coreService.loadRole(roleId,info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return role;
		
		//return "Success";
	}
	
	@RequestMapping("/deleteRole")
	public Object deleteRole(HttpServletRequest request,
	        HttpServletResponse response,@RequestParam("roleId") Long roleId) {
		Map<Integer,String> result = new HashMap<Integer,String>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.deleteRole(roleId,info);
			result.put(200, "Success");
		}
		catch(Exception e){
			e.printStackTrace();
			return result.put(100, "Error");
		}
		
		return result;
	}
	
	@RequestMapping("/assignRoleToDepartment")
	public Object assignRoleToDepartment(HttpServletRequest request,
	        HttpServletResponse response,@RequestParam("roleId") Long roleId,@RequestParam("deptIdList") List<Long> deptIdList) {
		Map<Integer,String> result = new HashMap<Integer,String>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.assignRoleToDepartment(roleId,deptIdList,info);
			result.put(200, "Success");
		}
		catch(Exception e){
			e.printStackTrace();
			return result.put(100, "Error");
		}
		
		return result;
	}
	
	@RequestMapping("/assignDepartmentToRole")
	public Object assignDepartmentToRole(HttpServletRequest request,
	        HttpServletResponse response,@RequestParam("deptId") Long deptId,@RequestParam("roleIdList") List<Long> roleIdList) {
		Map<Integer,String> result = new HashMap<Integer,String>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.assignDepartmentToRole(deptId,roleIdList,info);
			result.put(200, "Success");
		}
		catch(Exception e){
			e.printStackTrace();
			return result.put(100, "Error");
		}
		
		return result;
	}

	@RequestMapping(value="/loadFunctionList" , method = RequestMethod.POST)
	public Object loadFunctionList(HttpServletRequest request,HttpServletResponse response) {
		List<Function> funcList = new ArrayList<Function>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			funcList= coreService.loadFunctionList(info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return funcList;
		
	}
	
	@RequestMapping("/assignFunctionToRole")
	public Object assignFunctionToRole(HttpServletRequest request,
	        HttpServletResponse response,@RequestParam("roleId") Long roleId,@RequestParam("functionIdList") List<Long> functionIdList) {
		Map<Integer,String> result = new HashMap<Integer,String>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.assignFunctionToRole(roleId,functionIdList,info);
			result.put(200, "Success");
		}
		catch(Exception e){
			e.printStackTrace();
			return result.put(100, "Error");
		}
		
		return result;
	}
	
	@RequestMapping("/loadFunctionListUserWise")
	public Object loadFunctionListUserWise(HttpServletRequest request,
	        HttpServletResponse response) {
		List<Function> funcList = new ArrayList<Function>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			funcList = coreService.loadFunctionListUserWise(info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return funcList;
	}
	
	@RequestMapping("uploadShipExcel")	
	public Object uploadShipExcel(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		System.out.println("inside uploadShipExcel");
		List<String> status=new ArrayList<>();
		System.out.println(file.getOriginalFilename());
		Workbook workbook=null;
		try {
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			if (file.getOriginalFilename().endsWith("xls")) {
				workbook = new HSSFWorkbook(file.getInputStream());
			} else if (file.getOriginalFilename().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(file.getInputStream());
			} else {
				sendError(response, "Received file does not have a standard excel extension.");
			}
			
			if(null!=workbook){
				StringBuilder errorMessage = new StringBuilder("");
				int numberOfRecords=0;
				int startingRowNumber=2;
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD.MM.YYYY");
				Sheet userSheet=workbook.getSheet("Ship_Upload");
				if(null==userSheet){
					errorMessage.append("<li>User sheet not found.</li>");
				}else{
					Row userRow=userSheet.getRow(0);
					if(null==userRow){
						errorMessage.append("<li>Number of records not found.</li>");
					}else{
						Cell userCell = userRow.getCell(1);
						try{
							String numberOfRecordsStr = new DataFormatter().formatCellValue(userCell);
							try{
								numberOfRecords=Integer.parseInt(numberOfRecordsStr);
							}catch(Exception ex){
								errorMessage.append("<li>Number of records is inappropriate.</li>");
							}
							System.out.println("numberOfRecords - " + numberOfRecords);
							
							List<VesselTypeMaster> vesselTypeList = (List<VesselTypeMaster>) coreService.getMasterVesselTypeList();
							Map<String, VesselTypeMaster> vesselTypeMap = new HashMap<>();
							for (VesselTypeMaster vesselType : vesselTypeList) {
								vesselTypeMap.put(vesselType.getDescription(), vesselType);
							}
							
							
							List<Shipmaster> shipMasterList = new ArrayList<>();
							for(int i=0;i<numberOfRecords;i++){
								Shipmaster shipMaster = new Shipmaster();
								
								Row dataRow=userSheet.getRow(startingRowNumber+i);
								Cell cell = dataRow.getCell(0);
								String serialNo = new DataFormatter().formatCellValue(cell);
								cell = dataRow.getCell(1);
								String shipno = new DataFormatter().formatCellValue(cell);
								if("".equals(shipno)){
									errorMessage.append("<li>Ship No is mandatory in serial number "+serialNo+".</li>");
								}else{
									shipMaster.setShipno(shipno);
								}
								cell = dataRow.getCell(2);
								String name = new DataFormatter().formatCellValue(cell);
								if("".equals(name)){
									errorMessage.append("<li>Ship Name is mandatory in serial number "+serialNo+".</li>");
								}else{
									shipMaster.setName(name);
								}
								cell = dataRow.getCell(3);
								String dvessel_Description = new DataFormatter().formatCellValue(cell);
								if("".equals(dvessel_Description)){
									errorMessage.append("<li>Vessel Description is mandatory in serial number "+serialNo+".</li>");
								}else{
									shipMaster.setDescription(dvessel_Description);
								}
								cell = dataRow.getCell(4);
								String vesselName = new DataFormatter().formatCellValue(cell);
								if("".equals(vesselName)){
									errorMessage.append ("<li>Vessel Name is mandatory in serial number "+serialNo+".</li>");
								}else{
									shipMaster.setVesselname(vesselName);
								}
								cell = dataRow.getCell(5);
								String vessel_IMO_Number = new DataFormatter().formatCellValue(cell);
								if("".equals(vessel_IMO_Number)){
									errorMessage.append ("<li>Vessel IMO No is mandatory in serial number "+serialNo+".</li>");
								}else{
									try{
										shipMaster.setV_imo_no(Long.parseLong(vessel_IMO_Number));
									}catch(Exception ex){
										errorMessage.append ("<li>Invalid IMO No in serial number "+serialNo+".</li>");
									}
								}
								cell = dataRow.getCell(6);
								String mMSI_No = new DataFormatter().formatCellValue(cell);
								if(null!=mMSI_No && !"".equals(mMSI_No)){ 
									try{
										shipMaster.setMmsi_no(Long.parseLong(mMSI_No));
									}catch(Exception ex){
										errorMessage.append ("<li>Invalid MMSI No in serial number "+serialNo+".</li>");
									}
								}
								cell = dataRow.getCell(7);
								String callSign = new DataFormatter().formatCellValue(cell);
								shipMaster.setCall_sign(callSign);
								cell = dataRow.getCell(8);
								String officialNumber = new DataFormatter().formatCellValue(cell);
								shipMaster.setOfficial_no(officialNumber);
								cell = dataRow.getCell(9);
								String vesselType = new DataFormatter().formatCellValue(cell);
								if("".equals(vesselType) || null==vesselType){
									errorMessage.append ("<li>Vessel Type No is mandatory in serial number "+serialNo+".</li>");
								}else if(!vesselTypeMap.containsKey(vesselType)){
									errorMessage.append ("<li>Vessel Type is invalid in serial number "+serialNo+".</li>");
								}else{
									shipMaster.setV_type(vesselTypeMap.get(vesselType).getId());
								}
								cell = dataRow.getCell(10);
								String registeredOwnerIMOnumber = new DataFormatter().formatCellValue(cell);
								if(null!=registeredOwnerIMOnumber && !"".equals(registeredOwnerIMOnumber)){ 
									try{
										shipMaster.setOwner_imo_no(Long.parseLong(registeredOwnerIMOnumber));
									}catch(Exception ex){
										errorMessage.append ("<li>Invalid Registered Owner's IMO number in serial number "+serialNo+".</li>");
									}
								}
								cell = dataRow.getCell(11);
								String registerOwnerName = new DataFormatter().formatCellValue(cell);
								shipMaster.setOwner_name(registerOwnerName);
								
								cell = dataRow.getCell(12);
								String satF77 = new DataFormatter().formatCellValue(cell);
								shipMaster.setSat_f_77(satF77);
								cell = dataRow.getCell(13);
								String sat_C = new DataFormatter().formatCellValue(cell);
								shipMaster.setSat_c(sat_C);
								cell = dataRow.getCell(14);
								String fleetBroadband= new DataFormatter().formatCellValue(cell);
								shipMaster.setFleet_broadband(fleetBroadband);
								cell = dataRow.getCell(15);
								String satCeMail = new DataFormatter().formatCellValue(cell);
								if (!"".equals(satCeMail) && !DrydockWebUtil.isValidEmail(satCeMail)) {
									errorMessage.append("<li>SatC mail id is not in correct format in serial number "+serialNo+".</li>");
								}else{
									shipMaster.setSat_c_emailID(satCeMail);
								}
								cell = dataRow.getCell(16);
								String eMail = new DataFormatter().formatCellValue(cell);
								if (!"".equals(eMail) && !DrydockWebUtil.isValidEmail(eMail)) {
									errorMessage.append("<li>Mail ID is not in correct format in serial number "+serialNo+".</li>");
								}else{
									shipMaster.setEmailID(eMail);
								}
								cell = dataRow.getCell(17);
								String clas = new DataFormatter().formatCellValue(cell);
								shipMaster.setShipClass(clas);
								cell = dataRow.getCell(18);
								String classNotations = new DataFormatter().formatCellValue(cell);
								shipMaster.setClass_notations(classNotations);
								cell = dataRow.getCell(19);
								String classificationId = new DataFormatter().formatCellValue(cell);
								shipMaster.setClassi_Id_No(classificationId);
								cell = dataRow.getCell(20);
								String flag = new DataFormatter().formatCellValue(cell);
								shipMaster.setFlag(flag);
								
								cell = dataRow.getCell(21);
								String portOfRegistry = new DataFormatter().formatCellValue(cell);
								shipMaster.setPort_of_registry(portOfRegistry);
								cell = dataRow.getCell(22);
								String yearBuilt = new DataFormatter().formatCellValue(cell);
								shipMaster.setYear_built(yearBuilt);
								cell = dataRow.getCell(23);
								String keelLayingDate = new DataFormatter().formatCellValue(cell);
								if(null!=keelLayingDate && !"".equals(keelLayingDate))
									shipMaster.setKeel_laying_date(new Date(keelLayingDate).getTime());
								cell = dataRow.getCell(24);
								String vesselDeliveryDate = new DataFormatter().formatCellValue(cell);
								if(null!=vesselDeliveryDate && !"".equals(vesselDeliveryDate))
									shipMaster.setVessel_delivery_date(new Date(vesselDeliveryDate).getTime());
								cell = dataRow.getCell(25);
								String hullType = new DataFormatter().formatCellValue(cell);
								shipMaster.setHull_type(hullType);
								cell = dataRow.getCell(26);
								String lengthOverall = new DataFormatter().formatCellValue(cell);
								if(null!=lengthOverall && !"".equals(lengthOverall)){
									try{
										shipMaster.setLength_overall(new BigDecimal(lengthOverall));
									}catch(Exception ex){
										errorMessage.append("<li>Invalid Length Overall value in serial number "+serialNo+".</li>");
									}
								}
								cell = dataRow.getCell(27);
								String breadthMLD = new DataFormatter().formatCellValue(cell);
								if(null!=breadthMLD && !"".equals(breadthMLD)){
									try{
										shipMaster.setBreadth_MLD(new BigDecimal(breadthMLD));
									}catch(Exception ex){
										errorMessage.append("<li>Invalid Breadth MLD value in serial number "+serialNo+".</li>");
									}
								}
								cell = dataRow.getCell(28);
								String depth = new DataFormatter().formatCellValue(cell);
								if(null!=depth && !"".equals(depth)){
									try{
										shipMaster.setDepth(new BigDecimal(depth));
									}catch(Exception ex){
										errorMessage.append("<li>Invalid Depth value in serial number "+serialNo+".</li>");
									}
								}
								cell = dataRow.getCell(29);
								String summerDraft = new DataFormatter().formatCellValue(cell);
								if(null!=summerDraft && !"".equals(summerDraft)){
									try{
										shipMaster.setSummer_draft_M(new BigDecimal(summerDraft));
									}catch(Exception ex){
										errorMessage.append("<li>Invalid Summer Draft (M) value in serial number "+serialNo+".</li>");
									}
								}
								cell = dataRow.getCell(30);
								String summerDWT = new DataFormatter().formatCellValue(cell);
								shipMaster.setSummer_DWT_MT(summerDWT);
								cell = dataRow.getCell(31);
								String internationalGRT = new DataFormatter().formatCellValue(cell);
								shipMaster.setInternational_GRT(internationalGRT);
								cell = dataRow.getCell(32);
								String internationalNRT = new DataFormatter().formatCellValue(cell);
								shipMaster.setInternational_NRT(internationalNRT);
								cell = dataRow.getCell(33);
								String lifeBoatCapacity = new DataFormatter().formatCellValue(cell);
								if(null!=lifeBoatCapacity && !"".equals(lifeBoatCapacity)){
									try{
										shipMaster.setLife_boat_cap(Long.valueOf(lifeBoatCapacity));
									}catch(Exception ex){
										errorMessage.append("<li>Invalid Life Boat Capacity value in serial number "+serialNo+".</li>");
									}
								}
								cell = dataRow.getCell(34);
								String vesselShortName = new DataFormatter().formatCellValue(cell);
								shipMaster.setV_short_name(vesselShortName);
								cell = dataRow.getCell(35);
								String accountCodeOld = new DataFormatter().formatCellValue(cell);
								shipMaster.setAccount_code_old(accountCodeOld);
								cell = dataRow.getCell(36);
								String accountCodeNew = new DataFormatter().formatCellValue(cell);
								shipMaster.setAccount_code_new(accountCodeNew);
								cell = dataRow.getCell(37);
								String telFACCode = new DataFormatter().formatCellValue(cell);
								shipMaster.setTel_fac_code(telFACCode);
								
								cell = dataRow.getCell(38);
								String orgName = new DataFormatter().formatCellValue(cell);
								if("".equals(orgName)){
									errorMessage.append("<li>Organization name is mandatory in serial number "+serialNo+".</li>");
								}else if(null!=orgName ){
									Long orgId= coreService.getorgIdFromorgName(orgName);
									if(null==orgId || orgId==0){
										errorMessage.append("<li>Invalid Organization name in serial number "+serialNo+".</li>");
									}else{
										shipMaster.setOrgId(orgId);
									}
								}
								
								cell = dataRow.getCell(39);
								String maxEmailSizeInMB = new DataFormatter().formatCellValue(cell);
								if("".equals(maxEmailSizeInMB)){
									errorMessage.append("<li>Max Email size is mandatory in serial number "+serialNo+".</li>");
								}else{
									try{
										shipMaster.setMaxEmailSizeInMB(new BigDecimal(maxEmailSizeInMB));
									}catch(Exception ex){
										errorMessage.append("<li>Invalid Email size in serial number "+serialNo+".</li>");
									}
								}
								
								cell = dataRow.getCell(40);
								String dailyLimitInMb = new DataFormatter().formatCellValue(cell);
								if("".equals(dailyLimitInMb)){
									errorMessage.append("<li>Daily Limit is mandatory in serial number "+serialNo+".</li>");
								}else{
									try{
										shipMaster.setDailyDataLimitInMB(new BigDecimal(dailyLimitInMb));
									}catch(Exception ex){
										errorMessage.append("<li>Invalid Daily Limit in serial number "+serialNo+".</li>");
									}
								}
								
								cell = dataRow.getCell(41);
								String email1 = new DataFormatter().formatCellValue(cell);
								if (!"".equals(email1) && !DrydockWebUtil.isValidEmail(email1)) {
									errorMessage.append("<li>Mail ID 1 is not in correct format in serial number "+serialNo+".</li>");
								}else{
									shipMaster.setEmail1(email1);
								}
								
								cell = dataRow.getCell(42);
								String email2 = new DataFormatter().formatCellValue(cell);
								if (!"".equals(email2) && !DrydockWebUtil.isValidEmail(email2)) {
									errorMessage.append("<li>Mail ID 2 is not in correct format in serial number "+serialNo+".</li>");
								}else{
									shipMaster.setEmail2(email2);
								}
								
								cell = dataRow.getCell(43);
								String phnNo = new DataFormatter().formatCellValue(cell);
								shipMaster.setPhoneNo(phnNo);
								
								cell = dataRow.getCell(44);
								String phnNo1 = new DataFormatter().formatCellValue(cell);
								shipMaster.setPhoneNo1(phnNo1);
								
								cell = dataRow.getCell(45);
								String phnNo2 = new DataFormatter().formatCellValue(cell);
								shipMaster.setPhoneNo2(phnNo2);
								
								
								shipMasterList.add(shipMaster);
							}
							if("".equals(errorMessage.toString())){
								for (Shipmaster ship : shipMasterList) {
									coreService.saveShip(ship, info);
								}
								return shipMasterList;
							}
						}catch(Exception ex){
							ex.printStackTrace();
							errorMessage.append("<li>Some error occured while reading excel.</li>");
						}
					}
				}
				System.out.println("errorMessage - " + errorMessage);
				if(!"".equals(errorMessage.toString())){
					sendError(response, errorMessage.toString());
				}
			}else{
				sendError(response, "Not a proper excel file.");
			}
		}catch(ValidationException ex){
			throw ex;
		}
		catch (Exception e) {
			e.printStackTrace();
			sendError(response, "Some error occured.");
		}finally{
			if(null!=workbook){
				try {
					workbook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		status.add("success");
		return status;
	}
	
	@RequestMapping(value="/getMasterVesselTypeList" , method = RequestMethod.POST)
	public Object getMasterVesselTypeList(HttpServletRequest request,HttpServletResponse response) {
		List<VesselTypeMaster> orgList = new ArrayList<VesselTypeMaster>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			orgList= coreService.getMasterVesselTypeList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return orgList;
	}

	
	
	
	/**
	 * Arnabi Ends
	 */
	
	/**
	 * Rupak Starts
	 */
	
	@RequestMapping("/saveUser")
	public Long saveUser(HttpServletRequest request,
	        HttpServletResponse response,@RequestBody UserDetail userDetails) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.saveUser(userDetails, info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return userDetails.getUser_id();
	}
	
	@RequestMapping(value="/loadUserList" , method = RequestMethod.POST)
	public Object loadUserList(HttpServletRequest request,HttpServletResponse response) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return coreService.loadUserList(info);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		//return "Success";
	}
	
	@RequestMapping(value="/loadUserUnderLoggedInUser" , method = RequestMethod.POST)
	public Object loadUserUnderLoggedInUser(HttpServletRequest request,HttpServletResponse response) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return coreService.loadUserUnderLoggedInUser(info);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		//return "Success";
	}
	
	@RequestMapping(value="/loadAllUserUnderLoggedInUser" , method = RequestMethod.POST)
	public Object loadAllUserUnderLoggedInUser(HttpServletRequest request,HttpServletResponse response) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return coreService.loadAllUserUnderLoggedInUser(info);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		//return "Success";
	}
	
	@RequestMapping(value="/loadUser" , method = RequestMethod.POST)
	public Object loadUser(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("userId") Long userId) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return coreService.getUserbyUserId(userId, info);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/loadSAUser" , method = RequestMethod.POST)
	public Object loadSAUser(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("orgId") Long orgId) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return coreService.loadSAUser(orgId, info);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/checkLoginCodeExists" , method = RequestMethod.POST)
	public Object checkLoginCodeExists(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("userCode") String userCode) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return coreService.checkLoginCodeExists(userCode, info);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/forgotPasswordCheck")
	public void forgotPasswordCheck(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("email") String email, @RequestParam("userCode") String userCode) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			UserDetail userDetails = (UserDetail) coreService.checkLoginCodeExists(userCode, info);
			if(null!=userDetails){
				if(null!=email && email.equalsIgnoreCase(userDetails.getPersonalMailid())){
					System.out.println("Mail and usercode combination correct for forgot passsword.");
				}else{
					sendError(response, "Email address does not match.");
				}
			}else{
				sendError(response, "Invalid usercode");
			}
		}
		catch(ValidationException ex){
			throw ex;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/forgotPassword")
	public void forgotPassword(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("email") String email, @RequestParam("userCode") String userCode, 
			@RequestParam("password") String password) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			UserDetail userDetails = (UserDetail) coreService.checkLoginCodeExists(userCode, info);
			if(null!=userDetails){
				if(null!=email && email.equalsIgnoreCase(userDetails.getPersonalMailid())){
					coreService.forgotPassword(userDetails, password, info);
				}else{
					sendError(response, "Email address does not match.");
				}
			}else{
				sendError(response, "Invalid usercode");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/resetPassword")
	public void resetPassword(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("userId") long userId, @RequestParam("password") String password) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			UserDetail userDetails = (UserDetail) coreService.getUserbyUserId(userId, info);
			if(null!=userDetails){
				coreService.forgotPassword(userDetails, password, info);
			}else{
				sendError(response, "User details not present in database.");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/saveDesignation")
	public Long saveDesignation(HttpServletRequest request,
	        HttpServletResponse response,@RequestBody Designation designation) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.saveDesignation(designation, info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return designation.getDesignation_id();
	}
	
	@RequestMapping("/loadDesignationList")
	public Object loadDesignationList(HttpServletRequest request,HttpServletResponse response) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return coreService.loadDesignationList(info);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/loadDesignationById")
	public Object loadDesignationById(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("designationId") long designationId) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return coreService.loadDesignationById(designationId, info);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/shipListForCreation")
	public Object shipListForCreation(HttpServletRequest request,HttpServletResponse response) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return coreService.loadShipListForCreation(info);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/loadShipDetailsById")
	public Object loadShipDetailsById(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("shipId") long shipId) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return coreService.loadShipDetailsById(shipId, info);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping("/saveShip")
	public Long saveShip(HttpServletRequest request,
	        HttpServletResponse response,@RequestBody Shipmaster shipmaster) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.saveShip(shipmaster, info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return shipmaster.getShip_id();
	}
	
	@RequestMapping("/shipListForAssignment")
	public Object shipListForAssignment(HttpServletRequest request,HttpServletResponse response) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return coreService.loadShipListForAssignment(info);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/loadUserAssignedToShip")
	public Object loadUserAssignedToShip(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("shipId") long shipId) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return coreService.loadUserAssignedToShip(shipId, info);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/addUserToShip")
	public void addUserToShip(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("shipId") long shipId, @RequestParam("userId") List<Long> userIdList) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.addUserToShip(shipId, userIdList, info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/removeUserFromShip")
	public void removeUserFromShip(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("shipId") long shipId, @RequestParam("userId") List<Long> userIdList) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.removeUserFromShip(shipId, userIdList, info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/saveUserShipMapping")
	public void saveUserShipMapping(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("shipId") long shipId, @RequestParam("removedUserIdList") List<Long> removedUserIdList, 
			@RequestParam("addedUserIdList") List<Long> addedUserIdList) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.saveUserShipMapping(shipId, removedUserIdList, addedUserIdList, info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	
	@RequestMapping("uploadUserExcel")	
	public Object uploadUserExcel(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		System.out.println("inside uploadUserExcel");
		List<String> status=new ArrayList<>();
		System.out.println(file.getOriginalFilename());
		Workbook workbook=null;
		StringBuilder errorMessage = new StringBuilder("");
		try {
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			if (file.getOriginalFilename().endsWith("xls")) {
				workbook = new HSSFWorkbook(file.getInputStream());
			} else if (file.getOriginalFilename().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(file.getInputStream());
			} else {
				//sendError(response, "Received file does not have a standard excel extension.");
				errorMessage.append("<li>Received file does not have a standard excel extension.</li>");
			}
			
			if(null!=workbook){
				int numberOfRecords=0;
				int startingRowNumber=2;
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD.MM.YYYY");
				Sheet userSheet=workbook.getSheet("User_Upload");
				if(null==userSheet){
					errorMessage.append("<li>User sheet not found.</li>");
				}else{
					Row userRow=userSheet.getRow(0);
					if(null==userRow){
						errorMessage.append("<li>Number of records not found.</li>");
					}else{
						Cell userCell = userRow.getCell(1);
						try{
							String numberOfRecordsStr = new DataFormatter().formatCellValue(userCell);
							try{
								numberOfRecords=Integer.parseInt(numberOfRecordsStr);
							}catch(Exception ex){
								errorMessage.append("<li>Number of records is inappropriate.</li>");
							}
							System.out.println("numberOfRecords - " + numberOfRecords);
							List<UserDetail> userDetailsList = new ArrayList<>();
							if(numberOfRecords>0){
								List<Designation> designationList = (List<Designation>) coreService.loadDesignationList(info);
								Map<String, Designation> designationMap = new HashMap<>();
								for (Designation designation : designationList) {
									designationMap.put(designation.getCode(), designation);
								}
								Map<String, Department> departmentMap = new HashMap<>();
								List<Department> departmentList = coreService.loadDepartmentList(info);
								for (Department department : departmentList) {
									departmentMap.put(department.getDeptName(), department);
								}
								Map<String, UserDetail> userMap = new HashMap<>();
								List<UserDetail> userList = coreService.loadUserList(info);
								for (UserDetail userDetail : userList) {
									userMap.put(userDetail.getUserCode().toUpperCase(), userDetail);
								}
								List<String> userCodeList = coreService.loadAllUserCodeList(info);
								Map<String, Role> roleMap = new HashMap<>();
								List<Role> roleList = coreService.loadRoleList(info);
								for (Role role : roleList) {
									roleMap.put(role.getCode(), role);
								}
								for(int i=0;i<numberOfRecords;i++){
									UserDetail userDetails = new UserDetail();
									userDetailsList.add(userDetails);
									Row dataRow=userSheet.getRow(startingRowNumber+i);
									Cell cell = dataRow.getCell(0);
									String serialNo = new DataFormatter().formatCellValue(cell);
									cell = dataRow.getCell(1);
									String userType = new DataFormatter().formatCellValue(cell);
									if("".equals(userType)){
										errorMessage.append("<li>User type is mandatory in serial number "+serialNo+".</li>");
									}else if(!"Ship".equals(userType) && !"Office".equals(userType)){
										errorMessage.append("<li>Invalid user type in serial number "+serialNo+".</li>");
									}else{
										if("Ship".equalsIgnoreCase(userType)){
											userDetails.setUserType("1");
										}else{
											userDetails.setUserType("2");
										}
									}
									cell = dataRow.getCell(2);
									String userCode = new DataFormatter().formatCellValue(cell);
									if("".equals(userCode)){
										errorMessage.append("<li>User code is mandatory in serial number "+serialNo+".</li>");
									}else if(userCodeList.contains(userCode.toUpperCase())){
										errorMessage.append("<li>User code '"+userCode+"' already exists in serial number "+serialNo+".</li>");
									}else{
										userDetails.setUserCode(userCode);
									}
									cell = dataRow.getCell(3);
									String firstName = new DataFormatter().formatCellValue(cell);
									if("".equals(firstName)){
										errorMessage.append("<li>First name is mandatory in serial number "+serialNo+".</li>");
									}else{
										userDetails.setFirstname(firstName);
									}
									cell = dataRow.getCell(4);
									String lastName = new DataFormatter().formatCellValue(cell);
									if("".equals(lastName)){
										errorMessage.append("<li>Last name is mandatory in serial number "+serialNo+".</li>");
									}else{
										userDetails.setLastname(lastName);
									}
									cell = dataRow.getCell(5);
									String departmentCode = new DataFormatter().formatCellValue(cell);
									if("".equals(departmentCode)){
										errorMessage.append("<li>Department name is mandatory in serial number "+serialNo+".</li>");
									}else{
										if(departmentMap.containsKey(departmentCode)){
											userDetails.setDeptId(departmentMap.get(departmentCode).getDept_id());
										}else{
											errorMessage.append("<li>Invalid department name '"+departmentCode+"' in serial number "+serialNo+".</li>");
										}
									}
									cell = dataRow.getCell(6);
									String reportingTo = new DataFormatter().formatCellValue(cell);
									if("".equals(reportingTo)){
										errorMessage.append("<li>Reporting to login code is mandatory in serial number "+serialNo+".</li>");
									}else{
										if(userMap.containsKey(reportingTo.toUpperCase())){
											//userDetails.setCurrentReportTo(userMap.get(reportingTo.toUpperCase()).getUser_id());
											userDetails.setReportingToUserObject(userMap.get(reportingTo.toUpperCase())); 
											userMap.put(userDetails.getUserCode(), userDetails);
										}else{
											errorMessage.append("<li>Invalid reporting to login code '"+reportingTo+"' in serial number "+serialNo+".</li>");
										}
									}
									cell = dataRow.getCell(7);
									String address = new DataFormatter().formatCellValue(cell);
									userDetails.setAddress(address);
									cell = dataRow.getCell(8);
									String phoneNo = new DataFormatter().formatCellValue(cell);
									if("".equals(phoneNo)){
										errorMessage.append("<li>Phone number is mandatory in serial number "+serialNo+".</li>");
									}else{
										userDetails.setPhonenumber(phoneNo);
									}
									cell = dataRow.getCell(9);
									String personalMailID = new DataFormatter().formatCellValue(cell);
									if("".equals(personalMailID)){
										errorMessage.append("<li>Mail ID is mandatory in serial number "+serialNo+".</li>");
									}else if (!DrydockWebUtil.isValidEmail(personalMailID)) {
										errorMessage.append("<li>Mail ID is not in correct format in serial number "+serialNo+".</li>");
									}else{
										userDetails.setPersonalMailid(personalMailID);
									}
									cell = dataRow.getCell(10);
									String designationCode = new DataFormatter().formatCellValue(cell);
									if("".equals(designationCode)){
										errorMessage.append("<li>Designation code is mandatory in serial number "+serialNo+".</li>");
									}else{
										if(designationMap.containsKey(designationCode)){
											userDetails.setDesignationId(designationMap.get(designationCode).getDesignation_id());
										}else{
											errorMessage.append("<li>Invalid designation code '"+designationCode+"' in serial number "+serialNo+".</li>");
										}
									}
									cell = dataRow.getCell(11);
									String roleCodeString = new DataFormatter().formatCellValue(cell);
									if("".equals(roleCodeString)){
										errorMessage.append("<li>Role code is mandatory in serial number "+serialNo+".</li>");
									}else{
										userDetails.setRoleList(new ArrayList<Long>());
										String[] roleCodeList = roleCodeString.split(",");
										for (String roleString : roleCodeList) {
											if(roleMap.containsKey(roleString)){
												userDetails.getRoleList().add(roleMap.get(roleString).getRole_id());
											}else{
												errorMessage.append("<li>Invalid role code '"+roleString+"' in serial number "+serialNo+".</li>");
											}
										}
									}
									cell = dataRow.getCell(12);
									String email1 = new DataFormatter().formatCellValue(cell);
									if (!"".equals(email1) && !DrydockWebUtil.isValidEmail(email1)) {
										errorMessage.append("<li>Mail ID1 is not in correct format in serial number "+serialNo+".</li>");
									}else{
										userDetails.setEmail1(email1);
									}
									cell = dataRow.getCell(13);
									String email2 = new DataFormatter().formatCellValue(cell);
									if (!"".equals(email2) && !DrydockWebUtil.isValidEmail(email2)) {
										errorMessage.append("<li>Mail ID 2 is not in correct format in serial number "+serialNo+".</li>");
									}else{
									userDetails.setEmail2(email2);
									}
									cell = dataRow.getCell(14);
									String phone1 = new DataFormatter().formatCellValue(cell);
									userDetails.setPhoneNo1(phone1);
									cell = dataRow.getCell(15);
									String phone2 = new DataFormatter().formatCellValue(cell);
									userDetails.setPhoneNo2(phone2);
									cell = dataRow.getCell(16);
									String passWord = new DataFormatter().formatCellValue(cell);
									if("".equals(passWord)){
										errorMessage.append("<li>passWord is mandatory in serial number "+serialNo+".</li>");
									}else{
									userDetails.setPasscode(passWord);
									}
								}
								if("".equals(errorMessage.toString())){
									coreService.saveUserList(userDetailsList, true, info);
									//return userDetailsList;
								}
							}
						}catch(Exception ex){
							errorMessage.append("<li>Some error occured while reading excel.</li>");
						}
					}
				}
				System.out.println("errorMessage - " + errorMessage);
/*				if(!"".equals(errorMessage.toString())){
					sendError(response, errorMessage.toString());
				}
*/			}else{
				errorMessage.append("<li>Not a proper excel file.</li>");
			}
		} catch (IOException e) {
			e.printStackTrace();
			errorMessage.append("<li>Some error occured.</li>");
		}finally{
			if(null!=workbook){
				try {
					workbook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(!"".equals(errorMessage.toString())){
			status.add(errorMessage.toString());
		}
		return status;
	}

	
	@RequestMapping("uploadDepartmentExcel")	
	public Object uploadDepartmentExcel(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		System.out.println("inside uploadDepartmentExcel");
		List<String> status=new ArrayList<>();
		System.out.println(file.getOriginalFilename());
		Workbook workbook=null;
		StringBuilder errorMessage = new StringBuilder("");
		try {
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			if (file.getOriginalFilename().endsWith("xls")) {
				workbook = new HSSFWorkbook(file.getInputStream());
			} else if (file.getOriginalFilename().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(file.getInputStream());
			} else {
				//sendError(response, "Received file does not have a standard excel extension.");
				errorMessage.append("<li>Received file does not have a standard excel extension.</li>");
			}
			
			if(null!=workbook){
				int numberOfRecords=0;
				int startingRowNumber=2;
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD.MM.YYYY");
				Sheet departmentSheet=workbook.getSheet("Department_Upload");
				if(null==departmentSheet){
					errorMessage.append("<li>Department sheet not found.</li>");
				}else{
					Row departmentRow=departmentSheet.getRow(0);
					if(null==departmentRow){
						errorMessage.append("<li>Number of records not found.</li>");
					}else{
						Cell departmentCell = departmentRow.getCell(1);
						try{
							String numberOfRecordsStr = new DataFormatter().formatCellValue(departmentCell);
							try{
								numberOfRecords=Integer.parseInt(numberOfRecordsStr);
							}catch(Exception ex){
								errorMessage.append("<li>Number of records is inappropriate.</li>");
							}
							System.out.println("numberOfRecords - " + numberOfRecords);
							List<Department> departmentList = new ArrayList<>();
							if(numberOfRecords>0){
								for(int i=0;i<numberOfRecords;i++){
									Department department = new Department();
									departmentList.add(department);
									Row dataRow=departmentSheet.getRow(startingRowNumber+i);
									Cell cell = dataRow.getCell(0);
									String serialNo = new DataFormatter().formatCellValue(cell);
									cell = dataRow.getCell(1);
									String departmentName = new DataFormatter().formatCellValue(cell);
									if("".equals(departmentName)){
										errorMessage.append("<li>Department name is mandatory in serial number "+serialNo+".</li>");
									}else{
										department.setDeptName(departmentName);
									}
									cell = dataRow.getCell(2);
									String departmentEmail = new DataFormatter().formatCellValue(cell);
									if("".equals(departmentEmail)){
										errorMessage.append("<li>Department Email is mandatory in serial number "+serialNo+".</li>");
									}if ("".equals(departmentEmail) && !DrydockWebUtil.isValidEmail(departmentEmail)) {
										errorMessage.append("<li>Mail ID is not in correct format in serial number "+serialNo+".</li>");
									}else{
										department.setDeptMail(departmentEmail);
									}
								}
								if("".equals(errorMessage.toString())){
									coreService.saveDepartmentList(departmentList, info);
									//return userDetailsList;
								}
							}
						}catch(Exception ex){
							errorMessage.append("<li>Some error occured while reading excel.</li>");
						}
					}
				}
				System.out.println("errorMessage - " + errorMessage);
/*				if(!"".equals(errorMessage.toString())){
					sendError(response, errorMessage.toString());
				}
*/			}else{
				errorMessage.append("<li>Not a proper excel file.</li>");
			}
		} catch (IOException e) {
			e.printStackTrace();
			errorMessage.append("<li>Some error occured.</li>");
		}finally{
			if(null!=workbook){
				try {
					workbook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(!"".equals(errorMessage.toString())){
			status.add(errorMessage.toString());
		}
		return status;
	}
	
	@RequestMapping("/saveDockyard")
	public Object saveDockyard(HttpServletRequest request,
	        HttpServletResponse response,@RequestBody DockyardMaster dockyard) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.saveDockyard(dockyard, info);
		}
		catch(Exception e){
			e.printStackTrace();
			sendError(response, "Some error occurred while saving dockyard data");
		}
		return dockyard;
	}
	
	@RequestMapping("/loadDockyardList")
	public Object loadDockyardList(HttpServletRequest request,HttpServletResponse response) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return coreService.loadDockyardList(info);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/loadDockyardById")
	public Object loadDockyardById(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("dockyardId") long dockyardId) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return coreService.loadDockyardById(dockyardId, info);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/updateUserCurrentShipEntry")
	public void updateUserCurrentShipEntry(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("shipId") long shipId) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.updateUserCurrentShipEntry(shipId, info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/loadApplicationVesselDocTypeList" , method = RequestMethod.POST)
	public Object loadApplicationVesselDocTypeList(HttpServletRequest request,HttpServletResponse response) {
		List<ApplicationVesselDocType> appComponentDTOList = new ArrayList<ApplicationVesselDocType>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			appComponentDTOList = coreService.loadApplicationVesselDocTypeList(info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching application vessel doc type list");
		}
		return appComponentDTOList;
	}
	
	@RequestMapping(value="/saveApplicationVesselDocTypeList" , method = RequestMethod.POST)
	public Object saveApplicationVesselDocTypeList(HttpServletRequest request,HttpServletResponse response,
			@RequestBody List<ApplicationVesselDocType> applicationVesselDocTypeList) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.saveApplicationVesselDocTypeList(applicationVesselDocTypeList,info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while saving application vessel doc type.");
		}
		return applicationVesselDocTypeList;
	}	

	@RequestMapping(value="/loadOrganizationVesselDocTypeList" , method = RequestMethod.POST)
	public Object loadOrganizationVesselDocTypeList(HttpServletRequest request,HttpServletResponse response) {
		List<OrganizationVesselDocType> organizationVesselDocTypeList = new ArrayList<OrganizationVesselDocType>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			organizationVesselDocTypeList = coreService.loadOrganizationVesselDocTypeList(info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching Organization vessel doc type list");
		}
		return organizationVesselDocTypeList;
	}
	
	@RequestMapping(value="/saveOrganizationVesselDocTypeList" , method = RequestMethod.POST)
	public Object saveOrganizationVesselDocTypeList(HttpServletRequest request,HttpServletResponse response,
			@RequestBody List<OrganizationVesselDocType> organizationVesselDocTypeList) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			coreService.saveOrganizationVesselDocTypeList(organizationVesselDocTypeList,info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while saving Organization vessel doc type.");
		}
		return organizationVesselDocTypeList;
	}	
	
	@RequestMapping(value="/loadCurrencyList" , method = RequestMethod.POST)
	public Object loadCurrencyList(HttpServletRequest request,HttpServletResponse response) {
		List<CurrencyMaster> currencyList = new ArrayList<CurrencyMaster>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			currencyList= coreService.loadCurrencyList(info);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return currencyList;
		
	}
	
	@RequestMapping(value="/downloadReport" , method = RequestMethod.POST)
	public void downloadReport(HttpServletRequest request,HttpServletResponse response, @RequestParam byte[] file) {
		try{
			System.out.print('A');
//			byte[] bytes = Base64.getDecoder().decode(file);
			 
			FileUtils.writeByteArrayToFile(new File("e:\\test.pdf"), file);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/downloadAllReport" , method = RequestMethod.POST)
	public void downloadAllReport(HttpServletRequest request,HttpServletResponse response, @RequestBody List<KeyValue> reportList) {
		try{
			System.out.print('A');
			System.out.println();
			int i=0;
			for (KeyValue keyValue : reportList) {
				i++;
				System.out.println(keyValue.getValue().getClass()); 
				FileUtils.writeByteArrayToFile(new File("d:\\testPDF\\test"+i+".pdf"), ((String)keyValue.getValue()).getBytes());
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void sendError(HttpServletResponse response, String errorMessage){
		/*try {
			response.setHeader("errorMessage", errorMessage);
			response.setHeader("error", errorMessage);
			
			response.sendError(1000,errorMessage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		throw new ValidationException(errorMessage);
	}
	/**
	 * Rupak Ends
	 */
	
	
}