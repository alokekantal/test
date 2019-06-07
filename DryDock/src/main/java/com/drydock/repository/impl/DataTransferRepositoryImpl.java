package com.drydock.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.entity.ApplicationJob;
import com.drydock.entity.ApplicationJobMaterialDetails;
import com.drydock.entity.OrganizationJob;
import com.drydock.entity.OrganizationJobMaterialDetails;
import com.drydock.mapper.ApplicationJobMapper;
import com.drydock.mapper.ApplicationJobMaterialDetailsMapper;
import com.drydock.mapper.OrganizationJobMapper;
import com.drydock.mapper.OrganizationJobMaterialDetailsMapper;
import com.drydock.mapper.ShipComponentDetailsMapper;
import com.drydock.repository.CoreRepository;
import com.drydock.repository.DataTransferRepository;
import com.drydock.repository.ProjectRepository;
import com.drydock.repository.SequenceValueGenerator;
import com.drydock.util.DrydockConstant;
import com.drydock.util.DrydockQueryConstant;
import com.drydock.util.PDFGenerator;

@Component
public class DataTransferRepositoryImpl implements DataTransferRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public List<Object> getApplicationJob(BasicInfoDTO info) throws Exception {
		List<Object> applicationJob = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			applicationJob =(List<Object>) namedParameterJdbcTemplate.query(DrydockQueryConstant.DATA_TRANSFERAPPLICATION_JOB,parameters, new ApplicationJobMapper());
		}
		catch(Exception e){
			throw e;
		}
	    return applicationJob;
	}
	
	@Override
	public List<Object> getApplicationJobMaterial(BasicInfoDTO info) throws Exception {
		List<Object> applicationJobMaterial = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			applicationJobMaterial =(List<Object>) namedParameterJdbcTemplate.query(DrydockQueryConstant.DATA_TRANSFER_APPLICATION_JOB_MATERIAL_DETAILS_LIST,parameters, new ApplicationJobMaterialDetailsMapper());
		}
		catch(Exception e){
			throw e;
		}
	    return applicationJobMaterial;
	}
	
	@Override
	public List<Object> getOrganizationJob(BasicInfoDTO info) throws Exception {
		List<Object> organizationJob = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			organizationJob =(List<Object>) namedParameterJdbcTemplate.query(DrydockQueryConstant.DATA_TRANSFER_ORGANIZATION_JOB,parameters, new OrganizationJobMapper());
		}
		catch(Exception e){
			throw e;
		}
	    return organizationJob;
	}
	
	@Override
	public List<Object> getOrganizationJobMaterial(BasicInfoDTO info) throws Exception {
		List<Object> organizationJobMaterialDetails = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			organizationJobMaterialDetails =(List<Object>) namedParameterJdbcTemplate.query(DrydockQueryConstant.DATA_TRANSFER_ORGANIZATION_JOB_MATERIAL_DETAILS_LIST,parameters, new OrganizationJobMaterialDetailsMapper());
		}
		catch(Exception e){
			throw e;
		}
	    return organizationJobMaterialDetails;
	}
	
	@Override
	public List<Object> getShipComponentDetail(BasicInfoDTO info) throws Exception {
		List<Object> shipComponentDetail = new ArrayList<>();
		try{
			Map<String, Object> parameters = new HashMap<>();
			shipComponentDetail =(List<Object>) namedParameterJdbcTemplate.query(DrydockQueryConstant.DATA_TRANSFER_SHIP_COMPONENT_DETAIL,parameters, new ShipComponentDetailsMapper());
		}
		catch(Exception e){
			throw e;
		}
	    return shipComponentDetail;
	}
}
