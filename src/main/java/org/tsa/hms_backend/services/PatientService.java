package org.tsa.hms_backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tsa.hms_backend.converters.PatientConverter;
import org.tsa.hms_backend.dtos.PatientsDto;
import org.tsa.hms_backend.entities.Patients;
import org.tsa.hms_backend.repositories.PatientRepository;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientConverter patientConverter;

    public Patients signup(PatientsDto patientsDto) {
        return patientRepository.save(patientConverter.toEntity(patientsDto));
    }

    public Patients signin(String username, String password) {
        return null;
    }


}
