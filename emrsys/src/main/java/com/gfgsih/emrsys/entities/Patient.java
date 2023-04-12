package com.gfgsih.emrsys.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="PATIENT_DETAILS")
public class Patient {

	@Id
	String uid;
	@Column(name="GENDER")
	String gen;
	@Column(name="PATIENT_NAME")
	String name;
	@Column(name="DOB")
	String dob;
	@Column(name="ALLERGIES")
	String allergies;
	@Column(name="CURR_MEDICATION")
	String medication;
	@Column(name="SURGERIES")
	String surgeries;
	@Column(name="IMMUNIZATION")
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
