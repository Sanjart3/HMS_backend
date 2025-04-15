package org.tsa.hms_backend.converters;

import org.springframework.stereotype.Component;
import org.tsa.hms_backend.dtos.result.DoctorScheduleDto;
import org.tsa.hms_backend.entities.DoctorSchedule;

import java.time.LocalTime;

@Component
public class DoctorScheduleConverter {
    public DoctorScheduleDto toDto(DoctorSchedule schedule, LocalTime start, LocalTime end) {
        DoctorScheduleDto dto = new DoctorScheduleDto();
        dto.setScheduleDate(schedule.getScheduleDate());
        dto.setStartTime(start);
        dto.setEndTime(end);
        dto.setDoctor(schedule.getDoctor());
        return dto;
    }
}
