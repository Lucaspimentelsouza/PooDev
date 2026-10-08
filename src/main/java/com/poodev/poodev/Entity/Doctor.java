package com.poodev.poodev.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //for authentication
    @Column(name = "username",nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "active",nullable = false)
    private boolean active;

    //Users profile
    @Column(name = "full_name",length = 70, nullable = false)
    private String fullName;

    @Column(name = "medical_license", nullable = false,length = 20, unique = true)
    private String medicalLicense; //crm

    @Column(length = 100)
    private String specialty;

    @Column(name = "professional_email", length = 150)
    private String professionalEmail;

    //For audit
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // data e hora de criação do registro

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // data e hora da última atualização

}
