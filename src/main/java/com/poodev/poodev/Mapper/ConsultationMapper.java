package com.poodev.poodev.Mapper;

import com.poodev.poodev.DTO.Response.ConsultationSummaryResponse;
import com.poodev.poodev.Entity.Consultation;
import org.springframework.stereotype.Component;

@Component
public class ConsultationMapper {

    public ConsultationSummaryResponse toSummaryResponse(
            Consultation consultation
    ) {
        return new ConsultationSummaryResponse(
                consultation.getId(),
                consultation.getConsultationDateTime(),
                consultation.getChiefComplaint(),
                consultation.getDiagnosticHypothesis(),
                consultation.getClinicalPlan()
        );
    }
}