package com.drydock.mapper; 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.ProjectCurrencyConversion;

public class ProjectCurrencyConversionMapper  implements RowMapper { 
	 
	 @Override 
	 public ProjectCurrencyConversion mapRow(ResultSet rs, int rowNum) throws SQLException {
		 ProjectCurrencyConversion projectCurrencyConversion = new ProjectCurrencyConversion();
		 projectCurrencyConversion.setId(rs.getLong("id"));
		 projectCurrencyConversion.setOrgid(rs.getLong("orgid"));
		 projectCurrencyConversion.setShipid(rs.getLong("shipid"));
		 projectCurrencyConversion.setProjectid(rs.getLong("projectid"));
		 projectCurrencyConversion.setFromcurrencyid(rs.getLong("fromcurrencyid"));
		 projectCurrencyConversion.setTocurrencyid(rs.getLong("tocurrencyid"));
		 projectCurrencyConversion.setConversionRate(rs.getFloat("conversion_rate"));
		 
		 projectCurrencyConversion.setCreateid(rs.getLong("createid"));
		 projectCurrencyConversion.setCreatedate(rs.getLong("createdate"));
		 projectCurrencyConversion.setUpdateid(rs.getLong("updateid"));
		 projectCurrencyConversion.setUpdatedate(rs.getLong("updatedate"));
		 projectCurrencyConversion.setIsactive(rs.getInt("isactive"));	
		 
		 return projectCurrencyConversion;
	 }
}