package org.tsa.hms_backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.tsa.hms_backend.dtos.AppointmentConfirmationDto;
import org.tsa.hms_backend.entities.Appointments;
import org.tsa.hms_backend.entities.Doctors;
import org.tsa.hms_backend.entities.Patients;
import org.tsa.hms_backend.entities.Users;
import org.tsa.hms_backend.exceptions.ResourceNotFoundException;
import org.tsa.hms_backend.exceptions.UnAuthorizedException;
import org.tsa.hms_backend.exceptions.UserNotFoundException;
import org.tsa.hms_backend.repositories.AppointmentRepository;
import org.tsa.hms_backend.repositories.DoctorRepository;
import org.tsa.hms_backend.repositories.PatientRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public Page<Appointments> getAppointments(int page, int size,
                                              LocalDate from, LocalDate to,
                                              Long patientId, Long doctorId, boolean isConfirmed) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), (size>0)?size:10);
        return appointmentRepository.findFilteredAppointments(from, to, patientId, doctorId, isConfirmed, pageable);
    }

    public Appointments createAppointment(Appointments appointment, Long patientId, Long scheduleId) {
        Patients patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
        Doctors doctor = doctorRepository.findByScheduleId(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with this id: "+scheduleId));
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        return appointmentRepository.save(appointment);
    }

    public Appointments confirmAppointment(Long doctorId, Long appointmentId, AppointmentConfirmationDto confirmationDto) {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Doctors doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with this id: "+doctorId));
        if (!(user.getEmail().equals(doctor.getUser().getEmail()))) {
            throw new UnAuthorizedException("Access denied!");
        }

        Appointments appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with this id: "+appointmentId));

        appointment.setIsConfirmed(confirmationDto.isConfirm());
        return appointmentRepository.save(appointment);
    }
}
