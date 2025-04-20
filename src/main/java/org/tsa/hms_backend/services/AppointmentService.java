package org.tsa.hms_backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.tsa.hms_backend.converters.AppointmentConverter;
import org.tsa.hms_backend.dtos.AppointmentConfirmationDto;
import org.tsa.hms_backend.dtos.result.AppointmentDto;
import org.tsa.hms_backend.entities.Appointments;
import org.tsa.hms_backend.entities.Doctors;
import org.tsa.hms_backend.entities.Patients;
import org.tsa.hms_backend.entities.Users;
import org.tsa.hms_backend.exceptions.ResourceNotFoundException;
import org.tsa.hms_backend.exceptions.UnAuthorizedException;
import org.tsa.hms_backend.repositories.AppointmentRepository;
import org.tsa.hms_backend.repositories.DoctorRepository;
import org.tsa.hms_backend.repositories.PatientRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentConverter appointmentConverter;

    public Page<AppointmentDto> getAppointments(int page, int size,
                                              LocalDate from, LocalDate to,
                                              Long patientId, Long doctorId, boolean isConfirmed) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), (size>0)?size:10);
        Page<Appointments> appointments = appointmentRepository.findFilteredAppointments(from, to, patientId, doctorId, isConfirmed, pageable);
        List<AppointmentDto> appointmentDtos = appointmentConverter.getAppointmentDtoList(appointments.getContent());
    return new PageImpl<>(appointmentDtos, pageable, appointments.getTotalElements());
    }

    public AppointmentDto createAppointment(Appointments appointment, Long patientId, Long scheduleId) {
        Patients patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
        Doctors doctor = doctorRepository.findByScheduleId(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with this id: "+scheduleId));
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        Appointments savedAppointment = appointmentRepository.save(appointment);
        return appointmentConverter.getAppointmentDto(savedAppointment);
    }

    public AppointmentDto confirmAppointment(Long doctorId, Long appointmentId, AppointmentConfirmationDto confirmationDto) {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Doctors doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with this id: "+doctorId));
        if (!(user.getEmail().equals(doctor.getUser().getEmail()))) {
            throw new UnAuthorizedException("Access denied!");
        }

        Appointments appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with this id: "+appointmentId));

        appointment.setIsConfirmed(confirmationDto.isConfirm());
        Appointments savedAppointment = appointmentRepository.save(appointment);
        return appointmentConverter.getAppointmentDto(savedAppointment);
    }
}
