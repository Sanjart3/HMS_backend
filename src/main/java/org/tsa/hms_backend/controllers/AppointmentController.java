package org.tsa.hms_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tsa.hms_backend.dtos.AppointmentConfirmationDto;
import org.tsa.hms_backend.dtos.result.AppointmentDto;
import org.tsa.hms_backend.entities.Appointments;
import org.tsa.hms_backend.services.AppointmentService;

import java.time.LocalDate;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/get")
    public ResponseEntity<Page<AppointmentDto>> getAppointments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int limit,
                                                              @RequestParam(required = false) LocalDate fromDate, @RequestParam(required = false)LocalDate toDate,
                                                              @RequestParam(required = false) Long patientId, @RequestParam(required = false) Long doctorId,
                                                              @RequestParam(defaultValue = "true") boolean confirmed) {
        Page<AppointmentDto> appointments = appointmentService.getAppointments(page, limit, fromDate, toDate, patientId, doctorId, confirmed);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping("{patientId}/appointment/{scheduleId}")
    public ResponseEntity<AppointmentDto> createAppointment(@PathVariable Long patientId, @PathVariable Long scheduleId,
                                                          @RequestBody Appointments appointment) {
        AppointmentDto createdAppointment = appointmentService.createAppointment(appointment, patientId, scheduleId);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    @PutMapping("/{doctor_id}/appointment/{appointment_id}")
    public ResponseEntity<AppointmentDto> confirmAppointment(@PathVariable("doctor_id") Long doctorId, @PathVariable("appointment_id") Long appointmentId,
                                                   @RequestBody AppointmentConfirmationDto confirmationDto) {
        AppointmentDto appointment = appointmentService.confirmAppointment(appointmentId, doctorId, confirmationDto);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }
}
