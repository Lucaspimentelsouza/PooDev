package com.poodev.poodev.Exception;

public class AllergyNotFoundException extends RuntimeException {

    public AllergyNotFoundException(Long patientId, Long allergyId) {
        super(
                "Alergia com ID " + allergyId
                        + " não encontrada para o paciente com ID "
                        + patientId + "."
        );
    }
}