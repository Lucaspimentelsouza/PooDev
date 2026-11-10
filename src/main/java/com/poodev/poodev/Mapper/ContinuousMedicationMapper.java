package com.poodev.poodev.Mapper;

import com.poodev.poodev.DTO.Request.ContinuousMedicationCreateRequest;
import com.poodev.poodev.DTO.Response.ContinuousMedicationResponse;
import com.poodev.poodev.Entity.ContinuousMedication;
import com.poodev.poodev.Entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class ContinuousMedicationMapper {

    public ContinuousMedication toEntity(
            ContinuousMedicationCreateRequest request,
            Patient patient
    ) {
        ContinuousMedication medication = new ContinuousMedication();

        medication.setMedicationName(request.medicationName());
        medication.setDosage(request.dosage());
        medication.setFrequency(request.frequency());
        medication.setActive(request.active());
        medication.setPatient(patient);

        return medication;
    }

    public ContinuousMedicationResponse toResponse(
            ContinuousMedication medication
    ) {
        return new ContinuousMedicationResponse(
                medication.getId(),
                medication.getPatient().getId(),
                medication.getMedicationName(),
                medication.getDosage(),
                medication.getFrequency(),
                medication.isActive()
        );
    }
}