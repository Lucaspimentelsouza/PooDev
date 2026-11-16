package com.poodev.poodev.DTO.Response;

import java.util.List;

public record ClinicalSummaryResponse(
        PatientResponse patient,
        Integer age,
        List<CriticalAlertResponse> criticalAlerts,
        List<AllergyResponse> allergies,
        List<PreExistingConditionResponse> preExistingConditions,
        List<ContinuousMedicationResponse> continuousMedications,
        List<SurgeryResponse> surgeries,
        List<ConsultationHistoryItemResponse> consultationHistory
) {
}