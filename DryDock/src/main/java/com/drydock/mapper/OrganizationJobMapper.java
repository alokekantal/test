package com.drydock.mapper; 

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.OrganizationJob;;

public class OrganizationJobMapper implements RowMapper{

	@Override
	public OrganizationJob mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrganizationJob organizationJob = new OrganizationJob();
		organizationJob.setId(rs.getLong("id"));
		organizationJob.setDescription(rs.getString("description"));
		organizationJob.setCreateid(rs.getLong("createid"));
		organizationJob.setCreatedate(rs.getDate("createdate"));
		organizationJob.setUpdateid(rs.getLong("updateid"));
		organizationJob.setUpdatedate(rs.getDate("updatedate"));
		organizationJob.setIsactive(rs.getInt("isactive"));	
		//organizationJob.setJobdate(rs.getDate("job_date"));
		organizationJob.setJobno(rs.getString("job_no"));
		organizationJob.setAccountno(rs.getString("account_no"));
		organizationJob.setSpecification(rs.getString("specification"));
		organizationJob.setLocation(rs.getString("location"));
		organizationJob.setDetailedSpecification(rs.getString("detailed_specification"));
		organizationJob.setTotalArea(rs.getString("total_area"));
		organizationJob.setCheckboxes(rs.getString("checkboxes"));		
		organizationJob.setShipcomponentid(rs.getLong("shipcomponentid"));
		organizationJob.setOrgid(rs.getInt("orgid"));
		organizationJob.setApplicationjobid(rs.getInt("applicationjobid"));

		organizationJob.setVesselAge(rs.getString("vessel_age"));
		organizationJob.setVesselType(rs.getString("vessel_type"));
		organizationJob.setJobdate(rs.getLong("job_date"));
		organizationJob.setMake(rs.getString("make"));
		organizationJob.setModel(rs.getString("model"));
		organizationJob.setMakeModelDescription(rs.getString("makeModelDescription"));
		organizationJob.setEquipment(rs.getString("equipment"));

		return organizationJob;
	}

}
