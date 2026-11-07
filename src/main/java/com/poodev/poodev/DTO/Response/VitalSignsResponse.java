package com.poodev.poodev.DTO.Response;

import java.math.BigDecimal;

public record VitalSignsResponse(
        Long id,
        Long consultationId,
        String bloodPressure,
        BigDecimal weight,
        BigDecimal bodyTemperature
) {
}