package com.drydock.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.drydock.dto.BasicInfoDTO;

public class FileFolderUtil {

	
	public static void zipIt(String source,String zipFile,List<String> fileList, String mainFileOpLoc, String mainFileName, BasicInfoDTO info){

	     byte[] buffer = new byte[1024];
	    	
	     try{
	    		
	    	FileOutputStream fos = new FileOutputStream(zipFile);
	    	ZipOutputStream zos = new ZipOutputStream(fos);
	    	
			if(null !=mainFileName){
				ZipEntry ze= new ZipEntry(mainFileName);
	        	zos.putNextEntry(ze);
	               
	        	FileInputStream in = new FileInputStream(source + File.separator + mainFileOpLoc);
	       	   
	        	int len;
	        	while ((len = in.read(buffer)) > 0) {
	        		zos.write(buffer, 0, len);
	        	}
	               
	        	in.close();
			}
   		
			for(String file : fileList){
				File checkExisFile=new File(source + File.separator + file);
				if(checkExisFile.exists()){
					ZipEntry ze= new ZipEntry(file);
					zos.putNextEntry(ze);

					FileInputStream in = new FileInputStream(source + File.separator + file);

					int len;
					while ((len = in.read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
					in.close();
				}
			}
	    		
	    	zos.closeEntry();
	    	zos.close();
	          
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
	   }
	    

}
