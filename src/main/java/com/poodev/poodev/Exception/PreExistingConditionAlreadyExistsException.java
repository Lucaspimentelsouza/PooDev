package com.poodev.poodev.Exception;

public class PreExistingConditionAlreadyExistsException extends RuntimeException {

    public PreExistingConditionAlreadyExistsException(
            Long patientId,
            String conditionName
    ) {
        super(
                "O paciente " + patientId
                        + " já possui a doença preexistente informada: "
                        + conditionName + "."
        );
    }
}