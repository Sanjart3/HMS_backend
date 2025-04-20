package org.tsa.hms_backend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "ANALYSIS_TYPE")
@Data
public class AnalysisType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "analysisType", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Analysis> analyses;
}
