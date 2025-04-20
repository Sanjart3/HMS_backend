package org.tsa.hms_backend.dtos.result;

import lombok.Data;
import org.tsa.hms_backend.entities.AnalysisType;
import org.tsa.hms_backend.entities.Doctors;

@Data
public class AnalysisDto {
    private Long id;

    private String doctorName;

    private String patientName;

    private String analysisType;

    private String name;

    private String description;

    private Integer price;

    private String room;

    private String filePath;
}
