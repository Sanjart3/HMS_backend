package org.tsa.hms_backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tsa.hms_backend.entities.Appointments;

import java.time.LocalDate;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointments, Long> {

    @Query("""
            SELECT a FROM Appointments a
            WHERE (:from IS NULL OR a.date >= :from)
            AND (:to IS NULL OR a.date <= :to)
            AND (:patientId IS NULL OR a.patient.id = :patientId)
            AND (:doctorId IS NULL OR a.doctor.id = :doctorId)
            AND (a.isConfirmed = :isConfirmed)
            """)
    Page<Appointments> findFilteredAppointments(@Param("from") LocalDate from,
                                                @Param("to") LocalDate to,
                                                @Param("patientId") Long patientId,
                                                @Param("doctorId") Long doctorId,
                                                @Param("isConfirmed") boolean isConfirmed,
                                                Pageable pageable);
}
