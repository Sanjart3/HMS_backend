package org.tsa.hms_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.tsa.hms_backend.enums.BloodGroup;

import java.util.List;

@Entity
@Table(name = "PATIENTS")
@Data
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Users user;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group", nullable = false)
    private BloodGroup bloodGroup;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "patient_doctor",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    @JsonIgnore
    private List<Doctors> visitedDoctors;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Appointments> appointments;
}