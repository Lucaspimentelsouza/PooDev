package com.poodev.poodev.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PreExistingConditionCreateRequest(
        @NotBlank(message = "O nome da doença preexistente é obrigatório.")
        @Size(
                max = 130,
                message = "O nome da doença preexistente deve ter no máximo 130 caracteres."
        )
        String conditionName,

        @NotNull(message = "O status de ativação da doença preexistente é obrigatório.")
        Boolean active
) {
}