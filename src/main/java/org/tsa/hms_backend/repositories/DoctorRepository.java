package org.tsa.hms_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tsa.hms_backend.entities.Doctors;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctors, Long> {

}
