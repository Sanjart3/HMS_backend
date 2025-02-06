package org.tsa.hms_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.tsa.hms_backend.enums.BloodGroup;
import org.tsa.hms_backend.enums.Gender;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SignUpDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Gender gender;
    private LocalDate dateOfBirth;
    private BloodGroup bloodGroup;
}
