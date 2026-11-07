package com.poodev.poodev.Exception;

public class VitalSignsNotFoundException extends RuntimeException {

    public VitalSignsNotFoundException(Long consultationId) {
        super(
                "Não foram encontrados sinais vitais para a consulta "
                        + consultationId + "."
        );
    }
}