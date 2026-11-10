package com.poodev.poodev.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AllergyCreateRequest(
        @NotBlank(message = "O agente alérgico é obrigatório.")
        @Size(max = 130, message = "O agente alérgico deve ter no máximo 130 caracteres.")
        String allergen,

        @NotNull(message = "O status de ativação da alergia é obrigatório.")
        Boolean active
) {
}