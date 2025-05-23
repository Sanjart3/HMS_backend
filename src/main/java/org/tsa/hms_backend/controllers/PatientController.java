package org.tsa.hms_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tsa.hms_backend.dtos.PasswordChangeDto;
import org.tsa.hms_backend.entities.Doctors;
import org.tsa.hms_backend.entities.Patients;
import org.tsa.hms_backend.services.PatientService;

import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
@CrossOrigin()
public class PatientController {

    private final PatientService patientService;

    @GetMapping("{id}/profile")
    public ResponseEntity<Patients> profile(@PathVariable Long id){
        Patients patient = patientService.findById(id);
        return ResponseEntity.ok().body(patient);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<Patients> update(@PathVariable Long id,
                                 @RequestBody Patients patientUpdate){
        Patients updatedPatient = patientService.update(patientUpdate, id);
        return ResponseEntity.ok().body(updatedPatient);
    }

    @PutMapping("{id}/change-password")
    public ResponseEntity<Boolean> changePassword(@PathVariable Long id,
                                         @RequestBody PasswordChangeDto passwordChangeDto){
        return ResponseEntity.ok().body(patientService.changePassword(passwordChangeDto, id));
    }

    @GetMapping("{id}/doctors")
    public ResponseEntity<List<Doctors>> listDoctors(@PathVariable Long id){
        List<Doctors> assignedDoctors = patientService.getDoctors(id);
        return ResponseEntity.ok(assignedDoctors);
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<Patients>> pagination(@RequestParam(required = false) String name){
        List<Patients> patients = patientService.getPatientsFilter(name);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        patientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
