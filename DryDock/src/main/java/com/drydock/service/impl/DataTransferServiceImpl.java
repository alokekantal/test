package com.drydock.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration2.DatabaseConfiguration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.entity.ApplicationJob;
import com.drydock.entity.ApplicationJobMaterialDetails;
import com.drydock.entity.Job;
import com.drydock.entity.OrganizationJob;
import com.drydock.entity.OrganizationJobMaterialDetails;
import com.drydock.entity.ShipComponentDetails;
import com.drydock.repository.DataTransferRepository;
import com.drydock.repository.ProjectRepository;
import com.drydock.service.DataTransferService;
import com.drydock.util.ExcelGenerator;

@Component
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class DataTransferServiceImpl implements DataTransferService{
	@Autowired
	DataTransferRepository dataTransferRepository;
	
	@Autowired
	private DatabaseConfiguration databaseConfiguration;
	
	@Override
	public void excelGenerator(BasicInfoDTO info) throws Exception {		
		try(
	    	Workbook workbook = new XSSFWorkbook();
	    	FileOutputStream out = new FileOutputStream(new File("D:\\Development\\exportData.xlsx"));
	    	){
			/*Application job table export*/
			List<Object> applicationJob = dataTransferRepository.getApplicationJob( info);
			List<String> applicationJobFieldList = new ArrayList<String>();
			for(Field f : ApplicationJob.class.getDeclaredFields()){	    	
		    	if(!f.getName().equals("jobMaterialDetailsList")){
		    		applicationJobFieldList.add(f.getName());
		    	}	    	  
		      }
			
			Sheet sheet = workbook.createSheet("application_job");
			ExcelGenerator.gerericExcelCreationMethod(applicationJob, applicationJobFieldList, sheet);
			
			/*Application job material table export*/
			List<Object> applicationJobMaterial = dataTransferRepository.getApplicationJobMaterial(info);
			List<String> applicationJobMaterialFieldList = new ArrayList<String>();
			for(Field f : ApplicationJobMaterialDetails.class.getDeclaredFields()){	
		    	applicationJobMaterialFieldList.add(f.getName());
		    }
			
			sheet = workbook.createSheet("application_job_material");
			ExcelGenerator.gerericExcelCreationMethod(applicationJobMaterial, applicationJobMaterialFieldList, sheet);
			
			
			/*Organization job table export*/
			List<Object> organizationJob = dataTransferRepository.getOrganizationJob(info);
			List<String> organizationJobFieldList = new ArrayList<String>();
			for(Field f : OrganizationJob.class.getDeclaredFields()){	
				if(!f.getName().equals("jobMaterialDetailsList")){
					organizationJobFieldList.add(f.getName());
				}
		    }
			
			sheet = workbook.createSheet("organization_job");
			ExcelGenerator.gerericExcelCreationMethod(organizationJob, organizationJobFieldList, sheet);
			
			/*Organization job material table export*/
			List<Object> organizationJobMaterialDetails = dataTransferRepository.getOrganizationJobMaterial(info);
			List<String> organizationJobMaterialFieldList = new ArrayList<String>();
			for(Field f : OrganizationJobMaterialDetails.class.getDeclaredFields()){	
				organizationJobMaterialFieldList.add(f.getName());
		    }
			
			sheet = workbook.createSheet("organization_job_material");
			ExcelGenerator.gerericExcelCreationMethod(organizationJobMaterialDetails, organizationJobMaterialFieldList, sheet);
			
			/*Ship component detail table export*/
			List<Object> shipComponentDetail = dataTransferRepository.getShipComponentDetail(info);
			List<String> shipComponentDetailFieldList = new ArrayList<String>();
			for(Field f : ShipComponentDetails.class.getDeclaredFields()){	
				shipComponentDetailFieldList.add(f.getName());
		    }
			
			sheet = workbook.createSheet("shipComponentDetail");
			ExcelGenerator.gerericExcelCreationMethod(shipComponentDetail, shipComponentDetailFieldList, sheet);
			
			workbook.write(out);
		    out.close();
		}
		
		
		//List<OrganizationJob> organizationJob = dataTransferRepository.getOrganizationJob(info);
		//ExcelGenerator.applicationJobToExcel(organizationJob);
		
		//List<OrganizationJobMaterialDetails> organizationJobMaterialDetails = dataTransferRepository.getOrganizationJobMaterial(info);
		//ExcelGenerator.applicationJobMaterialToExcel(applicationJobMaterial);
	}
}
