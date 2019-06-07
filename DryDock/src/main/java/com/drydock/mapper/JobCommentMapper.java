package com.drydock.mapper; 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.JobComment;

public class JobCommentMapper  implements RowMapper { 
	 
	 @Override 
	 public JobComment mapRow(ResultSet rs, int rowNum) throws SQLException {
		 JobComment jobComment = new JobComment();
		 jobComment.setId(rs.getLong("id"));
		 jobComment.setJobid(rs.getLong("jobid"));		  
		 jobComment.setJobComment(rs.getString("job_comment"));
		 jobComment.setCreateid(rs.getLong("createid"));
		 jobComment.setCreatedate(rs.getLong("createdate"));
		 jobComment.setUpdateid(rs.getLong("updateid"));
		 jobComment.setUpdatedate(rs.getLong("updatedate"));
		 jobComment.setIsactive(rs.getInt("isactive"));
		 try{
			 jobComment.setUpdatedBy(rs.getString("updatedby"));
		 }catch(Exception ex){
			 
		 }
		 
		 return jobComment;
	 }
}