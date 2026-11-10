package com.poodev.poodev.Exception;

import java.time.LocalDate;

public class SurgeryAlreadyExistsException extends RuntimeException {

    public SurgeryAlreadyExistsException(
            Long patientId,
            String procedureName,
            LocalDate surgeryDate
    ) {
        super(
                "O paciente " + patientId
                        + " já possui a cirurgia '" + procedureName
                        + "' registrada na data " + surgeryDate + "."
        );
    }
}