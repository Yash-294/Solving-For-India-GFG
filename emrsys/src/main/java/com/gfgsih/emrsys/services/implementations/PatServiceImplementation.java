package com.gfgsih.emrsys.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfgsih.emrsys.dto.PatientDTO;
import com.gfgsih.emrsys.entities.Patient;
import com.gfgsih.emrsys.repository.PatientRepository;
import com.gfgsih.emrsys.services.PatService;

@Service
public class PatServiceImplementation implements PatService{

	private PatientRepository patrepo;
	
	@Autowired
	public PatServiceImplementation(PatientRepository patrepo) {
		this.patrepo=patrepo;
	}
	
	@Override
	public void savePat(PatientDTO patdto) {
		Patient pat=new Patient();
		pat.setGen(patdto.getGen());
		pat.setName(patdto.getFirstname()+" "+patdto.getLastname());
		pat.setUid(patdto.getUid());
		pat.setAllergies(patdto.getAllergies());
		pat.setDob(patdto.getDob());
		pat.setMedication(patdto.getMedication());
		pat.setSurgeries(patdto.getSurgeries());
		pat.setVaccinations(patdto.getVaccinations());
		patrepo.save(pat);
	}
	
	@Override
	public Patient findByPatId(String patid) {
		return patrepo.findByUid(patid);
	}
	
	@Override
	public PatientDTO mapEntityToDTO(Patient pat) {
		PatientDTO patdto=new PatientDTO();
		patdto.setAllergies(pat.getAllergies());
		patdto.setDob(pat.getDob());
		String arr[]=pat.getName().split(" ");
		patdto.setFirstame(arr[0]);
		patdto.setLastname(arr[1]);
		patdto.setGen(pat.getGen());
		patdto.setMedication(pat.getMedication());
		patdto.setSurgeries(pat.getSurgeries());
		patdto.setUid(pat.getUid());
		patdto.setVaccinations(pat.getVaccinations());
		return patdto;
	}
}
