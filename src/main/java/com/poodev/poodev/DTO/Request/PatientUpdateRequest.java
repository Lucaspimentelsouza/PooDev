//put
package com.poodev.poodev.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PatientUpdateRequest(

        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 70, message = "O nome deve ter no máximo 70 caracteres.")
        String name,

        @Size(max = 14, message = "O CPF deve ter no máximo 14 caracteres.")
        String cpf,

        @PastOrPresent(message = "A data de nascimento não pode estar no futuro.")
        LocalDate birthDate,

        String gender,

        String healthInsurance,

        String phone,

        boolean fallRisk
) {
}