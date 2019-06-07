package com.drydock.mapper; 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.ProjectAttachment;

public class ProjectAttachmentMapper  implements RowMapper { 
	 
	 @Override 
	 public ProjectAttachment mapRow(ResultSet rs, int rowNum) throws SQLException {
		 ProjectAttachment projectAttachment = new ProjectAttachment();
		 projectAttachment.setId(rs.getLong("id"));
		 projectAttachment.setOrgid(rs.getLong("orgid"));
		 projectAttachment.setShipid(rs.getLong("shipid"));
		 projectAttachment.setProjectid(rs.getLong("projectid"));
		 projectAttachment.setAttachmentName(rs.getString("attachment_name"));
		 projectAttachment.setAttachmentDescription(rs.getString("attachment_description"));
		 projectAttachment.setAttachmentType(rs.getString("attachment_type"));
		 projectAttachment.setRelativepath(rs.getString("relativepath"));
		 projectAttachment.setCreateid(rs.getLong("createid"));
		 projectAttachment.setCreatedate(rs.getDate("createdate"));
		 projectAttachment.setUpdateid(rs.getLong("updateid"));
		 projectAttachment.setUpdatedate(rs.getDate("updatedate"));
		 projectAttachment.setIsactive(rs.getInt("isactive"));	
		 
		 return projectAttachment;
	 }
}