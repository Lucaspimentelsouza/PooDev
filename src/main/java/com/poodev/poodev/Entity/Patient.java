package com.poodev.poodev.Entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "patients_medical_record_number", unique = true)
    private String medicalRecordNumber;

    @Column(length = 14, name = "patients_cpf",unique = true)
    private String cpf;

    @Column(length = 70, nullable = false)
    private String name;


    private LocalDate birthDate;
    private String gender;
    private String healthInsurance; //convenio
    private String phone;
}
