package com.gfgsih.emrsys.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfgsih.emrsys.dto.DoctorDTO;
import com.gfgsih.emrsys.entities.Doctor;
import com.gfgsih.emrsys.repository.DoctorRepository;
import com.gfgsih.emrsys.services.DocService;

@Service
public class DocServiceImplementation implements DocService{
	
	private DoctorRepository docrepo;

	@Autowired
	public DocServiceImplementation(DoctorRepository docrepo) {
		super();
		this.docrepo = docrepo;
	}
	@Override
	public void saveDoc(DoctorDTO docdto) {
		Doctor doc=new Doctor();
		doc.setDocid(docdto.getDoc_id());
		doc.setDoc_pass(docdto.getDoc_pass());
		doc.setEmail_id(docdto.getEmail_id());
		doc.setLicense(docdto.getLicense());
		doc.setName(docdto.getFirstname()+" "+docdto.getLastname());
		doc.setPhone(docdto.getPhone());
		doc.setSpeciality(docdto.getSpeciality());
		docrepo.save(doc);
	} 
	
	@Override
	public Doctor findByUserid(String doc_id) {
		return docrepo.findByDocid(doc_id);
	}

}
