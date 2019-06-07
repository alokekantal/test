package com.drydock.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.OrganizationVesselDocType;

public class OrganizationVesselDocTypeMapper implements RowMapper{
	@Override
	public OrganizationVesselDocType mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrganizationVesselDocType organizationVesselDocType = new OrganizationVesselDocType();
		organizationVesselDocType.setId(rs.getLong("id"));
		organizationVesselDocType.setOrgId(rs.getLong("org_id"));
		organizationVesselDocType.setVesselDocDescription(rs.getString("vessel_doc_description"));		 
		organizationVesselDocType.setCreateid(rs.getLong("create_id"));
		organizationVesselDocType.setCreatedate(rs.getDate("create_date"));
		organizationVesselDocType.setUpdateid(rs.getLong("update_id"));
		organizationVesselDocType.setUpdatedate(rs.getDate("update_date"));
		organizationVesselDocType.setIsactive(rs.getInt("is_active"));
		 
		 return organizationVesselDocType;
	}
}
