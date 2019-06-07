package com.drydock.repository;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.entity.ApplicationComponent;
import com.drydock.entity.ApplicationJob;
import com.drydock.entity.ApplicationJobMaterialDetails;
import com.drydock.entity.Job;
import com.drydock.entity.JobAttachment;
import com.drydock.entity.JobComment;
import com.drydock.entity.JobMaterialDetails;
import com.drydock.entity.JobProgressReport;
import com.drydock.entity.OrganizationComponent;
import com.drydock.entity.OrganizationJob;
import com.drydock.entity.OrganizationJobMaterialDetails;
import com.drydock.entity.Project;
import com.drydock.entity.ProjectAttachment;
import com.drydock.entity.ProjectCurrencyConversion;
import com.drydock.entity.ProjectDockyard;
import com.drydock.entity.ProjectJobCostLineitem;
import com.drydock.entity.ShipComponentDetails;

@Component
public interface ProjectRepository {

	
    public List<ApplicationComponent> loadApplicationComponentList(BasicInfoDTO info) throws Exception;
	public void saveApplicationComponent(ApplicationComponent applicationComponent, BasicInfoDTO info) throws Exception;
	public void deleteApplicationComponent(long applicationcomponentid, BasicInfoDTO info) throws Exception;
	
    public List<OrganizationComponent> loadOrganizationComponentList(BasicInfoDTO info) throws Exception;
	public void saveOrganizationComponent(OrganizationComponent organizationComponent, BasicInfoDTO info) throws Exception;
	public void deleteOrganizationComponent(long organizationcomponentid, BasicInfoDTO info) throws Exception;
	public void saveShipComponentDetails(ShipComponentDetails shipComponentDetails, BasicInfoDTO info) throws Exception;
	public Object loadShipComponentDetails(Long shipId,Long organizationcomponentid, BasicInfoDTO info) throws Exception;
	public List<Job> loadJobList(long shipId, boolean onlyApprovedFlag, BasicInfoDTO info) throws Exception;
	public List<Project> loadProjectList(long shipId, Boolean onlyActiveFlag,BasicInfoDTO info) throws Exception;
	public Job loadJobDetails(long jobId, BasicInfoDTO info) throws Exception;
	public Object loadProjectDetails(Long projectId, BasicInfoDTO info) throws Exception;
	public void saveProject(Project project, boolean attachmentFlag, BasicInfoDTO info) throws Exception;
	public void saveJob(Job job, boolean attachmentFlag, BasicInfoDTO info) throws Exception;
	public Object loadShipComponentList(Long shipId, BasicInfoDTO info) throws Exception;
	public void markJobComplete(Long jobId, String jobCloserRemark, BasicInfoDTO info) throws Exception;
	public void markJobCancel(Long jobId, BasicInfoDTO info) throws Exception;
	void markProjectComplete(Long projectId, String closerAttachmentRelativePath, String closerAttachmentName, String closerComment, BasicInfoDTO info) throws Exception;
	public List<Job> loadActiveJobListForProject(Long projectId, BasicInfoDTO info) throws Exception;
	public List<ApplicationJob> loadApplicationJobList(BasicInfoDTO info) throws Exception;
	public Object loadApplicationJobDetails(Long applicationJobId, BasicInfoDTO info)  throws Exception;
	public boolean saveApplicationJob(ApplicationJob applicationJob, BasicInfoDTO info)  throws Exception;
	public List<Job> loadMyJobList(long shipId, BasicInfoDTO info)  throws Exception;
	public Object loadCheckboxesList(BasicInfoDTO info) throws Exception;
	public void saveOrganizationJob(OrganizationJob organizationJob, BasicInfoDTO info)  throws Exception;
	
	public List<OrganizationJob> loadOrganizationJobList( BasicInfoDTO info) throws Exception;
	public Object loadOrganizationJobDetail( long jobId, BasicInfoDTO info) throws Exception;
	public  List<Project> loadCompletedProjectListByShipId( int shipId, BasicInfoDTO info) throws Exception;
	public  Project loadActiveProjectDetail( int shipId, BasicInfoDTO info) throws Exception;
	public List<JobMaterialDetails> loadJobMaterialDetailsList(long jobId,
			BasicInfoDTO info) throws Exception;
	public void saveJobMaterialDetailsList(long jobId,
			List<JobMaterialDetails> jobMaterialDetailsList, BasicInfoDTO info)
			throws Exception;
	public void saveJobAttachmentList(Job job, JobProgressReport jobProgressReport,
			BasicInfoDTO info) throws Exception;
	
	public void saveJobProgressReport(Job job,
			JobProgressReport jobProgressReport, BasicInfoDTO info) throws Exception;
	public List<JobProgressReport> loadJobProgressReportList(Job job,
			BasicInfoDTO info) throws Exception;
	public void saveProjectAttachmentList(Project project, BasicInfoDTO info)
			throws Exception;
	public void saveProjectDockyardList(Project project, BasicInfoDTO info)
			throws Exception;
	public void saveProjectCurrencyConversionList(Project project, BasicInfoDTO info)
			throws Exception;
	public void saveProjectJobList(Project project, BasicInfoDTO info)
			throws Exception;
	
	public List<ProjectJobCostLineitem> loadProjectJobCostLineitemList(Project project, BasicInfoDTO info) throws Exception;
	void saveProjectCostTabData(Project project, BasicInfoDTO info)
			throws Exception;
	
	public ByteArrayInputStream generateProjectReport(Long projectId, BasicInfoDTO info) throws Exception;
	
	List<ApplicationJobMaterialDetails> loadApplicationJobMaterialDetailsList(
			long applicationJobId, BasicInfoDTO info) throws Exception;
	List<OrganizationJobMaterialDetails> loadOrganizationJobMaterialDetailsList(
			long organizationJobId, BasicInfoDTO info) throws Exception;
	void saveApplicationJobMaterialDetailsList(
			long jobId,
			List<ApplicationJobMaterialDetails> applicationJobMaterialDetailsList,
			BasicInfoDTO info) throws Exception;
	void saveOrganizationJobMaterialDetailsList(
			long organizationJobId,
			List<OrganizationJobMaterialDetails> organizationJobMaterialDetailsList,
			BasicInfoDTO info) throws Exception;
	
	public Object copyJob( long projectId, Long jobId, String jobType, BasicInfoDTO info) throws Exception;
	void saveJobComment(Job job, JobComment jobComment, BasicInfoDTO info)
			throws Exception;
	public Object loadProjectDetailsForReport(long projectId, BasicInfoDTO info)
			throws Exception;
	public List<ProjectAttachment> loadProjectAttachmentList(Project project, BasicInfoDTO info) throws Exception;
	List<ProjectDockyard> loadProjectDockyardList(Project project,
			BasicInfoDTO info) throws Exception;
	List<ProjectCurrencyConversion> loadProjectCurrencyConversionList(
			Project project, BasicInfoDTO info) throws Exception;
	OrganizationComponent loadOrganizationComponentDetails(long orgComponentId,
			BasicInfoDTO info) throws Exception;
}
