package com.poodev.poodev.Mapper;

import com.poodev.poodev.DTO.Response.PatientHeaderResponse;
import com.poodev.poodev.Entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public PatientHeaderResponse toHeaderResponse(
            Patient patient,
            Integer age
    ) {
        return new PatientHeaderResponse(
                patient.getId(),
                patient.getName(),
                patient.getBirthDate(),
                age,
                patient.getHealthInsurance(),
                patient.isFallRisk()
        );
    }
}