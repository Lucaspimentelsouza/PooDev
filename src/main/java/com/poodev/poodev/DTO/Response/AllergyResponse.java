package com.poodev.poodev.DTO.Response;

public record AllergyResponse(
        Long id,
        String allergen,
        String reaction,
        String severity
) {
}