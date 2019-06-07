package com.drydock.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.ApplicationVesselDocType;

public class ApplicationVesselDocTypeMapper implements RowMapper{
	@Override
	public ApplicationVesselDocType mapRow(ResultSet rs, int rowNum) throws SQLException {
		ApplicationVesselDocType applicationVesselDocType = new ApplicationVesselDocType();
		applicationVesselDocType.setId(rs.getLong("id"));
		applicationVesselDocType.setVesselDocDescription(rs.getString("vessel_doc_description"));		 
		applicationVesselDocType.setCreateid(rs.getLong("create_id"));
		applicationVesselDocType.setCreatedate(rs.getDate("create_date"));
		applicationVesselDocType.setUpdateid(rs.getLong("update_id"));
		applicationVesselDocType.setUpdatedate(rs.getDate("update_date"));
		applicationVesselDocType.setIsactive(rs.getInt("is_active"));
		 
		 return applicationVesselDocType;
	}
}
