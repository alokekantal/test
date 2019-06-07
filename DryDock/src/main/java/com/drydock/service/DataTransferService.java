package com.drydock.service;

import org.springframework.stereotype.Component;
import com.drydock.dto.BasicInfoDTO;

@Component
public interface DataTransferService {

	void excelGenerator(BasicInfoDTO info)throws Exception;;

}
