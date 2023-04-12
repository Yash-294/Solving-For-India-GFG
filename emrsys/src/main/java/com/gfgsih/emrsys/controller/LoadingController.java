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
		PatientDTO patdto=new PatientDTO();
		mod.addAttribute("patient",patdto);
		return "dashboard";
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
		Patient existingPat=new Patient();
		existingPat=patserv.findByPatId(patdto.getUid());
		if(existingPat!=null) {
			return "redirect:/patient/register?fail";
		}
		patserv.savePat(patdto);
		return "redirect:/patient/register?success";
		
	}
	 
	@PostMapping("/dashboard")
	public String findPatient(@ModelAttribute("patient")PatientDTO patdto,Model mod) {
		Patient patFound=new Patient();
		patFound=patserv.findByPatId(patdto.getUid());
		if(patFound==null) {
			return "redirect:/dashboard?fail";
		}
		patdto=new PatientDTO();
		patdto=patserv.mapEntityToDTO(patFound);
		mod.addAttribute("patient", patdto); 
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
		appserv.saveAppoint(appointment);
		int count=appserv.getAppointCount(appointment.getDocid());
		String patnumber=patserv.findPhoneNumber(appointment.getPatid());
		Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"),System.getenv("TWILIO_AUTH_TOKEN"));
		Message.creator(new PhoneNumber("+91"+patnumber),new PhoneNumber("+15075967803"),"Your Appointment is fixed with Dr. "+docserv.findDocName(appointment.getDocid())+". Your Token Number is "+(count+1)).create();
		return "redirect:/?success";
		
	}

}
