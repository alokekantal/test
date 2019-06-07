package com.drydock.mapper; 
 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.UserShip;
 
public class UserShipMapper implements RowMapper { 
 
	 @Override 
	 public UserShip mapRow(ResultSet rs, int rowNum) throws SQLException { 
 
		UserShip userShip = new UserShip(); 
	 	userShip.setId(rs.getLong("id")); 
	 	userShip.setShipid(rs.getLong("shipid")); 
	 	userShip.setUserid(rs.getLong("userid")); 
	 	userShip.setCreateid(rs.getLong("createid")); 
	 	userShip.setCreatedate(rs.getDate("createdate")); 
	 	userShip.setUpdateid(rs.getLong("updateid")); 
	 	userShip.setUpdatedate(rs.getDate("updatedate")); 
	 	userShip.setIsactive(rs.getInt("isactive")); 
		return userShip;
	 }
}
