package org.tsa.hms_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tsa.hms_backend.entities.Comments;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

    @Query("""
    SELECT c FROM Comments c
        WHERE (:doctorId IS NULL OR c.doctor.id = :doctorId)
        AND (:patientId IS NULL OR c.patient.id = :patientId)
        AND (:from IS NULL OR c.creationDateTime >= :from)
        AND (:to IS NULL OR c.creationDateTime <= :to)
        ORDER BY c.creationDateTime ASC
    """)
    List<Comments> getComments(@Param("doctorId") Long doctorId,
                               @Param("patientId") Long patientId,
                               @Param("from") LocalDateTime from,
                               @Param("to") LocalDateTime to);
}
