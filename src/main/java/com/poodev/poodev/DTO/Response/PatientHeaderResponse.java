package com.poodev.poodev.DTO.Response;

import java.time.LocalDate;

public record PatientHeaderResponse(
        Long id,
        String name,
        LocalDate birthDate,
        Integer age,
        String healthInsurance,
        boolean fallRisk
) {
}