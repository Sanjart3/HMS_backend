package org.tsa.hms_backend.services;

import org.springframework.stereotype.Service;
import org.tsa.hms_backend.entities.Patients;
import org.tsa.hms_backend.repositories.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patients signup(Patients patient) {
        return patientRepository.save(patient);
    }

    public Patients signin(String username, String password) {
        return null;
    }


}
