package com.poodev.poodev.Exception;

public class DoctorNotFoundException extends RuntimeException {

    public DoctorNotFoundException(Long doctorId) {
        super("Médico não encontrado: " + doctorId);
    }
}