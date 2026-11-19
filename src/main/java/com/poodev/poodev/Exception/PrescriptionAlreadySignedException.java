package com.poodev.poodev.Exception;

public class PrescriptionAlreadySignedException extends RuntimeException {

    public PrescriptionAlreadySignedException(Long consultationId) {
        super(
                "A receita da consulta " + consultationId
                        + " já foi assinada."
        );
    }
}