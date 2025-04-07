package org.tsa.hms_backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tsa.hms_backend.entities.DoctorSchedule;
import org.tsa.hms_backend.services.DoctorScheduleService;

import java.util.List;

@RestController
@RequestMapping("{doctor_id}/schedule/")
@AllArgsConstructor
public class DoctorScheduleController {

    private final DoctorScheduleService doctorScheduleService;

    @GetMapping()
    public ResponseEntity<List<DoctorSchedule>> getSchedule(@PathVariable("doctor_id") Long doctorId) {
        List<DoctorSchedule> schedule = doctorScheduleService.getDoctorSchedule(doctorId);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @PostMapping("create/")
    public ResponseEntity<DoctorSchedule> createSchedule(@PathVariable("doctor_id") Long doctorId,
                                                         @RequestBody DoctorSchedule schedule) {
        DoctorSchedule savedSchedule = doctorScheduleService.save(schedule, doctorId);
        return new ResponseEntity<>(savedSchedule, HttpStatus.CREATED);
    }

    @PutMapping("{schedule_id}/update/")
    public ResponseEntity<DoctorSchedule> updateSchedule(@PathVariable("doctor_id") Long doctorId,
                                                         @PathVariable("schedule_id") Long scheduleId,
                                                         @RequestBody DoctorSchedule schedule) {
        DoctorSchedule updatedSchedule = doctorScheduleService.update(scheduleId, schedule, doctorId);
        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }

    @DeleteMapping("{schedule_id}/delete/")
    public ResponseEntity<String> deleteSchedule(@PathVariable("doctor_id") Long doctorId,
                                                 @PathVariable("schedule_id") Long scheduleId) {
        doctorScheduleService.delete(scheduleId, doctorId);
        return new ResponseEntity<>("Schedule deleted successfully!", HttpStatus.OK);
    }
}
