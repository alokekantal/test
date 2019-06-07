package com.drydock.repository.impl;

import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.dto.KeyValueDTO;
import com.drydock.entity.ApplicationComponent;
import com.drydock.entity.ApplicationJob;
import com.drydock.entity.ApplicationJobMaterialDetails;
import com.drydock.entity.Job;
import com.drydock.entity.JobAttachment;
import com.drydock.entity.JobComment;
import com.drydock.entity.JobCreationCheckboxes;
import com.drydock.entity.JobMaterialDetails;
import com.drydock.entity.JobProgressReport;
import com.drydock.entity.Organization;
import com.drydock.entity.OrganizationComponent;
import com.drydock.entity.OrganizationJob;
import com.drydock.entity.OrganizationJobMaterialDetails;
import com.drydock.entity.Project;
import com.drydock.entity.ProjectAttachment;
import com.drydock.entity.ProjectCurrencyConversion;
import com.drydock.entity.ProjectDockyard;
import com.drydock.entity.ProjectJobCostLineitem;
import com.drydock.entity.ProjectJobCostLineitemDetails;
import com.drydock.entity.ShipComponentDetails;
import com.drydock.entity.Shipmaster;
import com.drydock.mapper.ApplicationComponentMapper;
import com.drydock.mapper.ApplicationJobMapper;
import com.drydock.mapper.ApplicationJobMaterialDetailsMapper;
import com.drydock.mapper.JobAttachmentMapper;
import com.drydock.mapper.JobCommentMapper;
import com.drydock.mapper.JobCreationCheckboxesMapper;
import com.drydock.mapper.JobMapper;
import com.drydock.mapper.JobMaterialDetailsMapper;
import com.drydock.mapper.JobProgressReportMapper;
import com.drydock.mapper.OrganizationComponentMapper;
import com.drydock.mapper.OrganizationJobMapper;
import com.drydock.mapper.OrganizationJobMaterialDetailsMapper;
import com.drydock.mapper.ProjectAttachmentMapper;
import com.drydock.mapper.ProjectCurrencyConversionMapper;
import com.drydock.mapper.ProjectDockyardMapper;
import com.drydock.mapper.ProjectJobCostLineitemDetailsMapper;
import com.drydock.mapper.ProjectJobCostLineitemMapper;
import com.drydock.mapper.ProjectMapper;
import com.drydock.mapper.ShipComponentDetailsMapper;
import com.drydock.repository.CoreRepository;
import com.drydock.repository.ProjectRepository;
import com.drydock.repository.SequenceValueGenerator;
import com.drydock.util.DrydockConstant;
import com.drydock.util.DrydockQueryConstant;
import com.drydock.util.PDFGenerator;

