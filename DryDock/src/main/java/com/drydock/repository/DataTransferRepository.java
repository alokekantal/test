package com.drydock.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.entity.ApplicationJob;
import com.drydock.entity.ApplicationJobMaterialDetails;
import com.drydock.entity.OrganizationJob;
import com.drydock.entity.OrganizationJobMaterialDetails;

@Component
public interface DataTransferRepository {

	List<Object> getApplicationJob(BasicInfoDTO info) throws Exception;

	List<Object> getApplicationJobMaterial(BasicInfoDTO info) throws Exception;

	List<Object> getOrganizationJob(BasicInfoDTO info) throws Exception;

	List<Object> getOrganizationJobMaterial(BasicInfoDTO info) throws Exception;

	List<Object> getShipComponentDetail(BasicInfoDTO info) throws Exception;;

}
