package com.drydock.mapper; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 

import org.springframework.jdbc.core.RowMapper; 

import com.drydock.entity.Department; 
import com.drydock.entity.VesselTypeMaster;
 
public class VesselTypeMasterMapper implements RowMapper { 
 
	 @Override 
	 public VesselTypeMaster mapRow(ResultSet rs, int rowNum) throws SQLException { 
 
	 	VesselTypeMaster v_typeMaster = new VesselTypeMaster(); 
	 	v_typeMaster.setId(rs.getLong("id")); 
	 	v_typeMaster.setCode(rs.getString("code")); 
	 	v_typeMaster.setDescription(rs.getString("description")); 
	 	v_typeMaster.setOrgId(rs.getLong("orgid")); 
	 	v_typeMaster.setCreateid(rs.getLong("create_id")); 
	 	v_typeMaster.setCreatedate(rs.getDate("create_date")); 
	 	v_typeMaster.setUpdateid(rs.getLong("update_id")); 
	 	v_typeMaster.setUpdatedate(rs.getDate("update_date")); 
	 	v_typeMaster.setIsactive(rs.getInt("is_active")); 
		return v_typeMaster;
	 }
}
