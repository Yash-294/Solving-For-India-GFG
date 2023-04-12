package com.gfgsih.emrsys.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gfgsih.emrsys.entities.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,String> {
	int countByDocid(String docid);
}
