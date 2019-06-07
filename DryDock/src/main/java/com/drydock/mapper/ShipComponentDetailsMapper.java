package com.drydock.mapper; 

import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.Date;

import org.springframework.jdbc.core.RowMapper; 

import com.drydock.entity.ShipComponentDetails; 
 
public class ShipComponentDetailsMapper implements RowMapper { 
 
	 @Override 
	 public ShipComponentDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		 ShipComponentDetails shipComponentDetails = new ShipComponentDetails();
		 shipComponentDetails.setId(rs.getLong("id"));
		 shipComponentDetails.setOrgid(rs.getLong("orgid"));
		 shipComponentDetails.setOrgcomponentid(rs.getLong("orgcomponentid"));
		 shipComponentDetails.setShipid(rs.getLong("shipid"));		 
		 shipComponentDetails.setMake(rs.getString("make"));
		 shipComponentDetails.setModel(rs.getString("model"));
		 shipComponentDetails.setDescription(rs.getString("description"));
		 
		 shipComponentDetails.setCreateid(rs.getLong("createid"));
		 shipComponentDetails.setCreatedate(rs.getDate("createdate"));
		 shipComponentDetails.setUpdateid(rs.getLong("updateid"));
		 shipComponentDetails.setUpdatedate(rs.getDate("updatedate"));
		 shipComponentDetails.setIsactive(rs.getInt("isactive"));	
		 
		 return shipComponentDetails;
	 }
}



