package com.demo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
 











import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.demo.dto.BasicInfoDTO;

public class ExcelGenerator {
	public static String getStringValu(String value){
		return value.equals("null") ? "" : value;
	}
	
	public static void gerericExcelCreationMethod(List<Object> tableData, List<String> fieldList, Sheet sheet) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		// Row for Header
	      Row headerRow = sheet.createRow(0);	   
	      // Header
	      int count = 0;
	      for(String field : fieldList){	    	
    		Cell cell = headerRow.createCell(count);
	    	cell.setCellValue(field);
	    	count++; 
	      }
	      
	      
	      int rowCount = 1;
	      for(Object dataRow: tableData){
	    	  Row row = sheet.createRow(rowCount);	    	  
	    	  int dataColumnCount = 0;
		      for(String fieldName : fieldList){
		    	  Class ftClass = dataRow.getClass();
		    	  Field f2 = ftClass.getDeclaredField(fieldName);
		    	  f2.setAccessible(true);
		    	  row.createCell(dataColumnCount).setCellValue(getStringValu(f2.get(dataRow) + ""));
		    	  dataColumnCount++; 
		      }		      
		      rowCount++;
	      }	      
	}
}
