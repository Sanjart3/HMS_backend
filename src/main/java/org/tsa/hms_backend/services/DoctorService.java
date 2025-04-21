package org.tsa.hms_backend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tsa.hms_backend.converters.DoctorConverter;
import org.tsa.hms_backend.dtos.DoctorFilterDto;
import org.tsa.hms_backend.dtos.DoctorUpdateDto;
import org.tsa.hms_backend.dtos.PasswordChangeDto;
import org.tsa.hms_backend.entities.Analysis;
import org.tsa.hms_backend.entities.Doctors;
import org.tsa.hms_backend.entities.Patients;
import org.tsa.hms_backend.repositories.AnalysisRepository;
import org.tsa.hms_backend.repositories.DoctorRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final AnalysisRepository analysisRepository;
    private final DoctorConverter doctorConverter;
    private final PasswordEncoder passwordEncoder;

    public Doctors addDoctor(Doctors doctors) {
        log.info("Adding new doctor {}", doctorConverter.toDto(doctors));
        String password = passwordEncoder.encode(doctors.getUser().getPassword());
        doctors.getUser().setPassword(password);
        return doctorRepository.save(doctors);
    }

    public List<Doctors> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Page<Doctors> getFilteredDoctors(Integer page, Integer limit, DoctorFilterDto filter) {
        Pageable pageable = PageRequest.of(page, (limit>0)?limit:10);
        log.info("Getting doctors from page");
        return doctorRepository.findFilteredDoctors(filter.getName(), filter.getGender(), filter.getDepartment(), filter.getSpecialization(), filter.getAppointmentCost(), pageable);
    }

    public Doctors getDoctorById(Long id) {
        log.info("Getting doctor {}", id);
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctors updateDoctor(Long id, DoctorUpdateDto dto) {
        log.info("Updating doctor {}", id);
        Doctors existingDoctor = doctorRepository.findById(id).orElse(null);

        existingDoctor = prepareForUpdate(existingDoctor, dto);
        return doctorRepository.save(existingDoctor);
    }

    public Boolean changePassword(Long id, PasswordChangeDto dto) {
        Doctors existingDoctor = doctorRepository.findById(id).orElse(null);
        if (existingDoctor.getUser().getEmail().equals(dto.getEmail())) {
            existingDoctor.getUser().setPassword(dto.getNewPassword());
            doctorRepository.save(existingDoctor);
            return true;
        }
        return false;
    }

    public void deleteDoctor(Long id) {
        log.info("Deleting doctor {}", id);
        Doctors existingDoctor = doctorRepository.findById(id).orElse(null);
        List<Analysis> analysisList = analysisRepository.findAllByDoctor(existingDoctor);
        for (Analysis analysis : analysisList) {
            analysis.setDoctor(null);
            analysisRepository.save(analysis);
        }
        doctorRepository.delete(existingDoctor);
    }

    public List<Patients> getPatientsByDoctor(Long id) {
        return doctorRepository.getPatientsByDoctorId(id);
    }

    public Doctors prepareForUpdate(Doctors existingDoctor, DoctorUpdateDto dto) {
        log.info("Updating doctor {}", dto);
        if (dto.getName() != null) {
            existingDoctor.getUser().setFirstName(dto.getName());
        }
        if (dto.getGender() != null) {
            existingDoctor.getUser().setGender(dto.getGender());
        }
        if (dto.getPhoneNumber()!=null) {
            existingDoctor.getUser().setPhone(dto.getPhoneNumber());
        }
        if (dto.getDateOfBirth() != null) {
            existingDoctor.getUser().setDateOfBirth(dto.getDateOfBirth());
        }
        if (dto.getSpecialization() != null) {
            existingDoctor.setSpecialization(dto.getSpecialization());
        }
        if (dto.getRoom() != null) {
            existingDoctor.setRoom(dto.getRoom());
        }

        return existingDoctor;
    }
}
