package com.poodev.poodev.DTO.Response;

public record ContinuousMedicationResponse(
        Long id,
        Long patientId,
        String medicationName,
        String dosage,
        String frequency,
        boolean active
) {
}