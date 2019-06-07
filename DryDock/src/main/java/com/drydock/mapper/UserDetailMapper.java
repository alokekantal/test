package com.drydock.mapper; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
import com.drydock.entity.UserDetail; 
 
public class UserDetailMapper implements RowMapper { 
 
	 @Override 
	 public UserDetail mapRow(ResultSet rs, int rowNum) throws SQLException { 

		 	UserDetail userDetail = new UserDetail(); 
		 	userDetail.setUser_id(rs.getLong("user_id")); 
		 	userDetail.setUserCode(rs.getString("user_code")); 
		 	userDetail.setShipid(rs.getLong("shipid")); 
		 	userDetail.setUserType(rs.getString("user_type")); 
	 	userDetail.setPasscode(rs.getString("user_passcode")); 
	 	userDetail.setFirstname(rs.getString("firstname")); 
	 	userDetail.setLastname(rs.getString("lastname")); 
	 	userDetail.setAddress(rs.getString("address")); 
	 	userDetail.setPhonenumber(rs.getString("phoneno")); 
	 	userDetail.setPersonalMailid(rs.getString("email")); 
	 	userDetail.setCurrentReportTo(rs.getLong("currentreportto")); 
	 	userDetail.setUseruid(rs.getString("useruid")); 
	 	userDetail.setUidtype(rs.getString("uid_type")); 
	 	userDetail.setImagePath(rs.getString("imagepath")); 
	 	userDetail.setOrgId(rs.getLong("orgid")); 
	 	userDetail.setDeptId(rs.getLong("deptid")); 
	 	userDetail.setDesignationId(rs.getLong("designationid")); 
	 	userDetail.setCreateid(rs.getLong("create_id")); 
	 	userDetail.setCreatedate(rs.getDate("create_date")); 
	 	userDetail.setUpdateid(rs.getLong("update_id")); 
	 	userDetail.setUpdatedate(rs.getDate("update_date")); 
	 	userDetail.setIsactive(rs.getInt("is_active"));
	 	userDetail.setEmail1(rs.getString("email1"));
	 	userDetail.setEmail2(rs.getString("email2"));
	 	userDetail.setPhoneNo1(rs.getString("phoneno1")); 
	 	userDetail.setPhoneNo2(rs.getString("phoneno2")); 
	 	try{
	 		userDetail.setReportingToName(rs.getString("reporting_to_name"));
	 	}catch(Exception ex){}
	 	try{
	 		userDetail.setRoleListStr(rs.getString("role_list"));
	 	}catch(Exception ex){}
		return userDetail;
	 }
}
