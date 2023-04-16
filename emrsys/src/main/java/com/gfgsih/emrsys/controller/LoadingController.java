package com.gfgsih.emrsys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gfgsih.emrsys.dto.AppointmentDTO;
import com.gfgsih.emrsys.dto.DoctorDTO;
import com.gfgsih.emrsys.dto.PatientDTO;
import com.gfgsih.emrsys.entities.Doctor;
import com.gfgsih.emrsys.entities.Patient;
import com.gfgsih.emrsys.security.SecurityUtil;
import com.gfgsih.emrsys.services.AppointmentService;
import com.gfgsih.emrsys.services.DocService;
import com.gfgsih.emrsys.services.PatService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.validation.Valid;

@Controller
public class LoadingController {
	 
	private DocService docserv;
	private PatService patserv;
	private AppointmentService appserv;
	
	@Autowired
	public LoadingController(DocService docserv,PatService patserv,AppointmentService appserv) {
		this.docserv=docserv;
		this.patserv=patserv;
		this.appserv=appserv;
	}
	
	@GetMapping("/login")
	public String loadLogin() {
		return "login";
	}
	
	@GetMapping("/register")
	public String loadRegister(Model mod) {
		DoctorDTO docdto=new DoctorDTO();
		mod.addAttribute("doctor", docdto);
		return "register";
	}
	
	@GetMapping("/patient/register")
	public String loadPatientRegister(Model mod) {
		PatientDTO patdto=new PatientDTO();
		mod.addAttribute("patient", patdto);
		return "patientregister";
	}	
	
	@GetMapping("/dashboard")
	public String loadDashboard(Model mod) {
		List<AppointmentDTO> pats=new ArrayList<AppointmentDTO>();
		pats=appserv.getPatsByDocid(SecurityUtil.getSessionUser());
		mod.addAttribute("patients",pats);
		return "dashboard";
	}
	
	@GetMapping("/dashboard/id")
	public String loadPatientDashboard(Model mod) {
		PatientDTO pat=new PatientDTO();
		mod.addAttribute("patient",pat);
		return "patdash";
	}
	 
	@GetMapping("/")
	public String loadAppointment(Model mod) {
		AppointmentDTO appointment=new AppointmentDTO();
		List<DoctorDTO> doclist=new ArrayList<DoctorDTO>();
		doclist=docserv.findAllDocs();
		mod.addAttribute("doclist",doclist);
		mod.addAttribute("appoint", appointment);
		return "appointment";
	}
	 
	@PostMapping("/register")
	public String docRegister(@Valid@ModelAttribute("doctor")DoctorDTO docdto,BindingResult bindingresults,Model mod) {
		if(bindingresults.hasErrors()) {
			mod.addAttribute("doctor",docdto);
			return "register";
		}
		if(docdto.getSpeciality()==null) {
			return "register";
		}
		Doctor existingDoc=new Doctor();
		existingDoc=docserv.findByUserid(docdto.getDoc_id());
		if(existingDoc!=null) {
			return "redirect:/register?fail"; 
		}
		docserv.saveDoc(docdto);
		return "redirect:/register?success";
	} 
	
	@PostMapping("/patient/register")
	public String patRegister(@Valid@ModelAttribute("patient")PatientDTO patdto,BindingResult bindingresults,Model mod) {
		if(bindingresults.hasErrors()) {
			mod.addAttribute("patient",patdto);
			return "patientregister";
		}
		if(patdto.getGen()=="null") {
			return "patientregister";
		}
		Patient existingPat=new Patient();
		existingPat=patserv.findByPatId(patdto.getUid());
		if(existingPat!=null) {
			return "redirect:/patient/register?fail";
		}
		patserv.savePat(patdto);
		return "redirect:/patient/register?success";
		
	}
	 
	@PostMapping("/dashboard")
	public String findPatient(@ModelAttribute("pat")AppointmentDTO pat,Model mod) {
		appserv.deletePatientAppointment(pat.getPatid(),SecurityUtil.getSessionUser());
		List<AppointmentDTO> pats=new ArrayList<AppointmentDTO>();
		pats=appserv.getPatsByDocid(SecurityUtil.getSessionUser());
		mod.addAttribute("patients",pats);		
		return "dashboard";
	} 
	
	@PostMapping("/")
	public String appointDoc(@Valid@ModelAttribute("appoint")AppointmentDTO appointment,BindingResult bindingresults,Model mod) {
		if(bindingresults.hasErrors()) {
			mod.addAttribute("appoint",appointment);
			return "appointment";
		}
		Patient patFound=new Patient();
		patFound=patserv.findByPatId(appointment.getPatid());
		if(patFound==null) {
			return "redirect:/?fail";
		}
		List<Integer> tokenlist=appserv.getAppointCount(appointment.getDocid());
		int count;
		if(tokenlist.size()==0) {
			count=0;
		}
		else {
			count=tokenlist.get(tokenlist.size()-1);
		}
		appointment.setToken(count+1);
		appserv.saveAppoint(appointment);
		String param="success";
		try {
			String patnumber=patserv.findPhoneNumber(appointment.getPatid());
			Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"),System.getenv("TWILIO_AUTH_TOKEN"));
			Message.creator(new PhoneNumber("+91"+patnumber),new PhoneNumber("+15075967803"),"Your Appointment is fixed with Dr. "+docserv.findDocName(appointment.getDocid())+". Your Token Number is "+(count+1)).create();
		}
		catch(Exception e) {
			param="twilio&success";
		}
		return "redirect:/?"+param; 	
	}
	 
	@PostMapping("/dashboard/id")
	public String patDetails(@ModelAttribute("pat")AppointmentDTO pat,Model mod) {
			PatientDTO patient=new PatientDTO();
			patient=patserv.mapEntityToDTO(patserv.findByPatId(pat.getPatid()));
			mod.addAttribute("patient", patient);
			return "patdash";
	} 

}
