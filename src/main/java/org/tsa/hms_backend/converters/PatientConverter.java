package org.tsa.hms_backend.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tsa.hms_backend.dtos.PatientsDto;
import org.tsa.hms_backend.entities.Patients;

@Component
public class PatientConverter extends AbstractConverter<Patients, PatientsDto>{

    public PatientConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected Class<Patients> getEntity() {
        return Patients.class;
    }

    @Override
    protected Class<PatientsDto> getDto() {
        return PatientsDto.class;
    }
}