@Component
public class ProjectRepositoryImpl implements ProjectRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	SequenceValueGenerator sequenceValueGenerator;
	
	@Autowired
	CoreRepository coreRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;

    @Override
	public List<ApplicationComponent> loadApplicationComponentList(BasicInfoDTO info) throws Exception{
	    List<ApplicationComponent> appComponentList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			appComponentList =(List<ApplicationComponent>) namedParameterJdbcTemplate.query(DrydockQueryConstant.APPLICATION_COMPONENT_LIST,parameters, new ApplicationComponentMapper());
		}
		catch(Exception e){
			throw e;
		}
	    return appComponentList;
	}
	
	@Override
	public void saveApplicationComponent(ApplicationComponent applicationComponent, BasicInfoDTO info) throws Exception{
		try{
			String sql="";
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			if(applicationComponent.getId()==0)
			{
				Long applicationComponentidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.APPLICATION_COMPONENT_SEQ);

				sql = DrydockQueryConstant.APPLICATION_COMPONENT_INSERT;
				applicationComponent.setId(applicationComponentidSeq);
				parameters.addValue("id", applicationComponentidSeq);
				parameters.addValue("createid", info.getUserId());
				parameters.addValue("createdate", new Date());
			}
			else{
				sql = DrydockQueryConstant.APPLICATION_COMPONENT_UPDATE;
				parameters.addValue("id", applicationComponent.getId());
				parameters.addValue("createid", applicationComponent.getCreateid());
				parameters.addValue("createdate", applicationComponent.getCreatedate());
			}
			parameters.addValue("code", applicationComponent.getCode());
			parameters.addValue("description", applicationComponent.getDescription());
			parameters.addValue("parentcode", applicationComponent.getParentcode());
			parameters.addValue("updateid", info.getUserId());
			parameters.addValue("updatedate", new Date());
			parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			namedParameterJdbcTemplate.update(sql, parameters);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void deleteApplicationComponent(long applicationcomponentid, BasicInfoDTO info) throws Exception{
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.INACTIVE_FLAG);
			parameters.put("applicationcomponentid", applicationcomponentid);

			namedParameterJdbcTemplate.update(DrydockQueryConstant.APPLICATION_COMPONENT_DELETE, parameters);  
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public List<OrganizationComponent> loadOrganizationComponentList(BasicInfoDTO info) throws Exception{
	    List<OrganizationComponent> orgComponentList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("orgid", info.getOrgId());
			orgComponentList =(List<OrganizationComponent>) namedParameterJdbcTemplate.query(DrydockQueryConstant.ORGANIZATION_COMPONENT_LIST,parameters, new OrganizationComponentMapper());
		}
		catch(Exception e){
			throw e;
		}
	    return orgComponentList;
	}
	
	@Override
	public OrganizationComponent loadOrganizationComponentDetails(long orgComponentId, BasicInfoDTO info) throws Exception{
	    OrganizationComponent orgComponent = null;
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("orgid", info.getOrgId());
			parameters.put("orgComponentId", orgComponentId);
			orgComponent = (OrganizationComponent) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.ORGANIZATION_COMPONENT_DETAILS,parameters, new OrganizationComponentMapper());
		}
		catch(Exception e){
			throw e;
		}
	    return orgComponent;
	}
	
	@Override
	public void saveOrganizationComponent(OrganizationComponent organizationComponent, BasicInfoDTO info) throws Exception{
		try{
			String sql="";
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			if(organizationComponent.getId()==0)
			{
				Long organizationComponentidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.ORGANIZATION_COMPONENT_SEQ);
				organizationComponent.setId(organizationComponentidSeq);
				sql = DrydockQueryConstant.ORGANIZATION_COMPONENT_INSERT;
				parameters.addValue("id", organizationComponentidSeq);
				if(0==info.getOrgId() && organizationComponent.getOrgid()>0){
					parameters.addValue("orgid", organizationComponent.getOrgid());
				}else{
					parameters.addValue("orgid", info.getOrgId());
				}
				parameters.addValue("createid", info.getUserId());
				parameters.addValue("createdate", new Date());
			}
			else{
				sql = DrydockQueryConstant.ORGANIZATION_COMPONENT_UPDATE;
				parameters.addValue("id", organizationComponent.getId());
				parameters.addValue("orgid", organizationComponent.getOrgid());
				parameters.addValue("createid", organizationComponent.getCreateid());
				parameters.addValue("createdate", organizationComponent.getCreatedate());
			}
			parameters.addValue("applicationcomponentid", organizationComponent.getApplicationcomponentid());
			parameters.addValue("code", organizationComponent.getCode());
			parameters.addValue("description", organizationComponent.getDescription());
			parameters.addValue("parentcode", organizationComponent.getParentcode());
			parameters.addValue("updateid", info.getUserId());
			parameters.addValue("updatedate", new Date());
			parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			namedParameterJdbcTemplate.update(sql, parameters);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void deleteOrganizationComponent(long organizationcomponentid, BasicInfoDTO info) throws Exception{
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.INACTIVE_FLAG);
			parameters.put("organizationcomponentid", organizationcomponentid);

			namedParameterJdbcTemplate.update(DrydockQueryConstant.ORGANIZATION_COMPONENT_DELETE, parameters);  
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public void saveShipComponentDetails(
			ShipComponentDetails shipComponentDetails, BasicInfoDTO info)
			throws Exception {
		try{
			String sql="";
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			if(shipComponentDetails.getId()==0)
			{
				Long shipComponentDetailsIdSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.SHIP_COMPONENT_DETAILS_SEQ);

				sql = DrydockQueryConstant.SHIP_COMPONENT_DETAILS_INSERT;
				parameters.addValue("id", shipComponentDetailsIdSeq);
				parameters.addValue("orgid", info.getOrgId());
				parameters.addValue("createid", info.getUserId());
				parameters.addValue("createdate", new Date());
			}
			else{
				sql = DrydockQueryConstant.SHIP_COMPONENT_DETAILS_UPDATE;
				parameters.addValue("id", shipComponentDetails.getId());
				parameters.addValue("orgid", shipComponentDetails.getOrgid());
				parameters.addValue("createid", shipComponentDetails.getCreateid());
				parameters.addValue("createdate", shipComponentDetails.getCreatedate());
			}
			parameters.addValue("orgcomponentid", shipComponentDetails.getOrgcomponentid());
			parameters.addValue("shipid", shipComponentDetails.getShipid());
			parameters.addValue("make", shipComponentDetails.getMake());
			parameters.addValue("model", shipComponentDetails.getModel());
			parameters.addValue("description", shipComponentDetails.getDescription());
			parameters.addValue("updateid", info.getUserId());
			parameters.addValue("updatedate", new Date());
			parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			namedParameterJdbcTemplate.update(sql, parameters);
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public Object loadShipComponentDetails(Long shipId,
			Long organizationcomponentid, BasicInfoDTO info) throws Exception {
	    List<ShipComponentDetails> shipComponentDetails = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("shipId", shipId);
			parameters.put("organizationcomponentid", organizationcomponentid);
			parameters.put("orgid", info.getOrgId());
			shipComponentDetails =(List<ShipComponentDetails>) namedParameterJdbcTemplate.query(DrydockQueryConstant.SHIP_COMPONENT_DETAILS,parameters, new ShipComponentDetailsMapper());
		}
		catch(Exception e){
			throw e;
		}
		if(null!=shipComponentDetails && !shipComponentDetails.isEmpty())
			return shipComponentDetails;
		else
			return null;
	}

	@Override
	public List<Job> loadJobList(long shipId, boolean onlyApprovedFlag, BasicInfoDTO info)
			throws Exception {
	    List<Job> jobList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("orgid", info.getOrgId());
			parameters.put("projectstatus", DrydockConstant.PROJECT_STATUS_ACTIVE);
			if(onlyApprovedFlag){
				if(shipId>0){
					parameters.put("shipid", shipId);
					jobList =(List<Job>) namedParameterJdbcTemplate.query(DrydockQueryConstant.APPROVED_JOB_LIST_SINGLE_SHIP,parameters, new JobMapper());
				}else{
					jobList =(List<Job>) namedParameterJdbcTemplate.query(DrydockQueryConstant.APPROVED_JOB_LIST_ALL_SHIP,parameters, new JobMapper());
				}
			}else{
				if(shipId>0){
					parameters.put("shipid", shipId);
					jobList =(List<Job>) namedParameterJdbcTemplate.query(DrydockQueryConstant.JOB_LIST_SINGLE_SHIP,parameters, new JobMapper());
				}else{
					jobList =(List<Job>) namedParameterJdbcTemplate.query(DrydockQueryConstant.JOB_LIST_ALL_SHIP,parameters, new JobMapper());
				}
			}
		}
		catch(Exception e){
			throw e;
		}
	    return jobList;
	}

	@Override
	public Job loadJobDetails(long jobId, BasicInfoDTO info)
			throws Exception {
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("orgid", info.getOrgId());
			parameters.put("jobid", jobId);
			Job job = (Job)namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.JOB_DETAILS,parameters, new JobMapper());			
			job.setJobAttachmentList(loadJobAttachmentList(job, null, info));	
			job.setJobCommentList(loadJobCommentList(job, info));
			job.setJobMaterialDetailsList(loadJobMaterialDetailsList(jobId, info));
			job.setJobProgressReportList(loadJobProgressReportList(job, info));
			
			List<Job> jobList = loadJobList(job.getShipid(), true, info);
			boolean jobFound=false;
			Long previousJobId=null;
			Long nextJobId=null;
			for (Job job2 : jobList) {
				if(jobFound){
					nextJobId=job2.getId();
					break;
				}
				if(job.getId()==job2.getId()){
					jobFound=true;
				}
				if(!jobFound){
					previousJobId=job2.getId();
				}
			}
			job.setNextJobId(nextJobId);
			job.setPreviousJobId(previousJobId);
			return job;
		}
		catch(Exception e){
			throw e;
		}
	}

	private List<JobComment> loadJobCommentList(Job job, BasicInfoDTO info) {
	    List<JobComment> jobCommentList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("jobId", job.getId());
			jobCommentList =(List<JobComment>) namedParameterJdbcTemplate.query(DrydockQueryConstant.JOB_COMMENT_LIST,parameters, new JobCommentMapper());
		}
		catch(Exception e){
			throw e;
		}
		if(null!=jobCommentList && !jobCommentList.isEmpty())
			return jobCommentList;
		else
			return null;
	}

	@Override
	public List<Project> loadProjectList(long shipId, Boolean onlyActiveFlag, BasicInfoDTO info)
			throws Exception {
	    List<Project> projectList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("orgid", info.getOrgId());
			//parameters.put("projectstatus", DrydockConstant.PROJECT_STATUS_ACTIVE);
			if(shipId>0){
				parameters.put("shipid", shipId);
				if(onlyActiveFlag){
					parameters.put("projectstatus", DrydockConstant.PROJECT_STATUS_ACTIVE);
					projectList =(List<Project>) namedParameterJdbcTemplate.query(DrydockQueryConstant.PROJECT_LIST_SINGLE_SHIP,parameters, new ProjectMapper());
				}else{
					projectList =(List<Project>) namedParameterJdbcTemplate.query(DrydockQueryConstant.PROJECT_LIST_SINGLE_SHIP_ALL,parameters, new ProjectMapper());
				}
				
			}else{
				parameters.put("userId", info.getUserId());
				if(onlyActiveFlag){
					parameters.put("projectstatus", DrydockConstant.PROJECT_STATUS_ACTIVE);
					projectList =(List<Project>) namedParameterJdbcTemplate.query(DrydockQueryConstant.PROJECT_LIST_ALL_SHIP,parameters, new ProjectMapper());
				}else{
					projectList =(List<Project>) namedParameterJdbcTemplate.query(DrydockQueryConstant.PROJECT_LIST_ALL_SHIP_ALL,parameters, new ProjectMapper());
				}
				
			}
		}
		catch(Exception e){
			throw e;
		}
	    return projectList;
	}

	@Override
	public Object loadProjectDetails(Long projectId, BasicInfoDTO info)
			throws Exception {
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("orgid", info.getOrgId());
			parameters.put("projectid", projectId);
			Project project = (Project) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.PROJECT_DETAILS,parameters, new ProjectMapper());
			project.setProjectAttachmentList(loadProjectAttachmentList(project, info));
			project.setProjectDockyardList(loadProjectDockyardList(project, info));
			project.setProjectCurrencyConversionList(loadProjectCurrencyConversionList(project, info));
			project.setProjectJobList(loadJobList(project.getShipid(), false, info));
			project.setLineitemList(loadProjectJobCostLineitemList(project, info));
			return project;
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public void saveProject(Project project, boolean attachmentFlag, BasicInfoDTO info)
			throws Exception {
		try{
			String sql="";
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			if(project.getId()==0)
			{
				Long projectidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.PROJECT_SEQ);

				sql = DrydockQueryConstant.PROJECT_INSERT;
				parameters.addValue("id", projectidSeq);
				parameters.addValue("createid", info.getUserId());
				parameters.addValue("createdate", new Date());
				parameters.addValue("startdate", new Date());
				if(info.getOrgId()==0){
					parameters.addValue("orgid", project.getOrgid());
				}else{
					parameters.addValue("orgid", info.getOrgId());
				}
			}
			else{
				sql = DrydockQueryConstant.PROJECT_UPDATE;
				parameters.addValue("id", project.getId());
				parameters.addValue("createid", project.getCreateid());
				parameters.addValue("createdate", project.getCreatedate());
				parameters.addValue("startdate", project.getStartdate());
				parameters.addValue("orgid", project.getOrgid());
			}
			parameters.addValue("shipid", project.getShipid());
			parameters.addValue("description", project.getDescription());
			parameters.addValue("dockyard", project.getDockyard());
			parameters.addValue("preamble", project.getPreamble());
			parameters.addValue("orgcomponentid", 0);
			parameters.addValue("enddate", project.getEnddate());
			parameters.addValue("status", DrydockConstant.PROJECT_STATUS_ACTIVE);
			parameters.addValue("updateid", info.getUserId());
			parameters.addValue("updatedate", new Date());
			parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			
			parameters.addValue("estimatedStart", project.getEstimatedStart());
			parameters.addValue("estimatedFinish", project.getEstimatedfinish());
			parameters.addValue("actualStart", project.getActualStart());
			parameters.addValue("actualFinish", project.getActualfinish());
			parameters.addValue("currencyMasterId", project.getCurrencyMasterId());
			
			namedParameterJdbcTemplate.update(sql, parameters);
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public void saveJob(Job job, boolean attachmentFlag, BasicInfoDTO info) throws Exception {
		try{
			String sql="";
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("projectid", job.getProjectid()); 
			if(job.getId()==0)
			{
				JobComment initialComment = new JobComment();
				initialComment.setJobComment("Job Created.");
				Long jobidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.JOB_SEQ);
				initialComment.setJobid(jobidSeq);
				job.setId(jobidSeq);
				sql = DrydockQueryConstant.JOB_INSERT;
				parameters.addValue("createid", info.getUserId());
				parameters.addValue("createdate", System.currentTimeMillis());
				Shipmaster ship = (Shipmaster) coreRepository.loadShipDetailsById(job.getShipid(), info);
						
				Integer jobCount = namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.COUNT_JOB, parameters, Integer.class);
				job.setJobno(ship.getV_imo_no()+"/Project"+job.getProjectid()+"/Job"+(jobCount+1));
				job.setJobComment(initialComment); 
			}
			else{
				sql = DrydockQueryConstant.JOB_UPDATE;
			}
			parameters.addValue("id", job.getId());
			parameters.addValue("orgid", info.getOrgId());
			parameters.addValue("shipid", job.getShipid());
			parameters.addValue("description", job.getDescription());
			parameters.addValue("shipcomponentid", job.getShipcomponentid());
			parameters.addValue("status", DrydockConstant.JOB_STATUS_ACTIVE);
			parameters.addValue("updateid", info.getUserId());
			parameters.addValue("updatedate", System.currentTimeMillis());
			parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);

			parameters.addValue("job_date", job.getJobdate());
			parameters.addValue("job_no", job.getJobno());
			parameters.addValue("account_no", job.getAccountno());
			parameters.addValue("specification", job.getSpecification());
			parameters.addValue("location", job.getLocation());
			parameters.addValue("detailed_specification", job.getDetailedSpecification());
			parameters.addValue("total_area", job.getTotalArea());
			parameters.addValue("checkboxes", job.getCheckboxes());
			parameters.addValue("currency", job.getCurrency());
			parameters.addValue("estimated_budget", job.getEstimatedBudget());
			parameters.addValue("equipment", job.getEquipment());
			parameters.addValue("make", job.getMake());
			parameters.addValue("model", job.getModel());
			parameters.addValue("makeModelDescription", job.getMakeModelDescription());
			parameters.addValue("externalReference", job.getExternalReference());
			
			namedParameterJdbcTemplate.update(sql, parameters);			
			if(attachmentFlag){
				if(null!=job.getJobAttachmentList() && !job.getJobAttachmentList().isEmpty()){
					saveJobAttachmentList(job, null, info);
				}
				
				if(null!=job.getJobMaterialDetailsList()){
					saveJobMaterialDetailsList(job.getId(), job.getJobMaterialDetailsList(), info);
				}
				
				if(null!=job.getJobProgressReport()){
					saveJobProgressReport(job, job.getJobProgressReport(), info);
				}
			}
			
			if(null!=job.getJobComment() && null!=job.getJobComment().getJobComment() && !"".equals(job.getJobComment().getJobComment().trim())){
				Long jobCommentIdSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.JOB_COMMENT_SEQ);

				sql = DrydockQueryConstant.JOB_COMMENT_INSERT;
				parameters.addValue("id", jobCommentIdSeq);
				parameters.addValue("createid", info.getUserId());
				parameters.addValue("createdate", System.currentTimeMillis());
				parameters.addValue("updateid", info.getUserId());
				parameters.addValue("updatedate", System.currentTimeMillis());
				parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);
				parameters.addValue("jobId", job.getId());
				parameters.addValue("jobComment", job.getJobComment().getJobComment().trim());
				
				namedParameterJdbcTemplate.update(sql, parameters);
			}
		}
		catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void saveJobComment(Job job,
			JobComment jobComment, BasicInfoDTO info) throws Exception {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		try{
			Long jobCommentIdSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.JOB_COMMENT_SEQ);

			String sql = DrydockQueryConstant.JOB_COMMENT_INSERT;
			parameters.addValue("id", jobCommentIdSeq);
			parameters.addValue("createid", info.getUserId());
			parameters.addValue("createdate", System.currentTimeMillis());
			parameters.addValue("updateid", info.getUserId());
			parameters.addValue("updatedate", System.currentTimeMillis());
			parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.addValue("jobId", job.getId());
			parameters.addValue("jobComment", job.getJobComment().getJobComment().trim());
			
			namedParameterJdbcTemplate.update(sql, parameters);
		}catch(Exception ex){
			throw ex;
		}
		
	}

	@Override
	public void saveJobProgressReport(Job job,
			JobProgressReport jobProgressReport, BasicInfoDTO info) throws Exception {

		try{
			Map<String, Object> parameters = new HashMap<>();
			String sql = null;
			long degId = 0;
			if(jobProgressReport.getId()>degId){
				sql=DrydockQueryConstant.JOB_PROGRESS_REPORT_UPDATE;
				parameters.put("isactive", jobProgressReport.getIsactive());
				parameters.put("jobid", jobProgressReport.getJobid());
			}else{
				sql=DrydockQueryConstant.JOB_PROGRESS_REPORT_INSERT;
				long jobProgressReportId = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.JOB_PROGRESS_REPORT_SEQ);
				jobProgressReport.setId(jobProgressReportId);
				parameters.put("createid", info.getUserId());
				parameters.put("createdate", System.currentTimeMillis());
				parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
				parameters.put("jobid", job.getId());
			}

			parameters.put("id", jobProgressReport.getId()); //create sequence
			parameters.put("executiondate", jobProgressReport.getExecutionDate());
			parameters.put("reportingdate", jobProgressReport.getReportingDate());
			parameters.put("workdone", jobProgressReport.getWorkDone());
			parameters.put("jobattachmentids", "");
			parameters.put("updateid", info.getUserId());
			parameters.put("updatedate", System.currentTimeMillis());

			namedParameterJdbcTemplate.execute(sql, parameters,new PreparedStatementCallback() {  
				@Override  
				public Object doInPreparedStatement(PreparedStatement ps)  
						throws SQLException, DataAccessException {  
					return ps.executeUpdate();  
				}  
			}); 
			
			if(null!=jobProgressReport.getJobAttachmentList() && !jobProgressReport.getJobAttachmentList().isEmpty()){
				saveJobAttachmentList(job, jobProgressReport, info);
			}
		}
		catch(Exception e){
			throw e;
		}
	}

	private List<JobAttachment> loadJobAttachmentList(Job job, JobProgressReport jobProgressReport, BasicInfoDTO info) throws Exception {
	    List<JobAttachment> jobAttachmentList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("jobId", job.getId());
			parameters.put("orgId", job.getOrgid());
			parameters.put("shipId", job.getShipid());
			parameters.put("projectId", job.getProjectid());
			if(null!=jobProgressReport){
				parameters.put("progressreportid", jobProgressReport.getId());
			}else{
				parameters.put("progressreportid", 0);
//				jobAttachmentList =(List<JobAttachment>) namedParameterJdbcTemplate.query(DrydockQueryConstant.JOB_ATTACHMENT_LIST,parameters, new JobAttachmentMapper());
			}
			jobAttachmentList =(List<JobAttachment>) namedParameterJdbcTemplate.query(DrydockQueryConstant.JOB_PROGRESS_REPORT_ATTACHMENT_LIST,parameters, new JobAttachmentMapper());
		}
		catch(Exception e){
			throw e;
		}
		if(null!=jobAttachmentList && !jobAttachmentList.isEmpty())
			return jobAttachmentList;
		else
			return null;
	}

	@Override
	public Object loadShipComponentList(Long shipId, BasicInfoDTO info)
			throws Exception {
	    List<ShipComponentDetails> shipComponentDetails = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("shipId", shipId);
			parameters.put("orgid", info.getOrgId());
			shipComponentDetails =(List<ShipComponentDetails>) namedParameterJdbcTemplate.query(DrydockQueryConstant.SHIP_COMPONENT_DETAILS_LIST,parameters, new ShipComponentDetailsMapper());
		}
		catch(Exception e){
			throw e;
		}
		if(null!=shipComponentDetails && !shipComponentDetails.isEmpty())
			return shipComponentDetails;
		else
			return null;
	}

	@Override
	public void markJobComplete(Long jobId, String jobCloserRemark, BasicInfoDTO info) throws Exception {
		try{
			String sql=DrydockQueryConstant.UPDATE_JOB_STATUS;
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("id", jobId);
			parameters.addValue("status", DrydockConstant.JOB_STATUS_COMPLETE);
			parameters.addValue("jobCloserRemark", jobCloserRemark);
			parameters.addValue("updateid", info.getUserId());
			parameters.addValue("updatedate", new Date());
			namedParameterJdbcTemplate.update(sql, parameters);
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public void markJobCancel(Long jobId, BasicInfoDTO info) throws Exception {
		try{
			String sql=DrydockQueryConstant.UPDATE_JOB_STATUS;
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("id", jobId);
			parameters.addValue("status", DrydockConstant.JOB_STATUS_CANCEL);
			parameters.addValue("updateid", info.getUserId());
			parameters.addValue("updatedate", new Date());
			namedParameterJdbcTemplate.update(sql, parameters);
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public void markProjectComplete(Long projectId,String closerAttachmentRelativePath, String closerAttachmentName, String closerComment, BasicInfoDTO info) throws Exception {
		try{
			Project project = (Project) loadProjectDetails(projectId, info);
			String sql=DrydockQueryConstant.UPDATE_PROJECT_STATUS;
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("id", projectId);
			parameters.addValue("status", DrydockConstant.PROJECT_STATUS_COMPLETE);
			parameters.addValue("updateid", info.getUserId());
			parameters.addValue("updatedate", new Date());
			parameters.addValue("closerComment", closerComment);
			parameters.addValue("closerAttachmentRelativePath", closerAttachmentRelativePath);
			parameters.addValue("closerAttachmentName", closerAttachmentName);
			namedParameterJdbcTemplate.update(sql, parameters);
			
			Project defaultProject = new Project();
			defaultProject.setDescription(DrydockConstant.DEFAULT_PROJECT_DESCRIPTION);
			defaultProject.setOrgid(project.getOrgid());
			defaultProject.setShipid(project.getShipid());
			
			saveProject(defaultProject, false, info);
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public List<Job> loadActiveJobListForProject(Long projectId,
			BasicInfoDTO info) throws Exception {
	    List<Job> jobList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("projectId", projectId);
			parameters.put("status", DrydockConstant.JOB_STATUS_ACTIVE);
			jobList =(List<Job>) namedParameterJdbcTemplate.query(DrydockQueryConstant.JOB_LIST_SINGLE_PROJECT,parameters, new JobMapper());
		}
		catch(Exception e){
			throw e;
		}
	    return jobList;
	}

	@Override
	public List<ApplicationJob> loadApplicationJobList(BasicInfoDTO info)
			throws Exception {
	    List<ApplicationJob> jobList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			jobList =(List<ApplicationJob>) namedParameterJdbcTemplate.query(DrydockQueryConstant.APPLICATION_JOB_LIST,parameters, new ApplicationJobMapper());
		}
		catch(Exception e){
			throw e;
		}
	    return jobList;
	}

	@Override
	public Object loadApplicationJobDetails(Long applicationJobId, BasicInfoDTO info)
			throws Exception {
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("jobid", applicationJobId);
			ApplicationJob applicationJob = (ApplicationJob)namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.APPLICATION_JOB_DETAILS,parameters, new ApplicationJobMapper());			
			applicationJob.setJobMaterialDetailsList(loadApplicationJobMaterialDetailsList(applicationJobId, info));
			return applicationJob;
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public boolean saveApplicationJob(ApplicationJob applicationJob, BasicInfoDTO info) throws Exception {
		try{
			boolean freshJob=false;
			String sql="";
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			if(applicationJob.getId()==0)
			{
				freshJob=true;
				Long applicationJobidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.APPLICATION_JOB_SEQ);
				applicationJob.setId(applicationJobidSeq);
				sql = DrydockQueryConstant.APPLICATION_JOB_INSERT;
				parameters.addValue("id", applicationJobidSeq);
				parameters.addValue("createid", info.getUserId());
				parameters.addValue("createdate", new Date());
			}
			else{
				sql = DrydockQueryConstant.APPLICATION_JOB_UPDATE;
				parameters.addValue("id", applicationJob.getId());
				parameters.addValue("createid", applicationJob.getCreateid());
				parameters.addValue("createdate", applicationJob.getCreatedate());
			}
			parameters.addValue("description", applicationJob.getDescription());
			parameters.addValue("updateid", info.getUserId());
			parameters.addValue("updatedate", new Date());
			parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);

			parameters.addValue("job_no", applicationJob.getJobno());
			parameters.addValue("account_no", applicationJob.getAccountno());
			parameters.addValue("specification", applicationJob.getSpecification());
			parameters.addValue("location", applicationJob.getLocation());
			parameters.addValue("detailed_specification", applicationJob.getDetailedSpecification());
			parameters.addValue("total_area", applicationJob.getTotalArea());
			parameters.addValue("checkboxes", applicationJob.getCheckboxes());
			parameters.addValue("shipcomponentid", applicationJob.getShipcomponentid());
			
			parameters.addValue("make", applicationJob.getMake());
			parameters.addValue("model", applicationJob.getModel());
			parameters.addValue("makeModelDescription", applicationJob.getMakeModelDescription());
			parameters.addValue("equipment", applicationJob.getEquipment());
			parameters.addValue("jobdate", applicationJob.getJobdate());
			parameters.addValue("vesselType", applicationJob.getVesselType());
			parameters.addValue("vesselAge", applicationJob.getVesselAge());
			
			
			namedParameterJdbcTemplate.update(sql, parameters);
			
			if(null!=applicationJob.getJobMaterialDetailsList()){
				saveApplicationJobMaterialDetailsList(applicationJob.getId(), applicationJob.getJobMaterialDetailsList(), info);
			}
			return freshJob;
		}
		catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void saveOrganizationJob(OrganizationJob organizationJob, BasicInfoDTO info) throws Exception {
		try{
			String sql="";
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			if(organizationJob.getId()==0)
			{
				Long applicationJobidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.ORGANIZATION_JOB_SEQ);
				organizationJob.setId(applicationJobidSeq);
				sql = DrydockQueryConstant.ORGANIZATION_JOB_INSERT;
				parameters.addValue("id", applicationJobidSeq);
				parameters.addValue("createid", info.getUserId());
				parameters.addValue("createdate", new Date());
				parameters.addValue("orgid", organizationJob.getOrgid() == 0 ? info.getOrgId() : organizationJob.getOrgid());
				parameters.addValue("applicationjobid", organizationJob.getApplicationjobid());
			}
			else{
				sql = DrydockQueryConstant.ORGANIZATION_JOB_UPDATE;
				parameters.addValue("id", organizationJob.getId());
				parameters.addValue("createid", organizationJob.getCreateid());
				parameters.addValue("createdate", organizationJob.getCreatedate());
			}
			parameters.addValue("description", organizationJob.getDescription());
			parameters.addValue("updateid", info.getUserId());
			parameters.addValue("updatedate", new Date());
			parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);

			parameters.addValue("job_no", organizationJob.getJobno());
			parameters.addValue("account_no", organizationJob.getAccountno());
			parameters.addValue("specification", organizationJob.getSpecification());
			parameters.addValue("location", organizationJob.getLocation());
			parameters.addValue("detailed_specification", organizationJob.getDetailedSpecification());
			parameters.addValue("total_area", organizationJob.getTotalArea());
			parameters.addValue("checkboxes", organizationJob.getCheckboxes());
			parameters.addValue("shipcomponentid", organizationJob.getShipcomponentid());
			
			parameters.addValue("make", organizationJob.getMake());
			parameters.addValue("model", organizationJob.getModel());
			parameters.addValue("makeModelDescription", organizationJob.getMakeModelDescription());
			parameters.addValue("equipment", organizationJob.getEquipment());
			parameters.addValue("jobdate", organizationJob.getJobdate());
			parameters.addValue("vesselType", organizationJob.getVesselType());
			parameters.addValue("vesselAge", organizationJob.getVesselAge());
			
			namedParameterJdbcTemplate.update(sql, parameters);
			if(null!=organizationJob.getJobMaterialDetailsList()){
				saveOrganizationJobMaterialDetailsList(organizationJob.getId(), organizationJob.getJobMaterialDetailsList(), info);
			}
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public List<Job> loadMyJobList(long shipId, BasicInfoDTO info)
			throws Exception {
	    List<Job> jobList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("createid", info.getUserId());
			if(shipId>0){
				parameters.put("shipid", shipId);
				jobList =(List<Job>) namedParameterJdbcTemplate.query(DrydockQueryConstant.MY_JOB_LIST_SINGLE_SHIP,parameters, new JobMapper());
			}else{
				jobList =(List<Job>) namedParameterJdbcTemplate.query(DrydockQueryConstant.MY_JOB_LIST_ALL_SHIP,parameters, new JobMapper());
			}
		}
		catch(Exception e){
			throw e;
		}
	    return jobList;
	}

	@Override
	public Object loadCheckboxesList(BasicInfoDTO info) throws Exception {
	    List<JobCreationCheckboxes> checkboxList = new ArrayList<>();
		try{
			Map<String, KeyValueDTO> hmpFinal = new HashMap<>();
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			checkboxList =(List<JobCreationCheckboxes>) namedParameterJdbcTemplate.query(DrydockQueryConstant.CHECKBOX_LIST_ALL,parameters, new JobCreationCheckboxesMapper());
			for (JobCreationCheckboxes jobCreationCheckboxes : checkboxList) {
				if(!hmpFinal.containsKey(jobCreationCheckboxes.getKey())){
					KeyValueDTO kv = new KeyValueDTO();
					kv.setKey(jobCreationCheckboxes.getKey());
					hmpFinal.put(jobCreationCheckboxes.getKey(), kv);
				}
				hmpFinal.get(jobCreationCheckboxes.getKey()).getCheckboxList().add(jobCreationCheckboxes);
			}
		}
		catch(Exception e){
			throw e;
		}
	    return checkboxList;
	}

	@Override
	public List<OrganizationJob> loadOrganizationJobList( BasicInfoDTO info)
			throws Exception {
	    List<OrganizationJob> jobList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("orgid", info.getOrgId());
			
			jobList =(List<OrganizationJob>) namedParameterJdbcTemplate.query(DrydockQueryConstant.JOB_LIST_ALL_ORGANIZATION,parameters, new OrganizationJobMapper());
		}
		catch(Exception e){
			throw e;
		}
	    return jobList;
	}
	
	@Override
	public Object loadOrganizationJobDetail(long jobId, BasicInfoDTO info)
			throws Exception {
	    OrganizationJob job = null;
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("orgid", info.getOrgId());
			parameters.put("jobid", jobId);
			
			job =(OrganizationJob) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.ORGANIZATION_JOB_DETAILS, parameters, new OrganizationJobMapper());
			job.setJobMaterialDetailsList(loadOrganizationJobMaterialDetailsList(jobId, info));
			
		}
		catch(Exception e){
			throw e;
		}
	    return job;
	}

	@Override
	public List<Project> loadCompletedProjectListByShipId(int shipId, BasicInfoDTO info) throws Exception {
		List<Project> projectList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("orgid", info.getOrgId());
			parameters.put("projectstatus", DrydockConstant.PROJECT_STATUS_COMPLETE);
			parameters.put("shipid", shipId);
			
			projectList =(List<Project>) namedParameterJdbcTemplate.query(DrydockQueryConstant.COMPLETED_PROJECT_LIST_SINGLE_SHIP,parameters, new ProjectMapper());
		}
		catch(Exception e){
			throw e;
		}
	    return projectList;
	}

	@Override
	public Project loadActiveProjectDetail(int shipId, BasicInfoDTO info)
			throws Exception {
		Project project = null;
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("orgid", info.getOrgId());
			parameters.put("projectstatus", DrydockConstant.PROJECT_STATUS_ACTIVE);
			parameters.put("shipid", shipId);
			
			project = (Project) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.ACTIVE_PROJECT_DETAIL,parameters, new ProjectMapper());
		}
		catch(Exception e){
			throw e;
		}
	    return project;
	}

	@Override
	public void saveJobMaterialDetailsList(long jobId, 
			List<JobMaterialDetails> jobMaterialDetailsList,
			BasicInfoDTO info) throws Exception {

		try{
			List<JobMaterialDetails> existingJobMaterialDetailsList = loadJobMaterialDetailsList(jobId, info);
			Map<Long, JobMaterialDetails> existingMap = new HashMap<>();
			if(null != existingJobMaterialDetailsList){
				for (JobMaterialDetails jobMaterialDetails : existingJobMaterialDetailsList) {
					existingMap.put(jobMaterialDetails.getId(), jobMaterialDetails);
				}
			}			
			Map<String, Object> parameters = new HashMap<>();
			String sql = null;
			for (JobMaterialDetails jobMaterialDetails : jobMaterialDetailsList) {
				long degId = 0;
				if(jobMaterialDetails.getId()>degId){
					if(existingMap.containsKey(jobMaterialDetails.getId())){
						existingMap.remove(jobMaterialDetails.getId());
					}
					sql=DrydockQueryConstant.JOB_MATERIAL_DETAILS_UPDATE;
					parameters.put("isactive", jobMaterialDetails.getIsactive());
					parameters.put("jobid", jobMaterialDetails.getJobid());
				}else{
					sql=DrydockQueryConstant.JOB_MATERIAL_DETAILS_INSERT;
					long jobMaterialDetailsId = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.JOB_MATERIAL_DETAILS_SEQ);
					jobMaterialDetails.setId(jobMaterialDetailsId);
					parameters.put("createid", info.getUserId());
					parameters.put("createdate", System.currentTimeMillis());
					parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
					parameters.put("jobid", jobId);
				}
				
				parameters.put("id", jobMaterialDetails.getId()); //create sequence
				parameters.put("make", jobMaterialDetails.getMake());
				parameters.put("model", jobMaterialDetails.getModel());
				parameters.put("partNo", jobMaterialDetails.getPartNo());
				parameters.put("partName", jobMaterialDetails.getPartName());
				parameters.put("quantity", jobMaterialDetails.getQuantity());
				parameters.put("uom", jobMaterialDetails.getUom());
				parameters.put("drawingNo", jobMaterialDetails.getDrawingNo());	
				parameters.put("remarks", jobMaterialDetails.getRemarks());
				parameters.put("updateid", info.getUserId());
				parameters.put("updatedate", System.currentTimeMillis());
				
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
				namedParameterJdbcTemplate.update(DrydockQueryConstant.DELETE_JOB_MATERIAL_DETAILS, parameters);
			}
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public void saveApplicationJobMaterialDetailsList(long applicationJobId, 
			List<ApplicationJobMaterialDetails> applicationJobMaterialDetailsList,
			BasicInfoDTO info) throws Exception {

		try{
			List<ApplicationJobMaterialDetails> existingApplicationJobMaterialDetailsList = loadApplicationJobMaterialDetailsList(applicationJobId, info);
			Map<Long, ApplicationJobMaterialDetails> existingMap = new HashMap<>();
			if(null != existingApplicationJobMaterialDetailsList){
				for (ApplicationJobMaterialDetails applicationJobMaterialDetails : existingApplicationJobMaterialDetailsList) {
					existingMap.put(applicationJobMaterialDetails.getId(), applicationJobMaterialDetails);
				}
			}			
			Map<String, Object> parameters = new HashMap<>();
			String sql = null;
			for (ApplicationJobMaterialDetails applicationJobMaterialDetails : applicationJobMaterialDetailsList) {
				long degId = 0;
				if(applicationJobMaterialDetails.getId()>degId){
					if(existingMap.containsKey(applicationJobMaterialDetails.getId())){
						existingMap.remove(applicationJobMaterialDetails.getId());
					}
					sql=DrydockQueryConstant.APPLICATION_JOB_MATERIAL_DETAILS_UPDATE;
					parameters.put("isactive", applicationJobMaterialDetails.getIsactive());
					parameters.put("applicationjobid", applicationJobMaterialDetails.getApplicationJobid());
				}else{
					sql=DrydockQueryConstant.APPLICATION_JOB_MATERIAL_DETAILS_INSERT;
					long applicationJobMaterialDetailsId = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.APPLICATION_JOB_MATERIAL_DETAILS_SEQ);
					applicationJobMaterialDetails.setId(applicationJobMaterialDetailsId);
					parameters.put("createid", info.getUserId());
					parameters.put("createdate", System.currentTimeMillis());
					parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
					parameters.put("applicationjobid", applicationJobId);
				}
				
				parameters.put("id", applicationJobMaterialDetails.getId()); //create sequence
				parameters.put("make", applicationJobMaterialDetails.getMake());
				parameters.put("model", applicationJobMaterialDetails.getModel());
				parameters.put("partNo", applicationJobMaterialDetails.getPartNo());
				parameters.put("partName", applicationJobMaterialDetails.getPartName());
				parameters.put("quantity", applicationJobMaterialDetails.getQuantity());
				parameters.put("uom", applicationJobMaterialDetails.getUom());
				parameters.put("drawingNo", applicationJobMaterialDetails.getDrawingNo());	
				parameters.put("remarks", applicationJobMaterialDetails.getRemarks());
				parameters.put("updateid", info.getUserId());
				parameters.put("updatedate", System.currentTimeMillis());
				
				namedParameterJdbcTemplate.execute(sql, parameters,new PreparedStatementCallback() {  
				    @Override  
				    public Object doInPreparedStatement(PreparedStatement ps)  
				            throws SQLException, DataAccessException {  
				        return ps.executeUpdate();  
				    }  
				}); 
			}
			
			for (Long applicationJobMaterialDetailsId : existingMap.keySet()) {
				parameters.clear();
				parameters.put("id", applicationJobMaterialDetailsId);
				namedParameterJdbcTemplate.update(DrydockQueryConstant.DELETE_APPLICATION_JOB_MATERIAL_DETAILS, parameters);
			}
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public void saveOrganizationJobMaterialDetailsList(long organizationJobId, 
			List<OrganizationJobMaterialDetails> organizationJobMaterialDetailsList,
			BasicInfoDTO info) throws Exception {

		try{
			List<OrganizationJobMaterialDetails> existingOrganizationJobMaterialDetailsList = loadOrganizationJobMaterialDetailsList(organizationJobId, info);
			Map<Long, OrganizationJobMaterialDetails> existingMap = new HashMap<>();
			if(null != existingOrganizationJobMaterialDetailsList){
				for (OrganizationJobMaterialDetails organizationJobMaterialDetails : existingOrganizationJobMaterialDetailsList) {
					existingMap.put(organizationJobMaterialDetails.getId(), organizationJobMaterialDetails);
				}
			}			
			Map<String, Object> parameters = new HashMap<>();
			String sql = null;
			for (OrganizationJobMaterialDetails organizationJobMaterialDetails : organizationJobMaterialDetailsList) {
				long degId = 0;
				if(organizationJobMaterialDetails.getId()>degId){
					if(existingMap.containsKey(organizationJobMaterialDetails.getId())){
						existingMap.remove(organizationJobMaterialDetails.getId());
					}
					sql=DrydockQueryConstant.ORGANIZATION_JOB_MATERIAL_DETAILS_UPDATE;
					parameters.put("isactive", organizationJobMaterialDetails.getIsactive());
					parameters.put("organizationjobid", organizationJobMaterialDetails.getOrganizationJobid());
				}else{
					sql=DrydockQueryConstant.ORGANIZATION_JOB_MATERIAL_DETAILS_INSERT;
					long organizationJobMaterialDetailsId = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.ORGANIZATION_JOB_MATERIAL_DETAILS_SEQ);
					organizationJobMaterialDetails.setId(organizationJobMaterialDetailsId);
					parameters.put("createid", info.getUserId());
					parameters.put("createdate", System.currentTimeMillis());
					parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
					parameters.put("organizationjobid", organizationJobId);
				}
				
				parameters.put("id", organizationJobMaterialDetails.getId()); //create sequence
				parameters.put("make", organizationJobMaterialDetails.getMake());
				parameters.put("model", organizationJobMaterialDetails.getModel());
				parameters.put("partNo", organizationJobMaterialDetails.getPartNo());
				parameters.put("partName", organizationJobMaterialDetails.getPartName());
				parameters.put("quantity", organizationJobMaterialDetails.getQuantity());
				parameters.put("uom", organizationJobMaterialDetails.getUom());
				parameters.put("drawingNo", organizationJobMaterialDetails.getDrawingNo());	
				parameters.put("remarks", organizationJobMaterialDetails.getRemarks());
				parameters.put("updateid", info.getUserId());
				parameters.put("updatedate", System.currentTimeMillis());
				
				namedParameterJdbcTemplate.execute(sql, parameters,new PreparedStatementCallback() {  
				    @Override  
				    public Object doInPreparedStatement(PreparedStatement ps)  
				            throws SQLException, DataAccessException {  
				        return ps.executeUpdate();  
				    }  
				}); 
			}
			
			for (Long organizationJobMaterialDetailsId : existingMap.keySet()) {
				parameters.clear();
				parameters.put("id", organizationJobMaterialDetailsId);
				namedParameterJdbcTemplate.update(DrydockQueryConstant.DELETE_ORGANIZATION_JOB_MATERIAL_DETAILS, parameters);
			}
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public List<JobMaterialDetails> loadJobMaterialDetailsList(long jobId,
			BasicInfoDTO info) throws Exception {
	    List<JobMaterialDetails> jobMaterialDetailsList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("jobId", jobId);
			jobMaterialDetailsList =(List<JobMaterialDetails>) namedParameterJdbcTemplate.query(DrydockQueryConstant.JOB_MATERIAL_DETAILS_LIST,parameters, new JobMaterialDetailsMapper());
		}
		catch(Exception e){
			throw e;
		}
		if(null!=jobMaterialDetailsList && !jobMaterialDetailsList.isEmpty())
			return jobMaterialDetailsList;
		else
			return null;
	}

	@Override
	public List<ApplicationJobMaterialDetails> loadApplicationJobMaterialDetailsList(long applicationJobId,
			BasicInfoDTO info) throws Exception {
	    List<ApplicationJobMaterialDetails> applicationJobMaterialDetailsList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("applicationjobId", applicationJobId);
			applicationJobMaterialDetailsList =(List<ApplicationJobMaterialDetails>) namedParameterJdbcTemplate.query(DrydockQueryConstant.APPLICATION_JOB_MATERIAL_DETAILS_LIST,parameters, new ApplicationJobMaterialDetailsMapper());
		}
		catch(Exception e){
			throw e;
		}
		if(null!=applicationJobMaterialDetailsList && !applicationJobMaterialDetailsList.isEmpty())
			return applicationJobMaterialDetailsList;
		else
			return null;
	}

	@Override
	public List<OrganizationJobMaterialDetails> loadOrganizationJobMaterialDetailsList(long organizationJobId,
			BasicInfoDTO info) throws Exception {
	    List<OrganizationJobMaterialDetails> organizationJobMaterialDetailsList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("organizationjobId", organizationJobId);
			organizationJobMaterialDetailsList =(List<OrganizationJobMaterialDetails>) namedParameterJdbcTemplate.query(DrydockQueryConstant.ORGANIZATION_JOB_MATERIAL_DETAILS_LIST,parameters, new OrganizationJobMaterialDetailsMapper());
		}
		catch(Exception e){
			throw e;
		}
		if(null!=organizationJobMaterialDetailsList && !organizationJobMaterialDetailsList.isEmpty())
			return organizationJobMaterialDetailsList;
		else
			return null;
	}

	@Override
	public List<JobProgressReport> loadJobProgressReportList(Job job,
			BasicInfoDTO info) throws Exception {
	    List<JobProgressReport> jobProgressReportList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("jobId", job.getId());
			jobProgressReportList =(List<JobProgressReport>) namedParameterJdbcTemplate.query(DrydockQueryConstant.JOB_PROGRESS_REPORT_LIST,parameters, new JobProgressReportMapper());
			for (JobProgressReport jobProgressReport : jobProgressReportList) {
				jobProgressReport.setJobAttachmentList(loadJobAttachmentList(job, jobProgressReport, info));
			}
		}
		catch(Exception e){
			throw e;
		}
		if(null!=jobProgressReportList && !jobProgressReportList.isEmpty())
			return jobProgressReportList;
		else
			return null;
	}


	@Override
	public void saveJobAttachmentList(Job job, 
			JobProgressReport jobProgressReport, BasicInfoDTO info) throws Exception {
		String sql=null;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		try{
			List<JobAttachment> attachmentList = loadJobAttachmentList(job, jobProgressReport, info);
			Map<Long, JobAttachment> hmpExistingAttachment = new HashMap<>();
			if(null != attachmentList && !attachmentList.isEmpty())
				for (JobAttachment jobAttachment : attachmentList) {
					hmpExistingAttachment.put(jobAttachment.getId(), jobAttachment);
				}
			if(null==jobProgressReport){
				for (JobAttachment jobAttachment : job.getJobAttachmentList()) {
					if(jobAttachment.getId()>0 && hmpExistingAttachment.containsKey(jobAttachment.getId())){
						sql = DrydockQueryConstant.JOB_ATTACHMENT_UPDATE;
						hmpExistingAttachment.remove(jobAttachment.getId());
					}else{
						Long jobattachmentidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.JOB_ATTACHMENT_SEQ);
						jobAttachment.setId(jobattachmentidSeq);
						sql = DrydockQueryConstant.JOB_ATTACHMENT_INSERT;
						parameters.addValue("createid", info.getUserId());
						parameters.addValue("createdate", System.currentTimeMillis());
					}
					parameters.addValue("id", jobAttachment.getId());
					parameters.addValue("jobId", job.getId());
					parameters.addValue("orgId", job.getOrgid());
					parameters.addValue("shipId", job.getShipid());
					parameters.addValue("projectId", job.getProjectid());
					parameters.addValue("updateid", info.getUserId());
					parameters.addValue("updatedate", System.currentTimeMillis());
					parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);

					parameters.addValue("attachmentName", jobAttachment.getAttachmentName());
					parameters.addValue("relativePath", jobAttachment.getRelativepath());
					parameters.addValue("description", jobAttachment.getDescription());
					parameters.addValue("attachmentDescription", jobAttachment.getAttachmentDescription());
					parameters.addValue("attachmentType", jobAttachment.getAttachmentType());					
					parameters.addValue("progressreportid", 0);

					namedParameterJdbcTemplate.update(sql, parameters);
				}
			} else {
				for (JobAttachment jobAttachment : jobProgressReport.getJobAttachmentList()) {
					if(jobAttachment.getId()>0 && hmpExistingAttachment.containsKey(jobAttachment.getId())){
						sql = DrydockQueryConstant.JOB_ATTACHMENT_UPDATE;
						hmpExistingAttachment.remove(jobAttachment.getId());
					}else{
						Long jobattachmentidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.JOB_ATTACHMENT_SEQ);
						jobAttachment.setId(jobattachmentidSeq);
						sql = DrydockQueryConstant.JOB_ATTACHMENT_INSERT;
						parameters.addValue("createid", info.getUserId());
						parameters.addValue("createdate", System.currentTimeMillis());
					}
					parameters.addValue("id", jobAttachment.getId());
					parameters.addValue("jobId", job.getId());
					parameters.addValue("orgId", job.getOrgid());
					parameters.addValue("shipId", job.getShipid());
					parameters.addValue("projectId", job.getProjectid());
					parameters.addValue("updateid", info.getUserId());
					parameters.addValue("updatedate", new Date());
					parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);

					parameters.addValue("attachmentName", jobAttachment.getAttachmentName());
					parameters.addValue("relativePath", jobAttachment.getRelativepath());
					parameters.addValue("description", jobAttachment.getDescription());
					parameters.addValue("attachmentDescription", jobAttachment.getAttachmentDescription());
					parameters.addValue("attachmentType", jobAttachment.getAttachmentType());
					parameters.addValue("progressreportid", jobProgressReport.getId());					

					namedParameterJdbcTemplate.update(sql, parameters);
				}
			}

			for (Long jobAttachmentId : hmpExistingAttachment.keySet()) {
				Map<String, Object> parameterMap = new HashMap<>();
				parameterMap.put("isactive", DrydockConstant.INACTIVE_FLAG);
				parameterMap.put("id", jobAttachmentId);

				namedParameterJdbcTemplate.update(DrydockQueryConstant.JOB_ATTACHMENT_DELETE, parameterMap);  
			}
		}
		catch(Exception e){
			throw e;
		}
	}
	
	
	@Override
	public List<ProjectAttachment> loadProjectAttachmentList(Project project, BasicInfoDTO info) throws Exception {
	    List<ProjectAttachment> projectAttachmentList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("projectId", project.getId());
			parameters.put("orgId", project.getOrgid());
			parameters.put("shipId", project.getShipid());
			projectAttachmentList =(List<ProjectAttachment>) namedParameterJdbcTemplate.query(DrydockQueryConstant.PROJECT_ATTACHMENT_LIST,parameters, new ProjectAttachmentMapper());
		}
		catch(Exception e){
			throw e;
		}
		if(null!=projectAttachmentList && !projectAttachmentList.isEmpty())
			return projectAttachmentList;
		else
			return null;
	}
	
	@Override
	public List<ProjectJobCostLineitem> loadProjectJobCostLineitemList(Project project, BasicInfoDTO info) throws Exception {
	    List<ProjectJobCostLineitem> projectJobCostLineitemList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("projectId", project.getId());
			parameters.put("orgId", project.getOrgid());
			parameters.put("shipId", project.getShipid());
			projectJobCostLineitemList =(List<ProjectJobCostLineitem>) namedParameterJdbcTemplate.query(DrydockQueryConstant.PROJECT_JOB_COST_LINEITEM_LIST,parameters, new ProjectJobCostLineitemMapper());
			Map<Long, ProjectJobCostLineitem> hmp = new HashMap<>();
			for (ProjectJobCostLineitem projectJobCostLineitem : projectJobCostLineitemList) {
				hmp.put(projectJobCostLineitem.getId(), projectJobCostLineitem);
			}
			List<ProjectJobCostLineitemDetails> detailsList = (List<ProjectJobCostLineitemDetails>) namedParameterJdbcTemplate.query(DrydockQueryConstant.PROJECT_JOB_COST_LINEITEM_DETAILS_LIST,parameters, new ProjectJobCostLineitemDetailsMapper());;
			for (ProjectJobCostLineitemDetails projectJobCostLineitemDetails : detailsList) {
				if(hmp.containsKey(projectJobCostLineitemDetails.getLineitemid())){
					ProjectJobCostLineitem projectJobCostLineitem=hmp.get(projectJobCostLineitemDetails.getLineitemid());
					if(null==projectJobCostLineitem.getDetailsList()){
						projectJobCostLineitem.setDetailsList(new ArrayList<>());
					}
					projectJobCostLineitem.getDetailsList().add(projectJobCostLineitemDetails);
				}
			}
		}
		catch(Exception e){
			throw e;
		}
		if(null!=projectJobCostLineitemList && !projectJobCostLineitemList.isEmpty())
			return projectJobCostLineitemList;
		else
			return null;
	}
	
	
	@Override
	public List<ProjectDockyard> loadProjectDockyardList(Project project, BasicInfoDTO info) throws Exception {
	    List<ProjectDockyard> projectDockyardList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("projectId", project.getId());
			parameters.put("orgId", project.getOrgid());
			parameters.put("shipId", project.getShipid());
			projectDockyardList =(List<ProjectDockyard>) namedParameterJdbcTemplate.query(DrydockQueryConstant.PROJECT_DOCKYARD_LIST,parameters, new ProjectDockyardMapper());
		}
		catch(Exception e){
			throw e;
		}
		if(null!=projectDockyardList && !projectDockyardList.isEmpty())
			return projectDockyardList;
		else
			return null;
	}


	@Override
	public List<ProjectCurrencyConversion> loadProjectCurrencyConversionList(Project project, BasicInfoDTO info) throws Exception {
	    List<ProjectCurrencyConversion> projectCurrencyConversionList = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("projectId", project.getId());
			parameters.put("orgId", project.getOrgid());
			parameters.put("shipId", project.getShipid());
			projectCurrencyConversionList =(List<ProjectCurrencyConversion>) namedParameterJdbcTemplate.query(DrydockQueryConstant.PROJECT_CURRENCY_CONVERSION_LIST,parameters, new ProjectCurrencyConversionMapper());
		}
		catch(Exception e){
			throw e;
		}
		if(null!=projectCurrencyConversionList && !projectCurrencyConversionList.isEmpty())
			return projectCurrencyConversionList;
		else
			return null;
	}


	@Override
	public void saveProjectAttachmentList(Project project, BasicInfoDTO info) throws Exception {
		String sql=null;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		try{
			List<ProjectAttachment> attachmentList = loadProjectAttachmentList(project, info);
			Map<Long, ProjectAttachment> hmpExistingAttachment = new HashMap<>();
			if(null != attachmentList && !attachmentList.isEmpty())
				for (ProjectAttachment jobAttachment : attachmentList) {
					hmpExistingAttachment.put(jobAttachment.getId(), jobAttachment);
				}
				for (ProjectAttachment projectAttachment : project.getProjectAttachmentList()) {
					if(projectAttachment.getId()>0 && hmpExistingAttachment.containsKey(projectAttachment.getId())){
						sql = DrydockQueryConstant.PROJECT_ATTACHMENT_UPDATE;
						hmpExistingAttachment.remove(projectAttachment.getId());
					}else{
						Long projectAttachmentattachmentidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.PROJECT_ATTACHMENT_SEQ);
						projectAttachment.setId(projectAttachmentattachmentidSeq );
						sql = DrydockQueryConstant.PROJECT_ATTACHMENT_INSERT;
						parameters.addValue("createid", info.getUserId());
						parameters.addValue("createdate", new Date());
					}
					parameters.addValue("id", projectAttachment.getId());
					parameters.addValue("projectId", project.getId());
					parameters.addValue("orgId", project.getOrgid());
					parameters.addValue("shipId", project.getShipid());
					parameters.addValue("attachmentName", projectAttachment.getAttachmentName());
					parameters.addValue("relativePath", projectAttachment.getRelativepath());
					parameters.addValue("attachmentDescription", projectAttachment.getAttachmentDescription());
					parameters.addValue("attachmentType", projectAttachment.getAttachmentType());					

					parameters.addValue("updateid", info.getUserId());
					parameters.addValue("updatedate", new Date());
					parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);

					
					namedParameterJdbcTemplate.update(sql, parameters);
				}

			for (Long projectAttachmentId : hmpExistingAttachment.keySet()) {
				Map<String, Object> parameterMap = new HashMap<>();
				parameterMap.put("isactive", DrydockConstant.INACTIVE_FLAG);
				parameterMap.put("id", projectAttachmentId);

				namedParameterJdbcTemplate.update(DrydockQueryConstant.PROJECT_ATTACHMENT_DELETE, parameterMap);  
			}
		}
		catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void saveProjectDockyardList(Project project, BasicInfoDTO info) throws Exception {
		String sql=null;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		try{
			List<ProjectDockyard> dockyardList = loadProjectDockyardList(project, info);
			Map<Long, ProjectDockyard> hmpExistingAttachment = new HashMap<>();
			if(null != dockyardList && !dockyardList.isEmpty())
				for (ProjectDockyard projectDockyard : dockyardList) {
					hmpExistingAttachment.put(projectDockyard.getId(), projectDockyard);
				}
				for (ProjectDockyard projectDockyard : project.getProjectDockyardList()) {
					if(projectDockyard.getId()>0 && hmpExistingAttachment.containsKey(projectDockyard.getId())){
						sql = DrydockQueryConstant.PROJECT_DOCKYARD_UPDATE;
						hmpExistingAttachment.remove(projectDockyard.getId());
					}else{
						Long projectAttachmentattachmentidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.PROJECT_DOCKYARD_SEQ);
						projectDockyard.setId(projectAttachmentattachmentidSeq );
						sql = DrydockQueryConstant.PROJECT_DOCKYARD_INSERT;
						parameters.addValue("createid", info.getUserId());
						parameters.addValue("createdate", new Date());
					}
					parameters.addValue("id", projectDockyard.getId());
					parameters.addValue("projectId", project.getId());
					parameters.addValue("orgId", project.getOrgid());
					parameters.addValue("shipId", project.getShipid());
					parameters.addValue("dockyardid", projectDockyard.getDockyardId());
					parameters.addValue("contactdetails", projectDockyard.getContactDetails());
					parameters.addValue("remarks", projectDockyard.getRemarks());
					parameters.addValue("default_currencyid", projectDockyard.getDefaultCurrencyId());					

					parameters.addValue("updateid", info.getUserId());
					parameters.addValue("updatedate", new Date());
					parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);

					
					namedParameterJdbcTemplate.update(sql, parameters);
				}

			for (Long projectAttachmentId : hmpExistingAttachment.keySet()) {
				Map<String, Object> parameterMap = new HashMap<>();
				parameterMap.put("isactive", DrydockConstant.INACTIVE_FLAG);
				parameterMap.put("id", projectAttachmentId);

				namedParameterJdbcTemplate.update(DrydockQueryConstant.PROJECT_DOCKYARD_DELETE, parameterMap);  
			}
		}
		catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public void saveProjectCurrencyConversionList(Project project, BasicInfoDTO info) throws Exception {
		String sql=null;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		try{
			List<ProjectCurrencyConversion> existingList = loadProjectCurrencyConversionList(project, info);
			Map<Long, ProjectCurrencyConversion> hmpExistingCurrencyConversion = new HashMap<>();
			if(null != existingList && !existingList.isEmpty()){
				for (ProjectCurrencyConversion projectCurrencyConversion : existingList) {
					hmpExistingCurrencyConversion.put(projectCurrencyConversion.getId(), projectCurrencyConversion);
				}
			}
			for (ProjectCurrencyConversion projectCurrencyConversion : project.getProjectCurrencyConversionList()) {
				if(projectCurrencyConversion.getId()>0 && hmpExistingCurrencyConversion.containsKey(projectCurrencyConversion.getId())){
					sql = DrydockQueryConstant.PROJECT_CURRENCY_CONVERSION_UPDATE;
					hmpExistingCurrencyConversion.remove(projectCurrencyConversion.getId());
				}else{
					Long projectAttachmentattachmentidSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.PROJECT_CURRENCY_CONVERSION_SEQ);
					projectCurrencyConversion.setId(projectAttachmentattachmentidSeq );
					sql = DrydockQueryConstant.PROJECT_CURRENCY_CONVERSION_INSERT;
					parameters.addValue("createid", info.getUserId());
					parameters.addValue("createdate", System.currentTimeMillis());
				}
				parameters.addValue("id", projectCurrencyConversion.getId());
				parameters.addValue("projectId", project.getId());
				parameters.addValue("orgId", project.getOrgid());
				parameters.addValue("shipId", project.getShipid());
				parameters.addValue("fromcurrencyid", projectCurrencyConversion.getFromcurrencyid());
				parameters.addValue("tocurrencyid", projectCurrencyConversion.getTocurrencyid());
				parameters.addValue("conversionRate", projectCurrencyConversion.getConversionRate());

				parameters.addValue("updateid", info.getUserId());
				parameters.addValue("updatedate", System.currentTimeMillis());
				parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);


				namedParameterJdbcTemplate.update(sql, parameters);
			}
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public void saveProjectJobList(Project project, BasicInfoDTO info) throws Exception {
		String sql=null;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		try{
			for (Job projectJob : project.getProjectJobList()) {
				sql = DrydockQueryConstant.PROJECT_JOB_UPDATE;
				parameters.addValue("jobId", projectJob.getId());
				parameters.addValue("appLastUpdtdOn", projectJob.getApprovalLastUpdatedOn());
				parameters.addValue("appLastUpdtdBy", projectJob.getApprovalLastUpdatedBy());
				parameters.addValue("approvalComment", projectJob.getApprovalComment());
				parameters.addValue("approvalFlag", projectJob.getApprovalFlag());

				namedParameterJdbcTemplate.update(sql, parameters);
			}
		}
		catch(Exception e){
			throw e;
		}
	}
	@Override
	public void saveProjectCostTabData(Project project, BasicInfoDTO info) throws Exception {
		String sql=null;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		try{
			parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.addValue("projectId", project.getId());
			parameters.addValue("orgId", project.getOrgid());
			parameters.addValue("shipId", project.getShipid());
			List<ProjectJobCostLineitem> projectJobCostLineitemList =(List<ProjectJobCostLineitem>) namedParameterJdbcTemplate.query(DrydockQueryConstant.PROJECT_JOB_COST_LINEITEM_LIST,parameters, new ProjectJobCostLineitemMapper());
			Map<Long, ProjectJobCostLineitem> hmp = new HashMap<>();
			for (ProjectJobCostLineitem projectJobCostLineitem : projectJobCostLineitemList) {
				hmp.put(projectJobCostLineitem.getId(), projectJobCostLineitem);
			}
			Map<Long, ProjectJobCostLineitemDetails> hmpDetails = new HashMap<>();
			List<ProjectJobCostLineitemDetails> detailsList = (List<ProjectJobCostLineitemDetails>) namedParameterJdbcTemplate.query(DrydockQueryConstant.PROJECT_JOB_COST_LINEITEM_DETAILS_LIST,parameters, new ProjectJobCostLineitemDetailsMapper());;
			for (ProjectJobCostLineitemDetails projectJobCostLineitemDetails : detailsList) {
				hmpDetails.put(projectJobCostLineitemDetails.getId(), projectJobCostLineitemDetails);
			}
			
			for(ProjectJobCostLineitem projectJobCostLineitem : project.getLineitemList()){
				if(projectJobCostLineitem.getId()>0 && hmp.containsKey(projectJobCostLineitem.getId())){
					sql = DrydockQueryConstant.PROJECT_JOB_COST_LINEITEM_UPDATE;
					hmp.remove(projectJobCostLineitem.getId());
				}else{
					Long projectJobCosLineitemIdSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.PROJECT_JOB_COST_LINEITEM_SEQ);
					projectJobCostLineitem.setId(projectJobCosLineitemIdSeq );
					sql = DrydockQueryConstant.PROJECT_JOB_COST_LINEITEM_INSERT;
					parameters.addValue("createid", info.getUserId());
					parameters.addValue("createdate", System.currentTimeMillis());
				}
				parameters.addValue("id", projectJobCostLineitem.getId());
				parameters.addValue("projectId", project.getId());
				parameters.addValue("orgId", project.getOrgid());
				parameters.addValue("shipId", project.getShipid());
				parameters.addValue("jobId", projectJobCostLineitem.getJobid());
				parameters.addValue("lineitem", projectJobCostLineitem.getLineitem());

				parameters.addValue("updateid", info.getUserId());
				parameters.addValue("updatedate", System.currentTimeMillis());
				parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);


				namedParameterJdbcTemplate.update(sql, parameters);
				
				if(null!=projectJobCostLineitem.getDetailsList()){
					for(ProjectJobCostLineitemDetails projectJobCostLineitemDetails: projectJobCostLineitem.getDetailsList()){
						if(projectJobCostLineitemDetails.getId()>0 && hmpDetails.containsKey(projectJobCostLineitemDetails.getId())){
							sql = DrydockQueryConstant.PROJECT_JOB_COST_LINEITEM_DETAILS_UPDATE;
							hmpDetails.remove(projectJobCostLineitemDetails.getId());
						}else{
							Long projectJobCosLineitemDetailsIdSeq = sequenceValueGenerator.getNextTransactionNumberFromSequence(DrydockConstant.PROJECT_JOB_COST_LINEITEM_DETAILS_SEQ);
							projectJobCostLineitemDetails.setId(projectJobCosLineitemDetailsIdSeq );
							sql = DrydockQueryConstant.PROJECT_JOB_COST_LINEITEM_DETAILS_INSERT;
							parameters.addValue("createid", info.getUserId());
							parameters.addValue("createdate", System.currentTimeMillis());
						}
						parameters.addValue("id", projectJobCostLineitemDetails.getId());
						parameters.addValue("projectId", project.getId());
						parameters.addValue("orgId", project.getOrgid());
						parameters.addValue("shipId", project.getShipid());
						parameters.addValue("dockyardId", projectJobCostLineitemDetails.getDockyardId());
						parameters.addValue("jobId", projectJobCostLineitemDetails.getJobid());
						parameters.addValue("quoteCurrencyId", projectJobCostLineitemDetails.getQuoteCurrencyid());
						parameters.addValue("unit", projectJobCostLineitemDetails.getUnit());
						parameters.addValue("unitPrice", projectJobCostLineitemDetails.getUnitPrice());
						parameters.addValue("unitQuantity", projectJobCostLineitemDetails.getUnitQuantity());
						parameters.addValue("costQuoteCurrency", projectJobCostLineitemDetails.getCostQuoteCurrency());
						parameters.addValue("lineitemId", projectJobCostLineitem.getId());
						parameters.addValue("costProjectCurrency", projectJobCostLineitemDetails.getCostProjectCurrency());

						parameters.addValue("updateid", info.getUserId());
						parameters.addValue("updatedate", System.currentTimeMillis());
						parameters.addValue("isactive", DrydockConstant.IS_ACTIVE_FLAG);


						namedParameterJdbcTemplate.update(sql, parameters);
					}
				}
			}
			
			Map<String, Object> parameter = new HashMap<>();
			for(Long lineitemId: hmp.keySet()){
				parameter.clear();
				parameter.put("id", lineitemId);
				parameter.put("isactive", DrydockConstant.INACTIVE_FLAG);
				namedParameterJdbcTemplate.update(DrydockQueryConstant.DELETE_PROJECT_JOB_COST_LINEITEM, parameters);
			}
			for(Long lineitemDetailId: hmpDetails.keySet()){
				parameter.clear();
				parameter.put("id", lineitemDetailId);
				parameter.put("isactive", DrydockConstant.INACTIVE_FLAG);
				namedParameterJdbcTemplate.update(DrydockQueryConstant.DELETE_PROJECT_JOB_COST_LINEITEM_DETAIL, parameters);
			}
		}
		catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public ByteArrayInputStream generateProjectReport(Long projectId, BasicInfoDTO info)
			throws Exception {
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("orgid", info.getOrgId());
			parameters.put("projectid", projectId);
			Project project = (Project) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.PROJECT_DETAILS,parameters, new ProjectMapper());
			project.setProjectAttachmentList(loadProjectAttachmentList(project, info));
			project.setProjectDockyardList(loadProjectDockyardList(project, info));
			project.setProjectCurrencyConversionList(loadProjectCurrencyConversionList(project, info));
			project.setProjectJobList(loadJobList(project.getShipid(), false, info));
			project.setLineitemList(loadProjectJobCostLineitemList(project, info));
			
			return pdfGenerator.customerPDFReport(project);			
			//return project;
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public Object copyJob(long projectId, Long jobId, String jobType, BasicInfoDTO info) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object loadProjectDetailsForReport(long projectId, BasicInfoDTO info) throws Exception {
		try{
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("isactive", DrydockConstant.IS_ACTIVE_FLAG);
			parameters.put("orgid", info.getOrgId());
			parameters.put("projectid", projectId);
			Project project = (Project) namedParameterJdbcTemplate.queryForObject(DrydockQueryConstant.PROJECT_DETAILS,parameters, new ProjectMapper());
					
			return project;
		}
		catch(Exception e){
			throw e;
		}
	}
	

	
}
