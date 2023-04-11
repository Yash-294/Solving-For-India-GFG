package com.gfgsih.emrsys.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gfgsih.emrsys.entities.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,String> {
	Doctor findByDocid(String doc_id);
}
 