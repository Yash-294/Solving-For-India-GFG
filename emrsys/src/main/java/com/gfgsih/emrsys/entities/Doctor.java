package com.gfgsih.emrsys.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="DOCTOR_DETAILS")
public class Doctor {
	
	@Id
	String docid;
	@Column(name="PHONE_NO")
	String phone_no;
	@Column(name="EMAIL_ID")
	String email_id;
	@Column(name="LICENSE_NO")
	String license_no;
	@Column(name="DOC_PASS")
	String doc_pass;
	@Column(name="SPECIALITY")
	String speciality;
	@Column(name="DOCTOR_NAME")
	String doctor_name;
	
	// GETTER & SETTERS
	public String getDocid() {
		return docid;
	}
	public void setDocid(String docid) {
		this.docid = docid;
	}
	public String getPhone() {
		return phone_no;
	}
	public void setPhone(String phone) {
		this.phone_no = phone;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getLicense() {
		return license_no;
	}
	public void setLicense(String license) {
		this.license_no = license;
	}
	public String getDoc_pass() {
		return doc_pass;
	}
	public void setDoc_pass(String doc_pass) {
		this.doc_pass = doc_pass;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getName() {
		return doctor_name;
	}
	public void setName(String name) {
		this.doctor_name = name;
	}

}
