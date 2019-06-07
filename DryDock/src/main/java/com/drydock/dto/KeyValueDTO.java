package com.drydock.dto;

import java.util.ArrayList;
import java.util.List;

public class KeyValueDTO {

	private String key;
	private List<Object> checkboxList=new ArrayList<>();
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<Object> getCheckboxList() {
		return checkboxList;
	}
	public void setCheckboxList(List<Object> checkboxList) {
		this.checkboxList = checkboxList;
	}
	
}
