package org.tsa.hms_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ANALYSIS")
@Data
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctors doctor;

    @ManyToOne
    @JoinColumn(name = "analysis_type_id", nullable = false)
    private AnalysisType analysisType;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "room")
    private String room;
}
