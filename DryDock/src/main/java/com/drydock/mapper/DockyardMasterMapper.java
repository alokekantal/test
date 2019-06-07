package com.drydock.mapper; 
 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.DockyardMaster;
 
public class DockyardMasterMapper implements RowMapper { 
 
	 @Override 
	 public DockyardMaster mapRow(ResultSet rs, int rowNum) throws SQLException { 
 
		 DockyardMaster dockyard = new DockyardMaster(); 
	 	dockyard.setId(rs.getLong("id")); 
	 	dockyard.setOrgId(rs.getLong("orgid")); 
	 	dockyard.setCreateid(rs.getLong("createid")); 
	 	dockyard.setCreatedate(rs.getDate("createdate")); 
	 	dockyard.setUpdateid(rs.getLong("updateid")); 
	 	dockyard.setUpdatedate(rs.getDate("updatedate")); 
	 	dockyard.setIsactive(rs.getInt("isactive")); 
	 	dockyard.setDockyard(rs.getString("dockyard"));
		return dockyard;
	 }
}
