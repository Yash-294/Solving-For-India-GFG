package com.gfgsih.emrsys.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class PatientDTO {
	
	@Pattern(regexp="^\\d{12}$",message="Please enter valid details")
	String uid;
	@Pattern(regexp="[A-Z][a-z]*")
	String firstname;
	@Pattern(regexp="[A-Z][a-z]*")
	String lastname;
	@NotEmpty(message="Please fill this field")
	String gen;
	@NotEmpty(message="Please fill this field")
	String dob;
	String allergies;
	String medication;
	String surgeries;
	String vaccinations;
	
	//GETTER & SETTERS
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	public String getMedication() {
		return medication;
	}
	public void setMedication(String medication) {
		this.medication = medication;
	}
	public String getSurgeries() {
		return surgeries;
	}
	public void setSurgeries(String surgeries) {
		this.surgeries = surgeries;
	}
	public String getVaccinations() {
		return vaccinations;
	}
	public void setVaccinations(String vaccinations) {
		this.vaccinations = vaccinations;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstame(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	
	
}
