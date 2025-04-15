package org.tsa.hms_backend.dtos.result;

import lombok.Data;
import org.tsa.hms_backend.entities.Doctors;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DoctorScheduleDto {
    private LocalDate scheduleDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Doctors doctor;
}
