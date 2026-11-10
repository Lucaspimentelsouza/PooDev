package com.poodev.poodev.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ContinuousMedicationCreateRequest(
        @NotBlank(message = "O nome do medicamento é obrigatório.")
        @Size(max = 130, message = "O nome do medicamento deve ter no máximo 130 caracteres.")
        String medicationName,

        @Size(max = 70, message = "A dosagem deve ter no máximo 70 caracteres.")
        String dosage,

        @Size(max = 70, message = "A frequência deve ter no máximo 70 caracteres.")
        String frequency,

        @NotNull(message = "O status de ativação do medicamento é obrigatório.")
        Boolean active
) {
}