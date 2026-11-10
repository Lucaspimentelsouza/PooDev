package com.poodev.poodev.DTO.Response;

import java.time.LocalDate;

public record SurgeryResponse(
        Long id,
        Long patientId,
        String procedureName,
        LocalDate surgeryDate,
        String notes
) {
}