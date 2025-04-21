package org.tsa.hms_backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tsa.hms_backend.dtos.result.DoctorScheduleDto;
import org.tsa.hms_backend.entities.DoctorSchedule;
import org.tsa.hms_backend.services.DoctorScheduleService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/schedule")
@AllArgsConstructor
@CrossOrigin()
public class DoctorScheduleController {

    private final DoctorScheduleService doctorScheduleService;

    @GetMapping("/{doctor_id}")
    public ResponseEntity<List<DoctorSchedule>> getSchedule(@PathVariable("doctor_id") Long doctorId) {
        List<DoctorSchedule> schedule = doctorScheduleService.getDoctorSchedule(doctorId);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @GetMapping("available")
    public ResponseEntity<List<DoctorScheduleDto>> getScheduleAvailable(@RequestParam(required = false) String department,
                                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time,
                                                                        @RequestParam(required = false) UUID doctorId) {
        List<DoctorScheduleDto> availableSchedules = doctorScheduleService.getAvailableDoctorSchedules(department, date, time, doctorId);
        return new ResponseEntity<>(availableSchedules, HttpStatus.OK);
    }

    @PostMapping("/{doctor_id}/create/")
    public ResponseEntity<DoctorSchedule> createSchedule(@PathVariable("doctor_id") Long doctorId,
                                                         @RequestBody DoctorSchedule schedule) {
        DoctorSchedule savedSchedule = doctorScheduleService.save(schedule, doctorId);
        return new ResponseEntity<>(savedSchedule, HttpStatus.CREATED);
    }

    @PutMapping("{doctor_id}/update/{schedule_id}/")
    public ResponseEntity<DoctorSchedule> updateSchedule(@PathVariable("doctor_id") Long doctorId,
                                                         @PathVariable("schedule_id") Long scheduleId,
                                                         @RequestBody DoctorSchedule schedule) {
        DoctorSchedule updatedSchedule = doctorScheduleService.update(scheduleId, schedule, doctorId);
        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }

    @DeleteMapping("/{doctor_id}/delete/{schedule_id}/")
    public ResponseEntity<String> deleteSchedule(@PathVariable("doctor_id") Long doctorId,
                                                 @PathVariable("schedule_id") Long scheduleId) {
        doctorScheduleService.delete(scheduleId, doctorId);
        return new ResponseEntity<>("Schedule deleted successfully!", HttpStatus.OK);
    }
}
