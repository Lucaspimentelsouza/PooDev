package com.poodev.poodev.DTO.Response;

public record CurrentConsultationResponse(
        ConsultationResponse consultation,
        VitalSignsResponse vitalSigns
) {
}