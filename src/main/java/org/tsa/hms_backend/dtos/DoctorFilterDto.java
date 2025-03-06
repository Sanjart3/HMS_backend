package org.tsa.hms_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorFilterDto {
    private String name;
    private String gender;
    private String department;
    private String specialization;
    private Integer appointmentCost;
}
