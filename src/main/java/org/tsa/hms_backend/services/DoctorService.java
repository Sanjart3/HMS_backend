package org.tsa.hms_backend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tsa.hms_backend.repositories.DoctorRepository;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

}
