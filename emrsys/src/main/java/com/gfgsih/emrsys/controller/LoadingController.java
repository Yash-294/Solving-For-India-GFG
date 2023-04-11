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
import com.gfgsih.emrsys.services.DocService;

import jakarta.validation.Valid;

@Controller
public class LoadingController {
	 
	private DocService docserv;
	
	@Autowired
	public LoadingController(DocService docserv) {
		this.docserv=docserv;
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
	public String loadPatientRegister() {
		return "patientregister";
	}	
	
	@PostMapping("/register")
	public String docRegister(@Valid@ModelAttribute("doctor")DoctorDTO docdto,BindingResult bindingresults,Model mod) {
		Doctor existingDoc=new Doctor();
		existingDoc=docserv.findByUserid(docdto.getDoc_id());
		if(existingDoc!=null) {
			return "redirect:/register?fail";
		}
		if(bindingresults.hasErrors()) {
			mod.addAttribute("doctor",docdto);
			return "register";
		}
		docserv.saveDoc(docdto);
		return "redirect:/register?success";
	} 

}
