package com.drydock.mapper; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
import com.drydock.entity.UserRole; 
 
public class UserRoleMapper implements RowMapper { 
 
	 @Override 
	 public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException { 
 
	 	UserRole userRole = new UserRole(); 
	 	userRole.setId(rs.getLong("id")); 
	 	userRole.setRoleId(rs.getLong("roleid")); 
	 	userRole.setUserId(rs.getLong("userid")); 
	 	userRole.setCreateid(rs.getLong("create_id")); 
	 	userRole.setCreatedate(rs.getDate("create_date")); 
	 	userRole.setUpdateid(rs.getLong("update_id")); 
	 	userRole.setUpdatedate(rs.getDate("update_date")); 
	 	userRole.setIsactive(rs.getInt("is_active")); 
		return userRole;
	 }
}
