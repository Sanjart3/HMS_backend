package org.tsa.hms_backend.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tsa.hms_backend.dtos.DoctorFilterDto;
import org.tsa.hms_backend.dtos.DoctorUpdateDto;
import org.tsa.hms_backend.dtos.PasswordChangeDto;
import org.tsa.hms_backend.entities.Doctors;
import org.tsa.hms_backend.entities.Patients;
import org.tsa.hms_backend.repositories.DoctorRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public List<Doctors> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctors> getFilteredDoctors(Integer page, Integer limit, DoctorFilterDto filter) {
        Pageable pageable = PageRequest.of(page, (limit>0)?limit:10);
        return doctorRepository.findFilteredDoctors(filter.getName(), filter.getGender(), filter.getDepartment(), filter.getSpecialization(), filter.getAppointmentCost(), pageable);
    }

    public Doctors getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctors updateDoctor(Long id, DoctorUpdateDto dto) {
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
        Doctors existingDoctor = doctorRepository.findById(id).orElse(null);
        doctorRepository.delete(existingDoctor);
    }

    public List<Patients> getPatientsByDoctor(Long id) {
        return doctorRepository.findPatientsByDoctorId(id);
    }

    public Doctors prepareForUpdate(Doctors existingDoctor, DoctorUpdateDto dto) {
        if (dto.getFirstName() != null) {
            existingDoctor.getUser().setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            existingDoctor.getUser().setLastName(dto.getLastName());
        }
        if (dto.getGender() != null) {
            existingDoctor.getUser().setGender(dto.getGender());
        }
        if (dto.getEmail() != null) {
            existingDoctor.getUser().setEmail(dto.getEmail());
        }
        if (dto.getPhone()!=null) {
            existingDoctor.getUser().setPhone(dto.getPhone());
        }
        if (dto.getDateOfBirth() != null) {
            existingDoctor.getUser().setDateOfBirth(dto.getDateOfBirth());
        }

        return existingDoctor;
    }
}
