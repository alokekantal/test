package com.drydock.service.impl;

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

import org.apache.commons.configuration2.DatabaseConfiguration;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.entity.ApplicationComponent;
import com.drydock.entity.ApplicationJob;
import com.drydock.entity.ApplicationJobMaterialDetails;
import com.drydock.entity.CurrencyMaster;
import com.drydock.entity.DockyardMaster;
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
import com.drydock.entity.ReportDTO;
import com.drydock.entity.ShipComponentDetails;
import com.drydock.entity.Shipmaster;
import com.drydock.entity.VesselTypeMaster;
import com.drydock.repository.CoreRepository;
import com.drydock.repository.ProjectRepository;
import com.drydock.service.ProjectService;
import com.drydock.util.DrydockConstant;
import com.drydock.util.FileFolderUtil;
import com.drydock.util.HeaderFooterPageEvent;
import com.drydock.util.ItextPdfUtil;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

@Component
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	CoreRepository coreRepository;
	
    @Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	private DatabaseConfiguration databaseConfiguration;
	
	@Override
	public List<ApplicationComponent> loadApplicationComponentList(BasicInfoDTO info) throws Exception{
	    return projectRepository.loadApplicationComponentList(info);
	}
	
	@Override
	public void saveApplicationComponent(ApplicationComponent applicationComponent, BasicInfoDTO info) throws Exception{
	    projectRepository.saveApplicationComponent(applicationComponent, info);
	}
	
	@Override
	public void deleteApplicationComponent(long applicationcomponentid, BasicInfoDTO info) throws Exception{
	    projectRepository.deleteApplicationComponent(applicationcomponentid, info);
	}
	
	@Override
	public List<OrganizationComponent> loadOrganizationComponentList(BasicInfoDTO info) throws Exception{
	    return projectRepository.loadOrganizationComponentList(info);
	}
	
	@Override
	public void saveOrganizationComponent(OrganizationComponent organizationComponent, BasicInfoDTO info) throws Exception{
	    projectRepository.saveOrganizationComponent(organizationComponent, info);
	}
	
	@Override
	public void deleteOrganizationComponent(long organizationcomponentid, BasicInfoDTO info) throws Exception{
	    projectRepository.deleteOrganizationComponent(organizationcomponentid, info);
	}

	@Override
	public Object loadShipComponentDetails(Long shipId,
			Long organizationcomponentid, BasicInfoDTO info) throws Exception {
		return projectRepository.loadShipComponentDetails(shipId, organizationcomponentid, info);
	}

	@Override
	public void saveShipComponentDetails(
			ShipComponentDetails shipComponentDetails, BasicInfoDTO info)
			throws Exception {
		projectRepository.saveShipComponentDetails(shipComponentDetails, info);
		
	}

	@Override
	public List<Job> loadApprovedJobList(long shipId, BasicInfoDTO info) throws Exception {
		return projectRepository.loadJobList(shipId, true, info);
	}

	@Override
	public List<Job> loadAllJobList(long shipId, BasicInfoDTO info) throws Exception {
		return projectRepository.loadJobList(shipId, false, info);
	}

	@Override
	public List<Project> loadProjectList(Long shipId, Boolean onlyActiveFlag, BasicInfoDTO info)
			throws Exception {
		return projectRepository.loadProjectList(shipId, onlyActiveFlag, info);
	}

	@Override
	public Object loadJobDetails(Long jobId, BasicInfoDTO info)
			throws Exception {
		return projectRepository.loadJobDetails(jobId,  info);
	}

	@Override
	public Object loadProjectDetails(Long projectId, BasicInfoDTO info)
			throws Exception {
		return projectRepository.loadProjectDetails(projectId, info);
	}

	@Override
	public void saveProject(Project project, BasicInfoDTO info)
			throws Exception {
		projectRepository.saveProject(project, false, info);
		if(null!=project.getProjectAttachmentList()){
			projectRepository.saveProjectAttachmentList(project, info);
		}
		
		if(null!=project.getProjectDockyardList()){
			projectRepository.saveProjectDockyardList(project, info);
		}
		
		if(null!=project.getProjectCurrencyConversionList()){
			projectRepository.saveProjectCurrencyConversionList(project, info);
		}
		
		if(null!=project.getProjectJobList()){
			projectRepository.saveProjectJobList(project, info);
			project.setProjectJobList(projectRepository.loadJobList(project.getShipid(), false, info));
		}
		
		if(null!=project.getLineitemList()){
			projectRepository.saveProjectCostTabData(project, info);
		}
		
	}

	@Override
	public void saveJob(Job job, BasicInfoDTO info) throws Exception {
		projectRepository.saveJob(job, false, info);
		
	}

	@Override
	public Object loadShipComponentList(Long shipId, BasicInfoDTO info)
			throws Exception {
		return projectRepository.loadShipComponentList(shipId, info);
		}

	@Override
	public void saveJobWithAttachment(Job job, BasicInfoDTO info)
			throws Exception {
		projectRepository.saveJob(job, true, info);
		
	}

	@Override
	public void markJobComplete(Long jobId, String jobCloserRemark, BasicInfoDTO info) throws Exception {
		projectRepository.markJobComplete(jobId, jobCloserRemark, info);
	}

	@Override
	public void markJobCancel(Long jobId, BasicInfoDTO info) throws Exception {
		projectRepository.markJobCancel(jobId, info);
	}

	@Override
	public void markProjectComplete(Long projectId, String closerAttachmentRelativePath, String closerAttachmentName, String closerComment, BasicInfoDTO info) throws Exception {
		List<Job> jobList = projectRepository.loadActiveJobListForProject(projectId, info);
		if(null!=jobList && !jobList.isEmpty()){
			//throw new ValidationException("Cannot close project. All jobs are not yet closed.");
			for (Job job : jobList) {
				JobComment jobComment = new JobComment();
				jobComment.setJobComment("Project closed with job open.");
				projectRepository.saveJobComment(job, jobComment, info);
			}
		}
		projectRepository.markProjectComplete(projectId,closerAttachmentRelativePath, closerAttachmentName, closerComment, info);
	}

	@Override
	public List<ApplicationJob> loadApplicationJobList(BasicInfoDTO info) throws Exception {
		return projectRepository.loadApplicationJobList(info);
	}

	@Override
	public Object loadApplicationJobDetails(Long applicationJobId, BasicInfoDTO info)
			throws Exception {
		return projectRepository.loadApplicationJobDetails(applicationJobId,  info);
	}

	@Override
	public void saveApplicationJob(ApplicationJob applicationJob, BasicInfoDTO info) throws Exception {
		boolean freshJob = projectRepository.saveApplicationJob(applicationJob, info);
		
		if(freshJob){
			List<Organization> orgList=coreRepository.loadOrganizationList(info);

			for (Organization organization : orgList) {
				
				Map<Long, Long> hmpAppCompOrgCompId = new HashMap<>();
				List<OrganizationComponent> orgComponentList = projectRepository.loadOrganizationComponentList(info);
				for (OrganizationComponent orgComponent : orgComponentList) {
					hmpAppCompOrgCompId.put(orgComponent.getApplicationcomponentid(), orgComponent.getId());
				}
				
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


				projectRepository.saveOrganizationJob(orgJob, info);

				List<OrganizationJobMaterialDetails> orgJobMaterals = new ArrayList<>();
				for (ApplicationJobMaterialDetails applicationJobMaterialDetails : applicationJob.getJobMaterialDetailsList()) {
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
				projectRepository.saveOrganizationJobMaterialDetailsList(orgJob.getId(), orgJobMaterals, info);
			}
		}
		
	}

	@Override
	public List<Job> loadMyJobList(long shipId, BasicInfoDTO info) throws Exception {
		return projectRepository.loadMyJobList(shipId, info);
	}

	@Override
	public Object loadCheckboxesList(BasicInfoDTO info) throws Exception {
		return projectRepository.loadCheckboxesList(info);
	}

	@Override
	public List<OrganizationJob> loadOrganizationJobList(BasicInfoDTO info)throws Exception {
		return projectRepository.loadOrganizationJobList( info);
	}
	
	@Override
	public Object loadOrganizationJobDetail(int jobId, BasicInfoDTO info)throws Exception {
		return projectRepository.loadOrganizationJobDetail(jobId, info);
	}
	
	@Override
	public void saveOrganizationJob(OrganizationJob organizationJob, BasicInfoDTO info)throws Exception {
		projectRepository.saveOrganizationJob(organizationJob, info);
	}

	@Override
	public List<Project> loadCompletedProjectListByShipId(int shipId,
			BasicInfoDTO info) throws Exception {
		return projectRepository.loadCompletedProjectListByShipId(shipId, info);
	}

	@Override
	public Project loadActiveProjectDetail(int shipId, BasicInfoDTO info)
			throws Exception {
		return projectRepository.loadActiveProjectDetail(shipId, info);
	}

	@Override
	public List<JobMaterialDetails> loadJobMaterialDetailsList(long jobId,
			BasicInfoDTO info) throws Exception {
		return projectRepository.loadJobMaterialDetailsList(jobId, info);
	}

	@Override
	public void saveProjectWithAttachment(Project project, BasicInfoDTO info)
			throws Exception {
		projectRepository.saveProject(project, true, info);
		
	}

	@Override
	public ByteArrayInputStream generateProjectReport(long projectId, BasicInfoDTO info)
			throws Exception {
		try{
			return projectRepository.generateProjectReport(projectId, info);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		
	}

	@Override
	public Object copyJob(long orgId, long projectId, Long shipId, Long jobId, String jobType, List<String> attachmentTypes, BasicInfoDTO info) throws Exception {
		Job newJob = new Job();
		if("previous".equalsIgnoreCase(jobType)){
			Job oldJob = projectRepository.loadJobDetails(jobId, info);
			
			newJob.setAccountno(oldJob.getAccountno());
			newJob.setProjectid(projectId);
			newJob.setCheckboxes(oldJob.getCheckboxes());
			newJob.setDescription(oldJob.getDescription());
			newJob.setDetailedSpecification(oldJob.getDetailedSpecification());
			newJob.setEquipment(oldJob.getEquipment());
			newJob.setLocation(oldJob.getLocation());
			newJob.setMake(oldJob.getMake());
			newJob.setMakeModelDescription(oldJob.getMakeModelDescription());
			newJob.setModel(oldJob.getModel());
			newJob.setOrgid(orgId);
			newJob.setShipcomponentid(oldJob.getShipcomponentid());
			newJob.setShipid(shipId);
			newJob.setSpecification(oldJob.getSpecification());
			newJob.setTotalArea(oldJob.getTotalArea());
			
			projectRepository.saveJob(newJob, false, info);
			
			if(null!=oldJob.getJobMaterialDetailsList() && !oldJob.getJobMaterialDetailsList().isEmpty()){
				List<JobMaterialDetails> jobMaterals = new ArrayList<>();
				for (JobMaterialDetails jobMaterialDetails : oldJob.getJobMaterialDetailsList()) {
					JobMaterialDetails jobMaterial = new JobMaterialDetails();
					jobMaterial.setMake(jobMaterialDetails.getMake());
					jobMaterial.setModel(jobMaterialDetails.getModel());
					jobMaterial.setDrawingNo(jobMaterialDetails.getDrawingNo());
					jobMaterial.setPartName(jobMaterialDetails.getPartName());
					jobMaterial.setPartNo(jobMaterialDetails.getPartNo());
					jobMaterial.setQuantity(jobMaterialDetails.getQuantity());
					jobMaterial.setRemarks(jobMaterialDetails.getRemarks());
					jobMaterial.setUom(jobMaterialDetails.getUom());

					jobMaterals.add(jobMaterial);
				}
				newJob.setJobMaterialDetailsList(jobMaterals);
			}
			
			if(null!=oldJob.getJobAttachmentList() && !oldJob.getJobAttachmentList().isEmpty() && 
					null!=attachmentTypes && !attachmentTypes.isEmpty()){
				InputStream is = null;
			    OutputStream os = null;
				
				List<JobAttachment> jobAttachmentList = new ArrayList<>();
				for (JobAttachment oldJobAttachment : oldJob.getJobAttachmentList()) {
					if(attachmentTypes.contains(oldJobAttachment.getAttachmentType())){
						JobAttachment newJobAttachment = new JobAttachment();
						
						newJobAttachment.setAttachmentDescription(oldJobAttachment.getAttachmentDescription());
						newJobAttachment.setAttachmentName(oldJobAttachment.getAttachmentName());
						newJobAttachment.setAttachmentType(oldJobAttachment.getAttachmentType());
						newJobAttachment.setDescription(oldJobAttachment.getDescription());
						newJobAttachment.setJobid(newJob.getId());
						newJobAttachment.setOrgid(orgId);
						newJobAttachment.setProgressreportid(oldJobAttachment.getProgressreportid());
						newJobAttachment.setProjectid(projectId);
						newJobAttachment.setShipid(shipId);

						
						String docBaseJob = databaseConfiguration.getString(DrydockConstant.DOC_BASE);
						
						String fileName = docBaseJob+orgId+"/"+shipId+"/"+projectId+"/"+jobId+"/"+oldJobAttachment.getRelativepath();
						File file = new File(fileName);
						is = new FileInputStream(file);
						
						String fullPath = docBaseJob+orgId+"/"+shipId+"/"+projectId+"/"+newJob.getId()+"/";
						File dir = new File(fullPath);
						if(!dir.exists())
							dir.mkdirs();
						int numberOfSubfolders = dir.listFiles()==null?1:(dir.listFiles().length+1);
						System.out.println(numberOfSubfolders);
						File finalDir = new File(fullPath+"/"+numberOfSubfolders);

						finalDir.mkdirs();

						String relativePath = numberOfSubfolders+"/" + oldJobAttachment.getAttachmentName();
						
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
						
						newJobAttachment.setRelativepath(relativePath);
						jobAttachmentList.add(newJobAttachment);
					}
					
				}
				newJob.setJobAttachmentList(jobAttachmentList);
			}
		}else{
			OrganizationJob orgJob = (OrganizationJob) projectRepository.loadOrganizationJobDetail(jobId, info);
			
			newJob.setAccountno(orgJob.getAccountno());
			newJob.setProjectid(projectId);
			newJob.setCheckboxes(orgJob.getCheckboxes());
			newJob.setDescription(orgJob.getDescription());
			newJob.setDetailedSpecification(orgJob.getDetailedSpecification());
			newJob.setEquipment(orgJob.getEquipment());
			newJob.setLocation(orgJob.getLocation());
			newJob.setMake(orgJob.getMake());
			newJob.setMakeModelDescription(orgJob.getMakeModelDescription());
			newJob.setModel(orgJob.getModel());
			newJob.setOrgid(orgId);
			newJob.setShipcomponentid(orgJob.getShipcomponentid());
			newJob.setShipid(shipId);
			newJob.setSpecification(orgJob.getSpecification());
			newJob.setTotalArea(orgJob.getTotalArea());
			
			if(null!=orgJob.getJobMaterialDetailsList() && !orgJob.getJobMaterialDetailsList().isEmpty()){
				List<JobMaterialDetails> jobMaterals = new ArrayList<>();
				for (OrganizationJobMaterialDetails organizationJobMaterialDetails : orgJob.getJobMaterialDetailsList()) {
					JobMaterialDetails jobMaterial = new JobMaterialDetails();
					jobMaterial.setMake(organizationJobMaterialDetails.getMake());
					jobMaterial.setModel(organizationJobMaterialDetails.getModel());
					jobMaterial.setDrawingNo(organizationJobMaterialDetails.getDrawingNo());
					jobMaterial.setPartName(organizationJobMaterialDetails.getPartName());
					jobMaterial.setPartNo(organizationJobMaterialDetails.getPartNo());
					jobMaterial.setQuantity(organizationJobMaterialDetails.getQuantity());
					jobMaterial.setRemarks(organizationJobMaterialDetails.getRemarks());
					jobMaterial.setUom(organizationJobMaterialDetails.getUom());

					jobMaterals.add(jobMaterial);
				}
				newJob.setJobMaterialDetailsList(jobMaterals);
			}
		}
		projectRepository.saveJob(newJob, true, info);
		return newJob;
	}

	@Override
	public Object loadProjectDetailForReport(long projectId, boolean withImageData, BasicInfoDTO info) throws Exception {
		Project project = (Project) projectRepository.loadProjectDetailsForReport(projectId, info);
		project.setProjectAttachmentList(projectRepository.loadProjectAttachmentList(project, info));
		project.setProjectDockyardList(projectRepository.loadProjectDockyardList(project, info));
		project.setProjectCurrencyConversionList(projectRepository.loadProjectCurrencyConversionList(project, info));
		project.setLineitemList(projectRepository.loadProjectJobCostLineitemList(project, info));
		List<Job> jobList = projectRepository.loadJobList(project.getShipid(), true, info);
		List<Job> jobListNew = new ArrayList<>();
		for (Job job : jobList) {
			Job jobNew = projectRepository.loadJobDetails(job.getId(), info);
			if(withImageData){
				String docBase = databaseConfiguration.getString(DrydockConstant.DOC_BASE);
				List<JobAttachment> jobAttachmentList = jobNew.getJobAttachmentList();
				if(null != jobAttachmentList){
					for (JobAttachment jobAttachment : jobAttachmentList) {
						if(jobAttachment.getAttachmentName().toLowerCase().endsWith("jpeg") ||
								jobAttachment.getAttachmentName().toLowerCase().endsWith("jpg") ||
								jobAttachment.getAttachmentName().toLowerCase().endsWith("png")){
							String fileName = docBase+jobAttachment.getOrgid()+"/"+jobAttachment.getShipid()+"/"+jobAttachment.getProjectid()+"/"+jobAttachment.getJobid()+"/"+jobAttachment.getRelativepath();
							File file = new File(fileName);
							jobAttachment.setFileContent(IOUtils.toByteArray(new FileInputStream(file)));				
						}
					}
				}
			}
			
			jobListNew.add(jobNew);
		}
		
		project.setProjectJobList(jobListNew);
		
		
		return project;
	}

	@Override
	public Object generateProjectReportIText(long projectId, BasicInfoDTO info, ReportDTO reportFields) throws Exception{

		String basePath=databaseConfiguration.getString(DrydockConstant.DOC_BASE);
		long timestamp=System.currentTimeMillis();
		String fullPath = basePath+projectId+"/"+info.getUserId()+"/"+timestamp+"/";
		File finalDir = new File(fullPath);
		if(!finalDir.exists())
			finalDir.mkdirs();

		String zipFilePath=fullPath+"final_report.zip";
		Project project = (Project) projectRepository.loadProjectDetailsForReport(projectId, info);
		project.setProjectAttachmentList(projectRepository.loadProjectAttachmentList(project, info));
		project.setProjectDockyardList(projectRepository.loadProjectDockyardList(project, info));
		project.setProjectCurrencyConversionList(projectRepository.loadProjectCurrencyConversionList(project, info));
		project.setLineitemList(projectRepository.loadProjectJobCostLineitemList(project, info));
		project.setShipDetails((Shipmaster) coreRepository.loadShipDetailsById(project.getShipid(), info));		
		List<Job> jobList = projectRepository.loadJobList(project.getShipid(), true, info);
		List<Job> jobListNew = new ArrayList<>();
		for (Job job : jobList) {
			Job jobNew = projectRepository.loadJobDetails(job.getId(), info);
			OrganizationComponent orgCom = (OrganizationComponent) projectRepository.loadOrganizationComponentDetails( job.getShipcomponentid(), info);
			jobNew.setOrgComponent(orgCom);
			jobListNew.add(jobNew);			
		}		
		project.setProjectJobList(jobListNew); 
		
		VesselTypeMaster vesselType = (VesselTypeMaster) coreRepository.getMasterVesselTypeById(project.getShipDetails().getV_type());
		project.getShipDetails().setVesselTypeDesc(vesselType.getDescription());
		
		List<CurrencyMaster> currencyMaster = (List<CurrencyMaster>) coreRepository.loadCurrencyList(info);
		List<DockyardMaster> dockyardMaster = (List<DockyardMaster>) coreRepository.loadDockyardList(info);
		
		CurrencyMaster projectCurrency = currencyMaster.stream().filter(currency -> currency.getId() == project.getCurrencyMasterId()).findAny().orElse(null);
		if(projectCurrency != null){
			project.setCurrencyMasterDesc(projectCurrency.getCurrencyDescription());
		}
		
		for(ProjectDockyard projectDockyard : project.getProjectDockyardList()){
			CurrencyMaster currentCurrency = currencyMaster.stream().filter(currency -> currency.getId() == projectDockyard.getDefaultCurrencyId()).findAny().orElse(null);
			DockyardMaster currentDockyard = dockyardMaster.stream().filter(dockyard -> dockyard.getId() == projectDockyard.getDockyardId()).findAny().orElse(null);
			if(currentCurrency != null){
				projectDockyard.setDefaultCurrencyDesc(currentCurrency.getCurrencyDescription());
			}	
			if(currentDockyard != null){
				projectDockyard.setDockyardDesc(currentDockyard.getDockyard());
			}
		}
		if(project.getProjectCurrencyConversionList() != null){
			for(ProjectCurrencyConversion projectCurrencyConversion: project.getProjectCurrencyConversionList()){
				CurrencyMaster toCurrency = currencyMaster.stream().filter(currency -> currency.getId() == projectCurrencyConversion.getTocurrencyid()).findAny().orElse(null);
				CurrencyMaster fromCurrency = currencyMaster.stream().filter(currency -> currency.getId() == projectCurrencyConversion.getFromcurrencyid()).findAny().orElse(null);
				if(toCurrency != null){
					projectCurrencyConversion.setTocurrencyDesc(toCurrency.getCurrencyDescription());
				}	
				if(fromCurrency != null){
					projectCurrencyConversion.setFromcurrencyDesc(fromCurrency.getCurrencyDescription());
				}
			}
		}
		
		
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4, 72, 72, 72, 72);
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		HeaderFooterPageEvent event = new HeaderFooterPageEvent(project.getShipDetails().getVesselname(), project.getDescription());
		writer.setPageEvent(event);
		document.open();

		List<JobCreationCheckboxes> checkBoxList = (List<JobCreationCheckboxes>) projectRepository.loadCheckboxesList(info);
		ItextPdfUtil.createProjectReport(document, project, info, basePath, writer, checkBoxList, reportFields);

		document.close();
		writer.close();
		
		
		PdfReader reader = new PdfReader(baos.toByteArray());
		int n = reader.getNumberOfPages();
		 reader.selectPages(String.format("1, %d, 2-%d", n, n-1));
		 PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(basePath+"report.pdf"));
		 stamper.close();
		
		 List<String> fileList=new ArrayList<>();
		 if(null!=project.getProjectAttachmentList() && !project.getProjectAttachmentList().isEmpty()){
		 //attachment copy path prepare for project
		 for(ProjectAttachment projectAttachment: project.getProjectAttachmentList()){
			 fileList.add(info.getOrgId()+"/"+project.getShipid()+"/"+"project_attachment/"+projectId+"/"+projectAttachment.getRelativepath());
		 }
		 }
		//attachment copy path prepare for job
		 if(null!=project.getProjectJobList() && !project.getProjectJobList().isEmpty()){
			 for(Job job: project.getProjectJobList()){
				 if(null!=job.getJobAttachmentList() && !job.getJobAttachmentList().isEmpty()){
					 for (JobAttachment jobAttachment : job.getJobAttachmentList()) {
						if(jobAttachment.getAttachmentType().equals("SPEC")){
							fileList.add(info.getOrgId()+"/"+project.getShipid()+"/"+projectId+"/"+job.getId()+"/"+jobAttachment.getRelativepath());
						}					
					}
				}
				 if(null!=job.getJobProgressReportList() && !job.getJobProgressReportList().isEmpty()){
					//attachment copy path prepare for job progress report
					for(JobProgressReport jobProgressReport : job.getJobProgressReportList() ){
						if(null!=jobProgressReport.getJobAttachmentList() && !jobProgressReport.getJobAttachmentList().isEmpty()){
							for (JobAttachment jobProgressReportAttachment : jobProgressReport.getJobAttachmentList()) {
								fileList.add(info.getOrgId()+"/"+project.getShipid()+"/"+projectId+"/"+job.getId()+"/"+jobProgressReportAttachment.getRelativepath());
							}
						}
					}
				 }
			 }
		 }
		 
		 FileFolderUtil.zipIt(basePath, zipFilePath, fileList, "report.pdf", "project_report.pdf", info);
		 
		return zipFilePath;
	}


}
