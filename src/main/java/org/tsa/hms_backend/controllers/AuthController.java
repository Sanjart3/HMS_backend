package org.tsa.hms_backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tsa.hms_backend.dtos.LoginDto;
import org.tsa.hms_backend.dtos.PatientsDto;
import org.tsa.hms_backend.services.PatientService;
import org.tsa.hms_backend.services.UserDetailsServiceImpl;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
@CrossOrigin()
public class AuthController {

    private final PatientService patientService;
    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("sign-up")
    public ResponseEntity<String> signUp(@RequestBody PatientsDto signUpDto) {
        String token = patientService.signup(signUpDto);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String token = userDetailsService.signIn(loginDto);
        return ResponseEntity.ok(token);
    }
}
