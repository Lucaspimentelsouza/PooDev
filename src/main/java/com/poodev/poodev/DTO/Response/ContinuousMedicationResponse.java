package com.poodev.poodev.DTO.Response;

public record ContinuousMedicationResponse(
        Long id,
        String medicationName,
        String dosage,
        String frequency
) {
}