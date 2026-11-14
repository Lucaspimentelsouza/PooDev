package com.poodev.poodev.DTO.Response;

import java.time.LocalDateTime;

public record ConsultationHistoryItemResponse(
        Long id,
        LocalDateTime consultationDateTime,
        String type,
        Long returnOfConsultationId,
        String chiefComplaint,
        String diagnosticHypothesis,
        String clinicalPlan,
        Long doctorId,
        String doctorName
) {
}