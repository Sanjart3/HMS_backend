package org.tsa.hms_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tsa.hms_backend.entities.Patients;

@Repository
public interface PatientRepository extends JpaRepository<Patients, Long> {
}
