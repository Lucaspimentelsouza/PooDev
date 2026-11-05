package com.poodev.poodev.Exception;

public class ConsultationNotFoundException extends RuntimeException {

    public ConsultationNotFoundException(Long consultationId) {
        super("Consulta não encontrada: " + consultationId);
    }
}