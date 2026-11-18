package com.poodev.poodev.Exception;

public class PrescriptionAlreadyExistsException extends RuntimeException {

    public PrescriptionAlreadyExistsException(Long consultationId) {
        super(
                "Já existe uma receita para a consulta "
                        + consultationId + "."
        );
    }
}