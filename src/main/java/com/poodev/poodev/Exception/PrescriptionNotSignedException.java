package com.poodev.poodev.Exception;

public class PrescriptionNotSignedException extends RuntimeException {

    public PrescriptionNotSignedException(Long consultationId) {
        super(
                "A receita da consulta " + consultationId
                        + " ainda não foi assinada."
        );
    }
}