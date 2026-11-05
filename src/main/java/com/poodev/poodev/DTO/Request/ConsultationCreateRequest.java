package com.poodev.poodev.DTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ConsultationCreateRequest(

        @NotNull(message = "O paciente é obrigatório.")
        @Positive(message = "O identificador do paciente deve ser positivo.")
        Long patientId,

        @NotNull(message = "O médico é obrigatório.")
        @Positive(message = "O identificador do médico deve ser positivo.")
        Long doctorId,

        @NotNull(message = "A data e hora da consulta são obrigatórias.")
        LocalDateTime consultationDateTime,

        @Size(max = 255, message = "A queixa deve ter no máximo 255 caracteres.")
        String chiefComplaint,

        @Size(max = 255, message = "A hipótese deve ter no máximo 255 caracteres.")
        String diagnosticHypothesis,

        String clinicalPlan
) {
}