package com.drydock.mapper; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
import com.drydock.entity.DeptRole; 
 
public class DeptRoleMapper implements RowMapper { 
 
	 @Override 
	 public DeptRole mapRow(ResultSet rs, int rowNum) throws SQLException { 
 
	 	DeptRole deptRole = new DeptRole(); 
	 	deptRole.setId(rs.getLong("id")); 
	 	deptRole.setDeptId(rs.getLong("deptid")); 
	 	deptRole.setRoleId(rs.getLong("roleid")); 
	 	deptRole.setCreateid(rs.getLong("create_id")); 
	 	deptRole.setCreatedate(rs.getDate("create_date")); 
	 	deptRole.setUpdateid(rs.getLong("update_id")); 
	 	deptRole.setUpdatedate(rs.getDate("update_date")); 
	 	deptRole.setIsactive(rs.getInt("is_active")); 
		return deptRole;
	 }
}
