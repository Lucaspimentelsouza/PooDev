package com.poodev.poodev.Service;

import com.poodev.poodev.DTO.Response.AllergyResponse;
import com.poodev.poodev.DTO.Response.ConsultationSummaryResponse;
import com.poodev.poodev.DTO.Response.ContinuousMedicationResponse;
import com.poodev.poodev.DTO.Response.CriticalAlertResponse;
import com.poodev.poodev.DTO.Response.PatientClinicalSummaryResponse;
import com.poodev.poodev.DTO.Response.PatientHeaderResponse;
import com.poodev.poodev.DTO.Response.SurgeryResponse;
import com.poodev.poodev.Entity.Allergy;
import com.poodev.poodev.Entity.Consultation;
import com.poodev.poodev.Entity.ContinuousMedication;
import com.poodev.poodev.Entity.Patient;
import com.poodev.poodev.Entity.Surgery;
import com.poodev.poodev.Mapper.AllergyMapper;
import com.poodev.poodev.Mapper.ConsultationMapper;
import com.poodev.poodev.Mapper.ContinuousMedicationMapper;
import com.poodev.poodev.Mapper.PatientMapper;
import com.poodev.poodev.Mapper.SurgeryMapper;
import com.poodev.poodev.Repository.AllergyRepository;
import com.poodev.poodev.Repository.ConsultationRepository;
import com.poodev.poodev.Repository.ContinuousMedicationRepository;
import com.poodev.poodev.Repository.PatientRepository;
import com.poodev.poodev.Repository.SurgeryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientClinicalSummaryService {

    private final PatientRepository patientRepository;
    private final ConsultationRepository consultationRepository;
    private final AllergyRepository allergyRepository;
    private final ContinuousMedicationRepository continuousMedicationRepository;
    private final SurgeryRepository surgeryRepository;

    private final PatientMapper patientMapper;
    private final AllergyMapper allergyMapper;
    private final ContinuousMedicationMapper continuousMedicationMapper;
    private final SurgeryMapper surgeryMapper;
    private final ConsultationMapper consultationMapper;

    private final ClinicalAlertService clinicalAlertService;

    @Transactional(readOnly = true)
    public PatientClinicalSummaryResponse getClinicalSummary(Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Paciente não encontrado: " + patientId
                ));

        List<Allergy> allergies =
                allergyRepository.findByPatientIdAndActiveTrue(patientId);

        List<ContinuousMedication> medications =
                continuousMedicationRepository
                        .findByPatientIdAndActiveTrue(patientId);

        List<Surgery> surgeries =
                surgeryRepository
                        .findByPatientIdOrderBySurgeryDateDesc(patientId);

        List<Consultation> consultations =
                consultationRepository
                        .findByPatientIdOrderByConsultationDateTimeDesc(patientId);

        Integer age = calculateAge(patient.getBirthDate());

        PatientHeaderResponse patientHeader =
                patientMapper.toHeaderResponse(patient, age);

        List<CriticalAlertResponse> criticalAlerts =
                clinicalAlertService.buildAlerts(patient, allergies);

        List<AllergyResponse> allergyResponses = allergies.stream()
                .map(allergyMapper::toResponse)
                .toList();

        List<ContinuousMedicationResponse> medicationResponses =
                medications.stream()
                        .map(continuousMedicationMapper::toResponse)
                        .toList();

        List<SurgeryResponse> surgeryResponses = surgeries.stream()
                .map(surgeryMapper::toResponse)
                .toList();

        List<ConsultationSummaryResponse> consultationResponses =
                consultations.stream()
                        .map(consultationMapper::toSummaryResponse)
                        .toList();

        return new PatientClinicalSummaryResponse(
                patientHeader,
                criticalAlerts,
                allergyResponses,
                medicationResponses,
                surgeryResponses,
                consultationResponses
        );
    }

    private Integer calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            return null;
        }

        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}