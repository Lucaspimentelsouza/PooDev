package com.poodev.poodev.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vital_signs")
public class VitalSigns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blood_pressure", nullable = false, length = 25)
    private String bloodPressure;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal weight;

    @Column(name = "body_temperature", nullable = false, precision = 4, scale = 2)
    private BigDecimal bodyTemperature;

    @Column(name = "heart_rate")
    private Integer heartRate;

    @Column(name = "oxygen_saturation", precision = 5, scale = 2)
    private BigDecimal oxygenSaturation;

    @Column(name = "blood_glucose", precision = 7, scale = 2)
    private BigDecimal bloodGlucose;

    @OneToOne
    @JoinColumn(
            name = "consultation_id",
            nullable = false,
            unique = true
    )
    private Consultation consultation;
}
