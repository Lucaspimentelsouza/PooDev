package com.poodev.poodev.DTO.Response;

import java.time.LocalDateTime;

public record ConsultationResponse(
        Long id,
        LocalDateTime consultationDateTime,
        String chiefComplaint,
        String diagnosticHypothesis,
        String clinicalPlan,
        Long patientId,
        String patientName,
        Long doctorId,
        String doctorName
) {
}