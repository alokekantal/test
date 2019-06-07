package com.drydock.mapper; 

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drydock.entity.Job;

public class JobMapper implements RowMapper{

	@Override
	public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
		Job job = new Job();
		 job.setId(rs.getLong("id"));
		 job.setOrgid(rs.getLong("orgid"));
		 job.setShipid(rs.getLong("shipid"));
		 job.setProjectid(rs.getLong("projectid"));
		 job.setDescription(rs.getString("description"));
		 job.setShipcomponentid(rs.getLong("shipcomponentid"));
		 job.setStatus(rs.getString("status"));	
		 job.setEquipment(rs.getString("equipment"));
		 
		 job.setCreateid(rs.getLong("createid"));
		 job.setCreatedate(rs.getLong("createdate"));
		 job.setUpdateid(rs.getLong("updateid"));
		 job.setUpdatedate(rs.getLong("updatedate"));
		 job.setIsactive(rs.getInt("isactive"));	
		 try{
			 job.setJobLastUpdatedByName(rs.getString("updatedby"));
		 }catch(Exception e){
			 //do nothing
		 }
		 job.setJobdate(rs.getLong("job_date"));
		 job.setJobno(rs.getString("job_no"));
		 job.setAccountno(rs.getString("account_no"));
		 job.setSpecification(rs.getString("specification"));
		 job.setLocation(rs.getString("location"));
		 job.setDetailedSpecification(rs.getString("detailed_specification"));
		 job.setTotalArea(rs.getString("total_area"));
		 job.setCheckboxes(rs.getString("checkboxes"));
		 job.setCurrency(rs.getString("currency"));
		 job.setEstimatedBudget(rs.getBigDecimal("estimated_budget"));
		 
		 job.setMake(rs.getString("make"));
		 job.setModel(rs.getString("model"));
		 job.setMakeModelDescription(rs.getString("makeModelDescription"));
		 job.setExternalReference(rs.getString("externalReference"));
		 
		 job.setApprovalFlag(rs.getString("approval_flag"));
		 job.setApprovalComment(rs.getString("approval_comment"));
		 job.setApprovalLastUpdatedBy(rs.getLong("approval_last_updatedby"));
		 job.setApprovalLastUpdatedOn(rs.getLong("approval_last_updatedon"));
		 job.setJobCloserRemark(rs.getString("job_closer_remark"));
		 
		 try{
			 job.setApprovalLastUpdatedByName(rs.getString("applastupdatedby"));
		 }catch(Exception e){
			 //do nothing
		 }
		 
		 return job;
	}

}
