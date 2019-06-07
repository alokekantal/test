package com.drydock.mapper; 
 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.Shipmaster;
 
public class ShipmasterMapper implements RowMapper { 
 
	 @Override 
	 public Shipmaster mapRow(ResultSet rs, int rowNum) throws SQLException { 
 
	 	Shipmaster shipDetail = new Shipmaster(); 
	 	shipDetail.setShip_id(rs.getLong("shipid")); 
	 	shipDetail.setShipno(rs.getString("shipno")); 
	 	shipDetail.setOrgId(rs.getLong("orgid")); 
	 	shipDetail.setName(rs.getString("name")); 
	 	shipDetail.setDescription(rs.getString("description")); 
	 	shipDetail.setVesselname(rs.getString("vesselname")); 
	 	shipDetail.setV_imo_no(rs.getLong("v_imo_no")); 
	 	shipDetail.setMmsi_no(rs.getLong("mmsi_no")); 
	 	shipDetail.setCall_sign(rs.getString("call_sign")); 
	 	shipDetail.setOfficial_no(rs.getString("official_no")); 
	 	shipDetail.setV_type(rs.getLong("v_type")); 
	 	shipDetail.setOwner_imo_no(rs.getLong("owner_imo_no")); 
	 	shipDetail.setOwner_name(rs.getString("owner_name")); 
	 	shipDetail.setSat_f_77(rs.getString("sat_f_77")); 
	 	shipDetail.setSat_c(rs.getString("sat_c")); 
	 	shipDetail.setFleet_broadband(rs.getString("fleet_broadband")); 
	 	shipDetail.setSat_c_emailID(rs.getString("sat_c_emailID")); 
	 	shipDetail.setEmailID(rs.getString("emailID")); 
	 	shipDetail.setShipClass(rs.getString("class")); 
	 	shipDetail.setClass_notations(rs.getString("class_notations")); 
	 	shipDetail.setClassi_Id_No(rs.getString("Classi_Id_No")); 
	 	shipDetail.setFlag(rs.getString("flag")); 
	 	shipDetail.setPort_of_registry(rs.getString("port_of_registry")); 
	 	shipDetail.setYear_built(rs.getString("year_built")); 
	 	shipDetail.setKeel_laying_date(rs.getLong("keel_laying_date")); 
	 	shipDetail.setVessel_delivery_date(rs.getLong("vessel_delivery_date")); 
	 	shipDetail.setHull_type(rs.getString("hull_type")); 
	 	shipDetail.setLength_overall(rs.getBigDecimal("length_overall")); 
	 	shipDetail.setBreadth_MLD(rs.getBigDecimal("breadth_MLD")); 
	 	shipDetail.setDepth(rs.getBigDecimal("depth")); 
	 	shipDetail.setSummer_draft_M(rs.getBigDecimal("summer_draft_M")); 
	 	shipDetail.setSummer_DWT_MT(rs.getString("summer_DWT_MT")); 
	 	shipDetail.setInternational_GRT(rs.getString("international_GRT")); 
	 	shipDetail.setInternational_NRT(rs.getString("international_NRT")); 
	 	shipDetail.setLife_boat_cap(rs.getLong("life_boat_cap")); 
	 	shipDetail.setV_short_name(rs.getString("v_short_name")); 
	 	shipDetail.setAccount_code_old(rs.getString("account_code_old")); 
	 	shipDetail.setAccount_code_new(rs.getString("account_code_new")); 
	 	shipDetail.setTel_fac_code(rs.getString("tel_fac_code")); 
	 	shipDetail.setCreateid(rs.getLong("createid")); 
	 	shipDetail.setCreatedate(rs.getDate("createdate")); 
	 	shipDetail.setUpdateid(rs.getLong("updateid")); 
	 	shipDetail.setUpdatedate(rs.getDate("updatedate")); 
	 	shipDetail.setIsactive(rs.getInt("isactive")); 
	 	shipDetail.setMaxEmailSizeInMB(rs.getBigDecimal("max_email_size_in_mb"));
	 	shipDetail.setDailyDataLimitInMB(rs.getBigDecimal("daily_data_limit_in_mb"));
	 	
	 	shipDetail.setEmail1(rs.getString("email1"));
	 	shipDetail.setEmail2(rs.getString("email2"));
	 	shipDetail.setPhoneNo(rs.getString("phoneno")); 
	 	shipDetail.setPhoneNo1(rs.getString("phoneno1")); 
	 	shipDetail.setPhoneNo2(rs.getString("phoneno2"));
	 	try{
	 		shipDetail.setAssignedUsers(rs.getString("assigned_users")); 
	 	}catch(Exception ex){}
		return shipDetail;
	 }
}
