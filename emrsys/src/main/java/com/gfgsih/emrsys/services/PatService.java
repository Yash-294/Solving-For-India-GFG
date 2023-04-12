package com.gfgsih.emrsys.services;

import com.gfgsih.emrsys.dto.PatientDTO;
import com.gfgsih.emrsys.entities.Patient;

public interface PatService {
	void savePat(PatientDTO patdto);
	Patient findByPatId(String patid);
	PatientDTO mapEntityToDTO(Patient pat);
}
