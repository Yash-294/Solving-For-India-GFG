package com.gfgsih.emrsys.services;

import com.gfgsih.emrsys.dto.AppointmentDTO;

public interface AppointmentService {
	int getAppointCount(String docid);
	void saveAppoint(AppointmentDTO appoint);
}
