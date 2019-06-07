package com.drydock.mapper; 

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.ApplicationJob;

public class ApplicationJobMapper implements RowMapper{

	@Override
	public ApplicationJob mapRow(ResultSet rs, int rowNum) throws SQLException {
		ApplicationJob applicationJob = new ApplicationJob();
		applicationJob.setId(rs.getLong("id"));
		applicationJob.setDescription(rs.getString("description"));
		applicationJob.setCreateid(rs.getLong("createid"));
		applicationJob.setCreatedate(rs.getDate("createdate"));
		applicationJob.setUpdateid(rs.getLong("updateid"));
		applicationJob.setUpdatedate(rs.getDate("updatedate"));
		applicationJob.setIsactive(rs.getInt("isactive"));	
		applicationJob.setJobno(rs.getString("job_no"));
		applicationJob.setAccountno(rs.getString("account_no"));
		applicationJob.setSpecification(rs.getString("specification"));
		applicationJob.setLocation(rs.getString("location"));
		applicationJob.setDetailedSpecification(rs.getString("detailed_specification"));
		applicationJob.setTotalArea(rs.getString("total_area"));
		applicationJob.setCheckboxes(rs.getString("checkboxes"));
		
		applicationJob.setShipcomponentid(rs.getLong("shipcomponentid"));

		applicationJob.setVesselAge(rs.getString("vessel_age"));
		applicationJob.setVesselType(rs.getString("vessel_type"));
		applicationJob.setJobdate(rs.getLong("job_date"));
		applicationJob.setMake(rs.getString("make"));
		applicationJob.setModel(rs.getString("model"));
		applicationJob.setMakeModelDescription(rs.getString("makeModelDescription"));
		applicationJob.setEquipment(rs.getString("equipment"));

		return applicationJob;
	}

}
