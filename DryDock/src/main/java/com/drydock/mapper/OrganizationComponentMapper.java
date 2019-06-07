package com.drydock.mapper; 

import java.sql.ResultSet; 
import java.sql.SQLException; 

import org.springframework.jdbc.core.RowMapper; 

import com.drydock.entity.OrganizationComponent;;; 
 
public class OrganizationComponentMapper implements RowMapper { 
 
	 @Override 
	 public OrganizationComponent mapRow(ResultSet rs, int rowNum) throws SQLException {
		 OrganizationComponent organizationComponent = new OrganizationComponent();
		 organizationComponent.setId(rs.getLong("id"));
		 organizationComponent.setOrgid(rs.getLong("orgid"));
		 organizationComponent.setApplicationcomponentid(rs.getLong("applicationcomponentid"));
		 organizationComponent.setCode(rs.getString("code"));
		 organizationComponent.setDescription(rs.getString("description"));
		 organizationComponent.setParentcode(rs.getString("parentcode"));
		 
		 organizationComponent.setCreateid(rs.getLong("createid"));
		 organizationComponent.setCreatedate(rs.getDate("createdate"));
		 organizationComponent.setUpdateid(rs.getLong("updateid"));
		 organizationComponent.setUpdatedate(rs.getDate("updatedate"));
		 organizationComponent.setIsactive(rs.getInt("isactive"));		 
		 
		return organizationComponent;
	 }
}


