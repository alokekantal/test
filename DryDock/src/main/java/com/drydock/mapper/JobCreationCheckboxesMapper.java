package com.drydock.mapper; 

import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.Date;

import org.springframework.jdbc.core.RowMapper; 

import com.drydock.entity.Job;
import com.drydock.entity.JobCreationCheckboxes;

public class JobCreationCheckboxesMapper implements RowMapper{

	@Override
	public JobCreationCheckboxes mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobCreationCheckboxes checkbox = new JobCreationCheckboxes();
		 checkbox.setId(rs.getLong("id"));
		 checkbox.setKey(rs.getString("checkboxkey"));
		 checkbox.setDescription(rs.getString("description"));
		 
		 return checkbox;
	}

}
