package org.tsa.hms_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.tsa.hms_backend.entities.Users;
import org.tsa.hms_backend.enums.BloodGroup;

@Data
@AllArgsConstructor
public class PatientsDto {
    private Users user;
    private String address;
    private BloodGroup bloodGroup;
}
