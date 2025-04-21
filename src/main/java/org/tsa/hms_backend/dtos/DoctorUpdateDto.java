package org.tsa.hms_backend.dtos;

import lombok.Data;
import org.tsa.hms_backend.enums.Gender;

import java.time.LocalDate;

@Data
public class DoctorUpdateDto {
    private String name;
    private String phoneNumber;
    private String room;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String specialization;
}
