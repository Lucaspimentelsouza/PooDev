package com.poodev.poodev.Entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;

@Entity
public class Patient {
    private Long id;
    private String medicalRecordNumber;
    private String cpf;
    private String name;
    private LocalDate birthDate;
    private String gender;

    private String healthInsurance; //convenio
    private String phone;
}
