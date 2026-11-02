package com.poodev.poodev.DTO.Response;

import java.time.LocalDate;

public record PatientResponse(
        Long id,
        String name,
        String cpf,
        LocalDate birthDate,
        String gender,
        String healthInsurance,
        String phone,
        boolean fallRisk
) {
}