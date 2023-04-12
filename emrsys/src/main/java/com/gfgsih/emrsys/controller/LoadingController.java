package com.gfgsih.emrsys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gfgsih.emrsys.dto.DoctorDTO;
import com.gfgsih.emrsys.dto.PatientDTO;
import com.gfgsih.emrsys.entities.Doctor;
import com.gfgsih.emrsys.entities.Patient;
import com.gfgsih.emrsys.services.DocService;
import com.gfgsih.emrsys.services.PatService;
import jakarta.validation.Valid;

@Controller
public class LoadingController {
	 
	private DocService docserv;
	private PatService patserv;
	
	@Autowired
	public LoadingController(DocService docserv,PatService patserv) {
		this.docserv=docserv;
		this.patserv=patserv;
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

}
