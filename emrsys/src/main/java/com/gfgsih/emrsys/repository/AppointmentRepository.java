package com.gfgsih.emrsys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gfgsih.emrsys.entities.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,String> {
	@Query(value="SELECT a.token FROM Appointment a WHERE a.docid =:docid ORDER BY a.token")
	List<Integer> MaxTokenByDocid(String docid);
	@Query(value="SELECT a FROM Appointment a WHERE a.docid = :did")
	List<Appointment> findAllPatsByDocid(String did);
	@Query(value="SELECT a FROM Appointment a WHERE a.patid = :patid AND a.docid = :docid ORDER BY a.id")
	List<Appointment> findAppointmentsByPatidDocid(String patid,String docid);
}
