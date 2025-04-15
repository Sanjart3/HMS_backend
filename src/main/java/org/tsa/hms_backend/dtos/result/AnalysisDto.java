package org.tsa.hms_backend.dtos.result;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.tsa.hms_backend.entities.AnalysisType;
import org.tsa.hms_backend.entities.Doctors;

@Data
public class AnalysisDto {
    private Long id;

    private Doctors doctor;

    private AnalysisType analysisType;

    private String name;

    private String description;

    private Integer price;

    private String room;

    private String filePath;
}
