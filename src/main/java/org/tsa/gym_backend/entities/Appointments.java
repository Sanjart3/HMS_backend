package org.tsa.gym_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "APPOINTMENTS")
@Data
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Added ID generation
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false) // Fixed column name
    private Doctors doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false) // Fixed column name
    private Patients patient;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime; // Changed from String to LocalTime

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime; // Changed from String to LocalTime

    @Column(name = "is_confirmed", nullable = false)
    private Boolean isConfirmed;
}
