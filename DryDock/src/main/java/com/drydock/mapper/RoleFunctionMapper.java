package com.drydock.mapper; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
import com.drydock.entity.RoleFunction; 
 
public class RoleFunctionMapper implements RowMapper { 
 
	 @Override 
	 public RoleFunction mapRow(ResultSet rs, int rowNum) throws SQLException { 
 
	 	RoleFunction roleFunction = new RoleFunction(); 
	 	roleFunction.setId(rs.getLong("id")); 
	 	roleFunction.setRoleId(rs.getLong("roleid")); 
	 	roleFunction.setFunctionId(rs.getLong("functionid")); 
	 	roleFunction.setCreateid(rs.getLong("create_id")); 
	 	roleFunction.setCreatedate(rs.getDate("create_date")); 
	 	roleFunction.setUpdateid(rs.getLong("update_id")); 
	 	roleFunction.setUpdatedate(rs.getDate("update_date")); 
	 	roleFunction.setIsactive(rs.getInt("is_active")); 
		return roleFunction;
	 }
}
