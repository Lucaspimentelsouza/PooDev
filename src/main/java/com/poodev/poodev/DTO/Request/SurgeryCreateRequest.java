package com.poodev.poodev.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record SurgeryCreateRequest(
        @NotBlank(message = "O nome do procedimento é obrigatório.")
        @Size(max = 130, message = "O nome do procedimento deve ter no máximo 130 caracteres.")
        String procedureName,

        @NotNull(message = "A data da cirurgia é obrigatória.")
        @PastOrPresent(message = "A data da cirurgia não pode estar no futuro.")
        LocalDate surgeryDate,

        String notes
) {
}