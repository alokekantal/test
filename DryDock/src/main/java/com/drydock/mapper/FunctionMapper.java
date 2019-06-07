package com.drydock.mapper; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
import com.drydock.entity.Function; 
 
public class FunctionMapper implements RowMapper { 
 
	 @Override 
	 public Function mapRow(ResultSet rs, int rowNum) throws SQLException { 
 
	 	Function function = new Function(); 
	 	function.setFunction_id(rs.getLong("function_id")); 
	 	function.setFunctionKey(rs.getString("function_key")); 
	 	function.setFunctionDescription(rs.getString("function_description")); 
	 	function.setFunctionUrl(rs.getString("function_url")); 
	 	function.setImagePath(rs.getString("image_path")); 
	 	function.setCreateid(rs.getLong("create_id")); 
	 	function.setCreatedate(rs.getDate("create_date")); 
	 	function.setUpdateid(rs.getLong("update_id")); 
	 	function.setUpdatedate(rs.getDate("update_date")); 
	 	function.setIsactive(rs.getInt("is_active")); 
		return function;
	 }
}
