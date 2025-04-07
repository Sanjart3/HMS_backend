package org.tsa.hms_backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.tsa.hms_backend.entities.DoctorSchedule;
import org.tsa.hms_backend.entities.Doctors;
import org.tsa.hms_backend.exceptions.CustomException;
import org.tsa.hms_backend.exceptions.ResourceNotFoundException;
import org.tsa.hms_backend.exceptions.UserNotFoundException;
import org.tsa.hms_backend.repositories.DoctorRepository;
import org.tsa.hms_backend.repositories.DoctorScheduleRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DoctorScheduleService {

    private final DoctorScheduleRepository doctorScheduleRepository;
    private final DoctorRepository doctorRepository;

    public List<DoctorSchedule> getDoctorSchedule(long id) {
        return doctorScheduleRepository.findAllByDoctorId(id).orElse(null);
    }

    public DoctorSchedule save(DoctorSchedule doctorSchedule, Long doctorId) {
        Doctors doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new UserNotFoundException(doctorId.toString()));
        doctorSchedule.setDoctor(doctor);
        return doctorScheduleRepository.save(doctorSchedule);
    }

    public DoctorSchedule update(long id, DoctorSchedule doctorSchedule, Long doctorId) {
        DoctorSchedule existingSchedule = doctorScheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id " + id));
        if (!Objects.equals(existingSchedule.getDoctor().getId(), doctorId)) {
            throw new CustomException("Doctor can change only his/her schedule", HttpStatus.BAD_REQUEST);
        }
        doctorSchedule.setId(id);
        doctorSchedule.setDoctor(existingSchedule.getDoctor());
        return doctorScheduleRepository.save(doctorSchedule);
    }

    public void delete(long id, Long doctorId) {
        DoctorSchedule existingSchedule = doctorScheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id " + id));
        if (!Objects.equals(existingSchedule.getDoctor().getId(), doctorId)) {
            throw new CustomException("Doctor can change only his/her schedule", HttpStatus.BAD_REQUEST);
        }
        doctorScheduleRepository.deleteById(id);
    }

}
