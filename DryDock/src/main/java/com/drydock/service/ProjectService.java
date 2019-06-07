package com.drydock.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.entity.ApplicationComponent;
import com.drydock.entity.ApplicationJob;
import com.drydock.entity.Job;
import com.drydock.entity.JobMaterialDetails;
import com.drydock.entity.OrganizationComponent;
import com.drydock.entity.OrganizationJob;
import com.drydock.entity.Project;
import com.drydock.entity.ReportDTO;
import com.drydock.entity.ShipComponentDetails;


@Component
public interface ProjectService {

	public List<ApplicationComponent> loadApplicationComponentList(BasicInfoDTO info) throws Exception;
	public void saveApplicationComponent(ApplicationComponent applicationComponent, BasicInfoDTO info) throws Exception;
	public void deleteApplicationComponent(long applicationcomponentid, BasicInfoDTO info) throws Exception;
	
	public List<OrganizationComponent> loadOrganizationComponentList(BasicInfoDTO info) throws Exception;
	public void saveOrganizationComponent(OrganizationComponent organizationComponent, BasicInfoDTO info) throws Exception;
	public void deleteOrganizationComponent(long organizationcomponentid, BasicInfoDTO info) throws Exception;
	public Object loadShipComponentDetails(Long shipId, Long organizationcomponentid, BasicInfoDTO info) throws Exception;
	public void saveShipComponentDetails(ShipComponentDetails shipComponentDetails, BasicInfoDTO info) throws Exception;
	public List<Job> loadApprovedJobList(long shipId, BasicInfoDTO info) throws Exception;
	public List<Project> loadProjectList(Long shipId, Boolean onlyActiveFlag, BasicInfoDTO info) throws Exception;
	public Object loadJobDetails(Long jobId, BasicInfoDTO info) throws Exception;
	public Object loadProjectDetails(Long projectId, BasicInfoDTO info) throws Exception;
	public void saveProject(Project project, BasicInfoDTO info) throws Exception;
	public void saveJob(Job job, BasicInfoDTO info) throws Exception;
	public Object loadShipComponentList(Long shipId, BasicInfoDTO info) throws Exception;
	public void saveJobWithAttachment(Job job, BasicInfoDTO info) throws Exception;
	public void markJobComplete(Long jobId, String jobCloserRemark, BasicInfoDTO info) throws Exception;
	public void markJobCancel(Long jobId, BasicInfoDTO info) throws Exception;
	public void markProjectComplete(Long projectId, String closerAttachmentRelativePath, String closerAttachmentName, String closerComment, BasicInfoDTO info) throws Exception;
	public List<ApplicationJob> loadApplicationJobList(BasicInfoDTO info) throws Exception;
	public Object loadApplicationJobDetails(Long applicationJobId, BasicInfoDTO info) throws Exception;
	public void saveApplicationJob(ApplicationJob applicationJob, BasicInfoDTO info) throws Exception;
	public List<Job> loadMyJobList(long shipId, BasicInfoDTO info) throws Exception;
	public Object loadCheckboxesList(BasicInfoDTO info) throws Exception;
	
	public List<OrganizationJob> loadOrganizationJobList(BasicInfoDTO info) throws Exception;
	public Object loadOrganizationJobDetail(int jobId, BasicInfoDTO info) throws Exception;
	public void saveOrganizationJob(OrganizationJob organizationJob, BasicInfoDTO info) throws Exception;
	public List<Project> loadCompletedProjectListByShipId( int shipId, BasicInfoDTO info) throws Exception;
	public  Project loadActiveProjectDetail( int shipId, BasicInfoDTO info) throws Exception;
	public List<JobMaterialDetails> loadJobMaterialDetailsList(long jobId,
			BasicInfoDTO info) throws Exception;
	public void saveProjectWithAttachment(Project project, BasicInfoDTO info) throws Exception;
	List<Job> loadAllJobList(long shipId, BasicInfoDTO info) throws Exception;
	public ByteArrayInputStream generateProjectReport(long projectId, BasicInfoDTO info) throws Exception;
	
	public Object copyJob(long orgId, long projectId, Long shipId, Long jobId, String jobType, List<String> attachmentTypes, BasicInfoDTO info) throws Exception;
	public Object loadProjectDetailForReport(long projectId, boolean withImageData, BasicInfoDTO info) throws Exception;
	public Object generateProjectReportIText(long projectId, BasicInfoDTO info, ReportDTO reportFields) throws Exception;
	
	
}
