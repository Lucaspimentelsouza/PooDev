package com.poodev.poodev.Mapper;

import com.poodev.poodev.DTO.Request.ConsultationCreateRequest;
import com.poodev.poodev.DTO.Response.ConsultationResponse;
import com.poodev.poodev.DTO.Response.ConsultationSummaryResponse;
import com.poodev.poodev.Entity.Consultation;
import com.poodev.poodev.Entity.Doctor;
import com.poodev.poodev.Entity.Patient;
import org.springframework.stereotype.Component;
import com.poodev.poodev.DTO.Request.ConsultationUpdateRequest;
import com.poodev.poodev.DTO.Request.ConsultationReturnCreateRequest;

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

    public Consultation toEntity(
            ConsultationCreateRequest request,
            Patient patient,
            Doctor doctor
    ) {
        Consultation consultation = new Consultation();

        consultation.setConsultationDateTime(request.consultationDateTime());
        consultation.setChiefComplaint(request.chiefComplaint());
        consultation.setDiagnosticHypothesis(request.diagnosticHypothesis());
        consultation.setClinicalPlan(request.clinicalPlan());
        consultation.setPatient(patient);
        consultation.setDoctor(doctor);

        return consultation;
    }

    public ConsultationResponse toResponse(Consultation consultation) {
        Long returnOfId = consultation.getReturnOfConsultation() != null
                ? consultation.getReturnOfConsultation().getId()
                : null;

        return new ConsultationResponse(
                consultation.getId(),
                consultation.getConsultationDateTime(),
                consultation.getChiefComplaint(),
                consultation.getDiagnosticHypothesis(),
                consultation.getClinicalPlan(),
                consultation.getPatient().getId(),
                consultation.getPatient().getName(),
                consultation.getDoctor().getId(),
                consultation.getDoctor().getFullName(),
                returnOfId
        );
    }

    public void updateEntity(
            ConsultationUpdateRequest request,
            Consultation consultation
    ) {
        consultation.setConsultationDateTime(request.consultationDateTime());
        consultation.setChiefComplaint(request.chiefComplaint());
        consultation.setDiagnosticHypothesis(request.diagnosticHypothesis());
        consultation.setClinicalPlan(request.clinicalPlan());
    }

    public Consultation toReturnEntity(
            ConsultationReturnCreateRequest request,
            Consultation originalConsultation,
            Doctor doctor
    ) {
        Consultation returnConsultation = new Consultation();

        returnConsultation.setConsultationDateTime(request.consultationDateTime());
        returnConsultation.setChiefComplaint(request.chiefComplaint());
        returnConsultation.setDiagnosticHypothesis(request.diagnosticHypothesis());
        returnConsultation.setClinicalPlan(request.clinicalPlan());
        returnConsultation.setPatient(originalConsultation.getPatient());
        returnConsultation.setDoctor(doctor);
        returnConsultation.setReturnOfConsultation(originalConsultation);

        return returnConsultation;
    }
}