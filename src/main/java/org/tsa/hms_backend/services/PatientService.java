package org.tsa.hms_backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tsa.hms_backend.config.JWTService;
import org.tsa.hms_backend.converters.PatientConverter;
import org.tsa.hms_backend.dtos.PasswordChangeDto;
import org.tsa.hms_backend.dtos.PatientsDto;
import org.tsa.hms_backend.entities.Doctors;
import org.tsa.hms_backend.entities.Patients;
import org.tsa.hms_backend.repositories.PatientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientConverter patientConverter;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    public String signup(PatientsDto patientsDto) {
        patientsDto.getUser().setPassword(passwordEncoder.encode(patientsDto.getUser().getPassword()));
        Patients savedPatient = patientRepository.save(patientConverter.toEntity(patientsDto));
        return jwtService.generateToken(savedPatient.getUser());
    }

    public Patients findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patients update(PatientsDto patientsDto, Long id) {
        Patients existingPatient = patientRepository.findById(id).orElse(null);
        if (existingPatient == null) {
            return null;
        }
        Patients updatingPatient = prepareForUpdate(existingPatient, patientsDto);
        return patientRepository.save(updatingPatient);
    }

    public Patients prepareForUpdate(Patients patient, PatientsDto patientsDto) {
        patient.setAddress(patientsDto.getAddress());
        patient.setBloodGroup(patientsDto.getBloodGroup());
        Long userId = patient.getUser().getId();
        patient.setUser(patientsDto.getUser());
        patient.getUser().setId(userId);
        return patient;
    }

    public Boolean changePassword(PasswordChangeDto passwordChangeDto, Long id) {
        Patients existingPatient = patientRepository.findById(id).orElse(null);
        if (existingPatient == null) {
            return false;
        }

        if (existingPatient.getUser().getEmail().equals(passwordChangeDto.getEmail())) {
            existingPatient.getUser().setPassword(passwordEncoder.encode(passwordChangeDto.getNewPassword()));
            patientRepository.save(existingPatient);
            return true;
        }
        return false;
    }

    public List<Doctors> getDoctors() {
        return List.of();
    }
}
