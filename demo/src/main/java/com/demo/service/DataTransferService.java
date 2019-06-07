package com.demo.service;

import org.springframework.stereotype.Component;
import com.demo.dto.BasicInfoDTO;

@Component
public interface DataTransferService {

	void excelGenerator(BasicInfoDTO info)throws Exception;;

}
