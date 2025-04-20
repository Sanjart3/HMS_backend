package org.tsa.hms_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalysisFilterDto {
    private String analysisName;
    private String analysisDescription;
    private Integer price;
    private String analysisRoom;
    private String analysisTypeName;
}
