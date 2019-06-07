package com.drydock.mapper; 
 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.UserCurrentShip;
 
public class UserCurrentShipMapper implements RowMapper { 
 
	 @Override 
	 public UserCurrentShip mapRow(ResultSet rs, int rowNum) throws SQLException { 
 
		UserCurrentShip userCurrentShip = new UserCurrentShip(); 
	 	userCurrentShip.setShipid(rs.getLong("shipid")); 
	 	userCurrentShip.setUserid(rs.getLong("userid")); 
	 	userCurrentShip.setCreatedate(rs.getDate("createdate")); 
		return userCurrentShip;
	 }
}
