package com.poodev.poodev.Entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "patients_medical_record_number", unique = true)
    private String medicalRecordNumber;

    @Column(name = "patients_cpf",unique = true)
    private String cpf;

    @Column(length = 70, nullable = false)
    private String name;


    private LocalDate birthDate;
    private String gender;
    private String healthInsurance; //convenio
    private String phone;

    public Patient() {
    }

    public Patient(Long id, String medicalRecordNumber, String cpf, String name, LocalDate birthDate, String gender, String healthInsurance, String phone) {
        this.id = id;
        this.medicalRecordNumber = medicalRecordNumber;
        this.cpf = cpf;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.healthInsurance = healthInsurance;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate != null && birthDate.isAfter(LocalDate.now())){ //passar para o service no futuro
            throw new IllegalArgumentException("A data de nascimento está no futuro, ajeite por favor!");
        }
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
