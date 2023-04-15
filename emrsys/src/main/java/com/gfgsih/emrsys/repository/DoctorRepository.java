package com.gfgsih.emrsys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gfgsih.emrsys.entities.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,String> {
	Doctor findByDocid(String doc_id);
	List<Doctor> findAll();
	@Query(value="SELECT d.doctor_name FROM Doctor d WHERE d.docid = :docid")
	String findDocnameByDocid(String docid);
	Doctor findFirstByDocid(String docid);
}
 