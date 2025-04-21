package org.tsa.hms_backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String text;

    @ManyToOne
    private Patients patient;

    @ManyToOne
    private Doctors doctor;

    @CreationTimestamp
    private LocalDateTime creationDateTime = LocalDateTime.now();
}
