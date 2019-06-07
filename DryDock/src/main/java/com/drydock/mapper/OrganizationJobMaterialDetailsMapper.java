package com.drydock.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.OrganizationJobMaterialDetails;

public class OrganizationJobMaterialDetailsMapper  implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrganizationJobMaterialDetails jobMaterialDetails = new OrganizationJobMaterialDetails();
		jobMaterialDetails.setId(rs.getLong("id"));
		jobMaterialDetails.setOrganizationJobid(rs.getLong("organizationJobid"));
		jobMaterialDetails.setMake(rs.getString("make"));
		jobMaterialDetails.setModel(rs.getString("model"));
		jobMaterialDetails.setPartNo(rs.getString("part_no"));
		jobMaterialDetails.setPartName(rs.getString("part_name"));
		jobMaterialDetails.setQuantity(rs.getFloat("quantity"));		
		jobMaterialDetails.setRemarks(rs.getString("remarks"));	
		
		jobMaterialDetails.setCreateid(rs.getLong("create_id"));
		jobMaterialDetails.setCreatedate(rs.getLong("create_date"));
		jobMaterialDetails.setUpdateid(rs.getLong("update_id"));
		jobMaterialDetails.setUpdatedate(rs.getLong("update_date"));
		jobMaterialDetails.setIsactive(rs.getInt("is_active"));
		
		jobMaterialDetails.setUom(rs.getString("uom"));	
		jobMaterialDetails.setDrawingNo(rs.getString("drawingNo"));	
		 
		return jobMaterialDetails;
	}

}
