package com.poodev.poodev.Mapper;

import com.poodev.poodev.DTO.Response.ContinuousMedicationResponse;
import com.poodev.poodev.Entity.ContinuousMedication;
import org.springframework.stereotype.Component;

@Component
public class ContinuousMedicationMapper {

    public ContinuousMedicationResponse toResponse(
            ContinuousMedication medication
    ) {
        return new ContinuousMedicationResponse(
                medication.getId(),
                medication.getMedicationName(),
                medication.getDosage()
        );
    }
}