package com.drydock.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.JobProgressReport;;

public class JobProgressReportMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobProgressReport jobProgressReport = new JobProgressReport();
		jobProgressReport.setId(rs.getLong("id"));
		jobProgressReport.setJobid(rs.getLong("jobid"));
		jobProgressReport.setReportingDate(rs.getLong("reportingdate"));
		jobProgressReport.setExecutionDate(rs.getLong("executiondate"));
		jobProgressReport.setWorkDone(rs.getString("workdone"));
		jobProgressReport.setJobAttachmentIds(rs.getString("jobattachmentids"));
		 
		 jobProgressReport.setCreateid(rs.getLong("create_id"));
		 jobProgressReport.setCreatedate(rs.getLong("create_date"));
		 jobProgressReport.setUpdateid(rs.getLong("update_id"));
		 jobProgressReport.setUpdatedate(rs.getLong("update_date"));
		 jobProgressReport.setIsactive(rs.getInt("is_active"));	
		 
		 return jobProgressReport;
	}

}
