package com.poodev.poodev.DTO.Response;

public record PreExistingConditionResponse(
        Long id,
        Long patientId,
        String conditionName,
        boolean active
) {
}