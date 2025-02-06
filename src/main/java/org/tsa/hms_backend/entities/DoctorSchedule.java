package org.tsa.hms_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "DOCTOR_SCHEDULE")
@Data
public class DoctorSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "schedule_date", nullable = false)
    private LocalDate scheduleDate; // Changed from String to LocalDate

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime; // Changed from String to LocalTime

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime; // Changed from String to LocalTime

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctors doctor;
}
