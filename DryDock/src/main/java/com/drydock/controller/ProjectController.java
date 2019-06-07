package com.drydock.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration2.DatabaseConfiguration;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.dto.ComponentDTO;
import com.drydock.entity.ApplicationComponent;
import com.drydock.entity.ApplicationJob;
import com.drydock.entity.Job;
import com.drydock.entity.JobAttachment;
import com.drydock.entity.JobMaterialDetails;
import com.drydock.entity.KeyValue;
import com.drydock.entity.OrganizationComponent;
import com.drydock.entity.OrganizationJob;
import com.drydock.entity.OrganizationVesselDocType;
import com.drydock.entity.Project;
import com.drydock.entity.ProjectAttachment;
import com.drydock.entity.ReportDTO;
import com.drydock.entity.ShipAttachment;
import com.drydock.entity.ShipComponentDetails;
import com.drydock.exception.ValidationException;
import com.drydock.service.CoreService;
import com.drydock.service.ProjectService;
import com.drydock.util.DrydockConstant;

@RestController
@RequestMapping("project")
public class ProjectController {

	@Autowired
	CoreService coreService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	private DatabaseConfiguration databaseConfiguration;

	@RequestMapping(value="/loadApplicationComponentList" , method = RequestMethod.POST)
	public Object loadApplicationComponentList(HttpServletRequest request,HttpServletResponse response) {
		List<ComponentDTO> appComponentDTOList = new ArrayList<ComponentDTO>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			List<ApplicationComponent> appComponentList= projectService.loadApplicationComponentList(info);
			for (ApplicationComponent applicationComponent : appComponentList) {
				appComponentDTOList.add(new ComponentDTO(applicationComponent));
			}
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching application component list");
		}
		return appComponentDTOList;
	}
	
	@RequestMapping(value="/saveApplicationComponent" , method = RequestMethod.POST)
	public Object saveApplicationComponent(HttpServletRequest request,HttpServletResponse response,
			@RequestBody ApplicationComponent applicationComponent) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectService.saveApplicationComponent(applicationComponent,info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while saving application component.");
		}
		return applicationComponent;
	}

	@RequestMapping(value="/deleteApplicationComponent", method = RequestMethod.POST)
	public void deleteApplicationComponent(HttpServletRequest request, HttpServletResponse response,
		@RequestParam("applicationcomponentid") Long applicationcomponentid) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectService.deleteApplicationComponent(applicationcomponentid,info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while deleting application component.");
		}
	}

    
    @RequestMapping(value="/loadOrganizationComponentList" , method = RequestMethod.POST)
	public Object loadOrganizationComponentList(HttpServletRequest request,HttpServletResponse response) {
		List<ComponentDTO> orgComponentDTOList = new ArrayList<>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			List<OrganizationComponent> orgComponentList= projectService.loadOrganizationComponentList(info);
			for (OrganizationComponent organizationComponent : orgComponentList) {
				orgComponentDTOList.add(new ComponentDTO(organizationComponent));
			}
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching Organization component list");
		}
		return orgComponentDTOList;
	}
	
	@RequestMapping(value="/saveOrganizationComponent" , method = RequestMethod.POST)
	public Object saveOrganizationComponent(HttpServletRequest request,HttpServletResponse response,
			@RequestBody OrganizationComponent organizationComponent) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectService.saveOrganizationComponent(organizationComponent,info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while saving Organization component.");
		}
		return organizationComponent;
	}

	@RequestMapping(value="/deleteOrganizationComponent", method = RequestMethod.POST)
	public void deleteOrganizationComponent(HttpServletRequest request, HttpServletResponse response,
		@RequestParam("organizationcomponentid") Long organizationcomponentid) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectService.deleteOrganizationComponent(organizationcomponentid,info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while deleting Organization component.");
		}
	}

	


    
    @RequestMapping(value="/loadShipComponentDetails" , method = RequestMethod.POST)
	public Object loadShipComponentDetails(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("organizationcomponentid") Long organizationcomponentid,
			@RequestParam("shipId") Long shipId) {
		
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return projectService.loadShipComponentDetails(shipId, organizationcomponentid, info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching Organization component details for ship");
			return null;
		}
	}
	
    @RequestMapping(value="/loadShipComponentList" , method = RequestMethod.POST)
	public Object loadShipComponentList(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("shipId") Long shipId) {
		
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return projectService.loadShipComponentList(shipId, info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching Organization component details for ship");
			return null;
		}
	}
	
	@RequestMapping(value="/saveShipComponentDetails" , method = RequestMethod.POST)
	public Object saveShipComponentDetails(HttpServletRequest request,HttpServletResponse response,
			@RequestBody ShipComponentDetails shipComponentDetails) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectService.saveShipComponentDetails(shipComponentDetails,info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while saving Organization component.");
		}
		return shipComponentDetails;
	}

    @RequestMapping(value="/loadJobList" , method = RequestMethod.POST)
	public Object loadJobList(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("shipId") Long shipId) {
		List<Job> jobList = new ArrayList<>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			jobList= projectService.loadApprovedJobList(shipId, info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching Organization component list");
		}
		return jobList;
	}

    @RequestMapping(value="/loadMyJobList" , method = RequestMethod.POST)
	public Object loadMyJobList(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("shipId") Long shipId) {
		List<Job> jobList = new ArrayList<>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			jobList= projectService.loadMyJobList(shipId, info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching Organization component list");
		}
		return jobList;
	}

    @RequestMapping(value="/loadJobDetails" , method = RequestMethod.POST)
	public Object loadJobDetails(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("jobId") Long jobId) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return projectService.loadJobDetails(jobId, info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching job details");
			return null;
		}
	}

    @RequestMapping(value="/loadProjectList" , method = RequestMethod.POST)
	public Object loadProjectList(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("shipId") Long shipId) {
		List<Project> projectList = new ArrayList<>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectList= projectService.loadProjectList(shipId, true, info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching Organization component list");
		}
		return projectList;
	}
    
    @RequestMapping(value="/loadProjectListForCloser" , method = RequestMethod.POST)
	public Object loadProjectListForCloser(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("shipId") Long shipId) {
		List<Project> projectList = new ArrayList<>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectList= projectService.loadProjectList(shipId, false, info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching Organization component list");
		}
		return projectList;
	}

    @RequestMapping(value="/loadProjectDetails" , method = RequestMethod.POST)
	public Object loadProjectDetails(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("projectId") Long projectId) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return projectService.loadProjectDetails(projectId, info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching project details");
			return null;
		}
	}

	@RequestMapping(value="/saveProject" , method = RequestMethod.POST)
	public Object saveProject(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Project project) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectService.saveProject(project,info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while saving Project data.");
		}
		return project;
	}

	@RequestMapping(value="/saveProjectWithAttachment" , method = RequestMethod.POST)
	public Object saveProjectWithAttachment(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Project project) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectService.saveProjectWithAttachment(project,info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while saving Project data.");
		}
		return project;
	}

	@RequestMapping(value="/saveJob" , method = RequestMethod.POST)
	public Object saveJob(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Job job) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectService.saveJob(job,info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while saving application component.");
		}
		return job;
	}

	@RequestMapping(value="/saveJobWithAttachment" , method = RequestMethod.POST)
	public Object saveJobWithAttachment(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Job job) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectService.saveJobWithAttachment(job,info);
			return job;
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while saving application component.");
			return null;
		}
	}

	@RequestMapping(value="/markJobComplete" , method = RequestMethod.POST)
	public void markJobComplete(HttpServletRequest request,HttpServletResponse response,
			@RequestParam Long jobId, @RequestParam String jobCloserRemark) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectService.markJobComplete(jobId,jobCloserRemark,info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while saving application component.");
		}
	}

	@RequestMapping(value="/markJobCancel" , method = RequestMethod.POST)
	public void markJobCancel(HttpServletRequest request,HttpServletResponse response,
			@RequestParam Long jobId) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectService.markJobCancel(jobId,info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while saving application component.");
		}
	}

	@RequestMapping(value="/markProjectComplete" , method = RequestMethod.POST)
	public void markProjectComplete(HttpServletRequest request,HttpServletResponse response,
			@RequestParam Long projectId, @RequestParam String closerAttachmentRelativePath, @RequestParam String closerAttachmentName, @RequestParam String closerComment) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectService.markProjectComplete(projectId, closerAttachmentRelativePath, closerAttachmentName, closerComment, info);
		}catch(ValidationException e){
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while saving project status.");
		}
	}

    @RequestMapping(value="/loadApplicationJobList" , method = RequestMethod.POST)
	public Object loadApplicationJobList(HttpServletRequest request,HttpServletResponse response) {
		List<ApplicationJob> jobList = new ArrayList<>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			jobList= projectService.loadApplicationJobList(info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching job list");
		}
		return jobList;
	}

    @RequestMapping(value="/loadApplicationJobDetails" , method = RequestMethod.POST)
	public Object loadApplicationJobDetails(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("applicationJobId") Long applicationJobId) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return projectService.loadApplicationJobDetails(applicationJobId, info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching job details");
			return null;
		}
	}

	@RequestMapping(value="/saveApplicationJob" , method = RequestMethod.POST)
	public Object saveApplicationJob(HttpServletRequest request,HttpServletResponse response,
			@RequestBody ApplicationJob applicationJob) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectService.saveApplicationJob(applicationJob,info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while saving job in library.");
		}
		return applicationJob;
	}
    @RequestMapping(value="/loadCheckboxesList" , method = RequestMethod.POST)
	public Object loadCheckboxesList(HttpServletRequest request,HttpServletResponse response) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return projectService.loadCheckboxesList(info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching Organization component list");
			return null;
		}
	}
    
    @RequestMapping(value="/loadOrganizationJobList" , method = RequestMethod.POST)
	public Object loadOrganizationJobList(HttpServletRequest request,HttpServletResponse response) {
		List<OrganizationJob> jobList = new ArrayList<>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			jobList= projectService.loadOrganizationJobList(info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching Organization component list");
		}
		return jobList;
	}
    
    @RequestMapping(value="/loadOrganizationJobDetail" , method = RequestMethod.POST)
	public Object loadOrganizationJobDetail(HttpServletRequest request,HttpServletResponse response, @RequestParam int jobId) {
		OrganizationJob jobList = null;
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			jobList= (OrganizationJob) projectService.loadOrganizationJobDetail(jobId, info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching Organization component list");
		}
		return jobList;
	}
    
    @RequestMapping(value="/saveOrganizationJob" , method = RequestMethod.POST)
	public Object saveOrganizationJob(HttpServletRequest request,HttpServletResponse response,
			@RequestBody OrganizationJob organizationJob) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectService.saveOrganizationJob(organizationJob,info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while saving job in library.");
		}
		return organizationJob;
	}
    
    @RequestMapping(value="/loadCompletedProjectListByShipId" , method = RequestMethod.POST)
	public Object loadCompletedProjectListByShipId(HttpServletRequest request,HttpServletResponse response, @RequestParam int shipId) {
		List<Project> projectList = new ArrayList<>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			projectList= projectService.loadCompletedProjectListByShipId(shipId, info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching Organization component list");
		}
		return projectList;
	}
    
    @RequestMapping(value="/loadActiveProjectDetail" , method = RequestMethod.POST)
	public Object loadActiveProjectDetail(HttpServletRequest request,HttpServletResponse response, @RequestParam int shipId) {
		Project project = null;
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			project= projectService.loadActiveProjectDetail(shipId, info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching Organization component list");
		}
		return project;
	}

	@RequestMapping(value="/loadJobMaterialDetailsList" , method = RequestMethod.POST)
	public Object loadJobMaterialDetailsList(HttpServletRequest request,HttpServletResponse response, @RequestParam long jobId) {
		List<JobMaterialDetails> jobMaterialDetailsList = new ArrayList<JobMaterialDetails>();
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			jobMaterialDetailsList= projectService.loadJobMaterialDetailsList(jobId, info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching application component list");
		}
		return jobMaterialDetailsList;
	}
	

	@RequestMapping(value="/attachVesselDocWithJob" , method = RequestMethod.POST)
	public Object attachVesselDocWithJob(HttpServletRequest request,HttpServletResponse response,
			@RequestParam long jobId, @RequestParam long projectId, 
			@RequestParam String attachmentType, @RequestParam long progressReportId, 
			@RequestBody List<ShipAttachment> shipAttachmentList) {
		List<JobAttachment> newJobAttachmentList = new ArrayList<>();
		InputStream is = null;
	    OutputStream os = null;
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			Map<Long, OrganizationVesselDocType> hmp = new HashMap<>();
			List<OrganizationVesselDocType> organizationVesselDocTypeList = coreService.loadOrganizationVesselDocTypeList(info);
			for (OrganizationVesselDocType organizationVesselDocType : organizationVesselDocTypeList) {
				hmp.put(organizationVesselDocType.getId(), organizationVesselDocType);
			}
			for (ShipAttachment shipAttachment : shipAttachmentList) {
				String docBaseShip = databaseConfiguration.getString(DrydockConstant.DOC_BASE);
				String fileName = docBaseShip+shipAttachment.getOrgid()+"/ship_image/"+shipAttachment.getShipid()+"/"+shipAttachment.getRelativepath();
				File file = new File(fileName);
				is = new FileInputStream(file);
				
				
				String docBaseJob = databaseConfiguration.getString(DrydockConstant.DOC_BASE);
				String fullPath = docBaseJob+shipAttachment.getOrgid()+"/"+shipAttachment.getShipid()+"/"+projectId+"/"+jobId+"/";
				File dir = new File(fullPath);
				if(!dir.exists())
					dir.mkdirs();
				int numberOfSubfolders = dir.listFiles()==null?1:(dir.listFiles().length+1);
				System.out.println(numberOfSubfolders);
				File finalDir = new File(fullPath+"/"+numberOfSubfolders);

				finalDir.mkdirs();

				String relativePath = numberOfSubfolders+"/" + shipAttachment.getAttachmentName();
				
				os = new FileOutputStream(new File(fullPath+'/'+relativePath));
				byte[] buffer = new byte[1024];
		        int length;
		        while ((length = is.read(buffer)) > 0) {
		            os.write(buffer, 0, length);
		        }
		        try{
		        	is.close();
		        	os.close();
		        }catch(Exception ex){
						
					}
		        OrganizationVesselDocType organizationVesselDocType = hmp.get(shipAttachment.getVesselDocTypeId());
				JobAttachment ja = new JobAttachment();
				ja.setAttachmentDescription(organizationVesselDocType.getVesselDocDescription());
				ja.setAttachmentName(shipAttachment.getAttachmentName());
				ja.setAttachmentType(attachmentType);
				ja.setDescription(organizationVesselDocType.getVesselDocDescription());
				ja.setJobid(jobId);
				ja.setOrgid(shipAttachment.getOrgid());
				ja.setProgressreportid(progressReportId);
				ja.setProjectid(projectId);
				ja.setRelativepath(relativePath);
				ja.setShipid(shipAttachment.getShipid());
				
				newJobAttachmentList.add(ja);
			}
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while attaching vessel doc with job.");
		}finally {
			try{
				is.close();
				os.close();
			}catch(Exception ex){
				
			}
	    }
		return newJobAttachmentList;
	}
	

	@RequestMapping(value="/attachVesselDocWithProject" , method = RequestMethod.POST)
	public Object attachVesselDocWithProject(HttpServletRequest request,HttpServletResponse response,
			@RequestParam long projectId, @RequestParam String attachmentType, 
			@RequestBody List<ShipAttachment> shipAttachmentList) {
		List<ProjectAttachment> newProjectAttachmentList = new ArrayList<>();
		InputStream is = null;
	    OutputStream os = null;
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			Map<Long, OrganizationVesselDocType> hmp = new HashMap<>();
			List<OrganizationVesselDocType> organizationVesselDocTypeList = coreService.loadOrganizationVesselDocTypeList(info);
			for (OrganizationVesselDocType organizationVesselDocType : organizationVesselDocTypeList) {
				hmp.put(organizationVesselDocType.getId(), organizationVesselDocType);
			}
			for (ShipAttachment shipAttachment : shipAttachmentList) {
				String docBaseShip = databaseConfiguration.getString(DrydockConstant.DOC_BASE);
				String fileName = docBaseShip+shipAttachment.getOrgid()+"/ship_image/"+shipAttachment.getShipid()+"/"+shipAttachment.getRelativepath();
				File file = new File(fileName);
				is = new FileInputStream(file);
				
				
				String docBaseJob = databaseConfiguration.getString(DrydockConstant.DOC_BASE);
				String fullPath = docBaseJob+shipAttachment.getOrgid()+"/"+shipAttachment.getShipid()+"/project_attachment/"+projectId+"/";
				File dir = new File(fullPath);
				if(!dir.exists())
					dir.mkdirs();
				int numberOfSubfolders = dir.listFiles()==null?1:(dir.listFiles().length+1);
				System.out.println(numberOfSubfolders);
				File finalDir = new File(fullPath+"/"+numberOfSubfolders);

				finalDir.mkdirs();

				String relativePath = numberOfSubfolders+"/" + shipAttachment.getAttachmentName();
				
				os = new FileOutputStream(new File(fullPath+'/'+relativePath));
				byte[] buffer = new byte[1024];
		        int length;
		        while ((length = is.read(buffer)) > 0) {
		            os.write(buffer, 0, length);
		        }
		        try{
		        	is.close();
		        	os.close();
		        }catch(Exception ex){
						
					}
		        OrganizationVesselDocType organizationVesselDocType = hmp.get(shipAttachment.getVesselDocTypeId());
		        ProjectAttachment pa = new ProjectAttachment();
				pa.setAttachmentDescription(organizationVesselDocType.getVesselDocDescription());
				pa.setAttachmentName(shipAttachment.getAttachmentName());
				pa.setAttachmentType(attachmentType);
				pa.setOrgid(shipAttachment.getOrgid());
				pa.setProjectid(projectId);
				pa.setRelativepath(relativePath);
				pa.setShipid(shipAttachment.getShipid());
				
				newProjectAttachmentList.add(pa);
			}
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while attaching vessel doc with job.");
		}finally {
			try{
				is.close();
				os.close();
			}catch(Exception ex){
				
			}
	    }
		return newProjectAttachmentList;
	}
	
	@RequestMapping(value="/generateProjectReport" , method = RequestMethod.GET)
	public Object generateProjectReport(HttpServletRequest request,HttpServletResponse response, @RequestParam long projectId) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			ByteArrayInputStream bis  = projectService.generateProjectReport(projectId, info);
			 HttpHeaders headers = new HttpHeaders();
		        headers.add("Content-Disposition", "inline; filename=customers.pdf");
		 
		        return ResponseEntity
		                .ok()
		                .headers(headers)
		                .contentType(MediaType.APPLICATION_PDF)
		                .body(new InputStreamResource(bis));
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching application component list");
			return null;
		}
	}
	
	@RequestMapping(value="/copyJob" , method = RequestMethod.POST)
	public Object copyJob(HttpServletRequest request,HttpServletResponse response, 
			@RequestParam long orgId, @RequestParam long projectId, @RequestParam long shipId, 
			@RequestParam Long jobId, @RequestParam String jobType, @RequestParam List<String> attachmentTypes) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return projectService.copyJob(orgId, projectId, shipId, jobId, jobType, attachmentTypes, info);
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fcopy job");
			return null;
		}
	}
	
	@RequestMapping(value="/loadProjectDetailForReport" , method = RequestMethod.GET)
	public Object loadProjectDetailForReport(HttpServletRequest request,HttpServletResponse response, @RequestParam long projectId) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return projectService.loadProjectDetailForReport(projectId, false, info);
			 
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching project details");
			return null;
		}
	}
	
	@RequestMapping(value="/loadProjectDetailForReportWithImageData" , method = RequestMethod.GET)
	public Object loadProjectDetailForReportWithImageData(HttpServletRequest request,HttpServletResponse response, @RequestParam long projectId) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			return projectService.loadProjectDetailForReport(projectId, true, info);
			 
		}catch(Exception e){
			e.printStackTrace();
			CoreController.sendError(response, "Some error occurred while fetching project details");
			return null;
		}
	}
	
	@RequestMapping(value="/uploadAllReport" , method = RequestMethod.POST)
	public void uploadAllReport(HttpServletRequest request,HttpServletResponse response, @RequestParam String allJobIDs) {
		try{
			System.out.print('A');
			System.out.println();
			int i=0;
			if(null!=allJobIDs && !"".equals(allJobIDs)){
				if(allJobIDs.endsWith(",")){
					allJobIDs=allJobIDs.substring(0, allJobIDs.length()-1);
				}
				String[] allJobIDsArray = allJobIDs.split(",");
				for (String jobId : allJobIDsArray) {
					i++;
					byte[] file=request.getParameter(jobId).getBytes("iso-8859-1");
					if(null!=file)
						FileUtils.writeByteArrayToFile(new File("d:\\testPDF\\test"+i+".pdf"), file);
				}
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/uploadSingleReport" , method = RequestMethod.POST)
	public void uploadSingleReport(HttpServletRequest request,HttpServletResponse response, @RequestParam byte[] file,
			@RequestParam String jobId, @RequestParam long identifier) {
		try{
			System.out.print('A');
//			byte[] bytes = Base64.getDecoder().decode(file);
			 
			FileUtils.writeByteArrayToFile(new File("d:\\testPDF\\test"+jobId+".pdf"), file);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	
	@RequestMapping(value="/generateProjectReportIText" , method = RequestMethod.POST)
	public Object generateProjectReportIText(HttpServletRequest request,HttpServletResponse response, @RequestParam long projectId, @RequestBody ReportDTO reportFields) {
		try{
			System.out.print('A');
			 
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			//info.setOrgId(6010);
			String filepathzipped = (String) projectService.generateProjectReportIText(projectId, info, reportFields);
			
			//downloading the zip
            File file = new File(filepathzipped);
            if(!file.exists()){
                System.out.println("file not found");
            }
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition","attachment; filename=\"project_report.zip\"");

            OutputStream out = response.getOutputStream();
            FileInputStream in = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0){
               out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
            return filepathzipped;
		}
		catch(Exception e){    
			e.printStackTrace();
			return null;
		}
	}
	
}
