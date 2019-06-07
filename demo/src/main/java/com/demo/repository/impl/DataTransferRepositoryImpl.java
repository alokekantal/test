package com.demo.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.demo.dto.BasicInfoDTO;
import com.demo.repository.DataTransferRepository;
import com.demo.util.DrydockConstant;
import com.demo.util.QueryConstant;

@Component
public class DataTransferRepositoryImpl implements com.demo.repository.DataTransferRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
//	@Override
//	public List<Object> getShipComponentDetail(BasicInfoDTO info) throws Exception {
//		List<Object> shipComponentDetail = new ArrayList<>();
//		try{
//			Map<String, Object> parameters = new HashMap<>();
//			shipComponentDetail =(List<Object>) namedParameterJdbcTemplate.query(DrydockQueryConstant.DATA_TRANSFER_SHIP_COMPONENT_DETAIL,parameters, new ShipComponentDetailsMapper());
//		}
//		catch(Exception e){
//			throw e;
//		}
//	    return shipComponentDetail;
//	}
}
