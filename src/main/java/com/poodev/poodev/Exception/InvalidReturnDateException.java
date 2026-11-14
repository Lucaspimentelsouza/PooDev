package com.poodev.poodev.Exception;

public class InvalidReturnDateException extends RuntimeException {

    public InvalidReturnDateException(Long consultationId) {
        super(
                "A data do retorno deve ser posterior à data da consulta "
                        + consultationId + "."
        );
    }
}