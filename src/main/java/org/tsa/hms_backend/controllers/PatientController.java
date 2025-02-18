package org.tsa.hms_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tsa.hms_backend.dtos.PasswordChangeDto;
import org.tsa.hms_backend.dtos.PatientFilterDto;
import org.tsa.hms_backend.dtos.PatientsDto;
import org.tsa.hms_backend.entities.Patients;
import org.tsa.hms_backend.services.PatientService;

@Controller
@RequestMapping("patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("{id}/profile")
    public ResponseEntity<Patients> profile(@PathVariable Long id){
        Patients patient = patientService.findById(id);
        return ResponseEntity.ok().body(patient);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<Patients> update(@PathVariable Long id,
                                 @RequestBody PatientsDto patientUpdateDto){
        Patients updatedPatient = patientService.update(patientUpdateDto, id);
        return ResponseEntity.ok().body(updatedPatient);
    }

    @PutMapping("{id}/change-password")
    public ResponseEntity changePassword(@PathVariable Long id,
                                         @RequestBody PasswordChangeDto passwordChangeDto){
        return ResponseEntity.ok().body(patientService.changePassword(passwordChangeDto, id));
    }

    @GetMapping("{id}/doctors")
    public ResponseEntity listDoctors(@PathVariable Long id){
        return ResponseEntity.ok().build();
    }

    @GetMapping("pagination")
    public ResponseEntity pagination(@RequestParam Integer page, @RequestParam Integer pageSize,
                                     @RequestBody PatientFilterDto patientFilterDto){
        return ResponseEntity.ok().build();
    }
}
