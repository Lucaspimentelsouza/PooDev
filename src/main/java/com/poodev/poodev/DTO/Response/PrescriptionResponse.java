package com.poodev.poodev.DTO.Response;

import java.time.LocalDateTime;

public record PrescriptionResponse(
        Long id,
        Long consultationId,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}