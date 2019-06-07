package com.drydock.mapper; 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.ProjectJobCostLineitemDetails;

public class ProjectJobCostLineitemDetailsMapper  implements RowMapper { 
	 
	 @Override 
	 public ProjectJobCostLineitemDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		 ProjectJobCostLineitemDetails projectJobCostLineitemDetails = new ProjectJobCostLineitemDetails();
		 projectJobCostLineitemDetails.setId(rs.getLong("id"));
		 projectJobCostLineitemDetails.setOrgid(rs.getLong("orgid"));
		 projectJobCostLineitemDetails.setShipid(rs.getLong("shipid"));
		 projectJobCostLineitemDetails.setProjectid(rs.getLong("projectid"));
		 projectJobCostLineitemDetails.setJobid(rs.getLong("jobid"));
		 projectJobCostLineitemDetails.setLineitemid(rs.getLong("lineitemid"));
		 projectJobCostLineitemDetails.setDockyardId(rs.getLong("dockyardid"));
		 projectJobCostLineitemDetails.setQuoteCurrencyid(rs.getLong("quote_currencyid"));
		 projectJobCostLineitemDetails.setUnit(rs.getString("unit"));
		 projectJobCostLineitemDetails.setUnitPrice(rs.getFloat("unit_price"));
		 projectJobCostLineitemDetails.setUnitQuantity(rs.getFloat("unit_qty"));
		 projectJobCostLineitemDetails.setCostQuoteCurrency(rs.getFloat("cost_quote_currency"));
		 projectJobCostLineitemDetails.setCostProjectCurrency(rs.getFloat("cost_project_currency"));
		 
		 projectJobCostLineitemDetails.setCreateid(rs.getLong("createid"));
		 projectJobCostLineitemDetails.setCreatedate(rs.getLong("createdate"));
		 projectJobCostLineitemDetails.setUpdateid(rs.getLong("updateid"));
		 projectJobCostLineitemDetails.setUpdatedate(rs.getLong("updatedate"));
		 projectJobCostLineitemDetails.setIsactive(rs.getInt("isactive"));	
		 
		 return projectJobCostLineitemDetails;
	 }
}