package org.tsa.hms_backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tsa.hms_backend.entities.Analysis;
import org.tsa.hms_backend.entities.Doctors;
import org.tsa.hms_backend.entities.Patients;

import java.util.List;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
    @Query("""
            SELECT a FROM Analysis a
            WHERE (:analysisName IS NULL OR a.name = :analysisName)
            AND (:price IS NULL OR a.price <= :price)
            AND (:analysisRoom IS NULL OR a.room = :analysisRoom)
            AND (:analysisType IS NULL OR a.analysisType.name = :analysisType)
    """)
    Page<Analysis> findFilteredAnalysis(@Param("analysisName") String analysisName,
                                        @Param("price") Integer price,
                                        @Param("analysisRoom") String analysisRoom,
                                        @Param("analysisType") String analysisTypeName,
                                        Pageable pageable);

    List<Analysis> findAllByDoctor(Doctors doctor);

    List<Analysis> findAllByPatients(Patients existingPatient);
}
