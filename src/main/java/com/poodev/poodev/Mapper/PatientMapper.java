package com.poodev.poodev.Mapper;

import com.poodev.poodev.DTO.Request.PatientCreateRequest;
import com.poodev.poodev.DTO.Response.PatientHeaderResponse;
import com.poodev.poodev.DTO.Response.PatientResponse;
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

    public Patient toEntity(PatientCreateRequest request) {
        Patient patient = new Patient();

        patient.setName(request.name());
        patient.setCpf(request.cpf());
        patient.setBirthDate(request.birthDate());
        patient.setGender(request.gender());
        patient.setHealthInsurance(request.healthInsurance());
        patient.setPhone(request.phone());
        patient.setFallRisk(request.fallRisk());

        return patient;
    }

    public PatientResponse toResponse(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getName(),
                patient.getCpf(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getHealthInsurance(),
                patient.getPhone(),
                patient.isFallRisk()
        );
    }
}