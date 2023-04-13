package com.gfgsih.emrsys.dto;

import jakarta.validation.constraints.NotEmpty;

public class AppointmentDTO {
	
	Long id;
	@NotEmpty(message="This Field Cannot be empty")
	String docid;
	@NotEmpty(message="This Field Cannot be empty")
	String patid;
	String patname;
	int token;
	
	//GETTER & SETTERS
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public String getDocid() {
		return docid;
	}
	public void setDocid(String docid) {
		this.docid = docid;
	}
	public String getPatid() {
		return patid;
	}
	public void setPatid(String patid) {
		this.patid = patid;
	}
	public String getPatname() {
		return patname;
	}
	public void setPatname(String patname) {
		this.patname = patname;
	}
	public int getToken() {
		return token;
	}
	public void setToken(int token) {
		this.token=token;
	}

}
