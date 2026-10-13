package com.poodev.poodev.DTO.Response;

import java.time.LocalDateTime;

public record ConsultationSummaryResponse(
        Long id,
        LocalDateTime consultationDateTime,
        String chiefComplaint,
        String diagnosis,
        String conduct
) {
}