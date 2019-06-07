package com.drydock.util;

public class DrydockQueryConstant {


	/**
	 * Arnabi Starts
	 */

	/**
	 * Arnabi Ends
	 */

	/**
	 * Rupak Starts
	 */
	public static final String USER_SELECT_BY_USER_ID="SELECT * FROM USER WHERE USER_ID=:user_id";
	public static final String USER_SELECT_BY_USERCODE_PASSWORD = "SELECT * FROM USER WHERE UPPER(USER_CODE)=:userCode AND USER_PASSCODE=:passcode";
	public static final String USER_INSERT = "INSERT INTO USER VALUES(:user_id,:userCode,:passcode,:firstname,:lastname,:address,:phonenumber,:personalMailid,:currentReportTo,:useruid,:uidtype,:imagePath,:orgId,:deptId,:designationId,:createid,:createdate,:updateid,:updatedate,:isactive,:userType,:shipid,:email1,:email2,:phoneNo1,:phoneNo2)";
	public static final String USER_UPDATE = "UPDATE USER SET FIRSTNAME=:firstname, LASTNAME=:lastname, ADDRESS=:address, PHONENO=:phonenumber, EMAIL=:personalMailid, CURRENTREPORTTO=:currentReportTo, USERUID=:useruid, UID_TYPE=:uidtype, IMAGEPATH=:imagePath, DEPTID=:deptId, DESIGNATIONID=:designationId, UPDATE_ID=:updateid, UPDATE_DATE=:updatedate,EMAIL1=:email1,EMAIL2=:email2,PHONENO1=:phoneNo1,PHONENO2=:phoneNo2 WHERE USER_ID=:user_id";
	public static final String USER_LIST_BY_ORGID = "SELECT *, (select concat(firstname, ' ', lastname) from user where user_id=u.currentreportto) reporting_to_name, (select GROUP_CONCAT(description) from role where role_id in (select roleid from user_role where userid=u.user_id)) role_list FROM USER u WHERE ORGID=:orgId";
	public static final String USER_LIST_BY_PARENTUSERID = "SELECT * FROM USER WHERE ORGID=:orgId and currentreportto=:parentUserId";
	public static final String USER_LIST_BY_MULTIPLE_PARENTUSERID = "SELECT * FROM USER WHERE ORGID=:orgId and currentreportto in (:parentUserIds)";
	public static final String USER_SELECT_BY_USERCODE = "SELECT * FROM USER WHERE UPPER(USER_CODE)=:userCode";
	public static final String DESIGNATION_LIST_BY_ORGID = "SELECT * FROM DESIGNATION WHERE ORGID=:orgId and code not in (:defaultDesignationCode)";
	public static final String DESIGNATION_INSERT = "INSERT INTO DESIGNATION VALUES (:designation_id,:code,:description,:orgid,:createid,:createdate,:updateid,:updatedate,:isactive,:designation)";
	public static final String DESIGNATION_UPDATE = "UPDATE DESIGNATION SET CODE=:code, DESCRIPTION=:description, UPDATE_ID=:updateid, UPDATE_DATE=:updatedate,designation=:designation WHERE DESIGNATION_ID=:designation_id";
	public static final String DERSIGNATION_SELECT_BY_ID = "SELECT * FROM DESIGNATION WHERE DESIGNATION_ID=:designation_id";
	public static final String SHIP_LIST_BY_ORGID = "SELECT * FROM SHIP_MASTER WHERE ORGID=:orgId";
	public static final String SHIP_LIST_ALL = "SELECT * FROM SHIP_MASTER";
	public static final String SHIP_SELECT_BY_ID = "SELECT * FROM SHIP_MASTER WHERE SHIPID=:ship_id";
	public static final String SHIP_INSERT = "INSERT INTO SHIP_MASTER VALUES (:shipid,:shipno,:orgid,:name,:description,:vesselname,:v_imo_no,:mmsi_no,:call_sign,:official_no,:v_type,:owner_imo_no,:owner_name,:sat_f_77,:sat_c,:fleet_broadband,:sat_c_emailID,:emailID,:class,:class_notations,:Classi_Id_No,:flag,:port_of_registry,:year_built,:keel_laying_date,:vessel_delivery_date,:hull_type,:length_overall,:breadth_MLD,:depth,:summer_draft_M,:summer_DWT_MT,:international_GRT,:international_NRT,:life_boat_cap,:v_short_name,:account_code_old,:account_code_new,:tel_fac_code,:createid,:createdate,:updateid,:updatedate,:isactive,:maxEmailSizeInMB, :dailyDataLimitInMB,  :email1, :email2, :phoneno, :phoneno1, :phoneno2)";
	public static final String SHIP_UPDATE = "UPDATE SHIP_MASTER SET SHIPNO=:shipno, NAME=:name, DESCRIPTION=:description, VESSELNAME=:vesselname, V_IMO_NO=:v_imo_no,MMSI_NO=:mmsi_no,CALL_SIGN=:call_sign,OFFICIAL_NO=:official_no,V_TYPE=:v_type,OWNER_IMO_NO=:owner_imo_no,OWNER_NAME=:owner_name,SAT_F_77=:sat_f_77,SAT_C=:sat_c,FLEET_BROADBAND=:fleet_broadband,SAT_C_EMAILID=:sat_c_emailID,EMAILID=:emailID,CLASS=:class,CLASS_NOTATIONS=:class_notations,CLASSI_ID_NO=:Classi_Id_No,FLAG=:flag,PORT_OF_REGISTRY=:port_of_registry,YEAR_BUILT=:year_built,KEEL_LAYING_DATE=:keel_laying_date,VESSEL_DELIVERY_DATE=:vessel_delivery_date,HULL_TYPE=:hull_type,LENGTH_OVERALL=:length_overall,BREADTH_MLD=:breadth_MLD,DEPTH=:depth,SUMMER_DRAFT_M=:summer_draft_M,SUMMER_DWT_MT=:summer_DWT_MT,INTERNATIONAL_GRT=:international_GRT,INTERNATIONAL_NRT=:international_NRT,LIFE_BOAT_CAP=:life_boat_cap,V_SHORT_NAME=:v_short_name,ACCOUNT_CODE_OLD=:account_code_old,ACCOUNT_CODE_NEW=:account_code_new,TEL_FAC_CODE=:tel_fac_code,UPDATEID=:updateid,UPDATEDATE=:updatedate, daily_data_limit_in_mb=:dailyDataLimitInMB, max_email_size_in_mb=:maxEmailSizeInMB,email1=:email1,email2=:email2, phoneno=:phoneno, phoneno1=:phoneno1,phoneno2=:phoneno2 WHERE shipid=:shipid";
	public static final String SHIP_LIST_FOR_ASSIGNMENT = "SELECT *, (select group_concat(concat(firstname, ' ', lastname)) from user where user_id in (select userid from user_ship where shipid=sm.shipid)) assigned_users FROM SHIP_MASTER sm WHERE ORGID=:orgId AND SHIPID IN (SELECT SHIPID FROM USER_SHIP WHERE USERID=:userId)";
	public static final String SHIP_LIST_FOR_SHIP_USER = "SELECT *, (select group_concat(concat(firstname, ' ', lastname)) from user where user_id=:userId) assigned_users FROM SHIP_MASTER sm WHERE SHIPID IN (SELECT SHIPID FROM USER WHERE USER_ID=:userId)";
	public static final String USER_SHIP_ASSIGNMENT_LIST_BY_USERID = "SELECT * FROM USER_SHIP WHERE SHIPID=:shipId AND USERID=:userId";
	public static final String USER_SHIP_ASSIGNMENT_LIST = "SELECT * FROM USER WHERE user_id in(SELECT userid FROM USER_SHIP WHERE SHIPID=:shipId)";
	public static final String SHIP_USER_MAPPING_INSERT = "INSERT INTO USER_SHIP VALUES (:id,:userId,:shipId,:createid,:createdate,:updateid,:updatedate,:isactive)";
	public static final String SHIP_USER_MAPPING_DELETE = "DELETE FROM USER_SHIP WHERE USERID=:userId AND SHIPID=:shipId";
	public static final String USER_ROLE_ASSIGNMENT_LIST = "SELECT * FROM USER_ROLE WHERE USERID=:userId";
	public static final String ROLE_USER_MAPPING_INSERT = "INSERT INTO USER_ROLE VALUES (:id,:roleId,:userId,:createid,:createdate,:updateid,:updatedate,:isactive)";
	public static final String ROLE_USER_MAPPING_DELETE = "DELETE FROM USER_ROLE WHERE USERID=:userId AND ROLEID=:roleId";
	public static final String USER_ASSIGNED_TO_SHIP = "SELECT * FROM USER WHERE USER_ID in (SELECT USERID FROM USER_SHIP WHERE SHIPID=:shipId)";
	public static final String USER_PASSWORD_UPDATE = "UPDATE USER SET USER_PASSCODE=:passcode, UPDATE_ID=:updateid, UPDATE_DATE=:updatedate WHERE USER_ID=:user_id";
	public static final String SA_USER_LIST = "SELECT USERID FROM USER_ROLE WHERE ROLEID in (SELECT ROLE_ID FROM ROLE WHERE ORGID=:orgid and code in (:superAdminRoleCode))";
	public static final String SA_ALL_USER_CODE_LIST = "SELECT UPPER(USER_CODE) FROM USER";
	public static final String VESSEL_TYPE_MASTER = "SELECT * FROM vessel_type_master WHERE code=:code";
	public static final String VESSEL_TYPE_MASTER_BY_ID = "SELECT * FROM vessel_type_master WHERE id=:id";
	public static final String VESSEL_TYPE_MASTER_LIST = "SELECT * FROM vessel_type_master";
	public static final String ORG_ID = "select * from Organization where org_name =:orgName";
	public static final String SA_USER_SELECT_BY_ORGID = "SELECT * FROM USER WHERE USER_ID in (SELECT USERID FROM USER_ROLE WHERE ROLEID IN (SELECT ROLE_ID FROM ROLE WHERE CODE=:superAdminRoleCode AND ORGID=:orgId))";
	
	
	//Project module queries
	public static final String APPLICATION_COMPONENT_LIST = "select * from application_component where isactive=:isactive";
	public static final String APPLICATION_COMPONENT_DELETE = "update application_component set isactive=:isactive where id=:applicationcomponentid";
	public static final String ORGANIZATION_COMPONENT_LIST = "select * from organization_component where orgid=:orgid and isactive=:isactive";
	public static final String ORGANIZATION_COMPONENT_DETAILS = "select * from organization_component where orgid=:orgid and id=:orgComponentId";
	public static final String ORGANIZATION_COMPONENT_DELETE = "update organization_component set isactive=:isactive where id=:organizationcomponentid";
	public static final String APPLICATION_COMPONENT_INSERT = "insert into application_component values (:id, :code, :description, :parentcode, :createid, :createdate, :updateid, :updatedate, :isactive)";
	public static final String APPLICATION_COMPONENT_UPDATE = "update application_component set  code= :code, description =:description, parentcode = :parentcode, createid=:createid,createdate= :createdate, updateid=:updateid, updatedate = :updatedate,isactive= :isactive where id=:id";
	public static final String ORGANIZATION_COMPONENT_INSERT = "insert into organization_component values (:id, :orgid, :applicationcomponentid, :code, :description, :parentcode, :createid, :createdate, :updateid, :updatedate, :isactive)";
	public static final String ORGANIZATION_COMPONENT_UPDATE = "update organization_component set orgid=:orgid, applicationcomponentid=:applicationcomponentid, code= :code, description =:description, parentcode = :parentcode, createid=:createid,createdate= :createdate, updateid=:updateid, updatedate = :updatedate,isactive= :isactive where id=:id";
	public static final String SHIP_COMPONENT_DETAILS = "select * from ship_component_details where shipid=:shipId and orgcomponentid=:organizationcomponentid and isactive=:isactive and orgid=:orgid";
	public static final String SHIP_COMPONENT_DETAILS_INSERT = "insert into ship_component_details values(:id, :orgid, :orgcomponentid, :shipid, :make, :model, :description, :createid, :createdate, :updateid, :updatedate, :isactive)";
	public static final String SHIP_COMPONENT_DETAILS_UPDATE = "update ship_component_details set orgid=:orgid, orgcomponentid=:orgcomponentid, shipid=:shipid, make=:make, model=:model, description=:description, createid=:createid,createdate= :createdate, updateid=:updateid, updatedate = :updatedate,isactive= :isactive where id=:id";
	public static final String JOB_LIST_ALL_SHIP = "select *, (select concat(firstname, ' ', lastname)  from user u where u.user_id=j.updateid) updatedby, (select concat(firstname, ' ', lastname)  from user u where u.user_id=j.approval_last_updatedby) applastupdatedby from job j where orgid=:orgid and isactive=:isactive and projectid in (select id from project where orgid=:orgid and status=:projectstatus)";
	public static final String APPROVED_JOB_LIST_ALL_SHIP = "select *, (select concat(firstname, ' ', lastname)  from user u where u.user_id=j.updateid) updatedby, (select concat(firstname, ' ', lastname)  from user u where u.user_id=j.approval_last_updatedby) applastupdatedby from job j where orgid=:orgid and isactive=:isactive and projectid in (select id from project where orgid=:orgid and status=:projectstatus and approval_flag='Y')";
	public static final String JOB_LIST_SINGLE_SHIP = "select *, (select concat(firstname, ' ', lastname)  from user u where u.user_id=j.updateid) updatedby, (select concat(firstname, ' ', lastname)  from user u where u.user_id=j.approval_last_updatedby) applastupdatedby from job j where orgid=:orgid and shipid=:shipid and isactive=:isactive and projectid in (select id from project where orgid=:orgid and status=:projectstatus)";
	public static final String APPROVED_JOB_LIST_SINGLE_SHIP = "select *, (select concat(firstname, ' ', lastname)  from user u where u.user_id=j.updateid) updatedby, (select concat(firstname, ' ', lastname)  from user u where u.user_id=j.approval_last_updatedby) applastupdatedby from job j where orgid=:orgid and shipid=:shipid and isactive=:isactive and projectid in (select id from project where orgid=:orgid and status=:projectstatus and approval_flag='Y')";
	public static final String PROJECT_LIST_ALL_SHIP = "select * from project where orgid=:orgid and isactive=:isactive and status=:projectstatus AND SHIPID IN (SELECT SHIPID FROM USER_SHIP WHERE USERID=:userId)";
	public static final String PROJECT_LIST_ALL_SHIP_ALL = "select * from project where orgid=:orgid and isactive=:isactive AND SHIPID IN (SELECT SHIPID FROM USER_SHIP WHERE USERID=:userId)";
	public static final String PROJECT_LIST_SINGLE_SHIP = "select * from project where orgid=:orgid and shipid=:shipid and isactive=:isactive and status=:projectstatus";
	public static final String PROJECT_LIST_SINGLE_SHIP_ALL = "select * from project where orgid=:orgid and shipid=:shipid and isactive=:isactive";
	public static final String JOB_DETAILS = "select * from job where orgid=:orgid and isactive=:isactive and id=:jobid";
	public static final String PROJECT_DETAILS = "select * from project where orgid=:orgid and isactive=:isactive and id=:projectid";
	public static final String PROJECT_INSERT = "insert into project values(:id, :description, :orgid, :orgcomponentid, :shipid, :status, :startdate, :enddate, :createid, :createdate, :updateid, :updatedate, :isactive, :dockyard, :preamble, :estimatedStart, :estimatedFinish, :actualStart, :actualFinish, :currencyMasterId, null, null, null)";
	public static final String PROJECT_UPDATE = "update project set  description=:description, orgid=:orgid, orgcomponentid=:orgcomponentid, shipid=:shipid, status=:status, startdate=:startdate, enddate=:enddate, createid=:createid,createdate= :createdate, updateid=:updateid, updatedate = :updatedate, isactive= :isactive, dockyard=:dockyard, preamble=:preamble, estimated_start=:estimatedStart, estimated_finish=:estimatedFinish, actual_start=:actualStart, actual_finish=:actualFinish, currency_masterid=:currencyMasterId where id=:id";
	public static final String JOB_INSERT = "insert into job values(:id, :orgid, :shipid, :projectid, :description, :shipcomponentid, :status, :createid, :createdate, :updateid, :updatedate, :isactive, :job_date, :job_no, :account_no, :specification, :location, :detailed_specification, :total_area, :checkboxes, :estimated_budget, :currency, :equipment, :make, :model, :makeModelDescription, :externalReference, 'Y', null, null, null, null )";
	public static final String JOB_UPDATE = "update job set orgid=:orgid, shipid=:shipid, projectid=:projectid, description=:description, shipcomponentid=:shipcomponentid, status=:status, updateid=:updateid, updatedate = :updatedate,isactive= :isactive, job_date=:job_date, job_no=:job_no, account_no=:account_no, specification=:specification, location=:location, detailed_specification=:detailed_specification, total_area=:total_area, checkboxes=:checkboxes, estimated_budget=:estimated_budget, currency=:currency, equipment=:equipment, make=:make, model=:model, makeModelDescription=:makeModelDescription, externalReference=:externalReference  where id=:id";
	public static final String SHIP_COMPONENT_DETAILS_LIST = "select * from ship_component_details where shipid=:shipId and isactive=:isactive and orgid=:orgid";
	public static final String JOB_ATTACHMENT_LIST = "select * from job_attachment where orgid=:orgId and shipid=:shipId and projectid=:projectId and jobid=:jobId and isactive=:isactive";
	public static final String JOB_ATTACHMENT_INSERT = "insert into job_attachment values (:id, :orgId, :shipId, :projectId, :jobId, :attachmentName, :relativePath, :createid, :createdate, :updateid, :updatedate, :isactive, :attachmentDescription, :attachmentType, :description, :progressreportid)";
	public static final String JOB_ATTACHMENT_UPDATE = "update job_attachment set orgid=:orgId, shipid=:shipId, projectid=:projectId, jobid=:jobId, attachment_name=:attachmentName, relativepath=:relativePath, progressreportid=:progressreportid, updateid=:updateid, updatedate = :updatedate,isactive= :isactive, attachment_description=:attachmentDescription, attachment_type=:attachmentType, description=:description where id=:id";
	public static final String JOB_ATTACHMENT_DELETE = "update job_attachment set isactive=:isactive where id=:id";
	public static final String UPDATE_JOB_STATUS = "update job set status=:status, job_closer_remark=:jobCloserRemark, updateid=:updateid, updatedate = :updatedate where id=:id";
	public static final String UPDATE_PROJECT_STATUS = "update project set status=:status, closerAttachmentRelativePath=:closerAttachmentRelativePath, closerAttachmentName=:closerAttachmentName, updateid=:updateid, updatedate = :updatedate where id=:id";
	public static final String JOB_LIST_SINGLE_PROJECT = "select * from job where isactive=:isactive and projectid=:projectId and status=:status";
	public static final String APPLICATION_JOB_LIST = "select * from application_job where isactive=:isactive";
	public static final String APPLICATION_JOB_DETAILS = "select * from application_job where isactive=:isactive and id=:jobid";
	public static final String APPLICATION_JOB_INSERT = "insert into application_job values (:id, :description, :job_no, :account_no, :specification, :location, :detailed_specification, :total_area, :checkboxes, :createid, :createdate, :updateid, :updatedate, :isactive, :shipcomponentid, :make, :model, :makeModelDescription, :equipment, :jobdate, :vesselType, :vesselAge)";
	public static final String APPLICATION_JOB_UPDATE = "update application_job set description=:description, createid=:createid,createdate= :createdate, updateid=:updateid, updatedate = :updatedate,isactive= :isactive, job_no=:job_no, account_no=:account_no, specification=:specification, location=:location, detailed_specification=:detailed_specification, total_area=:total_area, checkboxes=:checkboxes, shipcomponentid=:shipcomponentid, make=:make, model=:model, makeModelDescription=:makeModelDescription, equipment=:equipment, job_date=:jobdate, vessel_type=:vesselType, vessel_age=:vesselAge where id=:id";
	public static final String JOB_COMMENT_INSERT = "insert into job_comment values (:id, :jobId, :jobComment, :createid, :createdate, :updateid, :updatedate, :isactive)";
	public static final String JOB_COMMENT_LIST = "select *,(select concat(firstname, ' ', lastname)  from user u where u.user_id=jc.updateid) updatedby from job_comment jc where jobid=:jobId and isactive=:isactive";
	public static final String MY_JOB_LIST_SINGLE_SHIP = "select * from job where createid=:createid and shipid=:shipid and isactive=:isactive";
	public static final String MY_JOB_LIST_ALL_SHIP = "select * from job where createid=:createid and isactive=:isactive";
	public static final String CHECKBOX_LIST_ALL = "select * from job_creation_checkboxes where isactive=:isactive";
	public static final String DOCKYARD_MASTER_INSERT = "insert into dockyard_master values (:id, :orgid, :dockyard, :createid, :createdate, :updateid, :updatedate, :isactive)";
	public static final String DOCKYARD_MASTER_UPDATE = "update dockyard_master set orgid=:orgid, dockyard=:dockyard, createid=:createid,createdate= :createdate, updateid=:updateid, updatedate = :updatedate,isactive= :isactive where id=:id";
	public static final String DOCKYARD_LIST_BY_ORGID = "select * from dockyard_master where orgid=:orgid and isactive=:isactive";
	public static final String DOCKYARD_SELECT_BY_ID = "select * from dockyard_master where id=:dockyardId";
	public static final String ORGANIZATION_JOB_INSERT = "insert into organization_job values (:id, :description, :job_no, :account_no, :specification, :location, :detailed_specification, :total_area, :checkboxes, :createid, :createdate, :updateid, :updatedate, :isactive, :shipcomponentid, :orgid, :applicationjobid, :make, :model, :makeModelDescription, :equipment, :jobdate, :vesselType, :vesselAge)";
	public static final String ORGANIZATION_JOB_UPDATE = "update organization_job set description=:description, createid=:createid,createdate= :createdate, updateid=:updateid, updatedate = :updatedate,isactive= :isactive, job_no=:job_no, account_no=:account_no, specification=:specification, location=:location, detailed_specification=:detailed_specification, total_area=:total_area, checkboxes=:checkboxes, shipcomponentid=:shipcomponentid, make=:make, model=:model, makeModelDescription=:makeModelDescription, equipment=:equipment, job_date=:jobdate, vessel_type=:vesselType, vessel_age=:vesselAge where id=:id";
	public static final String JOB_LIST_ALL_ORGANIZATION = "select * from organization_job where orgid=:orgid and isactive=:isactive";
	public static final String ORGANIZATION_JOB_DETAILS = "select * from organization_job where orgid=:orgid and isactive=:isactive and id=:jobid";
	public static final String COMPLETED_PROJECT_LIST_SINGLE_SHIP = "select * from project where orgid=:orgid and shipid=:shipid and isactive=:isactive and status=:projectstatus";
	public static final String ACTIVE_PROJECT_DETAIL = "select * from project where orgid=:orgid and shipid=:shipid and isactive=:isactive and status=:projectstatus";
	/**
	 * Rupak Ends
	 */
	public static final String USER_CURRENT_SHIP_INSERT = "insert into user_current_ship values (:userId, :shipId, :createdate)";	
	public static final String USER_CURRENT_SHIP_UPDATE = "update user_current_ship set shipid=:shipId, createdate=:createdate where userid=:userId";
	public static final String SHIP_ATTACHMENT_LIST = "select * from ship_attachment where orgid=:orgId and shipid=:shipId and isactive=:isactive";
	public static final String SHIP_ATTACHMENT_INSERT = "insert into ship_attachment values (:id, :orgId, :shipId, :attachmentName, :relativePath, :createid, :createdate, :updateid, :updatedate, :isactive, :attachmentType, :attachmentDescription, :vesselDocTypeId)";
	public static final String SHIP_ATTACHMENT_UPDATE = "update ship_attachment set orgid=:orgId, shipid=:shipId, attachment_name=:attachmentName, relativepath=:relativePath, createid=:createid,createdate= :createdate, updateid=:updateid, updatedate = :updatedate,isactive= :isactive, attachment_type=:attachmentType, attachment_description=:attachmentDescription, vessel_doc_typeid=:vesselDocTypeId where id=:id";
	public static final String SHIP_ATTACHMENT_DELETE = "update ship_attachment set isactive=:isactive where id=:id";
	public static final String LOAD_APPLICATION_VESSEL_DOC_TYPE_LIST = "select * from application_vessel_doc_type where is_active=:isactive";
	public static final String LOAD_ORGANIZATION_VESSEL_DOC_TYPE_LIST = "select * from organization_vessel_doc_type where org_id=:orgId and is_active=:isactive";
	public static final String APPLICATION_VESSEL_DOC_TYPE_UPDATE = "update application_vessel_doc_type set vessel_doc_description=:vesselDocDescription, update_id=:updateid, update_date = :updatedate, is_active= :isactive where id=:id";
	public static final String APPLICATION_VESSEL_DOC_TYPE_INSERT = "insert into application_vessel_doc_type values (:id, :vesselDocDescription, :createid, :createdate, :updateid, :updatedate, :isactive)";
	public static final String ORGANIZATION_VESSEL_DOC_TYPE_UPDATE = "update organization_vessel_doc_type set org_id=:orgid, vessel_doc_description=:vesselDocDescription, update_id=:updateid, update_date = :updatedate, is_active= :isactive where id=:id";
	public static final String ORGANIZATION_VESSEL_DOC_TYPE_INSERT = "insert into organization_vessel_doc_type values (:id, :orgid, :vesselDocDescription, :createid, :createdate, :updateid, :updatedate, :isactive)";
	public static final String CURRENCY_LIST = "select * from currency_master where is_active=:isactive";
	public static final String DELETE_APPLICATION_VESSEL_DOC_TYPE = "delete from application_vessel_doc_type where id=:id";
	public static final String DELETE_ORGANIZATION_VESSEL_DOC_TYPE = "delete from organization_vessel_doc_type where id=:id";
	public static final String DELETE_JOB_MATERIAL_DETAILS = "delete from job_material_details where id=:id";
	public static final String DELETE_APPLICATION_JOB_MATERIAL_DETAILS = "delete from application_job_material_details where id=:id";
	public static final String DELETE_ORGANIZATION_JOB_MATERIAL_DETAILS = "delete from organization_job_material_details where id=:id";
	public static final String JOB_MATERIAL_DETAILS_UPDATE = "update job_material_details set jobid=:jobid, make=:make, model=:model, part_no=:partNo, part_name=:partName, quantity=:quantity, remarks=:remarks, update_id=:updateid, update_date = :updatedate, is_active= :isactive, uom=:uom, drawingNo=:drawingNo where id=:id";
	public static final String APPLICATION_JOB_MATERIAL_DETAILS_UPDATE = "update application_job_material_details set applicationjobid=:applicationjobid, make=:make, model=:model, part_no=:partNo, part_name=:partName, quantity=:quantity, remarks=:remarks, update_id=:updateid, update_date = :updatedate, is_active= :isactive, uom=:uom, drawingNo=:drawingNo where id=:id";
	public static final String ORGANIZATION_JOB_MATERIAL_DETAILS_UPDATE = "update organization_job_material_details set organizationjobid=:organizationjobid, make=:make, model=:model, part_no=:partNo, part_name=:partName, quantity=:quantity, remarks=:remarks, update_id=:updateid, update_date = :updatedate, is_active= :isactive, uom=:uom, drawingNo=:drawingNo where id=:id";
	public static final String JOB_MATERIAL_DETAILS_INSERT = "insert into job_material_details values (:id, :jobid, :make, :model, :partNo, :partName, :quantity, :remarks, :createid, :createdate, :updateid, :updatedate, :isactive, :uom, :drawingNo)";
	public static final String APPLICATION_JOB_MATERIAL_DETAILS_INSERT = "insert into application_job_material_details values (:id, :applicationjobid, :make, :model, :partNo, :partName, :quantity, :remarks, :createid, :createdate, :updateid, :updatedate, :isactive, :uom, :drawingNo)";
	public static final String ORGANIZATION_JOB_MATERIAL_DETAILS_INSERT = "insert into organization_job_material_details values (:id, :organizationjobid, :make, :model, :partNo, :partName, :quantity, :remarks, :createid, :createdate, :updateid, :updatedate, :isactive, :uom, :drawingNo)";
	public static final String JOB_MATERIAL_DETAILS_LIST = "select * from job_material_details where jobid=:jobId and is_active=:isactive";
	public static final String APPLICATION_JOB_MATERIAL_DETAILS_LIST = "select * from application_job_material_details where applicationjobid=:applicationjobId and is_active=:isactive";
	public static final String ORGANIZATION_JOB_MATERIAL_DETAILS_LIST = "select * from organization_job_material_details where organizationjobid=:organizationjobId and is_active=:isactive";
	public static final String JOB_PROGRESS_REPORT_UPDATE = "update job_progress_report set reportingdate=:reportingdate, executiondate=:executiondate, workdone=:workdone, jobattachmentids=:jobattachmentids, update_id=:updateid, update_date = :updatedate, is_active= :isactive where id=:id";
	public static final String JOB_PROGRESS_REPORT_INSERT = "insert into job_progress_report values (:id, :jobid, :reportingdate, :executiondate, :workdone, :jobattachmentids, :createid, :createdate, :updateid, :updatedate, :isactive)";
	public static final String JOB_PROGRESS_REPORT_LIST = "select * from job_progress_report where jobid=:jobId and is_active=:isactive";
	public static final String JOB_PROGRESS_REPORT_ATTACHMENT_LIST = "select * from job_attachment where orgid=:orgId and shipid=:shipId and projectid=:projectId and jobid=:jobId and progressreportid=:progressreportid and isactive=:isactive";
	public static final String PROJECT_ATTACHMENT_LIST = "select * from project_attachment where orgid=:orgId and shipid=:shipId and projectid=:projectId and isactive=:isactive";
	public static final String PROJECT_ATTACHMENT_DELETE = "update project_attachment set isactive=:isactive where id=:id";
	public static final String PROJECT_ATTACHMENT_UPDATE = "update project_attachment set orgid=:orgId, shipid=:shipId, projectid=:projectId, attachment_name=:attachmentName, relativepath=:relativePath, attachment_description=:attachmentDescription, attachment_type=:attachmentType, updateid=:updateid, updatedate = :updatedate,isactive= :isactive where id=:id";
	public static final String PROJECT_ATTACHMENT_INSERT = "insert into project_attachment values (:id, :orgId, :shipId, :projectId, :attachmentName, :relativePath, :attachmentDescription, :attachmentType, :createid, :createdate, :updateid, :updatedate, :isactive)";
	public static final String COUNT_JOB = "select count(*) from job where projectid=:projectid";
	public static final String PROJECT_DOCKYARD_LIST = "select * from project_dockyard where orgid=:orgId and shipid=:shipId and projectid=:projectId and isactive=:isactive";
	public static final String PROJECT_DOCKYARD_DELETE = "update project_dockyard set isactive=:isactive where id=:id";
	public static final String PROJECT_DOCKYARD_UPDATE = "update project_dockyard set orgid=:orgId, shipid=:shipId, projectid=:projectId, dockyardid=:dockyardid, contactdetails=:contactdetails, remarks=:remarks, default_currencyid=:default_currencyid, updateid=:updateid, updatedate = :updatedate,isactive= :isactive where id=:id";
	public static final String PROJECT_DOCKYARD_INSERT = "insert into project_dockyard values (:id, :orgId, :shipId, :projectId, :dockyardid, :contactdetails, :remarks, :default_currencyid, :createid, :createdate, :updateid, :updatedate, :isactive)";
	public static final String PROJECT_CURRENCY_CONVERSION_UPDATE = "update project_currency_conversion set orgid=:orgId, shipid=:shipId, projectid=:projectId, tocurrencyid=:tocurrencyid, fromcurrencyid=:fromcurrencyid, conversion_rate=:conversionRate, updateid=:updateid, updatedate = :updatedate,isactive= :isactive where id=:id";
	public static final String PROJECT_CURRENCY_CONVERSION_INSERT = "insert into project_currency_conversion values (:id, :orgId, :shipId, :projectId, :fromcurrencyid, :tocurrencyid, :conversionRate, :createid, :createdate, :updateid, :updatedate, :isactive)";
	public static final String PROJECT_CURRENCY_CONVERSION_LIST = "select * from project_currency_conversion where orgid=:orgId and shipid=:shipId and projectid=:projectId and isactive=:isactive";
	public static final String PROJECT_JOB_UPDATE = "update job set approval_flag=:approvalFlag, approval_comment=:approvalComment, approval_last_updatedby=:appLastUpdtdBy, approval_last_updatedon=:appLastUpdtdOn where id=:jobId";
	public static final String PROJECT_JOB_COST_LINEITEM_LIST = "select * from project_job_cost_lineitem where orgid=:orgId and shipid=:shipId and projectid=:projectId and isactive=:isactive";
	public static final String PROJECT_JOB_COST_LINEITEM_DETAILS_LIST = "select * from project_job_cost_lineitem_details where orgid=:orgId and shipid=:shipId and projectid=:projectId and isactive=:isactive";
	public static final String PROJECT_JOB_COST_LINEITEM_INSERT = "insert into project_job_cost_lineitem values (:id, :orgId, :shipId, :projectId, :jobId, :lineitem, :createid, :createdate, :updateid, :updatedate, :isactive)";
	public static final String PROJECT_JOB_COST_LINEITEM_UPDATE = "update project_job_cost_lineitem set orgid=:orgId, shipid=:shipId, projectid=:projectId, jobid=:jobId, lineitem=:lineitem, updateid=:updateid, updatedate = :updatedate,isactive= :isactive where id=:id";
	public static final String PROJECT_JOB_COST_LINEITEM_DETAILS_INSERT = "insert into project_job_cost_lineitem_details values (:id, :orgId, :shipId, :projectId, :jobId, :lineitemId, :quoteCurrencyId, :unit, :unitPrice, :unitQuantity, :costQuoteCurrency, :costProjectCurrency, :createid, :createdate, :updateid, :updatedate, :isactive, :dockyardId)";
	public static final String PROJECT_JOB_COST_LINEITEM_DETAILS_UPDATE = "update project_job_cost_lineitem_details set orgid=:orgId, shipid=:shipId, projectid=:projectId, jobid=:jobId, lineitemid=:lineitemId, quote_currencyid=:quoteCurrencyId, unit=:unit, unit_price=:unitPrice, unit_qty=:unitQuantity, cost_quote_currency=:costQuoteCurrency, cost_project_currency=:costProjectCurrency, updateid=:updateid, updatedate = :updatedate,isactive= :isactive, dockyardid=:dockyardId where id=:id";
	public static final String DELETE_PROJECT_JOB_COST_LINEITEM = "update project_job_cost_lineitem set isactive=:isactive where id=:id";
	public static final String DELETE_PROJECT_JOB_COST_LINEITEM_DETAIL = "update project_job_cost_lineitem_details set isactive=:isactive where id=:id";
	
	
	
	
	
	/*data transfer query constant*/
	public static final String DATA_TRANSFERAPPLICATION_JOB = "select * from application_job";
	public static final String DATA_TRANSFER_APPLICATION_JOB_MATERIAL_DETAILS_LIST = "select * from application_job_material_details";
	public static final String DATA_TRANSFER_ORGANIZATION_JOB = "select * from organization_job";
	public static final String DATA_TRANSFER_ORGANIZATION_JOB_MATERIAL_DETAILS_LIST = "select * from organization_job_material_details";
	public static final String DATA_TRANSFER_SHIP_COMPONENT_DETAIL = "select * from ship_component_details";
	
	
}
