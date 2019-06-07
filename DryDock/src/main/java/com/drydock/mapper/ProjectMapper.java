package com.drydock.mapper; 

import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.Date;

import org.springframework.jdbc.core.RowMapper; 

import com.drydock.entity.Project;

public class ProjectMapper implements RowMapper{
	@Override 
	public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
		Project project = new Project();
		project.setId(rs.getLong("id"));
		project.setDescription(rs.getString("description"));		
		project.setOrgid(rs.getLong("orgid"));
		project.setShipid(rs.getLong("shipid"));	 
		project.setStatus(rs.getString("status"));
		project.setStartdate(rs.getDate("startdate"));
		project.setEnddate(rs.getDate("enddate"));

		project.setCreateid(rs.getLong("createid"));
		project.setCreatedate(rs.getDate("createdate"));
		project.setUpdateid(rs.getLong("updateid"));
		project.setUpdatedate(rs.getDate("updatedate"));
		project.setIsactive(rs.getInt("isactive"));	
		project.setDockyard(rs.getString("dockyard"));	
		project.setPreamble(rs.getString("preamble"));	
		

		project.setEstimatedStart(rs.getLong("estimated_start"));	
		project.setEstimatedfinish(rs.getLong("estimated_finish"));	
		project.setActualStart(rs.getLong("actual_start"));	
		project.setActualfinish(rs.getLong("actual_finish"));	
		project.setCurrencyMasterId(rs.getLong("currency_masterid"));		
		project.setCloserComment(rs.getString("closerComment"));
		project.setCloserAttachmentRelativePath(rs.getString("closerAttachmentRelativePath"));
		project.setCloserAttachmentName(rs.getString("closerAttachmentName"));
		
		return project;

	}
}