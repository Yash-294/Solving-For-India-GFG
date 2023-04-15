package com.gfgsih.emrsys.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfgsih.emrsys.dto.AppointmentDTO;
import com.gfgsih.emrsys.entities.Appointment;
import com.gfgsih.emrsys.repository.AppointmentRepository;
import com.gfgsih.emrsys.repository.PatientRepository;
import com.gfgsih.emrsys.services.AppointmentService;

import jakarta.transaction.Transactional;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

	private AppointmentRepository apprepo;
	private PatientRepository patrepo;
	
	@Autowired
	public AppointmentServiceImplementation(AppointmentRepository apprepo,PatientRepository patrepo) {
		super();
		this.apprepo = apprepo;
		this.patrepo = patrepo;
	}
	
	@Override
	public List<Integer> getAppointCount(String docid) {
		return apprepo.MaxTokenByDocid(docid);
	}
	
	@Override
	public void saveAppoint(AppointmentDTO appoint) {
		Appointment app=new Appointment();
		app.setDocid(appoint.getDocid());
		app.setPatid(appoint.getPatid());
		app.setToken(appoint.getToken());
		apprepo.save(app);
	}
	
	@Override
	public List<AppointmentDTO> getPatsByDocid(String did){
		List<Appointment> pats=apprepo.findAllPatsByDocid(did);
		return pats.stream().map((pat)->mapAppToAppDTO(pat)).collect(Collectors.toList());
	}
	
	private AppointmentDTO mapAppToAppDTO(Appointment app) {
		AppointmentDTO appdto=new AppointmentDTO();
		appdto.setDocid(app.getDocid());
		appdto.setPatid(app.getPatid());
		appdto.setToken(app.getToken());
		appdto.setPatname(patrepo.findNameByUid(app.getPatid()));
		return appdto;
	}
	
	@Transactional
	public void deletePatientAppointment(String patid,String docid) {
		List<Appointment> pats=apprepo.findAppointmentsByPatidDocid(patid, docid);
		apprepo.delete(pats.get(0));
		
	}
	 
}
