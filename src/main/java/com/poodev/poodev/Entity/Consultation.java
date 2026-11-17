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

    @Column(name = "history_of_present_illness", columnDefinition = "TEXT")
    private String historyOfPresentIllness;

    @Column(name = "physical_examination", columnDefinition = "TEXT")
    private String physicalExamination;

    @Column(name = "recommended_return_days")
    private Integer recommendedReturnDays;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name = "patient_id", nullable = false)
    private Patient patient; // paciente atendido

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor; // médico responsável pela consulta

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "return_of_consultation_id")
    private Consultation returnOfConsultation; // consulta original, se esta for um retorno
}
