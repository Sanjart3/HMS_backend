package org.tsa.hms_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tsa.hms_backend.entities.DoctorSchedule;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
    Optional<List<DoctorSchedule>> findAllByDoctorId(Long doctorId);

    @Query("""
            SELECT ds FROM DoctorSchedule ds
            WHERE (:department IS NULL OR ds.doctor.department = :department)
            AND (:doctorId IS NULL OR ds.doctor.id = :doctorId)
    """)
    List<DoctorSchedule> findFilteredSchedule(@Param("department") String department,
                                              @Param("doctorId") UUID doctorId);
}
