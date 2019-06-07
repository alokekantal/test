package com.demo.mapper; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
import com.demo.dto.*;
 
public class BasicInfoDTOMapper implements RowMapper { 
 
	 @Override 
	 public BasicInfoDTO mapRow(ResultSet rs, int rowNum) throws SQLException { 
 
		 BasicInfoDTO bi = new BasicInfoDTO(); 
		 bi.setUserId(rs.getLong("userId")); 
		return bi;
	 }
}
