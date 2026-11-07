package com.poodev.poodev.Exception;

public class VitalSignsAlreadyExistsException extends RuntimeException {

    public VitalSignsAlreadyExistsException(Long consultationId) {
        super(
                "A consulta " + consultationId
                        + " já possui sinais vitais cadastrados."
        );
    }
}