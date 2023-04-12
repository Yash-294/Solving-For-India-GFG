package com.gfgsih.emrsys.services;

import com.gfgsih.emrsys.dto.DoctorDTO;
import com.gfgsih.emrsys.entities.Doctor;

public interface DocService {
	void saveDoc(DoctorDTO docdto);
	Doctor findByUserid(String doc_id);
}
