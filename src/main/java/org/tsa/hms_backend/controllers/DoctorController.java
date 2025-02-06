package org.tsa.hms_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tsa.hms_backend.dtos.DoctorFilterDto;
import org.tsa.hms_backend.dtos.DoctorUpdateDto;
import org.tsa.hms_backend.dtos.PasswordChangeDto;

@Controller
@RequestMapping("doctor")
public class DoctorController {

    @GetMapping("get-all")
    public ResponseEntity getAllDoctors() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("get")
    public ResponseEntity getDoctors(@RequestParam Integer page, @RequestParam Integer size,
                                     @RequestBody DoctorFilterDto filter) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}/profile")
    public ResponseEntity getProfile(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/update")
    public ResponseEntity updateDoctor(@PathVariable Long id,
                                       @RequestBody DoctorUpdateDto dto) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/change-password")
    public ResponseEntity changePassword(@PathVariable Long id,
                                         @RequestBody PasswordChangeDto dto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}/patients")
    public ResponseEntity getPatients(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
