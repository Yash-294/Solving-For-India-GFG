package com.gfgsih.emrsys.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gfgsih.emrsys.entities.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient,String> {
	Patient findByUid(String uid);
	@Query("SELECT p.phone FROM Patient p WHERE p.uid = :uid")
	String findPhoneByUid(String uid);
	@Query("SELECT p.name FROM Patient p WHERE p.uid = :uid")
	String findNameByUid(String uid);
}

