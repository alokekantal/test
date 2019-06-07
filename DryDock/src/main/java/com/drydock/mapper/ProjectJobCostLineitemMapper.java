package com.drydock.mapper; 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.ProjectJobCostLineitem;

public class ProjectJobCostLineitemMapper  implements RowMapper { 
	 
	 @Override 
	 public ProjectJobCostLineitem mapRow(ResultSet rs, int rowNum) throws SQLException {
		 ProjectJobCostLineitem projectJobCostLineitem = new ProjectJobCostLineitem();
		 projectJobCostLineitem.setId(rs.getLong("id"));
		 projectJobCostLineitem.setOrgid(rs.getLong("orgid"));
		 projectJobCostLineitem.setShipid(rs.getLong("shipid"));
		 projectJobCostLineitem.setProjectid(rs.getLong("projectid"));
		 projectJobCostLineitem.setJobid(rs.getLong("jobid"));
		 projectJobCostLineitem.setLineitem(rs.getString("lineitem"));
		 
		 projectJobCostLineitem.setCreateid(rs.getLong("createid"));
		 projectJobCostLineitem.setCreatedate(rs.getLong("createdate"));
		 projectJobCostLineitem.setUpdateid(rs.getLong("updateid"));
		 projectJobCostLineitem.setUpdatedate(rs.getLong("updatedate"));
		 projectJobCostLineitem.setIsactive(rs.getInt("isactive"));	
		 
		 return projectJobCostLineitem;
	 }
}