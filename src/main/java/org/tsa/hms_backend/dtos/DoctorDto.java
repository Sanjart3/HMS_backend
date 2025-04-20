package org.tsa.hms_backend.dtos;

import lombok.Data;
import org.tsa.hms_backend.enums.Gender;

import java.time.LocalDate;

@Data
public class DoctorDto {
    private String name;
    private Gender gender;
    private String specialization;
    private String phoneNumber;
    private String room;
    private String email;
    private LocalDate dateOfBirth;
    private String password;
}
