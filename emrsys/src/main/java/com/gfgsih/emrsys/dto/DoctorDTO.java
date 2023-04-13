package com.gfgsih.emrsys.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class DoctorDTO {

	@NotEmpty(message="Field Cannot be empty")
	String doc_id;
	@Pattern(regexp="^\\d{10}$",message="Enter valid phone number")
	String phone;
	String email_id;
	String license;
	String doc_pass;
	@NotEmpty(message="Field Cannot be empty")
	String speciality;
	@Pattern(regexp="[A-Z][a-z]*",message="Enter valid name")
	String firstname;
	@Pattern(regexp="[A-Z][a-z]*",message="Enter valid name")
	String lastname;
	
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
