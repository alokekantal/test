package com.drydock.dto;
import java.util.Date;

import com.drydock.entity.ApplicationComponent;
import com.drydock.entity.OrganizationComponent;

public class ComponentDTO {
	private long id;
	private String code;
	private String description;
	private String parentcode;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getParentcode() {
		return parentcode;
	}
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}
	
	
	public ComponentDTO(ApplicationComponent appComponent){
		this.id=appComponent.getId();
		this.code=appComponent.getCode();
		this.description=appComponent.getDescription();
		this.parentcode=appComponent.getParentcode();
	}

	public ComponentDTO(OrganizationComponent orgComponent){
		this.id=orgComponent.getId();
		this.code=orgComponent.getCode();
		this.description=orgComponent.getDescription();
		this.parentcode=orgComponent.getParentcode();
	}

}
