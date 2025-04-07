package org.tsa.hms_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tsa.hms_backend.dtos.AppointmentConfirmationDto;
import org.tsa.hms_backend.entities.Appointments;
import org.tsa.hms_backend.services.AppointmentService;

import java.time.LocalDate;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/get")
    public ResponseEntity<Page<Appointments>> getAppointments(@RequestParam("page") int page, @RequestParam("limit") int limit,
                                                              @RequestParam("fromDate") LocalDate fromDate, @RequestParam("toDate")LocalDate toDate,
                                                              @RequestParam("patient_id") Long patientId, @RequestParam("doctor_id") Long doctorId,
                                                              @RequestParam("id_confirmed") boolean confirmed) {
        Page<Appointments> appointments = appointmentService.getAppointments(page, limit, fromDate, toDate, patientId, doctorId, confirmed);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping("{patientId}/appointment/{scheduleId}")
    public ResponseEntity<Appointments> createAppointment(@PathVariable Long patientId, @PathVariable Long scheduleId,
                                                          @RequestBody Appointments appointment) {
        Appointments createdAppointment = appointmentService.createAppointment(appointment, patientId, scheduleId);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    @PutMapping("/{doctor_id}/appointment/{appointment_id}")
    public ResponseEntity<Appointments> confirmAppointment(@PathVariable("doctor_id") Long doctorId, @PathVariable("appointment_id") Long appointmentId,
                                                   @RequestBody AppointmentConfirmationDto confirmationDto) {
        Appointments appointment = appointmentService.confirmAppointment(appointmentId, doctorId, confirmationDto);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }
}
