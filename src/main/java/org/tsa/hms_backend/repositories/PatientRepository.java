package org.tsa.hms_backend.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tsa.hms_backend.entities.Doctors;
import org.tsa.hms_backend.entities.Patients;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patients, Long> {

//    @Query(value = "SELECT d.* FROM patient_doctor pd " +
//            "JOIN doctors d ON d.id = pd.doctor_id " +
//            "WHERE pd.patient_id = :id", nativeQuery = true)

    @Query("""
            SELECT p.visitedDoctors FROM Patients p
            WHERE p.id = :patientId
            """)
    List<Doctors> getAssignedDoctorsByPatientId(@Param("id") Long id);

    @Query("""
            SELECT p FROM Patients p
            WHERE (:patientName IS NULL OR CONCAT(p.user.firstName, p.user.lastName) ILIKE :patientName)
            """)
    List<Patients> getPatientsFilter(@Param("patientName") String patientName, Pageable pageable);
}
