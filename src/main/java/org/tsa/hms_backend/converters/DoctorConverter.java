package org.tsa.hms_backend.converters;

import org.springframework.stereotype.Component;
import org.tsa.hms_backend.dtos.DoctorDto;
import org.tsa.hms_backend.entities.Doctors;
import org.tsa.hms_backend.entities.Users;
import org.tsa.hms_backend.enums.Role;

@Component
public class DoctorConverter {
    public Doctors toEntity(DoctorDto doctorDto) {
        Users user = new Users();
        user.setFirstName(doctorDto.getName());
        user.setPassword(doctorDto.getPassword());
        user.setEmail(doctorDto.getEmail());
        user.setGender(doctorDto.getGender());
        user.setDateOfBirth(doctorDto.getDateOfBirth());
        user.setRole(Role.DOCTOR);

        Doctors doctors = new Doctors();
        doctors.setUser(user);
        doctors.setSpecialization(doctorDto.getSpecialization());
        doctors.setRoom(doctorDto.getRoom());
        return doctors;
    }

    public DoctorDto toDto(Doctors doctors) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setName(doctors.getUser().getFirstName());
        doctorDto.setPassword(doctors.getUser().getPassword());
        doctorDto.setEmail(doctors.getUser().getEmail());
        doctorDto.setGender(doctors.getUser().getGender());
        doctorDto.setDateOfBirth(doctors.getUser().getDateOfBirth());
        doctorDto.setSpecialization(doctors.getSpecialization());
        doctorDto.setRoom(doctors.getRoom());
        return doctorDto;
    }
}
