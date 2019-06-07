package com.drydock.mapper; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
import com.drydock.entity.Role; 
 
public class RoleMapper implements RowMapper { 
 
	 @Override 
	 public Role mapRow(ResultSet rs, int rowNum) throws SQLException { 
 
	 	Role role = new Role(); 
	 	role.setRole_id(rs.getLong("role_id")); 
	 	role.setCode(rs.getString("code")); 
	 	role.setDescription(rs.getString("description"));
	 	role.setOrgId(rs.getLong("orgid")); 
	 	role.setCreateid(rs.getLong("create_id")); 
	 	role.setCreatedate(rs.getDate("create_date")); 
	 	role.setUpdateid(rs.getLong("update_id")); 
	 	role.setUpdatedate(rs.getDate("update_date")); 
	 	role.setIsactive(rs.getInt("is_active")); 
		return role;
	 }
}
