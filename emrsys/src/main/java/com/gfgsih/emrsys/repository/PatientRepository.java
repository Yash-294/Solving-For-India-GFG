package com.gfgsih.emrsys.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.gfgsih.emrsys.entities.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient,String> {
	Patient findByUid(String uid);
}
