package com.poodev.poodev.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PrescriptionRequest(

        @NotBlank(message = "O conteúdo da receita é obrigatório.")
        @Size(
                max = 20000,
                message = "O conteúdo da receita deve ter no máximo 20000 caracteres."
        )
        String content
) {
}