package com.poodev.poodev.DTO.Response;

public record AllergyResponse(
        Long id,
        Long patientId,
        String allergen,
        boolean active
) {
}