package com.poodev.poodev.DTO.Response;

import java.time.LocalDate;

public record SurgeryResponse(
        Long id,
        String procedure,
        LocalDate surgeryDate,
        String hospital,
        String notes
) {
}