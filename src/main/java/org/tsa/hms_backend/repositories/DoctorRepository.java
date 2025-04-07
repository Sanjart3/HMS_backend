package org.tsa.hms_backend.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tsa.hms_backend.entities.Doctors;
import org.tsa.hms_backend.entities.Patients;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctors, Long> {

    @Query("""
            SELECT d FROM Doctors d
            WHERE (:name IS NULL OR CONCAT(d.user.firstName, d.user.lastName) ILIKE CONCAT('%', :name, '%'))
            AND (:gender IS NULL OR d.user.gender = :gender)
            AND (:department IS NULL OR d.department = :department)
            AND (:specialization IS NULL OR d.specialization = :specialization)
            AND (:appointment IS NULL OR d.appointCost <= :appointment)
            """)
    List<Doctors> findFilteredDoctors(@Param("name") String name,
                                      @Param("gender") String gender,
                                      @Param("department") String department,
                                      @Param("specialization") String specialization,
                                      @Param("appointmentCost") Integer appointmentCost,
                                      Pageable pageable
                                      );

    @Query("""
        SELECT d.patients FROM Doctors d
        WHERE d.id = :doctorId
        """)
    List<Patients> findPatientsByDoctorId(@Param("doctorId") Long id);

    @Query("SELECT ds.doctor FROM DoctorSchedule ds WHERE ds.id = :scheduleId")
    Optional<Doctors> findByScheduleId(Long scheduleId);
}
