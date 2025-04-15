package org.tsa.hms_backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.tsa.hms_backend.converters.DoctorScheduleConverter;
import org.tsa.hms_backend.dtos.result.DoctorScheduleDto;
import org.tsa.hms_backend.entities.Appointments;
import org.tsa.hms_backend.entities.DoctorSchedule;
import org.tsa.hms_backend.entities.Doctors;
import org.tsa.hms_backend.exceptions.CustomException;
import org.tsa.hms_backend.exceptions.ResourceNotFoundException;
import org.tsa.hms_backend.exceptions.UserNotFoundException;
import org.tsa.hms_backend.repositories.AppointmentRepository;
import org.tsa.hms_backend.repositories.DoctorRepository;
import org.tsa.hms_backend.repositories.DoctorScheduleRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DoctorScheduleService {

    private final DoctorScheduleRepository doctorScheduleRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorScheduleConverter doctorScheduleConverter;

    public List<DoctorSchedule> getDoctorSchedule(long id) {
        return doctorScheduleRepository.findAllByDoctorId(id).orElse(null);
    }

    public List<DoctorScheduleDto> getAvailableDoctorSchedules(String department,
                                                            LocalDate date, LocalTime time,
                                                            UUID doctorId) {
        if (date == null) date = LocalDate.now();
        List<DoctorSchedule> doctorSchedules = doctorScheduleRepository.findFilteredSchedule(department, doctorId);

        List<DoctorScheduleDto> availableDoctorSchedules = new ArrayList<>();
        for (DoctorSchedule doctorSchedule : doctorSchedules) {
            LocalTime beginning = doctorSchedule.getStartTime();
            while (beginning.isBefore(doctorSchedule.getEndTime())) {
                Optional<Appointments> appointment = appointmentRepository.findByDoctorIdAndDateAndStartTime(doctorSchedule.getDoctor().getId(), date, time);
                if (appointment.isEmpty()){
                    DoctorScheduleDto dto = doctorScheduleConverter.toDto(doctorSchedule, beginning, beginning.plusMinutes(30));
                    availableDoctorSchedules.add(dto);
                }
                beginning = beginning.plusMinutes(30);
            }

            for (LocalTime beginnig = doctorSchedule.getStartTime(); beginning.isBefore(doctorSchedule.getEndTime()); beginnig = beginning.plusMinutes(30)) {
                Optional<Appointments> appointment = appointmentRepository.findByDoctorIdAndDateAndStartTime(doctorSchedule.getDoctor().getId(), date, time);
                if (appointment.isEmpty()){
                    DoctorScheduleDto dto = doctorScheduleConverter.toDto(doctorSchedule, beginning, beginning.plusMinutes(30));
                    availableDoctorSchedules.add(dto);
                }
                beginning = beginning.plusMinutes(30);
            }
        }

        return availableDoctorSchedules;
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
