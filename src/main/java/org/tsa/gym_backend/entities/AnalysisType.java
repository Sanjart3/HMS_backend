package org.tsa.gym_backend.entities;


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

    @OneToMany(mappedBy = "analysisType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Analysis> analyses;
}
