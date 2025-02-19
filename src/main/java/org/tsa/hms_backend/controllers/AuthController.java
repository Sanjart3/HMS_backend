package org.tsa.hms_backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tsa.hms_backend.dtos.LoginDto;
import org.tsa.hms_backend.dtos.PatientsDto;
import org.tsa.hms_backend.entities.Patients;
import org.tsa.hms_backend.services.PatientService;

@Controller
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {

    private final PatientService patientService;

    @PostMapping("sign-up")
    public ResponseEntity<String> signUp(@RequestBody PatientsDto signUpDto) {
        String token = patientService.signup(signUpDto);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok().build();
    }
}
