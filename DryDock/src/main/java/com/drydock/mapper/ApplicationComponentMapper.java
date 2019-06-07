package com.drydock.mapper; 

import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
import com.drydock.entity.ApplicationComponent;; 
 
public class ApplicationComponentMapper implements RowMapper { 
 
	 @Override 
	 public ApplicationComponent mapRow(ResultSet rs, int rowNum) throws SQLException {
		 ApplicationComponent applicationComponent = new ApplicationComponent();
		 applicationComponent.setId(rs.getLong("id"));
		 applicationComponent.setCode(rs.getString("code"));
		 applicationComponent.setDescription(rs.getString("description"));
		 applicationComponent.setParentcode(rs.getString("parentcode"));
		 applicationComponent.setCreateid(rs.getLong("createid"));
		 applicationComponent.setCreatedate(rs.getDate("createdate"));
		 applicationComponent.setUpdateid(rs.getLong("updateid"));
		 applicationComponent.setUpdatedate(rs.getDate("updatedate"));
		 applicationComponent.setIsactive(rs.getInt("isactive"));
		 
		return applicationComponent;
	 }
}

