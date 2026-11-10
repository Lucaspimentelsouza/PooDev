package com.poodev.poodev.Exception;

public class AllergyAlreadyExistsException extends RuntimeException {

    public AllergyAlreadyExistsException(Long patientId, String allergen) {
        super(
                "O paciente " + patientId
                        + " já possui a alergia informada: "
                        + allergen + "."
        );
    }
}