package com.poodev.poodev.Mapper;

import com.poodev.poodev.DTO.Response.PrescriptionResponse;
import com.poodev.poodev.Entity.Prescription;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionMapper {

    public PrescriptionResponse toResponse(Prescription prescription) {
        boolean signed = prescription.getSignature() != null;

        return new PrescriptionResponse(
                prescription.getId(),
                prescription.getConsultation().getId(),
                prescription.getContent(),
                prescription.getCreatedAt(),
                prescription.getUpdatedAt(),
                signed,
                prescription.getSignedAt(),
                prescription.getSignatureAlgorithm()
        );
    }
}