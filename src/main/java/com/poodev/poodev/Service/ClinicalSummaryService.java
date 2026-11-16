package com.poodev.poodev.Service;

import com.poodev.poodev.DTO.Response.ClinicalSummaryResponse;
import com.poodev.poodev.DTO.Response.PatientResponse;
import com.poodev.poodev.Entity.Allergy;
import com.poodev.poodev.Entity.Patient;
import com.poodev.poodev.Exception.PatientNotFoundException;
import com.poodev.poodev.Mapper.AllergyMapper;
import com.poodev.poodev.Mapper.ConsultationHistoryMapper;
import com.poodev.poodev.Mapper.ContinuousMedicationMapper;
import com.poodev.poodev.Mapper.PreExistingConditionMapper;
import com.poodev.poodev.Mapper.SurgeryMapper;
import com.poodev.poodev.Repository.AllergyRepository;
import com.poodev.poodev.Repository.ConsultationRepository;
import com.poodev.poodev.Repository.ContinuousMedicationRepository;
import com.poodev.poodev.Repository.PatientRepository;
import com.poodev.poodev.Repository.PreExistingConditionRepository;
import com.poodev.poodev.Repository.SurgeryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicalSummaryService {

    private final PatientRepository patientRepository;
    private final AllergyRepository allergyRepository;
    private final PreExistingConditionRepository preExistingConditionRepository;
    private final ContinuousMedicationRepository continuousMedicationRepository;
    private final SurgeryRepository surgeryRepository;
    private final ConsultationRepository consultationRepository;

    private final AllergyMapper allergyMapper;
    private final PreExistingConditionMapper preExistingConditionMapper;
    private final ContinuousMedicationMapper continuousMedicationMapper;
    private final SurgeryMapper surgeryMapper;
    private final ConsultationHistoryMapper consultationHistoryMapper;

    private final ClinicalAlertService clinicalAlertService;

    @Transactional(readOnly = true)
    public ClinicalSummaryResponse build(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new PatientNotFoundException(patientId)
                );

        List<Allergy> activeAllergies =
                allergyRepository.findByPatientIdAndActiveTrue(patientId);

        return new ClinicalSummaryResponse(
                toPatientResponse(patient),
                calculateAge(patient.getBirthDate()),
                clinicalAlertService.buildAlerts(patient, activeAllergies),
                activeAllergies.stream()
                        .map(allergyMapper::toResponse)
                        .toList(),
                preExistingConditionRepository
                        .findByPatientIdAndActiveTrue(patientId)
                        .stream()
                        .map(preExistingConditionMapper::toResponse)
                        .toList(),
                continuousMedicationRepository
                        .findByPatientIdAndActiveTrue(patientId)
                        .stream()
                        .map(continuousMedicationMapper::toResponse)
                        .toList(),
                surgeryRepository
                        .findByPatientIdOrderBySurgeryDateDesc(patientId)
                        .stream()
                        .map(surgeryMapper::toResponse)
                        .toList(),
                consultationRepository
                        .findByPatientIdOrderByConsultationDateTimeDesc(patientId)
                        .stream()
                        .map(consultationHistoryMapper::toHistoryItem)
                        .toList()
        );
    }

    private PatientResponse toPatientResponse(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getName(),
                patient.getCpf(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getHealthInsurance(),
                patient.getPhone(),
                patient.isFallRisk()
        );
    }

    private Integer calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            return null;
        }

        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}