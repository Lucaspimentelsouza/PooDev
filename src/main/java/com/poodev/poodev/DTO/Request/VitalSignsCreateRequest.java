package com.poodev.poodev.DTO.Request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record VitalSignsCreateRequest(

        @NotBlank(message = "A pressão arterial é obrigatória.")
        @Size(
                max = 25,
                message = "A pressão arterial deve ter no máximo 25 caracteres." //change
        )
        String bloodPressure,

        @NotNull(message = "O peso é obrigatório.")
        @Positive(message = "O peso deve ser positivo.")
        @Digits(
                integer = 3,
                fraction = 2,
                message = "O peso deve ter até 3 dígitos inteiros e 2 decimais."
        )
        BigDecimal weight,

        @NotNull(message = "A temperatura corporal é obrigatória.")
        @Positive(message = "A temperatura corporal deve ser positiva.")
        @Digits(
                integer = 2,
                fraction = 2,
                message = "A temperatura deve ter até 2 dígitos inteiros e 2 decimais."
        )
        BigDecimal bodyTemperature
) {
}