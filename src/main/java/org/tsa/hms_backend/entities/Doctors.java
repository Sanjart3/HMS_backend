package org.tsa.hms_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "DOCTORS")
@Data
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Users user;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DoctorSchedule> schedules;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @Column(name = "room")
    private String room;

    @Column(name = "about", columnDefinition = "TEXT")
    private String about;

    @Column(name = "img_link")
    private String imgLink;

    @Column(name = "address")
    private String address;

    @Column(name = "appoint_summa")
    private Integer appointCost;

    @Column(name = "career_start_year")
    private Integer careerStartYear;
}
