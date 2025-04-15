package org.tsa.hms_backend.converters;

import org.springframework.stereotype.Component;
import org.tsa.hms_backend.dtos.result.AppointmentDto;
import org.tsa.hms_backend.entities.Appointments;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentConverter {
    public List<AppointmentDto> getAppointmentDtoList(List<Appointments> appointments) {
        return appointments.stream()
                .map(this::getAppointmentDto)
                .collect(Collectors.toList());
    }

    public AppointmentDto getAppointmentDto(Appointments appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setDate(appointment.getDate());
        appointmentDto.setStartTime(appointment.getStartTime());
        appointmentDto.setEndTime(appointment.getEndTime());
        appointmentDto.setDoctorFullName(appointment.getDoctor().getUser().getFirstName() + " " + appointment.getDoctor().getUser().getLastName());
        appointmentDto.setPatientFullName(appointment.getPatient().getUser().getFirstName() + " " + appointment.getPatient().getUser().getLastName());
        appointmentDto.setConfirmed(appointment.getIsConfirmed());
        return appointmentDto;
    }
}
