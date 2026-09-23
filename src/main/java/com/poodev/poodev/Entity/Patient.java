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

    @Column(length = 14, name = "patients_cpf",unique = true)
    private String cpf;

    @Column(length = 70, nullable = false)
    private String name;

    private boolean fallRisk;
    private LocalDate birthDate;
    private String gender;
    private String healthInsurance; //convenio
    private String phone;
}
