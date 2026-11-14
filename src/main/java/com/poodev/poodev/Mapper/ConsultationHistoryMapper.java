package com.poodev.poodev.Mapper;

import com.poodev.poodev.DTO.Response.ConsultationHistoryItemResponse;
import com.poodev.poodev.Entity.Consultation;
import org.springframework.stereotype.Component;

@Component
public class ConsultationHistoryMapper {

    public ConsultationHistoryItemResponse toHistoryItem(
            Consultation consultation
    ) {
        boolean isReturn = consultation.getReturnOfConsultation() != null;

        Long returnOfId = isReturn
                ? consultation.getReturnOfConsultation().getId()
                : null;

        return new ConsultationHistoryItemResponse(
                consultation.getId(),
                consultation.getConsultationDateTime(),
                isReturn ? "RETURN" : "CONSULTATION",
                returnOfId,
                consultation.getChiefComplaint(),
                consultation.getDiagnosticHypothesis(),
                consultation.getClinicalPlan(),
                consultation.getDoctor().getId(),
                consultation.getDoctor().getFullName()
        );
    }
}