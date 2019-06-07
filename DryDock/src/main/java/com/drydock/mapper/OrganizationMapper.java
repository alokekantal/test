package com.drydock.mapper; 
 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import org.springframework.jdbc.core.RowMapper; 
import com.drydock.entity.Organization; 
 
public class OrganizationMapper implements RowMapper { 
 
	 @Override 
	 public Organization mapRow(ResultSet rs, int rowNum) throws SQLException { 
 
	 	Organization organization = new Organization(); 
	 	organization.setOrg_id(rs.getLong("org_id")); 
	 	organization.setOrgName(rs.getString("org_name")); 
	 	organization.setOrgMail(rs.getString("org_mail"));
	 	organization.setEmail1(rs.getString("email1"));
	 	organization.setEmail2(rs.getString("email2"));
	 	organization.setAddress(rs.getString("address")); 
	 	organization.setPhoneNo(rs.getString("phoneno")); 
	 	organization.setPhoneNo1(rs.getString("phoneno1")); 
	 	organization.setPhoneNo2(rs.getString("phoneno2")); 
	 	organization.setFaxNo(rs.getString("faxno")); 
	 	organization.setOrgRegNumber(rs.getString("org_reg_no")); 
	 	organization.setCreateid(rs.getLong("create_id")); 
	 	organization.setCreatedate(rs.getDate("create_date")); 
	 	organization.setUpdateid(rs.getLong("update_id")); 
	 	organization.setUpdatedate(rs.getDate("update_date")); 
	 	organization.setIsactive(rs.getInt("is_active")); 
	 	organization.setOrgDesc(rs.getString("org_desc")); 
		return organization;
	 }
}
