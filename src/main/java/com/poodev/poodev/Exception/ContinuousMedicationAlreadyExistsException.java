package com.poodev.poodev.Exception;

public class ContinuousMedicationAlreadyExistsException
        extends RuntimeException {

    public ContinuousMedicationAlreadyExistsException(
            Long patientId,
            String medicationName
    ) {
        super(
                "O paciente " + patientId
                        + " já possui o medicamento de uso contínuo informado: "
                        + medicationName + "."
        );
    }
}