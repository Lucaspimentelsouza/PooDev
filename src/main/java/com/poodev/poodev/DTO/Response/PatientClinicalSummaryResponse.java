package com.poodev.poodev.DTO.Response;

import java.util.List;

public record PatientClinicalSummaryResponse(
        PatientHeaderResponse patient,
        List<CriticalAlertResponse> criticalAlerts,
        List<AllergyResponse> allergies,
        List<ContinuousMedicationResponse> continuousMedications,
        List<SurgeryResponse> surgeries,
        List<ConsultationSummaryResponse> consultations
) {
}