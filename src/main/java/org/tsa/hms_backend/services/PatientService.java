package org.tsa.hms_backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientConverter patientConverter;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    public String signup(PatientsDto patientsDto) {
        log.info("Creating new patient: {}", patientsDto);
        patientsDto.getUser().setPassword(passwordEncoder.encode(patientsDto.getUser().getPassword()));
        Patients savedPatient = patientRepository.save(patientConverter.toEntity(patientsDto));
        return jwtService.generateToken(savedPatient.getUser());
    }

    public Patients findById(Long id) {
        log.info("Finding patient by id: {}", id);
        return patientRepository.findById(id).orElse(null);
    }

    public Patients update(PatientsDto patientsDto, Long id) {
        log.info("Updating patient with id: {}", id);
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
        log.info("Changing password for user with id: {}", id);
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

    public List<Doctors> getDoctors(Long id) {
        log.info("Getting doctors for user with id: {}", id);
        return patientRepository.getAssignedDoctorsByPatientId(id);
    }

    public List<Patients> getPatientsFilter( String patientName) {
        log.info("Getting patients filter for user with name: {}", patientName);
        return patientRepository.getPatientsFilter(patientName);
    }
}
