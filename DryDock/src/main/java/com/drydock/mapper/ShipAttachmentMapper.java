package com.drydock.mapper; 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.ShipAttachment;

public class ShipAttachmentMapper  implements RowMapper { 
	 
	 @Override 
	 public ShipAttachment mapRow(ResultSet rs, int rowNum) throws SQLException {
		 ShipAttachment shipAttachment = new ShipAttachment();
		 shipAttachment.setId(rs.getLong("id"));
		 shipAttachment.setOrgid(rs.getLong("orgid"));
		 shipAttachment.setShipid(rs.getLong("shipid"));  
		 shipAttachment.setAttachmentName(rs.getString("attachment_name"));
		 shipAttachment.setRelativepath(rs.getString("relativepath"));		 
		 shipAttachment.setCreateid(rs.getLong("createid"));
		 shipAttachment.setCreatedate(rs.getDate("createdate"));
		 shipAttachment.setUpdateid(rs.getLong("updateid"));
		 shipAttachment.setUpdatedate(rs.getDate("updatedate"));
		 shipAttachment.setIsactive(rs.getInt("isactive"));
		 shipAttachment.setAttachmentDescription(rs.getString("attachment_description"));
		 shipAttachment.setAttachmentType(rs.getString("attachment_type"));
		 shipAttachment.setVesselDocTypeId(rs.getLong("vessel_doc_typeid"));
		 
		 return shipAttachment;
	 }
}
