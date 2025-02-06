package org.tsa.hms_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tsa.hms_backend.dtos.LoginDto;
import org.tsa.hms_backend.dtos.SignUpDto;

@Controller
@RequestMapping("auth")
public class AuthController {

    @PostMapping("sign-up")
    public ResponseEntity signUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok().build();
    }
}
