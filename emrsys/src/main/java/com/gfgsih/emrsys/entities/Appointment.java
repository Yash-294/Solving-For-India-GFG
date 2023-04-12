package com.gfgsih.emrsys.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="DOCS_PATS")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="APPID")
	Long id;
	@Column(name="DID")
	String docid;
	@Column(name="PID")
	String patid;
	
	//GETTER & SETTERS
	public Long getId() {
		return id;
	}
	public void setId(long id) {
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
}
