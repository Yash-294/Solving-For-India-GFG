package com.gfgsih.emrsys.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfgsih.emrsys.dto.AppointmentDTO;
import com.gfgsih.emrsys.entities.Appointment;
import com.gfgsih.emrsys.repository.AppointmentRepository;
import com.gfgsih.emrsys.services.AppointmentService;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

	private AppointmentRepository apprepo;
	
	@Autowired
	public AppointmentServiceImplementation(AppointmentRepository apprepo) {
		super();
		this.apprepo = apprepo;
	}
	
	@Override
	public int getAppointCount(String docid) {
		return apprepo.countByDocid(docid);
	}
	
	@Override
	public void saveAppoint(AppointmentDTO appoint) {
		Appointment app=new Appointment();
		app.setDocid(appoint.getDocid());
		app.setPatid(appoint.getPatid());
		System.out.println(app.getId());
		apprepo.save(app);
	}
	 
}
