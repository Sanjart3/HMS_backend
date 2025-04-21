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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
//    @JsonIgnore
    private Doctors doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patients patients;

    @ManyToOne()
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

    @Column(name = "file_path")
    private String filePath;
}
