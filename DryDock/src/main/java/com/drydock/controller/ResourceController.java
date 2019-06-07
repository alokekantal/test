package com.drydock.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration2.DatabaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.util.DrydockConstant;

@RestController
@RequestMapping("resource")
public class ResourceController {
	@Autowired
	ServletContext servletContext;

	@Autowired
	private DatabaseConfiguration databaseConfiguration;

	@RequestMapping(value="/getApplicationType" , method = RequestMethod.GET)
	public Object getApplicationType(HttpServletRequest request,HttpServletResponse response) {
		return databaseConfiguration.getString(DrydockConstant.APPLICATION_TYPE_KEY); 
		
	}
	@RequestMapping(value="/loadLanguageJSON/{fileName}" , method = RequestMethod.GET)
	public Object loadLanguageJSON(HttpServletRequest request,HttpServletResponse response, @PathVariable String fileName) {
//		String fileName="de.json";
		try{
			if(!fileName.endsWith(".json")){
				fileName=fileName+".json";
			}
			ClassLoader classLoader = getClass().getClassLoader();
			File file  = new File(classLoader.getResource("i18n/"+fileName).getFile());
			MediaType mediaType = null;
			String mineType = servletContext.getMimeType(file.getAbsolutePath());
			try {
				mediaType = MediaType.parseMediaType(mineType);
			} catch (Exception e) {
				mediaType = MediaType.APPLICATION_OCTET_STREAM;
			}
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
					// Content-Type
					.contentType(mediaType)
					// Contet-Length
					.contentLength(file.length()) //
					.body(resource);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	@RequestMapping(value="/loadUserExcel" , method = RequestMethod.GET)
	public Object loadUserExcel(HttpServletRequest request,HttpServletResponse response) {
		try{
			ClassLoader classLoader = getClass().getClassLoader();
			File file  = new File(classLoader.getResource("format/User_Upload.xlsx").getFile());
			MediaType mediaType = null;
			String mineType = servletContext.getMimeType(file.getAbsolutePath());
			try {
				mediaType = MediaType.parseMediaType(mineType);
			} catch (Exception e) {
				mediaType = MediaType.APPLICATION_OCTET_STREAM;
			}
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
					// Content-Type
					.contentType(mediaType)
					// Contet-Length
					.contentLength(file.length()) //
					.body(resource);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value="/loadShipExcel" , method = RequestMethod.GET)
	public Object loadShipExcel(HttpServletRequest request,HttpServletResponse response) {
		try{
			ClassLoader classLoader = getClass().getClassLoader();
			File file  = new File(classLoader.getResource("format/Ship_Upload.xlsx").getFile());
			MediaType mediaType = null;
			String mineType = servletContext.getMimeType(file.getAbsolutePath());
			try {
				mediaType = MediaType.parseMediaType(mineType);
			} catch (Exception e) {
				mediaType = MediaType.APPLICATION_OCTET_STREAM;
			}
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
					// Content-Type
					.contentType(mediaType)
					// Contet-Length
					.contentLength(file.length()) //
					.body(resource);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	@RequestMapping(value="/loadDepartmentExcel" , method = RequestMethod.GET)
	public Object loadDepartmentExcel(HttpServletRequest request,HttpServletResponse response) {
		try{
			ClassLoader classLoader = getClass().getClassLoader();
			File file  = new File(classLoader.getResource("format/Department_Upload.xlsx").getFile());
			MediaType mediaType = null;
			String mineType = servletContext.getMimeType(file.getAbsolutePath());
			try {
				mediaType = MediaType.parseMediaType(mineType);
			} catch (Exception e) {
				mediaType = MediaType.APPLICATION_OCTET_STREAM;
			}
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
					// Content-Type
					.contentType(mediaType)
					// Contet-Length
					.contentLength(file.length()) //
					.body(resource);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping("uploadDocument")	
	public Object handleFileUpload(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("file") MultipartFile file, 
			@RequestParam("orgId") String orgId, 
			@RequestParam("shipId") String shipId, 
			@RequestParam("projectId") String projectId, 
			@RequestParam("jobId") String jobId) {
		System.out.println("inside uploadDocument");
		BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
		String relativePath=null;
		String docBase = databaseConfiguration.getString(DrydockConstant.DOC_BASE);
		String fullPath = docBase+orgId+"/"+shipId+"/"+projectId+"/"+jobId+"/";
		File dir = new File(fullPath);
		if(!dir.exists())
			dir.mkdirs();
		int numberOfSubfolders = dir.listFiles()==null?1:(dir.listFiles().length+1);
		System.out.println(numberOfSubfolders);
		File finalDir = new File(fullPath+"/"+numberOfSubfolders);

		finalDir.mkdirs();

		try {
			// read and write the file to the selected location-
			byte[] bytes = file.getBytes();
			Path path = Paths.get(finalDir.getAbsolutePath()+"/" + file.getOriginalFilename());
			Files.write(path, bytes);
			relativePath = numberOfSubfolders+"/" + file.getOriginalFilename();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> finalPath = new ArrayList<>();
		finalPath.add(relativePath);
		return finalPath;
	}

	@RequestMapping("downloadDocument")	
	public Object handleFileDownload(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("orgId") String orgId, 
			@RequestParam("shipId") String shipId, 
			@RequestParam("projectId") String projectId, 
			@RequestParam("jobId") String jobId, 
			@RequestParam("path") String filePath) {
		System.out.println("inside downloadDocument");
		String docBase = databaseConfiguration.getString(DrydockConstant.DOC_BASE);
		String fileName = docBase+orgId+"/"+shipId+"/"+projectId+"/"+jobId+"/"+filePath;
		MediaType mediaType = null;
		String mineType = servletContext.getMimeType(fileName);
		try {
			mediaType = MediaType.parseMediaType(mineType);
		} catch (Exception e) {
			mediaType = MediaType.APPLICATION_OCTET_STREAM;
		}
		try{
			File file = new File(fileName);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
					// Content-Type
					.contentType(mediaType)
					// Contet-Length
					.contentLength(file.length()) //
					.body(resource);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("uploadShipDocument")	
	public Object handleFileUploadForShip(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("file") MultipartFile file, 
			@RequestParam("orgId") String orgId, 
			@RequestParam("shipId") String shipId) {
		System.out.println("inside uploadShipDocument");
		BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
		String relativePath=null;
		String docBase = databaseConfiguration.getString(DrydockConstant.DOC_BASE);
		String fullPath = docBase+orgId+"/ship_image/"+shipId+"/";
		File dir = new File(fullPath);
		if(!dir.exists())
			dir.mkdirs();
		int numberOfSubfolders = dir.listFiles()==null?1:(dir.listFiles().length+1);
		System.out.println(numberOfSubfolders);
		File finalDir = new File(fullPath+"/"+numberOfSubfolders);

		finalDir.mkdirs();

		try {
			// read and write the file to the selected location-
			byte[] bytes = file.getBytes();
			Path path = Paths.get(finalDir.getAbsolutePath()+"/" + file.getOriginalFilename());
			Files.write(path, bytes);
			relativePath = numberOfSubfolders+"/" + file.getOriginalFilename();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> finalPath = new ArrayList<>();
		finalPath.add(relativePath);
		return finalPath;
	}

	@RequestMapping("downloadShipDocument")	
	public Object handleFileDownloadForShip(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("orgId") String orgId, 
			@RequestParam("shipId") String shipId,  
			@RequestParam("path") String filePath) {
		System.out.println("inside downloadShipDocument");
		String docBase = databaseConfiguration.getString(DrydockConstant.DOC_BASE);
		String fileName = docBase+orgId+"/ship_image/"+shipId+"/"+filePath;
		MediaType mediaType = null;
		String mineType = servletContext.getMimeType(fileName);
		try {
			mediaType = MediaType.parseMediaType(mineType);
		} catch (Exception e) {
			mediaType = MediaType.APPLICATION_OCTET_STREAM;
		}
		try{
			File file = new File(fileName);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
					// Content-Type
					.contentType(mediaType)
					// Contet-Length
					.contentLength(file.length()) //
					.body(resource);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	@RequestMapping("uploadProjectDocument")	
	public Object handleProjectFileUpload(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("file") MultipartFile file, 
			@RequestParam("orgId") String orgId, 
			@RequestParam("shipId") String shipId, 
			@RequestParam("projectId") String projectId) {
		System.out.println("inside uploadProjectDocument");
		BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
		String relativePath=null;
		String docBase = databaseConfiguration.getString(DrydockConstant.DOC_BASE);
		String fullPath = docBase+orgId+"/"+shipId+"/"+"project_attachment/"+projectId+"/";
		File dir = new File(fullPath);
		if(!dir.exists())
			dir.mkdirs();
		int numberOfSubfolders = dir.listFiles()==null?1:(dir.listFiles().length+1);
		System.out.println(numberOfSubfolders);
		File finalDir = new File(fullPath+"/"+numberOfSubfolders);

		finalDir.mkdirs();

		try {
			// read and write the file to the selected location-
			byte[] bytes = file.getBytes();
			Path path = Paths.get(finalDir.getAbsolutePath()+"/" + file.getOriginalFilename());
			Files.write(path, bytes);
			relativePath = numberOfSubfolders+"/" + file.getOriginalFilename();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> finalPath = new ArrayList<>();
		finalPath.add(relativePath);
		return finalPath;
	}

	@RequestMapping("downloadProjectDocument")	
	public Object handleProjectFileDownload(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("orgId") String orgId, 
			@RequestParam("shipId") String shipId, 
			@RequestParam("projectId") String projectId, 
			@RequestParam("path") String filePath) {
		System.out.println("inside downloadProjectDocument");
		String docBase = databaseConfiguration.getString(DrydockConstant.DOC_BASE);
		String fileName = docBase+orgId+"/"+shipId+"/"+"project_attachment/"+projectId+"/"+filePath;
		MediaType mediaType = null;
		String mineType = servletContext.getMimeType(fileName);
		try {
			mediaType = MediaType.parseMediaType(mineType);
		} catch (Exception e) {
			mediaType = MediaType.APPLICATION_OCTET_STREAM;
		}
		try{
			File file = new File(fileName);
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
					// Content-Type
					.contentType(mediaType)
					// Contet-Length
					.contentLength(file.length()) //
					.body(resource);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	

	
}