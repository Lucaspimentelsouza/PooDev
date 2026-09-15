package com.poodev.poodev.Entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "users")
public class User {
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

    private String specialty;

    private String yearsOfExperience;

    //Contact
    private String professionalEmail;
    private String phoneNumber;

    //For audit
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
