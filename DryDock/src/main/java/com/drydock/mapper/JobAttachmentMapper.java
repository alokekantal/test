package com.drydock.mapper; 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.JobAttachment;

public class JobAttachmentMapper  implements RowMapper { 
	 
	 @Override 
	 public JobAttachment mapRow(ResultSet rs, int rowNum) throws SQLException {
		 JobAttachment jobAttachment = new JobAttachment();
		 jobAttachment.setId(rs.getLong("id"));
		 jobAttachment.setOrgid(rs.getLong("orgid"));
		 jobAttachment.setShipid(rs.getLong("shipid"));
		 jobAttachment.setProjectid(rs.getLong("projectid"));
		 jobAttachment.setJobid(rs.getLong("jobid"));		  
		 jobAttachment.setAttachmentName(rs.getString("attachment_name"));
		 jobAttachment.setAttachmentDescription(rs.getString("attachment_description"));
		 jobAttachment.setAttachmentType(rs.getString("attachment_type"));
		 jobAttachment.setRelativepath(rs.getString("relativepath"));
		 jobAttachment.setDescription(rs.getString("description"));
		 jobAttachment.setCreateid(rs.getLong("createid"));
		 jobAttachment.setCreatedate(rs.getLong("createdate"));
		 jobAttachment.setUpdateid(rs.getLong("updateid"));
		 jobAttachment.setUpdatedate(rs.getLong("updatedate"));
		 jobAttachment.setIsactive(rs.getInt("isactive"));	
		 jobAttachment.setProgressreportid(rs.getLong("progressreportid"));
		 
		 return jobAttachment;
	 }
}