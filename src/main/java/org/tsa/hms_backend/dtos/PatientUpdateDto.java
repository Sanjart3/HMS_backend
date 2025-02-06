package org.tsa.hms_backend.dtos;

import lombok.Data;
import org.tsa.hms_backend.enums.BloodGroup;
import org.tsa.hms_backend.enums.Gender;

import java.time.LocalDate;

@Data
public class PatientUpdateDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private BloodGroup bloodGroup;
    private Gender gender;
}
