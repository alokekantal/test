package com.drydock.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration2.DatabaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.service.DataTransferService;;

@RestController
@RequestMapping("dataTransfer")
public class DataTransferController {	
	@Autowired
	private DataTransferService dataTransferService;
	
	@Autowired
	private DatabaseConfiguration databaseConfiguration;
	
	@RequestMapping(value="/excelGenerator" , method = RequestMethod.POST)
	public void excelGenerator(HttpServletRequest request,HttpServletResponse response) {
		try{
			BasicInfoDTO info = (BasicInfoDTO) request.getAttribute("info");
			dataTransferService.excelGenerator(info);
	     
		}catch(Exception e){    
			e.printStackTrace();
		}		
	}

}
