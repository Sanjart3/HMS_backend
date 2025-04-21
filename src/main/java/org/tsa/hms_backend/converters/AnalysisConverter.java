package org.tsa.hms_backend.converters;

import org.springframework.stereotype.Component;
import org.tsa.hms_backend.dtos.result.AnalysisDto;
import org.tsa.hms_backend.entities.Analysis;

@Component
public class AnalysisConverter {

    public AnalysisDto toDto(Analysis entity) {
        AnalysisDto dto = new AnalysisDto();
        dto.setId(entity.getId());
        dto.setDoctorName(entity.getDoctor().getUser().getFirstName() + " " + entity.getDoctor().getUser().getLastName());
        dto.setPatientName(entity.getPatients().getUser().getFirstName() + " " + entity.getPatients().getUser().getLastName());
        dto.setAnalysisType(entity.getAnalysisType().getName());
        dto.setPrice(entity.getPrice());
        dto.setRoom(entity.getRoom());
        dto.setFilePath(entity.getFilePath());
        dto.setDescription(entity.getDescription());
        dto.setName(entity.getName());
        return dto;
    }
}
