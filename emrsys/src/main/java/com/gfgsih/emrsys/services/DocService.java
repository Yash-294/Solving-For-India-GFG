package com.gfgsih.emrsys.services;

import java.util.List;

import com.gfgsih.emrsys.dto.DoctorDTO;
import com.gfgsih.emrsys.entities.Doctor;

public interface DocService {
	void saveDoc(DoctorDTO docdto);
	Doctor findByUserid(String doc_id);
	List<DoctorDTO> findAllDocs();
	String findDocName(String docid);
}
