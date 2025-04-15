package org.tsa.hms_backend.dtos.result;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentDto {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String doctorFullName;
    private String patientFullName;
    private boolean isConfirmed;
}
