package com.drydock.mapper; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
import com.drydock.entity.Department; 
 
public class DepartmentMapper implements RowMapper { 
 
	 @Override 
	 public Department mapRow(ResultSet rs, int rowNum) throws SQLException { 
 
	 	Department department = new Department(); 
	 	department.setDept_id(rs.getLong("dept_id")); 
	 	department.setDeptName(rs.getString("dept_name")); 
	 	department.setDeptMail(rs.getString("dept_mail")); 
	 	department.setOrgId(rs.getLong("orgid")); 
	 	department.setCreateid(rs.getLong("create_id")); 
	 	department.setCreatedate(rs.getDate("create_date")); 
	 	department.setUpdateid(rs.getLong("update_id")); 
	 	department.setUpdatedate(rs.getDate("update_date")); 
	 	department.setIsactive(rs.getInt("is_active")); 
		return department;
	 }
}
