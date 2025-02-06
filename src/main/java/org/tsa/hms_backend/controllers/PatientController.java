package org.tsa.hms_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tsa.hms_backend.dtos.PasswordChangeDto;
import org.tsa.hms_backend.dtos.PatientFilterDto;
import org.tsa.hms_backend.dtos.PatientUpdateDto;

@Controller
@RequestMapping("patient")
public class PatientController {

    @GetMapping("{id}/profile")
    public ResponseEntity profile(@PathVariable Long id){
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/update")
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody PatientUpdateDto patientUpdateDto){
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/change-password")
    public ResponseEntity changePassword(@PathVariable Long id,
                                         @RequestBody PasswordChangeDto passwordChangeDto){
        return ResponseEntity.ok().build();
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
