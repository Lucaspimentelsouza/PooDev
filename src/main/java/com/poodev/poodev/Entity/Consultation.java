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
@Table(name = "consultations")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "consultation_date_time", nullable = false)
    private LocalDateTime consultationDateTime;

    @Column (name = "chief_complaint")
    private String chiefComplaint;

    @Column (name = "diagnostic_hypothesis")
    private String diagnosticHypothesis;

    @Column (name = "clinical_plan", columnDefinition = "TEXT")
    private String clinicalPlan;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name = "patient_id", nullable = false)
    private Patient patient;
}
