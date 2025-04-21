package org.tsa.hms_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tsa.hms_backend.dtos.DoctorFilterDto;
import org.tsa.hms_backend.dtos.DoctorUpdateDto;
import org.tsa.hms_backend.dtos.PasswordChangeDto;
import org.tsa.hms_backend.entities.Doctors;
import org.tsa.hms_backend.entities.Patients;
import org.tsa.hms_backend.services.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
@CrossOrigin()
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("add")
    public ResponseEntity<Doctors> addDoctor(@RequestBody Doctors doctorEntity) {
        Doctors doctor = doctorService.addDoctor(doctorEntity);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Doctors>> getAllDoctors() {
        List<Doctors> allDoctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(allDoctors, HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity<List<Doctors>> getDoctors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(required = false) String name,
                                                    @RequestParam(required = false) String gender,
                                                    @RequestParam(required = false) String department,
                                                    @RequestParam(required = false) String specialization,
                                                    @RequestParam(required = false, name = "appointment_cost") Integer appointmentCost) {
        DoctorFilterDto filter = new DoctorFilterDto(name, gender, department, specialization, appointmentCost);
        Page<Doctors> doctorsList = doctorService.getFilteredDoctors(page, size, filter);
        return new ResponseEntity<>(doctorsList.getContent(), HttpStatus.OK);
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<Doctors> getProfile(@PathVariable Long id) {
        Doctors doctor = doctorService.getDoctorById(id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Doctors> updateDoctor(@PathVariable Long id,
                                       @RequestBody DoctorUpdateDto dto) {
        Doctors doctor = doctorService.updateDoctor(id, dto);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @PutMapping("{id}/change-password")
    public ResponseEntity<Boolean> changePassword(@PathVariable Long id,
                                         @RequestBody PasswordChangeDto dto) {
        Boolean successfulUpdate = doctorService.changePassword(id, dto);
        return new ResponseEntity<>(successfulUpdate, HttpStatus.OK);
    }

    @GetMapping("{id}/patients")
    public ResponseEntity<List<Patients>> getPatients(@PathVariable Long id) {
        List<Patients> patients = doctorService.getPatientsByDoctor(id);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
