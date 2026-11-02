package com.poodev.poodev.Exception;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(Long patientId) {
        super("Paciente não encontrado: " + patientId);
    }
}