package com.poodev.poodev.Exception;

public class PrescriptionNotFoundException extends RuntimeException {

    public PrescriptionNotFoundException(Long consultationId) {
        super(
                "Não foi encontrada receita para a consulta "
                        + consultationId + "."
        );
    }
}