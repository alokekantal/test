package com.drydock.mapper; 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.ProjectAttachment;
import com.drydock.entity.ProjectDockyard;

public class ProjectDockyardMapper  implements RowMapper { 
	 
	 @Override 
	 public ProjectDockyard mapRow(ResultSet rs, int rowNum) throws SQLException {
		 ProjectDockyard projectDockyard = new ProjectDockyard();
		 projectDockyard.setId(rs.getLong("id"));
		 projectDockyard.setOrgid(rs.getLong("orgid"));
		 projectDockyard.setShipid(rs.getLong("shipid"));
		 projectDockyard.setProjectid(rs.getLong("projectid"));
		 projectDockyard.setContactDetails(rs.getString("contactdetails"));
		 projectDockyard.setDefaultCurrencyId(rs.getLong("default_currencyid"));
		 projectDockyard.setDockyardId(rs.getLong("dockyardid"));
		 projectDockyard.setRemarks(rs.getString("remarks"));
		 
		 projectDockyard.setCreateid(rs.getLong("createid"));
		 projectDockyard.setCreatedate(rs.getDate("createdate"));
		 projectDockyard.setUpdateid(rs.getLong("updateid"));
		 projectDockyard.setUpdatedate(rs.getDate("updatedate"));
		 projectDockyard.setIsactive(rs.getInt("isactive"));	
		 
		 return projectDockyard;
	 }
}