package com.poodev.poodev.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PatientNotFoundException.class)
    public ProblemDetail handlePatientNotFound(
            PatientNotFoundException exception
    ) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );

        problem.setTitle("Paciente não encontrado");

        return problem;
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ProblemDetail handleDoctorNotFound(
            DoctorNotFoundException exception
    ) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );

        problem.setTitle("Médico não encontrado");

        return problem;
    }

    @ExceptionHandler(ConsultationNotFoundException.class)
    public ProblemDetail handleConsultationNotFound(
            ConsultationNotFoundException exception
    ) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );

        problem.setTitle("Consulta não encontrada");

        return problem;
    }

    @ExceptionHandler(VitalSignsNotFoundException.class)
    public ProblemDetail handleVitalSignsNotFound(
            VitalSignsNotFoundException exception
    ) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );

        problem.setTitle("Sinais vitais não encontrados");

        return problem;
    }

    @ExceptionHandler(VitalSignsAlreadyExistsException.class)
    public ProblemDetail handleVitalSignsAlreadyExists(
            VitalSignsAlreadyExistsException exception
    ) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                exception.getMessage()
        );

        problem.setTitle("Triagem já cadastrada");

        return problem;
    }

    @ExceptionHandler(AllergyAlreadyExistsException.class)
    public ProblemDetail handleAllergyAlreadyExists(
            AllergyAlreadyExistsException exception
    ) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                exception.getMessage()
        );

        problem.setTitle("Alergia já cadastrada");

        return problem;
    }

    @ExceptionHandler(PreExistingConditionAlreadyExistsException.class)
    public ProblemDetail handlePreExistingConditionAlreadyExists(
            PreExistingConditionAlreadyExistsException exception
    ) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                exception.getMessage()
        );

        problem.setTitle("Doença preexistente já cadastrada");

        return problem;
    }
}