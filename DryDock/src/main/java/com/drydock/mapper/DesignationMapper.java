package com.drydock.mapper; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
import com.drydock.entity.Designation; 
 
public class DesignationMapper implements RowMapper { 
 
	 @Override 
	 public Designation mapRow(ResultSet rs, int rowNum) throws SQLException { 
 
	 	Designation designation = new Designation(); 
	 	designation.setDesignation_id(rs.getLong("designation_id")); 
	 	designation.setCode(rs.getString("code")); 
	 	designation.setDescription(rs.getString("description")); 
	 	designation.setOrgId(rs.getLong("orgid")); 
	 	designation.setCreateid(rs.getLong("create_id")); 
	 	designation.setCreatedate(rs.getDate("create_date")); 
	 	designation.setUpdateid(rs.getLong("update_id")); 
	 	designation.setUpdatedate(rs.getDate("update_date")); 
	 	designation.setIsactive(rs.getInt("is_active")); 
	 	designation.setDesignation(rs.getString("designation"));
		return designation;
	 }
}
