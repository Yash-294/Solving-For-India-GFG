package com.gfgsih.emrsys.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gfgsih.emrsys.dto.DoctorDTO;
import com.gfgsih.emrsys.entities.Doctor;
import com.gfgsih.emrsys.repository.DoctorRepository;
import com.gfgsih.emrsys.services.DocService;

@Service
public class DocServiceImplementation implements DocService{
	
	private DoctorRepository docrepo;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public DocServiceImplementation(DoctorRepository docrepo,PasswordEncoder passwordEncoder) {
		super();
		this.docrepo = docrepo;
		this.passwordEncoder=passwordEncoder;
	}
	
	@Override
	public void saveDoc(DoctorDTO docdto) {
		Doctor doc=new Doctor();
		doc.setDocid(docdto.getDoc_id());
		doc.setDoc_pass(passwordEncoder.encode(docdto.getDoc_pass()));
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
	
	@Override
	public List<DoctorDTO> findAllDocs(){
		List<Doctor> docs=docrepo.findAll();
		return docs.stream().map((doc)->mapDocToDocDTO(doc)).collect(Collectors.toList());
	}
	
	private DoctorDTO mapDocToDocDTO(Doctor doc) {
		DoctorDTO docdto=new DoctorDTO();
		docdto.setDoc_id(doc.getDocid());
		docdto.setDoc_pass(doc.getDoc_pass());
		docdto.setEmail_id(doc.getEmail_id());
		String arr[]=doc.getName().split(" ");
		docdto.setFirstname(arr[0]);
		docdto.setLastname(arr[1]);
		docdto.setLicense(doc.getLicense());
		docdto.setPhone(doc.getPhone());
		docdto.setSpeciality(doc.getSpeciality());
		return docdto;
	}
	
	@Override
	public String findDocName(String docid) {
		return docrepo.findDocnameByDocid(docid);
	}

}
