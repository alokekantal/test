package com.demo.service.impl;

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

import com.demo.dto.BasicInfoDTO;
import com.demo.repository.DataTransferRepository;
import com.demo.service.DataTransferService;
import com.demo.util.ExcelGenerator;

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
//			List<Object> applicationJob = dataTransferRepository.getApplicationJob( info);
//			
//			Sheet sheet = workbook.createSheet("application_job");
//			ExcelGenerator.gerericExcelCreationMethod(applicationJob, applicationJobFieldList, sheet);
//			
//			
//			List<Object> applicationJobMaterial = dataTransferRepository.getApplicationJobMaterial(info);
//			
//			sheet = workbook.createSheet("organization_job_material");
//			ExcelGenerator.gerericExcelCreationMethod(organizationJobMaterialDetails, organizationJobMaterialFieldList, sheet);
//			
//			/*Ship component detail table export*/
//			List<Object> shipComponentDetail = dataTransferRepository.getShipComponentDetail(info);
//			List<String> shipComponentDetailFieldList = new ArrayList<String>();
//			for(Field f : ShipComponentDetails.class.getDeclaredFields()){	
//				shipComponentDetailFieldList.add(f.getName());
//		    }
//			
//			sheet = workbook.createSheet("shipComponentDetail");
//			ExcelGenerator.gerericExcelCreationMethod(shipComponentDetail, shipComponentDetailFieldList, sheet);
//			
//			workbook.write(out);
//		    out.close();
		}
	}
}
