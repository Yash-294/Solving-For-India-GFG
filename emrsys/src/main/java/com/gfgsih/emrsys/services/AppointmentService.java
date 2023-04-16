package com.gfgsih.emrsys.services;

import java.util.List;

import com.gfgsih.emrsys.dto.AppointmentDTO;

public interface AppointmentService {
	List<Integer> getAppointCount(String docid);
	void saveAppoint(AppointmentDTO appoint);
	List<AppointmentDTO> getPatsByDocid(String docid);
	void deletePatientAppointment(String patid,String docid);
}
